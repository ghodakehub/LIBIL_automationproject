package libil.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Libil.Utility.BaseTest;

public class TC19VerifyAdminDashBoard extends BaseTest{
	
	@Test
	
	public void adminDashBoard() throws Exception {
		
		driver.findElement(By.xpath("//a[@class='btn btn-primary px-4 rounded-5 fs-14']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//span[@class='me-2'])[1]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("admin@gmail.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Admin@345");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(5000);
		
		WebElement tr=driver.findElement(By.xpath("//h6[text()='Total Request']"));
		if(tr.isDisplayed()) {
			System.out.println("Total Request is visible on page.");
		}else {
			System.out.println("Total Req is not visible on page.");
		}
		
		WebElement pr=driver.findElement(By.xpath("//h6[text()='Pending Request']"));
		if(pr.isDisplayed()) {
			System.out.println("Pending Request is visible on page.");
		}else {
			System.out.println("Pending request is not visible on page.");
		}
		
		WebElement rs=driver.findElement(By.xpath("//h6[text()='Report Submitted']"));
		if(rs.isDisplayed()) {
			System.out.println("Report submitted is visible on page.");
		}else {
			System.out.println("Report submitted is not visible.");
		}
		
		WebElement wp=driver.findElement(By.xpath("//h6[text()='Work in Progress']"));
		if(wp.isDisplayed()) {
			System.out.println("Work in progress is visible on page.");
		}else {
			System.out.println("Work in progress is not visible.");
		}
		
		Thread.sleep(5000);
		
			
		
		
		
		
	}
	
	

}
