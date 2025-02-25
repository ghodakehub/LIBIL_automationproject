package Libil.Page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DownloadCSV extends BasePage {

	public DownloadCSV(WebDriver driver) {
		super(driver);
	}


    public void downloadCsvAndValidate(String fileName, int timeoutSeconds) throws IOException, InterruptedException {
        String downloadPath = "C:\\Users\\Super\\Downloads"; // Adjust path if needed

        // Wait for the CSV download button to be present & clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement csvDownloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='sendCsvButton']")));
        csvDownloadButton.click();

        // Wait for file to be downloaded
        if (isFileDownloaded(downloadPath, fileName, timeoutSeconds)) {
            System.out.println("✅ CSV file downloaded successfully.");
            extractColumnsFromCSV(downloadPath + File.separator + fileName, "court_name", "party_name");
        } else {
            System.out.println("❌ CSV file NOT found.");
        }
    }

    public boolean isFileDownloaded(String downloadPath, String fileName, int timeoutSeconds) {
        File file = new File(downloadPath, fileName);
        int elapsedTime = 0;
        int checkInterval = 2000; // Check every 2 seconds

        System.out.println("🔍 Checking for file: " + file.getAbsolutePath());

        while (!file.exists() && elapsedTime < timeoutSeconds * 1000) {
            try {
                Thread.sleep(checkInterval);
                elapsedTime += checkInterval;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        if (file.exists() && file.canRead()) {
            System.out.println("✅ File found: " + file.getAbsolutePath());
            return true;
        } else {
            System.out.println("❌ File NOT found after " + timeoutSeconds + " seconds.");
            return false;
        }
    }

    public void extractColumnsFromCSV(String filePath, String column1, String column2) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);

        // Remove blank lines and BOM if present
        lines.removeIf(String::isBlank);
        if (lines.isEmpty()) {
            System.out.println("❌ CSV file is empty!");
            return;
        }
        if (lines.get(0).startsWith("\uFEFF")) {
            lines.set(0, lines.get(0).substring(1));
        }

        // Detect delimiter dynamically
      //  String delimiter = detectDelimiter(lines.get(0));
        String[] headers = lines.get(0).split(","); // Preserve empty values

        // Find column indexes
        Map<String, Integer> columnIndexMap = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            columnIndexMap.put(headers[i].trim().toLowerCase(), i);
        }

        Integer indexCol1 = columnIndexMap.get(column1.toLowerCase());
        Integer indexCol2 = columnIndexMap.get(column2.toLowerCase());

        if (indexCol1 == null || indexCol2 == null) {
            System.out.println(" Specified columns not found in CSV.");
            System.out.println(" Available Columns: " + Arrays.toString(headers));
            System.out.println("Searching for: " + column1 + "" + column2 );
            return;
        }

        System.out.println(" Extracted Data (First 5 Rows):");
        int count = 0;
        for (int i = 1; i < lines.size() && count < 5; i++) {
            String[] row = lines.get(i).split(",", -1); // Use -1 to retain empty columns
            if (indexCol1 < row.length && indexCol2 < row.length) {
                String courtName = row[indexCol1].trim();
                String partyName = row[indexCol2].trim();

                if (!courtName.isEmpty() || !partyName.isEmpty()) { // Ignore empty rows
                    System.out.println(courtName + " | " + partyName);
                    count++;
                }
            }
        }
    }

    
    /*private String detectDelimiter(String headerLine) {
        if (headerLine.contains(",")) return ",";
        //if (headerLine.contains("\t")) return "\t";
       // if (headerLine.contains(";")) return ";";
        return ","; // Default to comma
    } */
	  
}

	
	
	




