package Libil.Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchExactForDownloadCSV extends BasePage {

	public SearchExactForDownloadCSV(WebDriver driver) {
		super(driver);
	}
	
	public void checkdownloadcsv(String Keyword) throws InterruptedException
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
	            searchBox.sendKeys(Keyword);
	            Thread.sleep(1000);
	            
	          WebElement exactbtn = driver.findElement(By.xpath("//*[@id=\"hideDiv\"]/section[1]/div/div/div[2]/div[1]/label"));
	          exactbtn.click();
	          Thread.sleep(1000);
	           
	          WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchForRelatedCompany\"]")));
	            submitButton.click();
	             WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofMinutes(2)); 
	             
	             WebElement resultsTable = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("caseTable")));
	             Thread.sleep(1000);
                 
		 }
	             catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	               // driver.quit();
	            }
	            
		 }

}
