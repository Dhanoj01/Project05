package stepDefinations;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps {

	WebDriver driver = Hooks.driver;
	LoginPage login;
	
	@Given("user is on login page")
	public void user_is_on_login_page() {
		
		login = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
	  }

	@When("user enters the userId and Password")
	public void user_enters_the_user_id_and_password() {
		
		login.enterDetails("Admin", "admin123" );
	   }

	@When("user click on login button")
	public void user_click_on_login_button() {
		login.clickLogin();
	   }

	@Then("user should land on dashboard page")
	public void user_should_land_on_dashboard_page() {
		
		Assert.assertTrue(login.validateDashboard());
	  }

}
