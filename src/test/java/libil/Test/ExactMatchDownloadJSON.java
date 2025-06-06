package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Libil.Page.ExactMatch_downloadJSON;
import Libil.Page.LoginPage;
import Libil.Page.SearchRadioButtons;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import Libil.Utility.ForMultiplemailReceipent;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
@Listeners(Libil.Utility.newallurelistner.class)
public class ExactMatchDownloadJSON extends BaseTest2{


	
		
		
	@Test(priority =1, description="Verify Downlaod JSON File")
	@Feature(" Exact Search Option - Download JSON File")
	@Description("Test Case Descriptions = Verify for exact search JSON file downlaoded successfully")
	@Severity(SeverityLevel.NORMAL)
	public void VerifydownloadJSONoptions() throws InterruptedException {
		Allure.step("Login with Valid credentails and Navigate to Admin Home Page");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId(ConfingDataProvider.Email);
		loginPage.enterPassword(ConfingDataProvider.Password);
		loginPage.clickOnLoginButton();
		loginPage.adminLogin();
		
		Allure.step("Enter keyword in search and select exact search option");
        SearchRadioButtons radio = new SearchRadioButtons(driver);
        radio.ClickonExactsearchOption();

        Allure.step("click on downlaod json button and check is downloaded");
        ExactMatch_downloadJSON report = new ExactMatch_downloadJSON(driver);
        
        boolean isDownloadValid = report.verifydownlaodJSON("C:\\Users\\Super\\Downloads", "data.json", 20);
        Allure.step("verify downloaded json file");
        // Assert that the JSON file was downloaded and is valid
       Assert.assertTrue(isDownloadValid, "JSON file download or validation failed!");
    }

	
	
	
	
	@AfterMethod
	public void finish(ITestResult result) throws IOException, MessagingException
	{
	if(ITestResult.FAILURE==result.getStatus())
	{
		String screenshot=  Libil.Utility.ScreenShotsUtility.takeScreenshot(driver,"ScreenshotFordonwloadjson");
		
		String testUrl = driver.getCurrentUrl();  
		 ForMultiplemailReceipent.sendEmail(
           	   driver, new String[]{"ghodake6896@gmail.com"},
           	    "LIBIL : DownloadJSON",
           	    "Please check Download JSON button not working  , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	}

}
