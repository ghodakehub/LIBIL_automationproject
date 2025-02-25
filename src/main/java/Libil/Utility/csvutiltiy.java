package Libil.Utility;

import java.io.File;

public class csvutiltiy {

	public static boolean waitForFileContains(String directory, String partialName, int timeoutSeconds) {
	    File dir = new File(directory);
	    int waited = 0;
	    while (waited < timeoutSeconds) {
	        File[] files = dir.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                if (file.getName().contains(partialName) && file.getName().endsWith(".csv")) {
	                    return true;
	                }
	            }
	        }
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        waited++;
	    }
	    return false;
	}
}
