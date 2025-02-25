package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.annotations.Test;

import Libil.Page.LoginPage;
import Libil.Page.UploadMCAReport;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class UploadMCAreportTest extends BaseTest2 {
	
	
	

	@Test(priority=1, description="Verify Upload MCA Report")
	@Feature("Upload MCA Report")
	@Description("Test Case Description: Verifying MCA Report uploaded successfully.")
	@Severity(SeverityLevel.CRITICAL)

	public void VerifyUploadMCA_Report() throws MessagingException, IOException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId(ConfingDataProvider.Email);
		loginPage.enterPassword(ConfingDataProvider.Password);
		loginPage.clickOnLoginButton();
		loginPage.adminLogin();
		
		UploadMCAReport report= new UploadMCAReport(driver);
		report.searchBarTest();
	}


}
