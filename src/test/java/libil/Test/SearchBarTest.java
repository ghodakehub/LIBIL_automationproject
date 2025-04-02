package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Libil.Page.LoginPage;
import Libil.Page.Search_InputBar;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import Libil.Utility.ForMultiplemailReceipent;
import Libil.Utility.UtilityClass;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
@Listeners(Libil.Utility.newallurelistner.class)
public class SearchBarTest extends BaseTest2 {
	
	

	@Test(priority = 1, description = "Verify search functionality on Admin Dashboard")
	@Feature("Admin Dashboard - Search Functionality")
	@Description("Test to verify if searching in the Admin Dashboard returns results")
	@Step("Check SearchBar Functionality")
	@Severity(SeverityLevel.CRITICAL)

	public void VerifySearchbarinput() {
		Allure.step("Login with valid credentails and navigate to admin dashboard home page");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId(ConfingDataProvider.Email);
		loginPage.enterPassword(ConfingDataProvider.Password);
		loginPage.clickOnLoginButton();
		loginPage.adminLogin();
		Allure.step("Enter keyword in search and check is displayed proper searched request");
		Search_InputBar search = new Search_InputBar(driver);
		search.CheckSearchBarFunctionality("Sandeep Singhal");
		
		
		   
	}

	@AfterMethod
	public void finish(ITestResult result) throws IOException, MessagingException
	{
	if(ITestResult.FAILURE==result.getStatus())
	{
		String screenshot=  Libil.Utility.ScreenShotsUtility.takeScreenshot(driver,"ScreenshotforSearchbar");
		
		
		String testUrl = driver.getCurrentUrl();  
		 ForMultiplemailReceipent.sendEmail(
          	   driver, new String[]{"ghodake6896@gmail.com"},
          	    "LIBIL : Admin dashboard home page ",
          	    "Please check Libil search functionality not working , please find the attached screenshot for details." ,
          	 screenshot , testUrl
          	   
          	);
	
	}
	

}
}
