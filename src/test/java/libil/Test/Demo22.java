package libil.Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Libil.Page.HomePage;
import Libil.Page.LoginPage;
import Libil.Utility.BaseTest;
import Libil.Utility.Library;

public class Demo22 extends BaseTest {

	@Test

	public void reportGeneration() throws Exception {

		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		loginPage.adminLogin("admin@gmail.com", "Admin@345");

		Library.scrollByPixelSize(driver, 10000);
		Thread.sleep(2000);

		List<WebElement> page = driver.findElements(By.xpath("//a[@class='page-link shadow-sm px-3']"));
		System.out.println("Total pages: " + page.size());

		for (int currentPage = 2; currentPage <= page.size() - 1; currentPage++) {

			Thread.sleep(3000);
			WebElement pages = driver
					.findElement(By.xpath("(//a[@class='page-link shadow-sm px-3'])[" + currentPage + "]"));
			pages.click();
			Thread.sleep(3000);

			WebElement st = driver.findElement(By.id("searchInput"));
			st.sendKeys("Varroc Group");
			Thread.sleep(5000);

			List<WebElement> elements = driver.findElements(By.xpath("//a[@class='text-decoration-none fw-semibold']"));// page
																														// cases
																														// Xpath

			// String attributeName = "data";

			String searchQuery = "Varroc Group";

			WebElement foundElement = null;

			for (WebElement element : elements) {
				String elememtTxt = element.getText();

				if (elememtTxt.equals(searchQuery)) {
					foundElement = element;
					break;
				}

				List<WebElement> gr = driver.findElements(By.xpath("//a[text()='Generate Report']"));
				System.out.println(gr.size());

				for (int i = 1; i <= gr.size(); i++) {

					WebElement gReport = driver.findElement(By.xpath("(//a[text()='Generate Report'])[" + i + "]"));

					if (gReport.isDisplayed()) {
						gReport.click();
						break;
					}

//					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//					driver.findElement(By.xpath("//button[@id='searchForRelatedCompany']")).click();
//					Thread.sleep(10000);

				}

			}
		}

		Thread.sleep(10000);
	}

}
