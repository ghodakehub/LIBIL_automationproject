package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Libil.Page.EditManageUserPage;
import Libil.Page.LoginPage;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import Libil.Utility.ForMultiplemailReceipent;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class Editmanageusertest extends BaseTest2 {

	
	
	
	@Test(priority = 1, description = "Verify Edit button on Add new user page.")
    @Feature("ManageUser : Add New user edit button")
    @Description("Test Case Descriptions = Verify the edit button working and edited data is display properly")
    @Severity(SeverityLevel.NORMAL)
    public void verifyExactMatch_DownloadCSVoption() throws InterruptedException, IOException {
		Allure.step("Login with valid credentails and Naviagate to Admin Dashboard page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnFirstLoginButton();
        loginPage.clickOnSecondLoginButton();
        loginPage.enterEmailId(ConfingDataProvider.Email);
        loginPage.enterPassword(ConfingDataProvider.Password);
        loginPage.clickOnLoginButton();
        loginPage.adminLogin();
        Allure.step("Go to add new user page and click on edit button");
        EditManageUserPage edit = new EditManageUserPage(driver);
        edit.openManageUser();
        Allure.step("Edit Name and contact no");
        edit.fillUserForm();
        Allure.step("Check edit data updated properly");
        edit.verifyNewEntry(driver);
    }
	@AfterMethod
	public void finish(ITestResult result) throws IOException, MessagingException
	{
	if(ITestResult.FAILURE==result.getStatus())
	{
		String screenshot=  Libil.Utility.ScreenShotsUtility.takeScreenshot(driver,"ScreenshotForLoginpage");
		
		
		String testUrl = driver.getCurrentUrl();  
		 ForMultiplemailReceipent.sendEmail(
	       	   driver, new String[]{"ghodake6896@gmail.com"},
	       	    "LIBIL : Add new user",
	       	    "Please check Add new user issue coming while Edit user, please find the attached screenshot for details." ,
	       	 screenshot , testUrl
	       	   
	       	);


	}
	}

}
