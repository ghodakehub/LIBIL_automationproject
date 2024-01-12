package libil.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Libil.Page.HomePage;
import Libil.Page.LoginPage;
import Libil.Utility.BaseTest;

public class TC07IndiAddRequest extends BaseTest {

	@Test

	public void individualRequest() throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		loginPage.adminLogin("admin@gmail.com", "Admin@345");

		driver.findElement(By.xpath("//button[text()='Add Request']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement dropDown = driver.findElement(By.xpath("//select[@name='company']"));// dropdown
		Select select = new Select(dropDown);
		select.selectByVisibleText("Individual");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);

		driver.findElement(By.id("search_name")).sendKeys("Kabir");// individual name tab
		driver.findElement(By.id("individual_email")).sendKeys("vaibhav.joshi@legitquest.com");// email tab
		driver.findElement(By.id("individual_phone")).sendKeys("9325553919");// phone tab
		driver.findElement(By.id("individual_address")).sendKeys("Chhatrapti Sambhajinagar");// address tab
		driver.findElement(By.id("individual_district")).sendKeys("Chhatrapti Sambhajinagar");// District tab
		WebElement stateDropdown = driver.findElement(By.id("individual_state"));
		Select select1 = new Select(stateDropdown);
		select1.selectByValue("maharashtra");
		driver.findElement(By.id("individual_pincode")).sendKeys("431009");
		driver.findElement(By.id("addRequest")).click();
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();

		Thread.sleep(3000);

		// Not important
//		WebElement Yad=driver.findElement(By.xpath("//a[text()='Yadav']"));//added case
//        System.out.println(Yad.getText());
//        Thread.sleep(5000);

		driver.findElement(By.id("navbarDropdownMenuLink")).click();// logOut
		driver.findElement(By.xpath("//a[@class='dropdown-item']")).click();
		Thread.sleep(5000);

	}

}
