package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Libil.Page.LoginPage;
import Libil.Page.UploadCSVReport;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
@Listeners(Libil.Utility.newallurelistner.class)
public class UploadCSVReportTest extends BaseTest2 {
	
	
	@Test(priority=1, description="Verify Upload CSV Reports")
	@Feature("Upload CSV Report")
	@Description("Test Case Description: Verifying CSV Reports uploaded successfully.")
	@Severity(SeverityLevel.NORMAL)

	public void VerifyUploadCSV() throws MessagingException, IOException {
		Allure.step("Login with Valid credentails and Navigate to Admin Home Page");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId(ConfingDataProvider.Email);
		loginPage.enterPassword(ConfingDataProvider.Password);
		loginPage.clickOnLoginButton();
		loginPage.adminLogin();
		Allure.step("click on actions three dots options and click on upload CSV Report");
		UploadCSVReport report= new UploadCSVReport(driver);
		Allure.step("check can we upload csv reprot successfully");
		report.CheckUploadCSVReport();
		
	}


}
