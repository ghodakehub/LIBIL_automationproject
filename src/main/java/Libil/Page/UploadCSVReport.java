package Libil.Page;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import javax.mail.MessagingException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Libil.Utility.ForMultiplemailReceipent;
import Libil.Utility.Library;


public class UploadCSVReport extends BasePage {

	public UploadCSVReport(WebDriver driver) {
		super(driver);
	}

	
	 public void CheckUploadCSVReport() throws MessagingException, IOException
	 {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		 try {
	           
	            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Start typing to search']")));
	            searchBox.sendKeys("Vaibhav Joshi");
	           // searchBox.sendKeys(Keys.ENTER);
	            Thread.sleep(1000);
	            
	            Library.scrollByPixelSize(driver, 500);
	            
	    		WebElement slider = driver.findElement(By.xpath("//div[contains(@class, 'table-responsive mb-4')]"));
	    		((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 500;", slider);
	    		Thread.sleep(3000);
            
	    		
	    		
	            // Step 2: Click on the three-dot Actions menu
	    		 WebElement actionsMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@class, 'btn btn-link')])[1]")));
	    		 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actionsMenu);
	             System.out.println(" Clicked on Actions menu.");
	            Thread.sleep(3000);
	            
	           
	            
	            JavascriptExecutor js1 = (JavascriptExecutor) driver;
	    		WebElement element1 = driver.findElement(By.xpath("//button[contains(text(), 'Upload CSV Report')]"));
	    		js1.executeScript("arguments[0].scrollIntoView(true);", element1);
	    		element1.click();
	            Thread.sleep(3000);
	            
	           

	            // Step 4: Upload a file
	            WebElement uploadInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"csv_files\"]")));
	            
	            
	            if (!uploadInput.isDisplayed()) {
	                System.out.println("File input is hidden, using JavaScript to make it visible...");
	                JavascriptExecutor js = (JavascriptExecutor) driver;
	                js.executeScript("arguments[0].style.display = 'block';", uploadInput);
	            }
	            
	            File file = new File("D:\\LibilWeb\\src\\test\\resources\\Book1.xlsx");
	            uploadInput.sendKeys(file.getAbsolutePath());
	            System.out.println("File uploaded successfully.");
                Thread.sleep(1000);
	            // Step 5: Click Submit button
	            WebElement submitBtn = driver.findElement(By.xpath("//*[@id=\"uploadCSVSubmit\"]"));
	            submitBtn.click();
	            System.out.println("Clicked on Submit.");
	            Thread.sleep(2000);
	            // Step 6: Wait for success message
	            try {
	   
	            	// Wait for success message popup
	            	
	                WebElement successPopup = driver.findElement(By.xpath("//*[contains(text(), 'Uploaded Libil CSV Report!')]"));
	                System.out.println("Success message displayed.");

	                // Click on "OK" button in the popup
	                WebElement okButton = driver.findElement(By.xpath("/html/body/div[11]/div/div[6]/button[1]"));
	                okButton.click();
	                System.out.println("Clicked on OK button.");
	                
	                Thread.sleep(1000);
	               WebElement closepop = driver.findElement(By.xpath("//*[@id=\"uploadCSVModal\"]/div/div/div[3]/button[1]"));
	               closepop.click();
	               Thread.sleep(2000);
	               
	                // Ensure the original screen is visible
	                WebElement originalScreen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Admin Dashboard')]")));
	                System.out.println("Original screen is visible after upload.");

	            	
	            } catch (Exception e) {
	                // Step 7: Handle UI rendering issue
	                System.out.println("UI Rendering Issue detected!");
	                String screenshot=  Libil.Utility.ScreenShotsUtility.takeScreenshot(driver,"UiRenderingissue");
	        		
	               
	               
	               String testUrl = driver.getCurrentUrl();  
	      		 ForMultiplemailReceipent.sendEmail(
	                 	   driver, new String[]{"ghodake6896@gmail.com"},
	                 	    "LIBIL : Upload CSV Report ",
	                 	    "Please check Libil after upload CSV , original screen not visible UI rendering issue occur , please find the attached screenshot for details." ,
	                 	   screenshot , testUrl
	                 	   
	                 	);
	               System.out.println("UI Rendering Issue in Report Upload");
	            }

	        } catch (Exception e) {
	            System.out.println("Test Failed: " + e.getMessage());
	            
	        } 
	    }

}
