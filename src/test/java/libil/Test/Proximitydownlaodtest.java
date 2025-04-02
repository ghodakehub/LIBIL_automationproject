package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Libil.Page.DownloadProximity;
import Libil.Page.ExactMatch_DowloadCSV;
import Libil.Page.LoginPage;
import Libil.Page.SearchExactForDownloadCSV;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import Libil.Utility.ForMultiplemailReceipent;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class Proximitydownlaodtest extends BaseTest2 {

	
	
	
	@Test(priority = 1, description = "Verify Download proximity File")
    @Feature("Exact Search Option - Download proximity File")
    @Description("Test Case Descriptions = Verify the download proximity button working and file is downloaded successfully")
    @Severity(SeverityLevel.NORMAL)
    public void verifyExactMatch_DownloadCSVoption() throws InterruptedException, IOException {
		Allure.step("Login with valid credentails and navigate to admin dashboard home page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnFirstLoginButton();
        loginPage.clickOnSecondLoginButton();
        loginPage.enterEmailId(ConfingDataProvider.Email);
        loginPage.enterPassword(ConfingDataProvider.Password);
        loginPage.clickOnLoginButton();
        loginPage.adminLogin();
        Allure.step("Enter keyword and select proximity search options");
        DownloadProximity download= new DownloadProximity(driver);
        Allure.step("Click on downlaod proximity button and check is downloaded");
        download.downloadJSON("Sohail w/5 hoque");
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
       	    "LIBIL : Downlaod proximity button",
       	    "Please check issue coming in download proximity , please find the attached screenshot for details." ,
       	 screenshot , testUrl
       	   
       	);


}
}

}
