package Libil.Page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	
       public void checkallpaginations()
	{
		
		
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

           try {
               for (int page = 1; page <= 88; page++) {
                   try {
                       // Wait for pagination links to be visible
                       wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.pagination li.page-item a.page-link")));

                       // Find the pagination element for the current page
                       List<WebElement> paginationLinks = driver.findElements(By.cssSelector("ul.pagination li.page-item a.page-link"));
                     
                          
                       for (WebElement link : paginationLinks) {
                           String linkText = link.getText().trim();
                           if (linkText.equals(String.valueOf(page))) {
                               ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
                               Thread.sleep(2000);
                               link.click();
                               System.out.println("Clicked on Page: " + page);
                               Allure.step("Checking all pagenation on admindashboard home page");
                               break;
                           }
                       }

                  

                       // Wait for page content to load
                       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table/tbody/tr")));
                       Thread.sleep(3000);
       

                   } catch (Exception e) {
                	   System.out.println("Error on Page " + page);
                   }
               }

           } finally {
                  // driver.quit();
               }
	}
       
       public boolean isHomePageDisplayed() {
    	    try {
    	        return driver.findElement(By.xpath("//h4[contains(text(),'Admin Dashboard')]")).isDisplayed();
    	    } catch (NoSuchElementException e) {
    	        return false;
    	    }
}

}    


	
	
	

