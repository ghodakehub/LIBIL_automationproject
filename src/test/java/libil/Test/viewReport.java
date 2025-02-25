package libil.Test;

import java.io.IOException;

import javax.mail.MessagingException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import Libil.Page.LoginPage;
import Libil.Page.NearSearchViewReport;
import Libil.Utility.BaseTest2;
import Libil.Utility.ConfingDataProvider;
import Libil.Utility.ForMultiplemailReceipent;
import Libil.Utility.UtilityClass;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class viewReport extends BaseTest2 {
	
	
	

	@Test(priority=1, description="Verify view Report")
	@Feature("View Report")
	@Description("Test Case Description: Verifying generate view report successfully.")
	@Severity(SeverityLevel.CRITICAL)

	public void verifyviewReport() throws MessagingException, IOException, InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondLoginButton();
		loginPage.enterEmailId(ConfingDataProvider.Email);
		loginPage.enterPassword(ConfingDataProvider.Password);
		loginPage.clickOnLoginButton();
		loginPage.adminLogin();
		 	 NearSearchViewReport report= new NearSearchViewReport(driver);
		 	report.NearsearchviewReport();
		 	 boolean viewreport = true;
		        Assert.assertTrue(viewreport, "View Reprot should be visible");
		        Libil.Utility.ScreenShotsUtility.addScreenshotToReport(driver,"ScreenshotForLoginpage");
				
	}

	@AfterMethod
	public void finish(ITestResult result) throws IOException, MessagingException
	{
	if(ITestResult.FAILURE==result.getStatus())
	{
		String screenshot=  Libil.Utility.ScreenShotsUtility.addScreenshotToReport(driver,"ScreenshotForViewReport");
		
		
		String testUrl = driver.getCurrentUrl();  
		 ForMultiplemailReceipent.sendEmail(
           	   driver, new String[]{"ghodake6896@gmail.com"},
           	    "LIBIL : View Report ",
           	    "Please check Libil view report not working , please find the attached screenshot for details." ,
           	 screenshot , testUrl
           	   
           	);
	
	}
	}


}
