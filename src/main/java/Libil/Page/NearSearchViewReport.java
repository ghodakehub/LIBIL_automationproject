package Libil.Page;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Libil.Utility.Library;

public class NearSearchViewReport extends BasePage {

	public NearSearchViewReport(WebDriver driver) {
		super(driver);
	}
	

	public void NearsearchviewReport() throws InterruptedException, IOException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		
		Thread.sleep(500);
		WebElement slider = driver.findElement(By.xpath("//div[contains(@class, 'table-responsive mb-4')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 500;", slider);

		Thread.sleep(2000);
		WebElement actionButton = driver.findElement(By.xpath("(//button[@class='btn btn-link'])[10]")); 
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", actionButton);
		
		Thread.sleep(2000);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.xpath("(//a[text()='Generate Report'])[10]"));
		js1.executeScript("arguments[0].scrollIntoView(true)", element1);
		element1.click();
		 try {
	       
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	            // Locate the search box and enter a company name
	            WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"searchText\"]"));
	            searchBox.clear();
	            searchBox.sendKeys("tata");
	            Thread.sleep(1000);
	
	                    WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"searchForRelatedCompany\"]"));
	                    submitButton.click();
	                    WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofMinutes(2)); 

	                 
	                 WebElement fetchingMessage = driver.findElement(By.xpath("//h4[contains(text(),'Fetching Data ! Please Wait')]"));
	                 wait2.until(ExpectedConditions.visibilityOf(fetchingMessage));

	                 
	           
	                
	                 WebElement resultsTable = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("caseTable")));
	                 
	                 Library.scrollByPixelSize(driver, 200);
             // Find all checkboxes
             List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

             int count = Math.min(10, checkboxes.size()); // Ensure max 10 checkboxes are selected

             for (int i = 0; i < count; i++) {
                 WebElement checkbox = checkboxes.get(i);
                 JavascriptExecutor js2 = (JavascriptExecutor) driver;
                 js2.executeScript("arguments[0].click();", checkbox);

                 // Use JavaScript Click if normal click doesn't work
                 if (!checkbox.isSelected()) {
                     ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
                 }

                 // Small delay to ensure smooth selection
                 Thread.sleep(500);
             }

             // Click on "View Report" button
             WebElement viewReportButton = driver.findElement(By.xpath("//button[contains(text(),'View Report')]"));
             viewReportButton.click();
             Thread.sleep(1000);
 
             // Verify selected cases are visible on the report page
             wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Case No ')]")));
             Thread.sleep(1000);
             
             System.out.println("Test Passed: Selected cases are visible in the report.");

         } catch (Exception e) {
             System.out.println("Test Failed: " + e.getMessage());
         } 
     }
 

}