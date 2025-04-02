package Libil.Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Libil.Utility.Library;

public class EditManageUserPage extends BasePage {

	public EditManageUserPage(WebDriver driver) {
		super(driver);
	}
	 // Method to click Manage User button
    public void openManageUser() throws InterruptedException {
      
        WebElement manageUserBtn = driver.findElement(By.xpath("/html/body/header/div/div/a[2]"));
        manageUserBtn.click();
        System.out.println("Navigated to Manage User.");
        Thread.sleep(1000);
    }

   
    public void fillUserForm() throws InterruptedException {
    	
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // userEmail = "pratiksha.damodar@legitquest.com";
         
         
         WebElement editButton = wait.until(ExpectedConditions.presenceOfElementLocated(
        		    By.xpath("//a[contains(text(),'pratiksha.damodar@legitquest.com')]/ancestor::tr//button[contains(@class,'edit-user')]")
        		));

        		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
         System.out.println("Edit button clicked successfully!");
         Thread.sleep(1000);
         WebElement nameField = driver.findElement(By.xpath("//*[@id=\"edit-name\"]"));
         nameField.clear();
         Thread.sleep(1000);
         nameField.sendKeys("Updated name by automation");
         Thread.sleep(1000);
         WebElement email = driver.findElement(By.xpath("//*[@id=\"edit-contact_no\"]")); 
         email.clear();
         Thread.sleep(1000);
         email.sendKeys("9487384780");


         WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"edit-submit-btn\"]")); 
         saveButton.click();
         Thread.sleep(1000);
    }	
    	 public void verifyNewEntry(WebDriver driver) {
        	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        	  // Wait for the SweetAlert message to appear
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));
            String alertText = alert.getText();

            
            if (alertText.contains("User Updated Successfully!")) {
                System.out.println("Success: User Updated Successfully!");
            } else if (alertText.contains("Fail to update data!")) {
                System.out.println("Fail to update data!");
            } else {
                System.err.println("Unexpected message: " + alertText);
            }
        }
 }

