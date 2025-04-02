package Libil.Page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Libil.Utility.ForMultiplemailReceipent;
import Libil.Utility.UtilityClass;

public class SearchOptions extends BasePage {

	public SearchOptions(WebDriver driver) {
		super(driver);
	}
	  public void CheckAll_SearchOptions() throws InterruptedException {
		
		  JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			
			Thread.sleep(500);
			WebElement slider = driver.findElement(By.xpath("//div[contains(@class, 'table-responsive mb-4')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 500;", slider);

			Thread.sleep(2000);
			WebElement actionButton = driver.findElement(By.xpath("(//button[@class='btn btn-link'])[10]")); 
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actionButton);
			actionButton.click();
			
			Thread.sleep(2000);
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			WebElement element1 = driver.findElement(By.xpath("(//a[text()='Generate Report'])[10]"));
			js1.executeScript("arguments[0].click();", element1);
		
			 try {
		       
				 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		            // Locate the search box and enter a company name
		            WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"searchText\"]"));
		            searchBox.clear();
		            searchBox.sendKeys("tata");
		            Thread.sleep(1000);
		            
		                List<WebElement> radioButtons = driver.findElements(By.xpath("//*[@id=\"hideDiv\"]/section[1]/div/div/div[2]/div/input"));

		                for (WebElement radioButton : radioButtons) {
		                   
		                  
		                	radioButton.click();
		                	Thread.sleep(1000);
		
		                    WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"searchForRelatedCompany\"]"));
		                    submitButton.click();
		                    WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofMinutes(5)); 
                                
		                 
		                 WebElement fetchingMessage = driver.findElement(By.xpath("//h4[contains(text(),'Fetching Data ! Please Wait')]"));
		                 wait2.until(ExpectedConditions.visibilityOf(fetchingMessage));

		                 
		                 wait2.until(ExpectedConditions.invisibilityOf(fetchingMessage));

		                
		                 WebElement resultsTable = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("caseTable")));
		                 
		                    List<WebElement> tableRows = resultsTable.findElements(By.xpath(".//tbody/tr"));

		                if (tableRows.size() <= 1) { // Assuming first row is a header
		                    System.out.println("No results found for option: " +radioButton.getText() );
		                    
		                    String Screenshot = UtilityClass.Capaturescreenshot(driver,"SEARCH OPTIONS");
		 	               
		 	               
		 	               String testUrl = driver.getCurrentUrl();  
		 	      		 ForMultiplemailReceipent.sendEmail(
		 	                 	   driver, new String[]{"ghodake6896@gmail.com"},
		 	                 	    "LIBIL : SearchOptionsResults",
		 	                 	    "Please check Libil Search Options Not provide any Results , please find the attached screenshot for details." ,
		 	                 	   Screenshot , testUrl
		 	                 	   
		 	                 ); 
		                } else 
		                {
		                    System.out.println("Results found for option: " +radioButton.getText());
		                   
		                }
		                
		                }
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
}
