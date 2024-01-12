package libil.Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Libil.Page.HomePage;
import Libil.Page.LoginPage;
import Libil.Utility.BaseTest;
import Libil.Utility.Library;

public class TC22VerifyReportGeneration extends BaseTest {

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

			int x = 0;
			Thread.sleep(3000);
			WebElement pages = driver
					.findElement(By.xpath("(//a[@class='page-link shadow-sm px-3'])[" + currentPage + "]"));
			pages.click();
			Thread.sleep(3000);
			WebElement st = driver.findElement(By.id("searchInput"));
			st.sendKeys("Kabir");
			Thread.sleep(3000);

			List<WebElement> elements = driver.findElements(By.xpath("//a[@class='text-decoration-none fw-semibold']"));
			// String attributeName = "data";
			String searchQuery = "Kabir";
			WebElement foundElement = null;
			for (WebElement element : elements) {
				String elememtTxt = element.getText();
				if (elememtTxt.equals(searchQuery)) {
					foundElement = element;
					break;
				}
			}

			// Genrate Report
			List<WebElement> gr = driver.findElements(By.xpath("//a[text()='Generate Report']"));
			System.out.println(gr.size());

			for (int i = 1; i <= gr.size(); i++) {
				WebElement gReport = driver.findElement(By.xpath("(//a[text()='Generate Report'])[" + i + "]"));
				if (gReport.isDisplayed()) {
					gReport.click();
					Thread.sleep(5000);
					x = 1;
					break;
				}
			}

			if (x == 1) {
				break;
			}

		}
		driver.findElement(By.xpath("//button[@id='searchForRelatedCompany']")).click();

		Library.threadSleep(10000);
		List<WebElement> Check_Box = driver.findElements(By.xpath("//input[@type='checkbox']"));
		System.out.println(Check_Box.size());
		for (int CheckBox = 1; CheckBox <= 4; CheckBox++) {
			WebElement Check_box = driver.findElement(By.xpath("(//input[@type='checkbox'])[" + CheckBox + "]"));
			Library.click(driver, Check_box, "Clicked on case");
			// Library.threadSleep(1000);

		}
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.bodyscrollTop)");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[@id='viewreportenable']")).click();
		Library.threadSleep(5000);

		WebElement Report_Heading = driver.findElement(By.xpath("//span[@class='fs-14 fw-semibold text-primary']"));
		WebElement Title = driver.findElement(By.xpath("//h4[@class='fw-bold mb-4 mt-2']"));
		System.out.println(Report_Heading.getText() + " " + Title.getText());

		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='download-report-wrapper position-fixed end-0 bottom-0 m-4 close']"))
				.click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//i[@class='bi bi-file-earmark-pdf']")).click();
	}

}
