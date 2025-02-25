package Libil.Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CsvUtility {
	 /**
     * Validates that the CSV file exists, is not empty, and appears to be in CSV format.
     * It also prints the first two columns of the first few rows for debugging.
     *
     * @param filePath The full path to the CSV file.
     * @return true if the CSV is valid, false otherwise.
     */
    public static boolean isValidCsv(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            System.out.println("CSV file does not exist or is empty: " + filePath);
            return false;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line == null || !line.contains(",")) { // basic check for CSV format
                System.out.println("CSV file content is not as expected.");
                return false;
            }
            
            System.out.println("CSV file appears valid. Printing first two columns of the first 5 rows:");
            int rowCount = 0;
            // Print header (if available) and the first few rows.
            do {
                // Split the line by commas (you may need a more advanced CSV parser if data contains commas)
                String[] columns = line.split(",");
                if (columns.length >= 2) {
                    System.out.println(columns[0].trim() + " | " + columns[1].trim());
                } else {
                    System.out.println("Row doesn't have two columns: " + line);
                }
                rowCount++;
                if (rowCount >= 5) {  // Print only the first 5 rows for brevity
                    break;
                }
            } while ((line = br.readLine()) != null);
            
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
            return false;
        }
        return true;
    }
}


