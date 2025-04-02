package Libil.Page;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Libil.Utility.Library;

public class ManageUserPage extends BasePage {

	public ManageUserPage(WebDriver driver) {
		super(driver);

	}
    public void openManageUser() {
      
        WebElement manageUserBtn = driver.findElement(By.xpath("/html/body/header/div/div/a[2]"));
        manageUserBtn.click();
        System.out.println("Navigated to Manage User.");
    }

    // Method to fill the form
    public void fillUserForm(String name, String company, String email, String contact, String password, String userType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click "Add New User"
        WebElement addUserBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add New User')]")));
        addUserBtn.click();
           Library.threadSleep(1000);
        // Fill form fields
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id=\"company\"]")).sendKeys(company);
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id=\"contact_no\"]")).sendKeys(contact);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"password_confirmation\"]")).sendKeys(password);
        Library.threadSleep(5000);
       
        WebElement dropdown = driver.findElement(By.xpath("(//*[@id=\"usertype\"])[2]"));
        Select select = new Select(dropdown);
        select.selectByVisibleText(userType);  
        Library.threadSleep(3000);
        driver.findElement(By.xpath("//*[@id=\"submit-btn\"]")).click();
        System.out.println("Form submitted.");
       
    }
    
    public void verifyNewEntry(WebDriver driver) {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	  // Wait for the SweetAlert message to appear
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));
        String alertText = alert.getText();

        // Verify if success or duplicate message appears
        if (alertText.contains("New User Added Successfully")) {
            System.out.println("Success: User added successfully.");
        } else if (alertText.contains("Email Already exist!")) {
            System.out.println("Duplicate: User already exists.");
        } else {
            System.err.println("Unexpected message: " + alertText);
        }
    }
       
}


