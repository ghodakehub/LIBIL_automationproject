package Libil.Page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;

public class Search_InputBar extends BasePage {

	public Search_InputBar(WebDriver driver) {
		super(driver);
	}

	
	 public boolean CheckSearchBarFunctionality(String keyword)
	 {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        try {
	            // Locate the search bar and enter the search query
	            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Start typing to search']")));
	            searchBox.sendKeys(keyword);
	            Thread.sleep(1000);
	            searchBox.sendKeys(Keys.ENTER); // Press Enter to search

	            // Wait for results to load
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table/tbody/tr")));
	            Thread.sleep(3000);

	            // Verify search results
	            List<WebElement> results = driver.findElements(By.xpath("//*[@id=\"record1187\"]/td[1]/a"));
	            boolean found = false;

	            for (WebElement result : results) {
	                if (result.getText().contains("Sandeep Singhal")) {
	                    found = true;
	                    break;
	                }
	            }

	            if (found) {
	                System.out.println("Search Test Passed: Results found for 'Sandeep Singhal'");
	                Allure.step("Search Test Passed: Results found for 'Sandeep Singhal'");
	            } else {
	                System.out.println("Search Test Failed: No results found");
	                Allure.step("Search Test failed: Results  not found for 'Sandeep Singhal'");
	            }

	        } catch (Exception e) {
	            System.out.println("Test failed due to error: " + e.getMessage());
	        }
			return false; 
	    }



	 }

