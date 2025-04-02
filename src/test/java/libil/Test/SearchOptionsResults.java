package libil.Test;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Libil.Page.SearchOptions;
import Libil.Page.LoginPage;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
@Listeners(Libil.Utility.newallurelistner.class)
public class SearchOptionsResults extends BaseTest2 {
	
	
	

	@Test(priority=1, description="Verify all Search Options Functionality")
	@Feature("All search Options like Exact , proximity ,+/-, Near....")
	@Description("Test to verify searching options  buttons like Exact , proximity ,+/-, Near.... provide result")
	@Step("Check Search Options (Exact , Proximity ,+/- ,Near) Functionality")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyLoginPage() throws MessagingException, IOException, InterruptedException {
		Allure.step("Login with valid credentails and navigate to admin dashboard home page");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId(ConfingDataProvider.Email);
		loginPage.enterPassword(ConfingDataProvider.Password);
		loginPage.clickOnLoginButton();
		loginPage.adminLogin();
		Allure.step("Click on generate report and choose options one by one");
		SearchOptions report= new SearchOptions(driver);
		report.CheckAll_SearchOptions();
		Allure.step("check after click on searh options is display or provide results");
        WebElement resultsTable = driver.findElement(By.id("caseTable"));
        List<WebElement> tableRows = resultsTable.findElements(By.xpath(".//tbody/tr"));
        
        Assert.assertTrue(tableRows.size() > 1, "Search options did not return expected results!");
        //  Libil.Utility.ScreenShotsUtility.addScreenshotToReport(driver,"ScreenshotForLoginpage");
		
    }
	}


