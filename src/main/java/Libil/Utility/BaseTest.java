package Libil.Utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest extends ConfigDataProvider {
	
	public WebDriver driver;
	
	@BeforeClass
	public void launchBrowser(){
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.close();
	}

	
	
	

}
