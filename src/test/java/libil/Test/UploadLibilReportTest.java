package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Libil.Page.LoginPage;
import Libil.Page.UploadLibilReport;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
@Listeners(Libil.Utility.newallurelistner.class)
public class UploadLibilReportTest extends BaseTest2 {
	
	
	
	@Test(priority=1, description="Verify Upload LIBIL Report")
	@Feature("Upload LIBIL Report")
	@Description("Test Case Description: Verifying LIBIL Report uploaded successfully and original screen display properly")
	@Severity(SeverityLevel.CRITICAL)
	public void VerifyUploadLIBIL_Report() throws MessagingException, IOException {
		Allure.step("Login with Valid credentails and Navigate to Admin Home Page");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId(ConfingDataProvider.Email);
		loginPage.enterPassword(ConfingDataProvider.Password);
		loginPage.clickOnLoginButton();
		loginPage.adminLogin();
		Allure.step("click on actions three dots options and click on upload LIBIL Report");
		UploadLibilReport report= new UploadLibilReport(driver);
		Allure.step("check can we upload libil reprot successfully");
		report.searchBarTest();
	}

	


}
