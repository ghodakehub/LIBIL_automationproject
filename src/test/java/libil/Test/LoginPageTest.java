package libil.Test;

import org.testng.annotations.Test;

import Libil.Page.LoginPage;
import Libil.Utility.BaseTest;
import Libil.Utility.Library;

public class LoginPageTest extends BaseTest {

	@Test

	public void verifyLoginPage() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId("vaibhav.joshi@legitquest.com");
		loginPage.enterPassword("Vaibhav@345");
		loginPage.clickOnLoginButton();
		Library.threadSleep(5000);
	}

}
