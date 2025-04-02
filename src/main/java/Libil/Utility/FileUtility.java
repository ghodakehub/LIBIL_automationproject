package Libil.Utility;

import java.io.File;
import java.io.FileReader;
import java.time.Duration;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import io.qameta.allure.Allure;

public class FileUtility {
	private String downloadPath = "C:\\Downloads";  // Your download folder path

	 
		    public boolean waitForDownload(String keyword, Duration timeout) {
		        File downloadDir = new File(downloadPath);
		        long endTime = System.currentTimeMillis() + timeout.toMillis();

		        while (System.currentTimeMillis() < endTime) {
		            File[] files = downloadDir.listFiles();

		            if (files != null) {
		                for (File file : files) {
		                    if (file.getName().startsWith(keyword) && file.getName().endsWith(".json") && file.length() > 0) {
		                        
		                        System.out.println("✅ File downloaded: " + file.getName());
		                        
		                        // Attach to Allure report
		                        Allure.addAttachment("Downloaded File", "File: " + file.getName() + "\nSize: " + file.length() + " bytes");
		                        
		                        return true;
		                    }
		                }
		            }
		        }

		        System.out.println("❌ Timeout reached. No file downloaded.");
		        return false;
		    }

		    // ✅ Validate content of downloaded JSON
		    public boolean validateDownloadedJson(String keyword, String expectedKey, String expectedValue) {
		        File downloadDir = new File(downloadPath);
		        File[] files = downloadDir.listFiles();

		        if (files != null) {
		            for (File file : files) {
		                if (file.getName().startsWith(keyword) && file.getName().endsWith(".json")) {
		                    
		                    try (FileReader reader = new FileReader(file)) {
		                        JSONParser jsonParser = new JSONParser();
		                        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

		                        // ✅ Extract and validate the expected key-value pair
		                        String actualValue = (String) jsonObject.get(expectedKey);

		                        if (actualValue != null && actualValue.equals(expectedValue)) {
		                            System.out.println("✅ Data Verified: " + expectedKey + " = " + actualValue);
		                            Allure.addAttachment("Data Verified", "Key: " + expectedKey + "\nValue: " + actualValue);
		                            return true;
		                        } else {
		                            System.out.println("❌ Data Mismatch: Expected " + expectedKey + " = " + expectedValue + ", but found " + actualValue);
		                            Allure.addAttachment("Data Mismatch", "Expected: " + expectedKey + " = " + expectedValue + "\nActual: " + actualValue);
		                            return false;
		                        }

		                    } catch (Exception e) {
		                        e.printStackTrace();
		                        Allure.addAttachment("Error", "Failed to read JSON file: " + e.getMessage());
		                        return false;
		                    }
		                }
		            }
		        }

		        System.out.println("❌ JSON file not found.");
		        return false;
		    }
		}
	
	


