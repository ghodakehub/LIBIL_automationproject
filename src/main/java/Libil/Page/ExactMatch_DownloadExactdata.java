package Libil.Page;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExactMatch_DownloadExactdata extends BasePage {

	public ExactMatch_DownloadExactdata(WebDriver driver) {
		super(driver);
	}
	
	
	public void downloadJsonAndValidate(String fileName, int timeoutSeconds) throws IOException, InterruptedException {
        String downloadPath = "C:\\Users\\Super\\Downloads"; // Ensure this matches the actual location
        
        // Wait for the button to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), 'Download Exact Data')]")));

        // Click the button
        WebElement exactDataButton = driver.findElement(By.xpath("//button[@id='sendDataButton']"));
        exactDataButton.click();

        boolean isDownloaded = isFileDownloaded(downloadPath, fileName, timeoutSeconds);
        
        if (isDownloaded) {
            System.out.println("✅ Exact Data JSON file downloaded successfully.");

            // Validate JSON file with expected values
            validateJSONFile(downloadPath + File.separator + fileName, "amazon", 357000, "no");
        } else {
            System.out.println("❌ Exact Data JSON file NOT found.");
        }
    }
    

    // Function to check if the file exists with wait time
    public boolean isFileDownloaded(String downloadPath, String fileName, int timeoutSeconds) {
    	File file = new File(downloadPath, fileName); // ✅ Correct file path
        int elapsedTime = 0;
        
        while (!file.exists() && elapsedTime < timeoutSeconds) {
            try {
                Thread.sleep(1000); // Wait for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            elapsedTime++;
        }

        if (file.exists() && file.canRead()) {
            System.out.println("✅ File found: " + file.getAbsolutePath());
            return true;
        } else {
            System.out.println("❌ File not found: " + file.getAbsolutePath());
            return false;
        }
    }

    // Function to validate JSON content
    
    public static void validateJSONFile(String filePath, String expectedPartyName, int expectedLibilScore, String expectedRecommendation) throws IOException {
        // Initialize ObjectMapper to read JSON
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File(filePath));

        // Navigate to the required fields
        JsonNode partyNode = jsonNode.path("data").path("party");

        // Extract values
        String actualPartyName = partyNode.path("party_name").asText();
        int actualLibilScore = partyNode.path("libil_score").asInt();
        String actualRecommendation = partyNode.path("is_recommended").asText();

        // Validate the values
        if (actualPartyName.equals(expectedPartyName) && actualLibilScore == expectedLibilScore && actualRecommendation.equals(expectedRecommendation)) {
            System.out.println("✅ Validation Passed: JSON file contains expected values.");
        } else {
            System.out.println("❌ Validation Failed: Expected values do not match.");
            System.out.println("Expected: party_name=" + expectedPartyName + ", libil_score=" + expectedLibilScore + ", is_recommended=" + expectedRecommendation);
            System.out.println("Actual: party_name=" + actualPartyName + ", libil_score=" + actualLibilScore + ", is_recommended=" + actualRecommendation);
        }

        // Validate summary_items array
        JsonNode summaryItems = partyNode.path("summary_items");
        if (summaryItems.isArray() && summaryItems.size() > 0) {
            JsonNode firstItem = summaryItems.get(0);
            String caseType = firstItem.path("case_type").asText();
            int casesPending = firstItem.path("cases_pending").asInt();
            int casesDisposed = firstItem.path("cases_disposed").asInt();
            int casesInFavor = firstItem.path("cases_in_favor").asInt();
            int totalCases = firstItem.path("total_cases").asInt();

            // Expected values
            if (caseType.equals("Civil") && casesPending == 0 && casesDisposed == 2180 && casesInFavor == 1321 && totalCases == 2180) {
                System.out.println("✅ Validation Passed for summary_items.");
            } else {
                System.out.println("❌ Validation Failed for summary_items.");
            }
        }
    }
    	}

		
		    
		
    


