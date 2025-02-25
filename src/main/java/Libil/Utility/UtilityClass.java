package Libil.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Attachment;



public class UtilityClass {
	
	
	
	public  static String Capaturescreenshot(WebDriver driver , String screenshots) throws IOException 
	{
		
		 String dateName= new SimpleDateFormat("yyyy/MMddhmmss").format(new Date());
		 
		 TakesScreenshot ts=(TakesScreenshot)driver;
		 File source=ts.getScreenshotAs(OutputType.FILE);
		 String destination= System.getProperty("user.dir")+"/FailedTestScreenshots/" +screenshots +dateName+".png";
		 
		 File finalDestination =new File(destination);
		FileUtils.copyFile(source, finalDestination);
		 
		 return destination;
		
	}

		
	@Attachment(value = "Screenshot - {screenshotName}", type = "image/png")
    public static byte[] captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	
	
	
	

}
