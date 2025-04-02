package Libil.Page;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Libil.Utility.FileUtility;
import Libil.Utility.JsonUtility;

public class ExactMatch_downloadJSON extends BasePage {

	public ExactMatch_downloadJSON(WebDriver driver) {
		super(driver);
	}
	
	
		  public boolean verifydownlaodJSON(String downloadDir, String fileName, int timeoutSeconds) {
		        try {
		            // 1. Click the "Download JSON" button (adjust the locator as needed)
		            driver.findElement(By.xpath("//button[@id='sendJsonButton']")).click();
		                // 2. Wait a short period to check for an error popup
		                Thread.sleep(2000);

		                // 3. Check for an error popup (modify locator if needed)
		                List<WebElement> errorPopups = driver.findElements(By.id("errorPopup"));
		                if (!errorPopups.isEmpty() && errorPopups.get(0).isDisplayed()) {
		                    throw new Exception("An error popup appeared during JSON download.");
		                }

		                FileUtility utils = new FileUtility();
		                boolean isDownloaded = utils.waitForDownload("ReportData", Duration.ofMinutes(2));
		                boolean isValid = utils.validateDownloadedJson("ReportData", "invoiceId", "789456");

		           

		            } catch (Exception e) {
		                //MailUtil.sendErrorMail("JSON Download Error", e.getMessage());
		                return false;
		            }
				return false;
		        }
		    }
		
    


		
		
		
		
		
		
	
	
	

