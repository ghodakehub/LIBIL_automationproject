package Libil.Page;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddNewUser_DeleteButton  extends BasePage {

	public AddNewUser_DeleteButton(WebDriver driver) {
		super(driver);
	}
	 // Method to click Manage User button
    public void openManageUser() throws InterruptedException {
      
        WebElement manageUserBtn = driver.findElement(By.xpath("/html/body/header/div/div/a[2]"));
        manageUserBtn.click();
        System.out.println("Navigated to Manage User.");
        Thread.sleep(1000);
    }
 public void verifydeleteuser()
 {
	 try {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	  
     String userEmail = "pratiksha.damodar@legitquest.com";

    
     WebElement deleteButton = wait.until(ExpectedConditions.presenceOfElementLocated(
         By.xpath("//a[contains(text(),'" + userEmail + "')]/ancestor::tr//button[contains(@class,'delete-user')]")
     ));

    
     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteButton);
     Thread.sleep(500);

     
     ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);

     System.out.println("Delete button clicked successfully!");

    
     try {
         WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
         Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
         alert.accept(); // Clicks 'OK' on the alert
         System.out.println("Deletion confirmed.");
     } catch (Exception e) {
         System.out.println("No confirmation alert found.");
     }

 } catch (Exception e) {
     System.out.println("Error : coming while delete user");
 } }

}
