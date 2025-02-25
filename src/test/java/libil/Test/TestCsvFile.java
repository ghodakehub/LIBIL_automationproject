package libil.Test;

import java.io.IOException;

import org.testng.annotations.Test;

import Libil.Page.ExactMatch_DowloadCSV;
import Libil.Page.LoginPage;
import Libil.Page.SearchExactForDownloadCSV;
import Libil.Page.DownloadCSV;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class TestCsvFile extends BaseTest2 {

	
	
	
	@Test(priority = 1, description = "Verify Download CSV File")
    @Feature("Exact Search Option - Download CSV File")
    @Description("Test Case Descriptions = Verify that the exact search CSV file is downloaded successfully")
    @Severity(SeverityLevel.NORMAL)
    public void checkAllRequestOnHomePage() throws InterruptedException, IOException {
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


        
        ExactMatch_DowloadCSV csvs = new ExactMatch_DowloadCSV(driver);
        csvs.downloadCSV();

       
        
    }
      

}
