package Libil.Utility;




import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtility {
	
	private final String downloadPath = "C:\\Users\\Super\\Downloads"; 
	/**
     * Reads the file and checks if it’s valid JSON.
     * 
     * @param filePath The full path to the JSON file.
     * @return true if valid JSON, false otherwise.
     */
	public boolean verifyKeywordInJSON(File jsonFile, String keyword) {
		 if (jsonFile == null || !jsonFile.exists()) {
		        System.out.println("JSON file not found!");
		        return false;
		    }

		    try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
		        StringBuilder jsonContent = new StringBuilder();
		        String line;

		        while ((line = reader.readLine()) != null) {
		            jsonContent.append(line);
		        }

		        // Handle both JSONObject and JSONArray
		        String content = jsonContent.toString().trim();

		        if (content.startsWith("{")) {
		            // Single JSON Object
		            JSONObject jsonObject = new JSONObject(content);
		            if (jsonObject.toString().toLowerCase().contains(keyword.toLowerCase())) {
		                System.out.println("✅ Keyword found in JSONObject!");
		                return true;
		            }
		        } else if (content.startsWith("[")) {
		            // JSON Array
		            JSONArray jsonArray = new JSONArray(content);
		            for (int i = 0; i < jsonArray.length(); i++) {
		                JSONObject obj = jsonArray.getJSONObject(i);
		                if (obj.toString().toLowerCase().contains(keyword.toLowerCase())) {
		                    System.out.println("✅ Keyword found in JSONArray!");
		                    return true;
		                }
		            }
		        } else {
		            System.out.println("❌ Invalid JSON format!");
		            return false;
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    System.out.println("❌ Keyword NOT found in JSON!");
		    return false;
		}
	}

