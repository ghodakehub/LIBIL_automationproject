package Libil.Page;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Libil.Utility.Library;

public class EditReportPage extends BasePage {

	public EditReportPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//*[@id=\"hideDiv\"]/section[1]/div/div/div[1]/div[2]/div/button")   
    WebElement choosebtn;
	
	 @FindBy(xpath = "//a[contains(text(),'Edit Report')]")   
	    WebElement editButton;

	    @FindBy(id = "subjectName")
	    WebElement subjectNameField;

	    @FindBy(id = "partyAddress")
	    WebElement address;

	    @FindBy(xpath = "//*[@id=\"saveButton\"]")  
	    WebElement saveButton1;

	    @FindBy(xpath = "//div[contains(text(),'Saved Successfully')]") 
	    WebElement successMessage;


	    
	    
	    public void checkEditAndSavebuttonsOptions(String newName, String newAddress) throws InterruptedException, IOException
		{
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
			
				 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		            // Locate the search box and enter a company name
		            WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"searchText\"]"));
		            searchBox.clear();
		            searchBox.sendKeys("Pratiksha");
		            Thread.sleep(1000);
		
		                    WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"searchForRelatedCompany\"]"));
		                    submitButton.click();
		                    WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofMinutes(2)); 

		                 
		                 WebElement fetchingMessage = driver.findElement(By.xpath("//h4[contains(text(),'Fetching Data ! Please Wait')]"));
		                 wait2.until(ExpectedConditions.visibilityOf(fetchingMessage));

		                 
		           
		                
		                 WebElement resultsTable = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("caseTable")));
		                 
		                 Library.scrollByPixelSize(driver, 200);
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

	             
	             // Click on "View Report" button
	             WebElement viewReportButton = driver.findElement(By.xpath("//button[contains(text(),'View Report')]"));
	             viewReportButton.click();
	             Thread.sleep(10000);
	             
	             choosebtn.click();
	             Thread.sleep(2000);
	             
	             editButton.click();
	             Thread.sleep(3000);
	   
	             
	           
	             subjectNameField.clear();
	 	        subjectNameField.sendKeys(newName);
	 	       Thread.sleep(3000);
	             
	 	       address.clear();
		    	address.sendKeys(newAddress);
		    	 Thread.sleep(500);
	             
		    	// Actions actions = new Actions(driver);
		    	// actions.sendKeys(Keys.PAGE_UP).perform();
		    	 //Thread.sleep(1000);
		    	 
		    	// Scroll to the top of the page
		    	 js.executeScript("window.scrollTo(0, 0);");
		    	 Thread.sleep(1000);

		    	 // Click on "Choose" button using JavaScript
		    	 WebElement chooseBtn = driver.findElement(By.xpath("//*[@id=\"hideDiv\"]/section[1]/div/div/div[1]/div[2]/div/button"));
		    	 js.executeScript("arguments[0].click();", chooseBtn);
		    	 Thread.sleep(2000);

		   
		    	 WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
		    	 WebElement saveButton1 = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Save Edits')]")));
		    	 js.executeScript("arguments[0].click();", saveButton1);
		    	 Thread.sleep(500);
	             
			 }
	

	    public boolean isSaveSuccessful() {
	        return successMessage.isDisplayed();
	    }
	    
	    
	    public boolean isEditEnabled() {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            WebElement editMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//div[contains(text(),'You can Edit Now')]"))); 

	            String messageText = editMessage.getText();
	            System.out.println("Edit Message Displayed: " + messageText);

	            return messageText.equalsIgnoreCase("You can edit now"); // Validation
	        } catch (TimeoutException e) {
	            System.out.println("Edit message did not appear.");
	            return false;
	        }
	    }
	}


