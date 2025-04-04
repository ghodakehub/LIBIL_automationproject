package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Libil.Page.LoginPage;
import Libil.Page.SearchExactForDownloadCSV;
import Libil.Page.ExactMatch_DowloadCSV;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import Libil.Utility.ForMultiplemailReceipent;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
public class ExactMatch_downloadCSV extends BaseTest2 {

	
	
	
	@Test(priority = 1, description = "Verify Download CSV File")
    @Feature("Exact Search Option - Download CSV File")
    @Description("Test Case Descriptions = Verify the exact search  download csv button working and file is downloaded successfully")
    @Severity(SeverityLevel.NORMAL)
    public void verifyExactMatch_DownloadCSVoption() throws InterruptedException, IOException {
		Allure.step("Login with Valid credentails and Navigate to Admin Home Page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnFirstLoginButton();
        loginPage.clickOnSecondLoginButton();
        loginPage.enterEmailId(ConfingDataProvider.Email);
        loginPage.enterPassword(ConfingDataProvider.Password);
        loginPage.clickOnLoginButton();
        loginPage.adminLogin();

        Allure.step("Enter keyword in search bar and select exact search options");
        SearchExactForDownloadCSV csv = new SearchExactForDownloadCSV(driver);
        csv.checkdownloadcsv("SOHAIL HAQUE");
        Allure.step("Click on dowlaod CSV button and check csv file");
        ExactMatch_DowloadCSV test= new ExactMatch_DowloadCSV(driver);
        test.downloadCSV();
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
       	    "LIBIL : DownloadCSV",
       	    "Please check issue coming in downlaod csv , please find the attached screenshot for details." ,
       	 screenshot , testUrl
       	   
       	);


}
}
}

