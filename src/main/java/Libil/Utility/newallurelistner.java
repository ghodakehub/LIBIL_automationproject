package Libil.Utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class newallurelistner implements ITestListener {

	   // Capture Screenshot for Allure Report
    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] saveFailureScreenShot(WebDriver driver) {
        if (driver == null) {
            return new byte[0]; // Return empty array if driver is null
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult iTR) {
        System.out.println("Test Failed: " + iTR.getName());

        WebDriver driver = BaseTest2.getDriver();
        if (driver != null) {
            saveFailureScreenShot(driver);  // Capture Screenshot for Allure Report
        }
    }
    
    
	
}
