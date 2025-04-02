package Libil.Page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Allure;

public class JSOfileDownload extends BasePage {

	public JSOfileDownload(WebDriver driver) {
		super(driver);
	}
	private final String downloadPath = "C:\\Users\\Super\\Downloads"; 

	public boolean downloadJSON(String keyword) throws InterruptedException {
		
		try {
		
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll to the bottom of the page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(1000);

        // Scroll horizontally within the table
        WebElement slider = driver.findElement(By.xpath("//div[contains(@class, 'table-responsive mb-4')]"));
        js.executeScript("arguments[0].scrollLeft += 500;", slider);
        Thread.sleep(2000);

        // Click the action button
        WebElement actionButton = driver.findElement(By.xpath("(//button[@class='btn btn-link'])[10]"));
        js.executeScript("arguments[0].click();", actionButton);
        Thread.sleep(2000);

        // Click "Generate Report"
        WebElement generateReport = driver.findElement(By.xpath("(//a[text()='Generate Report'])[10]"));
        js.executeScript("arguments[0].scrollIntoView(true);", generateReport);
        generateReport.click();
        Thread.sleep(2000);

        // Enter the keyword
        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"searchText\"]"));
        searchBox.clear();
        searchBox.sendKeys(keyword);
        Thread.sleep(2000);

        // Select "Exact match" radio button
        driver.findElement(By.xpath("//label[contains(text(),'Exact match')]")).click();
        Thread.sleep(2000);
        
        // Wait for the download button to be visible
        WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Download JSON')]")));
        downloadButton.click();

        // ✅ Verify if the JSON file is downloaded
        boolean isDownloaded = waitForDownloadAndValidate(keyword, ".json", Duration.ofMinutes(5));

        if (isDownloaded) {
            System.out.println("✅ JSON file downloaded successfully for: " + keyword);
            Allure.addAttachment("Download Success", "JSON file downloaded successfully for: " + keyword);
            return true;
        } else {
            System.out.println("❌ Failed to download JSON for: " + keyword);
            Allure.addAttachment("Download Failed", "JSON download failed for: " + keyword);
            return false;
        }

    } catch (Exception e) {
        e.printStackTrace();
        Allure.addAttachment("Error", "Exception occurred: " + e.getMessage());
        return false;
    }
}

	public boolean waitForDownloadAndValidate(String fileNamePrefix, String extension, Duration timeout) {
	    File downloadDir = new File(downloadPath);
	    long endTime = System.currentTimeMillis() + timeout.toMillis();

	    while (System.currentTimeMillis() < endTime) {
	        File[] files = downloadDir.listFiles();

	        if (files != null) {
	            for (File file : files) {
	                //  Check for downloaded JSON file
	                if (file.getName().startsWith(fileNamePrefix) && file.getName().endsWith(extension) && file.length() > 0) {

	                    //  Print JSON content for validation
	                    System.out.println("File downloaded: " + file.getName());
	                    
	                    // Validate the content by reading the first few lines
	                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	                        String line;
	                        int lineCount = 0;
	                        System.out.println("JSON Content (First 5 lines):");
	                        while ((line = reader.readLine()) != null && lineCount < 10) {
	                            System.out.println(line);
	                            lineCount++;
	                        }

	                        //  Attach the content to Allure for verification
	                        Allure.addAttachment("JSON Content", Files.readString(Paths.get(file.getAbsolutePath())));

	                        return true;
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                        Allure.addAttachment("Error", "Failed to read JSON file: " + e.getMessage());
	                        return false;
	                    }
	                }
	            }
	        }
	    }

	    System.out.println("Timeout reached. JSON file not downloaded.");
	    return false;
	}
}

        





