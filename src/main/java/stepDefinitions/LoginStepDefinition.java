package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class LoginStepDefinition {

	WebDriver driver;
	
	@Given("^User is already on login page$")
	public void user_already_on_login_page() {
		
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://ui.freecrm.com/");
	}
	
	@When("^title page of login page is Free CRM$")
	public void titlePageOfCRM() {
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals("Cogmento CRM", title);
	}
	
	@Then("^User enters \"(.*)\" and \"(.*)\"$")
	public void user_enters_username_and_password(String username, String password) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.findElement(By.name("email")).sendKeys(username);
	    driver.findElement(By.name("password")).sendKeys(password);
	}

	@And("^User clicks on Login button$")
	public void user_clicks_on_Login_button() {
		driver.findElement(By.xpath("//div[contains(text(),'Login')]")).click();
	}
	
	@Then("^User is on Home Page$")
	public void user_is_on_Home_Page(){
		String titleHome = driver.getTitle();
		System.out.println(titleHome);
		Assert.assertEquals("Cogmento CRM", titleHome);
	}
	
	@Then("^Close the browser$")
	public void close_the_browser() {
	   driver.quit();
	}

}
