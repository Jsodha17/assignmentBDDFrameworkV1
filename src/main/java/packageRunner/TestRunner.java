package packageRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;



@RunWith(Cucumber.class)
@CucumberOptions(
		features = "C:\\Users\\hp\\eclipse-workspace\\assignmentBDDFrameworkV1\\src\\main\\java\\Features\\Login.feature", //the path of the feature files
		glue={"stepDefinitions"}, // the path of the steps definition file/package/entire path of the file.
		plugin={"pretty", "html:test-output"}, // to generate the different types of output i.e.: HTML, JSON, XML
		monochrome = true, // Display the console output in a proper readable format.
		strict = true, // will fail the execution if there are any pending method from feature file. 
		dryRun = false // If it is true, then it will check which method from the feature file is missing.
		)

public class TestRunner {
}

