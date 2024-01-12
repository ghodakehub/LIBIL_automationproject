package Libil.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import Libil.Utility.Library;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.XPATH, using = "//a[@class='btn btn-primary px-4 rounded-5 fs-14']")
	private WebElement FIRST_LOGIN_BUTTON;

	@FindBy(how = How.XPATH, using = "(//span[@class='me-2'])[1]")
	private WebElement SECOND_LOGIN_BUTTON;

	@FindBy(how = How.XPATH, using = "//input[@id='email']")
	private WebElement EMAIL;

	@FindBy(how = How.XPATH, using = "//input[@id='password']")
	private WebElement PASSWORD;

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement SUBMIT_BUTTON;

	public void clickOnFirstLoginButton() {
		Library.click(driver, FIRST_LOGIN_BUTTON, "setFirstloginBtn");
	}

	public void clickOnSecondLoginButton() {
		Library.click(driver, SECOND_LOGIN_BUTTON, "setSecondLoginBtn");
	}

	public void enterEmailId(String EmailId) {
		Library.sendKeys(driver, EMAIL, "setEmail", EmailId);
	}

	public void enterPassword(String userPass) {
		Library.sendKeys(driver, PASSWORD, "setPassword", userPass);
	}

	public void clickOnLoginButton() {
		Library.click(driver, SUBMIT_BUTTON, "performAction");
	}

	public void adminLogin(String EmailId, String Password) {
		clickOnFirstLoginButton();
		clickOnSecondLoginButton();
		enterEmailId(EmailId);
		enterPassword(Password);
		clickOnLoginButton();
		Library.threadSleep(5000);
	}

}
