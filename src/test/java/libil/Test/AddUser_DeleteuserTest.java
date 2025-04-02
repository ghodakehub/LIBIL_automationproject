package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Libil.Page.AddNewUser_DeleteButton;
import Libil.Page.LoginPage;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import Libil.Utility.ForMultiplemailReceipent;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AddUser_DeleteuserTest extends BaseTest2 {

	
	
	
	@Test(priority = 1, description = "Verify Delete button on Add new user page")
    @Feature("ManageUser : Add New user Delete button")
    @Description("Test Case Descriptions = Verify the Delete button working and can we delete added user successfully")
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
        Allure.step("Go to add new user page and click on delete button");
        AddNewUser_DeleteButton delete = new AddNewUser_DeleteButton(driver);
        delete.openManageUser();
        delete.verifydeleteuser();
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
	       	    "Please check Add new user issue coming while delete user, please find the attached screenshot for details." ,
	       	 screenshot , testUrl
	       	   
	       	);


	}

}}
