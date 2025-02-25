package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.annotations.Test;

import Libil.Page.LoginPage;
import Libil.Page.UploadCSVReport;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class UploadCSVReportTest extends BaseTest2 {
	
	
	@Test(priority=1, description="Verify Upload CSV Reports")
	@Feature("Upload CSV Report")
	@Description("Test Case Description: Verifying CSV Reports uploaded successfully.")
	@Severity(SeverityLevel.NORMAL)

	public void VerifyUploadCSV() throws MessagingException, IOException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId(ConfingDataProvider.Email);
		loginPage.enterPassword(ConfingDataProvider.Password);
		loginPage.clickOnLoginButton();
		loginPage.adminLogin();
		
		UploadCSVReport report= new UploadCSVReport(driver);
		report.CheckUploadCSVReport();
		
	}


}
