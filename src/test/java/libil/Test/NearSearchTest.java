package libil.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Libil.Page.LoginPage;
import Libil.Page.NearSearchResults;
import Libil.Utility.BaseTest2;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
@Listeners(Libil.Utility.newallurelistner.class)
public class NearSearchTest extends BaseTest2{

	
	@Test(priority=1, description="Verifing Near search options")
	@Feature("Near search options")
	@Description("Test Case to Verifying Near search Option provide results ,doest not show pop")
	@Step("Check NearSearch Functionality")
	@Severity(SeverityLevel.CRITICAL)

	public void verifyNearSearchOptionsProvideResult() throws InterruptedException, IOException {
		Allure.step("Login with valid credentails and navigate to admin dashboard home page");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId("admin@gmail.com");
		loginPage.enterPassword("Admin@345");
		loginPage.clickOnLoginButton();
		loginPage.adminLogin();
		Allure.step("choose Near search options");
		NearSearchResults res= new NearSearchResults(driver);
		Allure.step("Check results are display properly");
		res.checkNearSearch_Options();
		  boolean isResultsPresent = res.isResultsAvailable();
          Assert.assertTrue(isResultsPresent, "Near Search did not return any results");
          // Libil.Utility.ScreenShotsUtility.addScreenshotToReport(driver,"ScreenshotForLoginpage");
  		
		  
	}
	
	

}
