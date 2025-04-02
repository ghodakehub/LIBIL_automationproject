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

public class ExactMatch_DowloadCSV extends BasePage {

	public ExactMatch_DowloadCSV(WebDriver driver) {
		super(driver);
	}
	
	 private final String downloadPath = "C:\\Users\\Super\\Downloads"; 

 	public boolean downloadCSV() throws InterruptedException {
 		
 		try {
		            		
		        			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		                // Wait for the download button to be visible
		                WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Download CSV')]")));
		                downloadButton.click();

		                // Verify if the csv file is downloaded
		                boolean isDownloaded = waitForDownloadAndValidate("cases", ".csv", Duration.ofMinutes(4));

		                if (isDownloaded) {
		                    System.out.println("CSV file downloaded successfully for: ");
		                    Allure.addAttachment("Download Success", "CSV file downloaded successfully for");
		                    return true;
		                } else {
		                    System.out.println("Failed to download JSON for: ");
		                    Allure.addAttachment("Download Failed", "JSON download failed for: ");
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
                    if (file.getName().startsWith(fileNamePrefix) && file.getName().endsWith(extension) && file.length() > 0) {
                        System.out.println("File downloaded: " + file.getName());

                        // Attach file info to Allure report
                        Allure.addAttachment("Downloaded File", "Name: " + file.getName() + "\nSize: " + file.length() + " bytes");

                        //  Read and attach CSV content
                        if (readCSVContent(file)) {
                            return true;
                        }
                    }
                }
            }
        }

        System.out.println("❌ Timeout reached. CSV file not downloaded.");
        Allure.addAttachment("Download Failed", "CSV file not downloaded.");
        return false;
    }

    // Method to read and validate CSV content
    public boolean readCSVContent(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder csvContent = new StringBuilder();
            String line;
            int lineCount = 0;

            System.out.println("CSV Content (First 3 lines):");
            while ((line = reader.readLine()) != null && lineCount < 10) {
                System.out.println(line);
                csvContent.append(line).append("\n");
                lineCount++;
            }

            // Attach CSV content to Allure report
            Allure.addAttachment("CSV Content", csvContent.toString());

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            Allure.addAttachment("Error", "Failed to read CSV file: " + e.getMessage());
            return false;
        }
    }
}
    




