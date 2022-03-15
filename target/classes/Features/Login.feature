Feature: Free CRM login feature

Scenario: Free CRM Login Test Scenario
	Given User is already on login page
	When title page of login page is Free CRM
	Then User enters "jigarsodha17@gmail.com" and "Automation@12345"
	And User clicks on Login button
	Then User is on Home Page
	Then Close the browser