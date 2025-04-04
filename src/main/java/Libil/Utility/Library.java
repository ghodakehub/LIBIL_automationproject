package Libil.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Library {

	public static WebElement waitForVisibilityOf(WebDriver driver, WebElement element) {
		Library.scrollByPixelSize(driver, 100);
		Library.threadSleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public static void scrollTillElementDisplay(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}

	public static void scrollByPixelSize(WebDriver driver, int PixelSize) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + PixelSize + ")", "");
		System.out.println(PixelSize);
	}

	
	public static void scrollLeftToRight(WebDriver driver, int PixelSize) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy("+PixelSize+",0)");
		System.out.println("Left right moved for to click on three dot.");
		
		
	}
	
	
	public static void click(WebDriver driver, WebElement element, String LogMessege) {
		try {
			element.click();
			System.out.println( LogMessege);
		} catch (Exception e) {
			
			scrollTillElementDisplay(driver, element);
			waitForVisibilityOf(driver, element);
			element.click();
			System.out.println(LogMessege);
		}
	}

	public static void sendKeys(WebDriver driver, WebElement element, String LogMessege, String enterValue) {
		try {
			scrollTillElementDisplay(driver, element);
			waitForVisibilityOf(driver, element);
			element.clear();
			element.sendKeys(enterValue);
			System.out.println(LogMessege);
		} catch (Exception e) {
			element.sendKeys(enterValue);
			System.out.println("Test failed at step : " + LogMessege);
		}
	}

	public static void performEnterAction(WebElement element) {
		element.sendKeys(Keys.ENTER);
	}

	public static void threadSleep(int MiliSeconds) {
		try {
			Thread.sleep(MiliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String getConfigueData(String key) {
		Properties pro = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\configue.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			pro.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String value = pro.getProperty(key);
		return value;
	}

	public static void dropDown(WebDriver driver, WebElement element, String name) {
		Select select = new Select(element);
		Library.seleniumWait(20, driver);
		select.selectByVisibleText(name);
	}

	public static void seleniumWait(int sec, WebDriver driver) {
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}
	
	public static void scrollToBottomAndLeft(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll vertically to bottom
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Scroll horizontally to the left
        js.executeScript("document.querySelector('div').scrollLeft = 0;");
    }

    // Click the three dots and select an option
    public static void clickThreeDotsAndSelectOption(WebDriver driver,String optionText) {
        try {
            // Scroll to bottom and le]\
            scrollToBottomAndLeft(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Wait for the three dots to be visible
            WebElement threeDots = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//tr)[11]//button[contains(@class, 'btn btn-link')]")));
            
            // Click the three dots
            threeDots.click();
            
            // Select the desired option from the dropdown
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='dropdown-item' and contains(text(), '" + optionText +"')]")));
            option.click();

            System.out.println("Clicked on option: " + optionText);
        } catch (Exception e) {
            System.out.println("Failed to click on the option: " + e.getMessage());
        }
    }

	
}

