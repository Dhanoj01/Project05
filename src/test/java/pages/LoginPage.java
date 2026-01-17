package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "username")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath = "//h6[text()='Dashboard']")
	WebElement dashboard;
	
	
	public void enterDetails(String userid , String pass)
	{
		wait.until(ExpectedConditions.visibilityOf(username));
		username.sendKeys(userid);

		wait.until(ExpectedConditions.visibilityOf(password));
		password.sendKeys(pass);
	}
	
	public void clickLogin()
	{
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		loginBtn.click();
	}
	
	public boolean validateDashboard()
	{
		wait.until(ExpectedConditions.visibilityOf(dashboard));
		return dashboard.isDisplayed();
	}
}
