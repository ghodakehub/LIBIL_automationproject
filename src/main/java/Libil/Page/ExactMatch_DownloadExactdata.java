package Libil.Page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;

public class ExactMatch_DownloadExactdata extends BasePage {

	public ExactMatch_DownloadExactdata(WebDriver driver) {
		super(driver);
	}
	
            private final String downloadPath = "C:\\Users\\Super\\Downloads"; 

        	public boolean downloadJSON(String keyword) throws InterruptedException {
        		
        		try {
        		
        		
        			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                // Wait for the download button to be visible
                WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Download Exact Data')]")));
                downloadButton.click();

                // Verify if the JSON file is downloaded
                boolean isDownloaded = waitForDownloadAndValidate(keyword, ".json", Duration.ofMinutes(5));

                if (isDownloaded) {
                    System.out.println("JSON file downloaded successfully for: " + keyword);
                    Allure.addAttachment("Download Success", "JSON file downloaded successfully for: " + keyword);
                    return true;
                } else {
                    System.out.println("Failed to download JSON for: " + keyword);
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
    

   
    	

		
		    
		
    


