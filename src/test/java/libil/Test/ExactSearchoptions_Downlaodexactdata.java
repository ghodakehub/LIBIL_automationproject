package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Libil.Page.ExactMatch_DownloadExactdata;
import Libil.Page.LoginPage;
import Libil.Page.SearchExactForDownloadCSV;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfigReader;
import Libil.Utility.ConfingDataProvider;
import Libil.Utility.ForMultiplemailReceipent;
import Libil.Utility.UtilityClass;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ExactSearchoptions_Downlaodexactdata extends BaseTest2{


	
		
		
	@Test(priority =1, description="Verify Downlaod exact data json File")
	@Feature(" Exact Search Option - Download ExactData button")
	@Description("Test Case Descriptions = Verify for exact search exact data JSON file downlaoded successfully")
	@Severity(SeverityLevel.NORMAL)
	public void VerifyExactdatadownlaodoption() throws InterruptedException, IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnFirstLoginButton();
        loginPage.clickOnSecondLoginButton();
        loginPage.enterEmailId(ConfingDataProvider.Email);
        loginPage.enterPassword(ConfingDataProvider.Password);
        loginPage.clickOnLoginButton();
        loginPage.adminLogin();

        
        SearchExactForDownloadCSV radio = new SearchExactForDownloadCSV(driver);
        radio.checkdownloadcsv();

        
        ExactMatch_DownloadExactdata report = new ExactMatch_DownloadExactdata(driver);
        
       
        String downloadPath = "C:\\Users\\Super\\Downloads";
        String fileName = "amazon.json";
        int timeoutSeconds = 40;

       
        report.downloadJsonAndValidate("amazon.json", 40);

        
        boolean isDownloaded = report.isFileDownloaded(downloadPath, fileName, timeoutSeconds);
        Assert.assertTrue(isDownloaded, "for exact match json file was not downloaded successfully.");
        if (isDownloaded) {
            // Validate JSON content if file exists
            ExactMatch_DownloadExactdata.validateJSONFile(downloadPath + "\\" + fileName, "amazon", 357000, "no");
        } else {
            System.out.println("Test Failed: JSON file not downloaded.");
        }
    }
    

	
	
	
	
	@AfterMethod
	public void finish(ITestResult result) throws IOException, MessagingException
	{
	if(ITestResult.FAILURE==result.getStatus())
	{
		String screenshot=  Libil.Utility.ScreenShotsUtility.addScreenshotToReport(driver,"screenshotforExactdatadownlaodJSONfile");
		
		String testUrl = driver.getCurrentUrl();  
		 ForMultiplemailReceipent.sendEmail(
           	   driver, new String[]{"ghodake6896@gmail.com"},
           	    "LIBIL : ExactSearch optionsC(download exactdata button)",
           	    "Please check for exact search download exactdata button not working, please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	}

}
