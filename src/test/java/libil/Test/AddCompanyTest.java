package libil.Test;

import org.testng.annotations.Test;

import Libil.Page.AddCompanyPage;
import Libil.Page.LoginPage;
import Libil.Utility.BaseTest;

public class AddCompanyTest extends BaseTest {

	@Test

	public void verifyAddCompany() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId("vaibhav.joshi@legitquest.com");
		loginPage.enterPassword("Vaibhav@345");
		loginPage.clickOnLoginButton();

		AddCompanyPage addCompanyPage = new AddCompanyPage(driver);
		addCompanyPage.clickOnRequestButton();
		addCompanyPage.selectCompanyType();
		addCompanyPage.enterCompanyName("RedBull");
		addCompanyPage.enterComment("Testing Framework");
		addCompanyPage.clickOnSubmitButton();
	}

}
