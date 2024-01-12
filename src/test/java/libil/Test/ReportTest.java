package libil.Test;

import org.testng.annotations.Test;

import Libil.Page.HomePage;
import Libil.Page.LoginPage;
import Libil.Utility.BaseTest;

public class ReportTest extends BaseTest {

	@Test
	public void verifyReportsDisplay() {
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		loginPage.adminLogin("admin@gmail.com", "Admin@345");
		homePage.scrollTillBottomPage();
		homePage.serchClients("kabir");
		homePage.clickOnSUbmitButton();
		homePage.selectCases(4);
		homePage.clickOnViewReportButton();
		homePage.verifyReportDisplay();
	}

}
