package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Libil.Page.LoginPage;
import Libil.Page.SearchExactForDownloadCSV;
import Libil.Page.DownloadCSV;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import Libil.Utility.ForMultiplemailReceipent;
import Libil.Utility.UtilityClass;
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
        // Login flow
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnFirstLoginButton();
        loginPage.clickOnSecondLoginButton();
        loginPage.enterEmailId(ConfingDataProvider.Email);
        loginPage.enterPassword(ConfingDataProvider.Password);
        loginPage.clickOnLoginButton();
        loginPage.adminLogin();

        // Navigate to the "Exact match" search option
        SearchExactForDownloadCSV csv = new SearchExactForDownloadCSV(driver);
        csv.checkdownloadcsv();


        
        String downloadPath = "C:\\Users\\Super\\Downloads";
        String fileName = "cases.csv";  // Ensure this matches actual downloaded file

       
        DownloadCSV test = new DownloadCSV(driver);

       
        test.downloadCsvAndValidate(fileName, 60);

       
        boolean isDownloaded = test.isFileDownloaded(downloadPath, fileName, 40);
        Assert.assertTrue(isDownloaded, "CSV file is not downloaded successfully.");

        if (!isDownloaded) {
            System.out.println("CSV download verification failed.");
            
            return;  // Stop execution if the file is missing
        }

       
        test.extractColumnsFromCSV("C:\\Users\\Super\\Downloads\\cases.csv","court_name", "party_name");
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

