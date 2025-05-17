package Libil.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import Libil.Utility.Library;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.XPATH, using = "//*[@id=\"navbarNav\"]/ul/li[2]/a")
	private WebElement FIRST_LOGIN_BUTTON;

	@FindBy(how = How.XPATH, using = "//*[@id=\"login-modal\"]/div/div/div[2]/div/div[1]/div/button/a/div/span")
	private WebElement SECOND_LOGIN_BUTTON;
	
	@FindBy(how = How.XPATH, using = "//input[@id='email']")
	private WebElement EMAIL;

	@FindBy(how = How.XPATH, using = "//input[@id='password']")
	private WebElement PASSWORD;

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement SUBMIT_BUTTON;

	public void clickOnFirstLoginButton() {
		Library.click(driver, FIRST_LOGIN_BUTTON, "Clicked on first login button");
	}

	public void clickOnSecondLoginButton() {
		Library.click(driver, SECOND_LOGIN_BUTTON, "Clicked on second login button");
	}

	public void enterEmailId(String EmailId) {
		Library.sendKeys(driver, EMAIL, "Email id entered", EmailId);
	}

	public void enterPassword(String userPass) {
		Library.sendKeys(driver, PASSWORD, "Pass word entered", userPass);
	}

	public void clickOnLoginButton() {
		Library.click(driver, SUBMIT_BUTTON, "performAction");
		System.out.println("Clicked on login button");
	}

	public void adminLogin() {
		
		
		  WebElement Activefirm = driver.findElement(By.xpath("//h4[contains(text(),'Admin Dashboard')]"));
          
          if (Activefirm.isDisplayed()) {
              System.out.println("Admin Dashboard title is displayed. Login page open successfully ,Test passed!");
          } else {
              System.out.println("Admin Dashboard title is NOT displayed. Means error coming , Test failed!");
              Assert.fail("Admin Dashboard title not display means It throw error while open it");
          }
     
	}

}
