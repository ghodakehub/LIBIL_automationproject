package libil.Test;

import org.testng.annotations.Test;

import Libil.Page.LoginPage;
import Libil.Page.ReportPage;
import Libil.Utility.BaseTest;

public class ReportGenerationTest extends BaseTest {
	@Test
//  Vaibhav
	public void verifyReportDisplay() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId("admin@gmail.com");
		loginPage.enterPassword("Admin@345");
		loginPage.clickOnLoginButton();

		ReportPage reportPage = new ReportPage(driver);
		reportPage.scrollTillBottomPage();
		reportPage.searchClient("Chinmay");
		reportPage.clickOnSubmitButton();
		reportPage.selectCases(10);
		reportPage.viewReport();
		reportPage.verifyReportDisplay();
	}

}
