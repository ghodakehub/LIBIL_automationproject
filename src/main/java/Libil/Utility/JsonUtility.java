package Libil.Utility;




import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtility {
	
	/**
     * Reads the file and checks if it’s valid JSON.
     * 
     * @param filePath The full path to the JSON file.
     * @return true if valid JSON, false otherwise.
     */
    public static boolean isValidJson(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            System.out.println("File does not exist or is empty: " + filePath);
            return false;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(file);
            // If we can parse it successfully, it’s valid
            return jsonNode != null;
        } catch (IOException e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
            return false;
        }
    }

	}

