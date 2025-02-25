package Libil.Page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;

public class newhomedashboard extends BasePage {

	public newhomedashboard(WebDriver driver) {
		super(driver);
	}
	
	
       public void checkallpaginations()
	{
		
		
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	
	

    try {
        for (int page = 1; page <= 80; page++) {
            try {
                
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.pagination li.page-item a.page-link")));

                // Find all pagination links
                List<WebElement> paginationLinks = driver.findElements(By.cssSelector("ul.pagination li.page-item a.page-link"));

                boolean pageFound = false;
                for (WebElement link : paginationLinks) {
                    String linkText = link.getText().trim();
                    if (linkText.equals(String.valueOf(page))) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
                        Thread.sleep(2000);
                        link.click();
                        System.out.println("Clicked on Page: " + page);
                        Allure.step("Navigated to Page: " + page);
                        pageFound = true;
                        break;
                    }
                }

                if (!pageFound) {
                    System.out.println("Page " + page + " not found in pagination.");
                    Allure.step("Page " + page + " not found in pagination.");
                    break; // Stop if pagination stops
                }

            } catch (Exception e) {
                System.out.println("Error on Page " + page + ": " + e.getMessage());
                Allure.step("Error on Page " + page + ": " + e.getMessage());
            }
        }
    } catch (Exception e) {
        System.out.println("Pagination checking failed: " + e.getMessage());
        Allure.step("Pagination checking failed: " + e.getMessage());
    }

	}
}
