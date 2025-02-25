package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Libil.Page.HomePage;
import Libil.Page.LoginPage;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import Libil.Utility.ForMultiplemailReceipent;
import Libil.Utility.UtilityClass;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

public class HomePageTest  extends BaseTest2{


	
		
		
	@Test(priority =1, description="Verify admin home dashboard")
	@Feature("Home Page - Admin Dashboard")
	@Description("Verify that the admin dashboard loads correctly and pagination is functional.")
	@Severity(SeverityLevel.NORMAL)
	@Step("Checking all requests on the admin home page")
	public void CheckAllRequestOnHomePage() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId(ConfingDataProvider.Email);
		loginPage.enterPassword(ConfingDataProvider.Password);
		loginPage.clickOnLoginButton();
		loginPage.adminLogin();
		
		HomePage page= new HomePage(driver);
		page.checkallpaginations();
		 Assert.assertTrue(page.isHomePageDisplayed(), "HomePage did not load successfully!");
		 
		 Libil.Utility.ScreenShotsUtility.addScreenshotToReport(driver,"for home page");
			
	  
	}
	
	
	
	@AfterMethod
	public void finish(ITestResult result) throws IOException, MessagingException
	{
	if(ITestResult.FAILURE==result.getStatus())
	{
		String screenshot=  Libil.Utility.ScreenShotsUtility.addScreenshotToReport(driver,"ScreenshotForHomepage");
		
		
		String testUrl = driver.getCurrentUrl();  
		 ForMultiplemailReceipent.sendEmail(
           	   driver, new String[]{"ghodake6896@gmail.com"},
           	    "LIBIL : Home Page ",
           	    "Please check Libil home page , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	}
}
