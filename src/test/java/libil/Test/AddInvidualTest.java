package libil.Test;

import org.testng.annotations.Test;

import Libil.Page.AddIndividualPage;
import Libil.Page.LoginPage;
import Libil.Utility.BaseTest;

public class AddInvidualTest extends BaseTest {
	@Test

	public void verifyAddIndividual() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId("vaibhav.joshi@legitquest.com");
		loginPage.enterPassword("Vaibhav@345");
		loginPage.clickOnLoginButton();

		AddIndividualPage addIndividualPage = new AddIndividualPage(driver);
		addIndividualPage.clickOnRequestButton();

		addIndividualPage.selectType();
		addIndividualPage.enterName("Chinmay ");
		addIndividualPage.enterEmail("vaibhav.joshi@legitquest.com");
		addIndividualPage.enterPhoneNo("9325553919");
		addIndividualPage.enterAddress("Chh. Sambhajinagar");
		addIndividualPage.enterDistrict("Chh. Sambhajinagar");
		addIndividualPage.selectState("Maharashtra");
		addIndividualPage.entePincode("431009");
		addIndividualPage.clickOnSubmitButton();
		addIndividualPage.clickOnCloseButton();
		addIndividualPage.clickOnProfile();
		addIndividualPage.clickOnLogOut();
	}
}
