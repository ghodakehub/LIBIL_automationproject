package Libil.Utility;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest2 {

    public WebDriver driver;
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

    public WebDriver initializeDriver(Map<String, Object> customPrefs) {
    	String browser = ConfingDataProvider.Browser.toLowerCase();
        boolean headless = Boolean.parseBoolean(ConfingDataProvider.Headless);
        
        String downloadFilePath = "C:\\Users\\Super\\Downloads"; // Set your desired download path here
       
        switch (browser) {
            case "chrome":
            	WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                
                
                // Create a default prefs map
                Map<String, Object> defaultChromePrefs = new HashMap<>();
                defaultChromePrefs.put("download.default_directory", downloadFilePath);
                defaultChromePrefs.put("download.prompt_for_download", false);
                defaultChromePrefs.put("safebrowsing.enabled", true);
                
                // Merge customPrefs (from the test) into the default prefs
                if (customPrefs != null && !customPrefs.isEmpty()) {
                    for (Map.Entry<String, Object> entry : customPrefs.entrySet()) {
                        defaultChromePrefs.put(entry.getKey(), entry.getValue());
                    }
                }

                // Set the final prefs
                chromeOptions.setExperimentalOption("prefs", defaultChromePrefs);
                

                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless");
                }
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException("Invalid browser specified: " + browser);
        }

        driver.manage().window().maximize();
        driver.get(ConfingDataProvider.URL);
        tdriver.set(driver);
        return BaseTest2.getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }

    @BeforeClass
    public void launchBrowser() {
    	initializeDriver(null);
    }

    @AfterClass
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
           
        }
    }
}