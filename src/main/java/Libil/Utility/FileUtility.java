package Libil.Utility;

import java.io.File;

public class FileUtility {
	  public static boolean isFileDownloaded(String directory, String fileName, int timeoutSeconds) {
	        File dir = new File(directory);
	        int waited = 0;
	        
	        while (waited < timeoutSeconds) {
	            File[] files = dir.listFiles();
	            if (files != null) {
	                for (File file : files) {
	                	if (file.getName().equals(fileName)) {
	                		 System.out.println("Found file: " + file.getName());
	                        return true;
	                    }
	                }
	            }
	            try {
	                Thread.sleep(3000);  // wait 1 second
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            waited++;
	        }
	        return false;
	    }
	
	}


