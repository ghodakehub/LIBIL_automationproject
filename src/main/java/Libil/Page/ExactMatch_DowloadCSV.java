package Libil.Page;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Libil.Utility.CsvUtility;
import Libil.Utility.FileUtility;

public class ExactMatch_DowloadCSV extends BasePage {

	public ExactMatch_DowloadCSV(WebDriver driver) {
		super(driver);
	}
	
	
		  public void downloadCSV() {
			  
			  try {
				  String downloadPath = "C:\\Users\\Super\\Downloads";

				  
		            // 1. Click the "Download CSV" button (adjust the locator as needed)
		            driver.findElement(By.xpath("//button[@id='sendCsvButton']")).click();

		

		                // Click on "Download CSV" button
		                WebElement downloadButton = driver.findElement(By.xpath("//button[@id='sendCsvButton']"));

		                downloadButton.click();

		                // Wait for the file to download (Adjust sleep as needed)
		                Thread.sleep(9000);

		                // Verify if file is downloaded
		                File downloadedFile = getLatestFileFromDir(downloadPath);
		                if (downloadedFile != null && downloadedFile.getName().endsWith(".csv")) {
		                    System.out.println("CSV file downloaded successfully: " + downloadedFile.getName());
		                } else {
		                    System.out.println("CSV file download failed!");
		                }

		            } catch (Exception e) {
		                e.printStackTrace();
		            } 
			  }
		
		        // Method to get the latest file from a directory
		        private static File getLatestFileFromDir(String dirPath) {
		            File dir = new File(dirPath);
		            File[] files = dir.listFiles();
		            if (files == null || files.length == 0) {
		                return null;
		            }
		            File latestFile = files[0];
		            for (File file : files) {
		                if (file.lastModified() > latestFile.lastModified()) {
		                    latestFile = file;
		                }
		            }
		            return latestFile;
		        }
		    
		    }
		    
		
    



