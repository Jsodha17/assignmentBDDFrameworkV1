/**
 * Update execution status in zephyr execution cycle/folder
 * 
 * @param issueId
 * @param executionId
 * @param status
 * @return
 */
private boolean updateExecutionStatus(String issueId, String executionId, int status) {

    boolean flag = false;

    try {

        String relativePath = ZEPHYR_UPDATE_STATUS_JWT
                .replace("{execution_id}", executionId);

        long currUnixTime = System.currentTimeMillis() / 1000L;

        LOG.info("currUnixTime - " + currUnixTime);

        String canonicalPath = "PUT&"
                + relativePath
                + "&_="
                + currUnixTime
                + "&issueId="
                + issueId
                + "&projectId="
                + ZEPHYR_PROJECT_ID;

        LOG.info("Canonical Path - " + canonicalPath);

        String jwtToken = createJwtToken(canonicalPath);

        LOG.info("JWT Token - " + jwtToken);

        Map<String, String> headerParamDataMap = new HashMap<>();

        headerParamDataMap.put("Authorization", "JWT " + jwtToken);
        headerParamDataMap.put("zapiAccessKey", ZEPHYR_ACCESS_KEY);
        headerParamDataMap.put("Content-Type", "application/json");

        Map<String, Object> pathParamDataMap = new HashMap<>();

        pathParamDataMap.put("execution_id", executionId);
        pathParamDataMap.put("projectId", ZEPHYR_PROJECT_ID);
        pathParamDataMap.put("_", String.valueOf(currUnixTime));
        pathParamDataMap.put("issueId", issueId);

        // =========================
        // UPDATED PAYLOAD
        // =========================

        Map<String, Object> payloadDataMap = new HashMap<>();

        payloadDataMap.put("cycleId", ZEPHYR_CYCLE_ID);
        payloadDataMap.put("id", executionId);
        payloadDataMap.put("issueId", issueId);
        payloadDataMap.put("projectId", ZEPHYR_PROJECT_ID);
        payloadDataMap.put("versionId", ZEPHYR_VERSION_ID);

        // NEW STATUS STRUCTURE
        Map<String, Integer> statusMap = new HashMap<>();
        statusMap.put("id", status);

        payloadDataMap.put("status", statusMap);

        // OPTIONAL COMMENT SUPPORT
        payloadDataMap.put("comment",
                status == 1
                        ? "Test Passed via Automation"
                        : "Test Failed via Automation");

        Response response = ApiUtils.getInstance()
                .method("PUT")
                .baseUri(ZEPHYR_BASE_URL)
                .endpoint(ZEPHYR_UPDATE_STATUS)
                .headers(headerParamDataMap)
                .expectedStatusCode(200, 204)
                .pathParams(pathParamDataMap)
                .body(payloadDataMap)
                .storeResponse("UpdateStatus")
                .send();

        LOG.info("======================================");
        LOG.info("ZEPHYR UPDATE RESPONSE");
        LOG.info("STATUS CODE : {}", response.getStatusCode());
        LOG.info(response.asPrettyString());
        LOG.info("======================================");

        flag = response.getStatusCode() == 200
                || response.getStatusCode() == 204;

    } catch (Exception e) {

        LOG.error("Error while updating Zephyr status", e);
    }

    return flag;
}
