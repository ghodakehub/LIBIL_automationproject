package Libil.Page;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Libil.Utility.UtilityClass;

public class NearSearchResults extends BasePage {

	public NearSearchResults(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//*[@id=\"record1188\"]/td[8]/div/button/i")
	private WebElement actions;

	@FindBy(xpath="//*[@id=\"record1188\"]/td[8]/div/ul/li[7]/a")
	private WebElement generatereport;

	@FindBy(xpath="//button[@id='searchForRelatedCompany']")
	private WebElement submitbtn;

	public void checkNearSearch_Options() throws InterruptedException, IOException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		
		Thread.sleep(2000);
		WebElement slider = driver.findElement(By.xpath("//div[contains(@class, 'table-responsive mb-4')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 500;", slider);

		Thread.sleep(2000);
		WebElement actionButton = driver.findElement(By.xpath("(//tr)[11]//button[contains(@class, 'btn btn-link')]")); 
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actionButton);
		actionButton.click();
		Thread.sleep(3000);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.xpath("//a[@class='dropdown-item' and contains(text(), 'Generate Report')]"));
		js1.executeScript("arguments[0].click();", element1);
		

		 try {
	           
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(5)); 
              //Click "Submit"
             WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchForRelatedCompany\"]")));
             submitButton.click();

             // Wait for the "Fetching Data" message to disappear
             wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(), 'Fetching Data ! Please Wait...')]")));

             // Check if the unresponsive popup appears
             handleUnresponsivePopup(driver);

             // Validate if the data is loaded
             boolean isDataLoaded = driver.findElements(By.xpath("//*[@id=\"caseTable\"]/tbody/tr")).size() > 0;
             if (isDataLoaded) {
                 System.out.println("Data successfully loaded.");
             } else {
                 System.out.println("No data found or the page is still unresponsive.");
                 UtilityClass.Capaturescreenshot(driver, "error");
             }

         } catch (Exception e) {
             System.out.println("An error occurred: " + e.getMessage());
             UtilityClass.Capaturescreenshot(driver, "ErrorScreenshot.png");
         } 
     }

	
	
    
    	  
       // Handle Unresponsive Popup
       public static void handleUnresponsivePopup(WebDriver driver) {
           try {
               WebElement waitButton = driver.findElement(By.xpath("//button[contains(text(), 'Wait')]"));
               if (waitButton.isDisplayed()) {
                   System.out.println("Page Unresponsive popup detected. Clicking 'Wait'.");
                   waitButton.click();
               }
           } catch (NoSuchElementException e) {
               System.out.println("No Unresponsive Popup detected.");
           }
       }

       public boolean isResultsAvailable() {
           try {
               WebElement resultsTable = driver.findElement(By.id("caseTable"));
               List<WebElement> tableRows = resultsTable.findElements(By.xpath(".//tbody/tr"));
               return !tableRows.isEmpty();
           } catch (NoSuchElementException e) {
               return false;
           }
       }
      
}
