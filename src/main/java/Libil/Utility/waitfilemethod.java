package Libil.Utility;

import java.io.File;

public class waitfilemethod {
	
	public static boolean waitForDownload(String downloadPath, int timeoutInSeconds) throws InterruptedException {
	    File downloadDir = new File(downloadPath);
	    long endTime = System.currentTimeMillis() + (timeoutInSeconds * 1000);

	    while (System.currentTimeMillis() < endTime) {
	        File[] files = downloadDir.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                if (file.getName().matches(".*(\\.json|\\.csv)$") && file.length() > 0) {
	                    System.out.println("Download completed: " + file.getName());
	                    return true;  // File downloaded successfully
	                }
	            }
	        }
	        Thread.sleep(2000);  // Wait 1 second before rechecking
	    }
	    return false;  // Timeout reached
	}
}
