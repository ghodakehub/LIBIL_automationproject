package libil.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Libil.Page.HomePage;
import Libil.Page.LoginPage;
import Libil.Utility.BaseTest;

public class TC06CompAddRequest extends BaseTest {

	@Test

	public void addRequest() throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		loginPage.adminLogin("admin@gmail.com", "Admin@345");

		driver.findElement(By.xpath("//button[text()='Add Request']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement dropDown = driver.findElement(By.xpath("//select[@name='company']"));// dropdown
		Select select = new Select(dropDown);
		select.selectByVisibleText("Company");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);

		driver.findElement(By.id("search_name")).sendKeys("IndusInd");// companyName Tab
		// Thread.sleep(5000);
		driver.findElement(By.id("comment")).sendKeys("Automation Testing Team.");
		driver.findElement(By.id("addRequest")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement cap = driver.findElement(By.xpath("//a[text()='capgemini']"));
		System.out.println(cap.getText());
		Thread.sleep(5000);

	}

}
