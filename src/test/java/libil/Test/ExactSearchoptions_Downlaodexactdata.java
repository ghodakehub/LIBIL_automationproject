package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Libil.Page.ExactMatch_DownloadExactdata;
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
@Listeners(Libil.Utility.newallurelistner.class)
public class ExactSearchoptions_Downlaodexactdata extends BaseTest2{

	@Test(priority =1, description="Verify Downlaod exact data json File")
	@Feature(" Exact Search Option - Download ExactData button")
	@Description("Test Case Descriptions = Verify for exact search exact data JSON file downlaoded successfully")
	@Severity(SeverityLevel.NORMAL)
	public void VerifyExactdatadownlaodoption() throws InterruptedException, IOException {
		Allure.step("Login with Valid credentails and Navigate to Admin Home Page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnFirstLoginButton();
        loginPage.clickOnSecondLoginButton();
        loginPage.enterEmailId(ConfingDataProvider.Email);
        loginPage.enterPassword(ConfingDataProvider.Password);
        loginPage.clickOnLoginButton();
        loginPage.adminLogin();

        Allure.step("Enter keyword in search and select exact search option");
        SearchExactForDownloadCSV radio = new SearchExactForDownloadCSV(driver);
        radio.checkdownloadcsv("Tata");

        Allure.step("click on download exact button and check is downlaod json file");
        ExactMatch_DownloadExactdata report = new ExactMatch_DownloadExactdata(driver);
        report.downloadJSON("Tata");
        
    }
    

	
	
	
	
	@AfterMethod
	public void finish(ITestResult result) throws IOException, MessagingException
	{
	if(ITestResult.FAILURE==result.getStatus())
	{
		String screenshot=  Libil.Utility.ScreenShotsUtility.takeScreenshot(driver,"screenshotforExactdatadownlaodJSONfile");
		
		String testUrl = driver.getCurrentUrl();  
		 ForMultiplemailReceipent.sendEmail(
           	   driver, new String[]{"ghodake6896@gmail.com"},
           	    "LIBIL : ExactSearch options(download exactdata button)",
           	    "Please check for exact search download exactdata button not working, please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	}

}
