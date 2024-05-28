package stepdefinitons;

import java.util.Date;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountSuccessPage;
import pages.HomePage;
import pages.RegisterPage;
import utils.CommonUtils;

import org.junit.Assert;

public class Register {

	WebDriver driver;
	private RegisterPage registerPage;
	private AccountSuccessPage accountSuccessPage;
	private CommonUtils commonUtils;
	
	@Given("user navigates to Register count Page")
	public void user_navigates_to_register_count_page() {
		
		driver = DriverFactory.getDriver();
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.selectRegisterOption();
			
	}

	@When("User enters the details into the below fields")
	public void user_enters_the_details_into_the_below_fields(DataTable dataTable) {

		Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
		
		registerPage.enterFirstNameField(dataMap.get("FirstName"));
		registerPage.enterLastNameField(dataMap.get("LastName"));
		commonUtils = new CommonUtils();
		registerPage.enterEmailAddress(commonUtils.getEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataMap.get("Telephone"));
		registerPage.enterPassword(dataMap.get("Password"));
		registerPage.enterConfirmPassword(dataMap.get("PasswordConfirm"));
		
	}

	@When("User enters the details into the below fields with duplicate email")
	public void user_enters_the_details_into_the_below_fields_with_duplicate_email(DataTable dataTable) {

		Map<String, String> dataMap = dataTable.asMap(String.class, String.class);

		registerPage.enterFirstNameField(dataMap.get("FirstName"));
		registerPage.enterLastNameField(dataMap.get("LastName"));
		registerPage.enterEmailAddress(dataMap.get("Email"));
		registerPage.enterTelephoneNumber(dataMap.get("Telephone"));
		registerPage.enterPassword(dataMap.get("Password"));
		registerPage.enterConfirmPassword(dataMap.get("PasswordConfirm"));

	}

	@When("User Clicks on continue button")
	public void user_clicks_on_continue_button() {

		accountSuccessPage = registerPage.clickContinueButton();
		
	}

	@Then("User account should get created successfully")
	public void user_account_should_get_created_successfully() {

		Assert.assertEquals("Your Account Has Been Created!", accountSuccessPage.getPageHeading());
	
	}

	@When("User selects for newsletter")
	public void user_selects_for_newsletter() {
		
		registerPage.selectYesNewsLetterOption();
		
	}

	@When("User select privacy policy")
	public void user_select_privacy_policy() {
		
		registerPage.selectPrivacyPolicy();
		
	}

	@Then("User should get proper warning about duplicate email address")
	public void user_should_get_proper_warning_about_duplicate_email_address() {
		
		Assert.assertTrue(registerPage.getWarningMessage().contains("Warning: E-Mail Address is already registered!"));
	}

	@When("User dont enters the details into the fields")
	public void user_dont_enters_the_details_into_the_fields() {

		registerPage.enterFirstNameField("");
		registerPage.enterLastNameField("");
		registerPage.enterEmailAddress("");
		registerPage.enterTelephoneNumber("");
		registerPage.enterPassword("");
		registerPage.enterConfirmPassword("");

	}

	@Then("User account should get warning message for every mandatory fields")
	public void user_account_should_get_warning_message_for_every_mandatory_fields() {
		
		Assert.assertTrue(registerPage.getWarningMessage().contains("Warning: You must agree to the Privacy Policy!"));
		Assert.assertEquals("First Name must be between 1 and 32 characters!", registerPage.getFirstNameWarning());
		Assert.assertEquals("Last Name must be between 1 and 32 characters!", registerPage.getlastNameWarning());
		Assert.assertEquals("E-Mail Address does not appear to be valid!", registerPage.getemailWarning());
		Assert.assertEquals("Telephone must be between 3 and 32 characters!", registerPage.getTelephoneWarning());
		Assert.assertEquals("Password must be between 4 and 20 characters!", registerPage.getPasswordWarning());
		
	}
	
}
