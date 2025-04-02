package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Libil.Page.LoginPage;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import Libil.Utility.ForMultiplemailReceipent;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

@Listeners(Libil.Utility.newallurelistner.class)
public class LoginPageTest extends BaseTest2 {
	
	

	@Test(priority =1, description=" Verify Login Page")
    @Feature("Login Feature")
	@Description("Test case Description = Verify that the login functionality works correctly")
	@Severity(SeverityLevel.CRITICAL)
	@Step("Checking Login with valid credentials")

	public void verifyLoginPage() {
		Allure.step("Enter Email and password and click on login button");
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId(ConfingDataProvider.Email);
		loginPage.enterPassword(ConfingDataProvider.Password);
		loginPage.clickOnLoginButton();
		loginPage.adminLogin();
		Allure.step("After login check Admin dashboard is display");
		boolean loginSuccess = true;
        Assert.assertTrue(loginSuccess, "Login should be successful");
        
        
	}

	@AfterMethod
	public void finish(ITestResult result) throws IOException, MessagingException
	{
	if(ITestResult.FAILURE==result.getStatus())
	{
		
		// Libil.Utility.ScreenShotsUtility.takeScreenshot(driver);
		String screenshot=  Libil.Utility.ScreenShotsUtility.takeScreenshot(driver,"ScreenshotForLoginpage");
		
		String testUrl = driver.getCurrentUrl();  
		 ForMultiplemailReceipent.sendEmail(
           	   driver, new String[]{"ghodake6896@gmail.com"},
           	    "LIBIL : Login Page ",
           	    "Please check Libil login page not working , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	

	}
}
