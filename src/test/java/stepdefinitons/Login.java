package stepdefinitons;

import java.time.Duration;
import java.util.Date;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import utils.CommonUtils;

public class Login {
	
	WebDriver driver;
	private LoginPage loginPage;
	private AccountPage accountPage;
	private CommonUtils commonUtils;
	
	@Given("User navigates to login page")
	public void User_navigate_to_login_page() {
		
		driver = DriverFactory.getDriver();
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption();
				
	}
	
	@When("^User enters valid email address (.+) in email address field$")
	public void User_enters_valid_email_address_in_email_address_field(String validEmailText) {
		
		loginPage.enterEmailAddress(validEmailText);
		
	}
	@And("^User enters valid password (.+) in password field$")
	public void User_enters_valid_password_in_password_field(String validPassword) {
		
		loginPage.enterPassword(validPassword);
		
	}
	
	@And("User clicks on Login button")
	public void User_clicks_on_Login_button() {
		
		accountPage = loginPage.clickLoginButton();
		
	}
	
	@Then("User loggedin successfully")
	public void User_loggedin_successfully() {

		Assert.assertTrue(accountPage.displayStatusOfEditYourAccountInformation());

	}
	
	@When("User enters invalid email address in email address field")
	public void user_enters_invalid_email_address_in_email_address_field() {

		commonUtils = new CommonUtils();
		loginPage.enterEmailAddress(commonUtils.getEmailWithTimeStamp());
		
	}
	
	@And("User enters invalid password {string} in password field")
	public void User_enters_invalid_password_in_password_field(String invalidPassword) {
		
		loginPage.enterPassword(invalidPassword);
		
	}
	
	@Then("User should get a warning message about credentials mismatch")
	public void user_should_get_a_warning_message_about_credentials_mismatch() {
		
		Assert.assertTrue(loginPage.getWarningMessage().contains("Warning: No match for E-Mail Address and/or Password."));
	}
	
	@When("User dont enters email address in email address field")
	public void user_dont_enters_email_address_in_email_address_field() {
		
		loginPage.enterEmailAddress("");

	}

	@When("User dont enters password in password field")
	public void user_dont_enters_password_in_password_field() {
	
		loginPage.enterPassword("");
		
	}
	
}
