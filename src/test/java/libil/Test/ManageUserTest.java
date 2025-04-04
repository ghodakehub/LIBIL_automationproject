package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Libil.Page.LoginPage;
import Libil.Page.ManageUserPage;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import Libil.Utility.ForMultiplemailReceipent;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ManageUserTest  extends BaseTest2 {
	
	

	@Test(priority =1, description=" Verify ManageUser page")
    @Feature("Login Feature")
	@Description("Test case Description = Verify that the Manage User can we add new user")
	@Severity(SeverityLevel.CRITICAL)

	public void verifyLoginPage() {
		Allure.step("Login with valid credentails");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId(ConfingDataProvider.Email);
		loginPage.enterPassword(ConfingDataProvider.Password);
		loginPage.clickOnLoginButton();
		loginPage.adminLogin();
		Allure.step("Click on ManageUser and Add new User");
		 ManageUserPage user= new ManageUserPage(driver);
		 user.openManageUser();
		 Allure.step("Fillup all fields on form");
		 user.fillUserForm("TestUserByAutomation", "LQ","pratiksha.damodar@legitquest.com", "9878688030", "Libil@1234", "User");
		 user.verifyNewEntry(driver);
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
           	    "LIBIL : ManageUser ",
           	    "Please check Libil ManageUser page not working , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	

	}
}


