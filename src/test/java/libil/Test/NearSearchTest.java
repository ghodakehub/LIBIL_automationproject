package libil.Test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Libil.Page.LoginPage;
import Libil.Page.NearSearchResults;
import Libil.Utility.BaseTest2;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

public class NearSearchTest extends BaseTest2{

	
	@Test(priority=1, description="Verifing Near search options")
	@Feature("Near search options")
	@Description("Test Case to Verifying Near search Option provide results ,doest not show pop")
	@Step("Check NearSearch Functionality")
	@Severity(SeverityLevel.CRITICAL)

	public void verifyNearSearchOptionsProvideResult() throws InterruptedException, IOException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId("admin@gmail.com");
		loginPage.enterPassword("Admin@345");
		loginPage.clickOnLoginButton();
		loginPage.adminLogin();
		
		NearSearchResults res= new NearSearchResults(driver);
		res.checkNearSearch_Options();
		  boolean isResultsPresent = res.isResultsAvailable();
          Assert.assertTrue(isResultsPresent, "Near Search did not return any results");
           Libil.Utility.ScreenShotsUtility.addScreenshotToReport(driver,"ScreenshotForLoginpage");
  		
		  
	}
	
	

}
