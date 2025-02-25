package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Libil.Page.EditReportPage;
import Libil.Page.LoginPage;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import Libil.Utility.ForMultiplemailReceipent;
import Libil.Utility.UtilityClass;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

public class EditReportTest extends BaseTest2{
	
	
	 @Test(description = "Verify editing and saving the report")
	    @Severity(SeverityLevel.CRITICAL)
	    @Description("Test to edit a report, save it, and verify that changes persist")
	    @Step("Editing report details")
	    public void VerifyEditAndSaveReport() throws InterruptedException, IOException {
		 
		 LoginPage loginPage = new LoginPage(driver);
	        loginPage.clickOnFirstLoginButton();
	        loginPage.clickOnSecondLoginButton();
	        loginPage.enterEmailId(ConfingDataProvider.Email);
	        loginPage.enterPassword(ConfingDataProvider.Password);
	        loginPage.clickOnLoginButton();
	        loginPage.adminLogin();
	        
	       
		 EditReportPage  editReportPage= new EditReportPage(driver);
		 editReportPage.checkEditAndSavebuttonsOptions("TestSubject", "TestAddress");
		
	        
	        Assert.assertTrue(editReportPage.isSaveSuccessful(), "Save operation failed!");
	       
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
	           	    "LIBIL : ChooseOptions save and edit Report",
	           	    "Please check choose options save and edit button not working , please find the attached screenshot for details." ,
	           	 screenshot , testUrl
	           	   
	           	);
		
		}
		}
}
