package Libil.Page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditSaveReport extends BasePage {

	public EditSaveReport(WebDriver driver) {
		super(driver);
	}
	

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
	
	   public void enterKeywordAndSearch(String keyword) throws InterruptedException {
		   
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
			
	        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("searchText")));
	        searchBox.clear();
	        searchBox.sendKeys(keyword);

	        WebElement submitButton = driver.findElement(By.id("searchForRelatedCompany"));
	        submitButton.click();

	        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofMinutes(2)); 

            
            WebElement fetchingMessage = driver.findElement(By.xpath("//h4[contains(text(),'Fetching Data ! Please Wait')]"));
            wait2.until(ExpectedConditions.visibilityOf(fetchingMessage));

            
      
           
            WebElement resultsTable = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("caseTable")));
            
	    }

	    public void selectCases(int maxCases) throws InterruptedException {
	    	   // Find all checkboxes
            List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

            int count = Math.min(10, checkboxes.size()); // Ensure max 10 checkboxes are selected

            for (int i = 0; i < count; i++) {
                WebElement checkbox = checkboxes.get(i);
                JavascriptExecutor js2 = (JavascriptExecutor) driver;
                js2.executeScript("arguments[0].click();", checkbox);

                // Use JavaScript Click if normal click doesn't work
                if (!checkbox.isSelected()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
                }

                // Small delay to ensure smooth selection
                Thread.sleep(500);
            }
	        }
	    

	    public void clickViewReport() {
	        WebElement viewReportButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'View Report')]")));
	        viewReportButton.click();
	    }

	    public void clickChooseAndEditReport(String newName, String newAddress) {
	        WebElement chooseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Choose')]")));
	        chooseButton.click();

	        WebElement editReportButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Edit Report')]")));
	        editReportButton.click();

	        // Edit subject name and address
	        WebElement subjectNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"subjectName\"]")));
	        subjectNameField.clear();
	        subjectNameField.sendKeys(newName);

	        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"partyAddress\"]")));
	        addressField.clear();
	        addressField.sendKeys(newAddress);
	        
	        
	    }

	    public void saveReport() throws InterruptedException {
	    	
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	 js.executeScript("window.scrollTo(0, 0);");
	    	 Thread.sleep(1000);

	    	 // Click on "Choose" button using JavaScript
	    	 WebElement chooseBtn = driver.findElement(By.xpath("//*[@id=\"hideDiv\"]/section[1]/div/div/div[1]/div[2]/div/button"));
	    	 js.executeScript("arguments[0].click();", chooseBtn);
	    	 Thread.sleep(2000);

	    	 // Wait until "Save Edits" button is clickable
	    	 WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	 WebElement saveButton1 = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Save Edits')]")));
	    	 js.executeScript("arguments[0].click();", saveButton1);
	    	 Thread.sleep(500);
	       
	        // Verify save success
	        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Saved Successfully')]")));
	        System.out.println("Report saved successfully!");
	    }

	    public void saveForLater() throws InterruptedException {
	    	
	    	
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	 // Click on "Choose" button using JavaScript
	    	 WebElement chooseBtn = driver.findElement(By.xpath("//*[@id=\"hideDiv\"]/section[1]/div/div/div[1]/div[2]/div/button"));
	    	 js.executeScript("arguments[0].click();", chooseBtn);
	    	 Thread.sleep(2000);

	        WebElement saveForLaterButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Save For Later')]")));
	        saveForLaterButton.click();
	        System.out.println("Report saved for later.");
	    }

	    public void continueEditing() throws InterruptedException {
	    	
	    	
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	 // Click on "Choose" button using JavaScript
	    	 WebElement chooseBtn = driver.findElement(By.xpath("//*[@id=\"hideDiv\"]/section[1]/div/div/div[1]/div[2]/div/button"));
	    	 js.executeScript("arguments[0].click();", chooseBtn);
	    	 Thread.sleep(2000);
	    	WebElement chooseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Choose')]")));
	        chooseButton.click();
	        WebElement continueEditingButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Continue Editing')]")));
	        continueEditingButton.click();
	        System.out.println("Continuing editing.");
	    }
}
