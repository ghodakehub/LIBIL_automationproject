package Libil.Page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import Libil.Utility.Library;

public class ReportPage extends BasePage {

	public ReportPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//a[@class='page-link shadow-sm px-3']")
	private List<WebElement> PAGE_NUMBER;

	@FindBy(id = "searchInput")
	private WebElement SEARCH_BAR;

	@FindBy(xpath = "//button[@id='searchForRelatedCompany']")
	private WebElement SUBMIT_BUTTON;

	@FindBy(xpath = "//input[@type='checkbox']")
	private List<WebElement> CHECK_BOX;

	@FindBy(xpath = "//button[@id='viewreportenable']")
	private WebElement VIEW_REPORT_BUTTON;

	@FindBy(xpath = "//div[@class='logo p-4 border-end']")
	private WebElement LIBIL_LOGO;

	
	
	
	
	
	
	public void scrollTillBottomPage() {
		Library.scrollByPixelSize(driver, 10000);
		Library.threadSleep(4000);
	}

	public void searchClient(String ClientName) {
		List<WebElement> page = driver.findElements(By.xpath("//a[@class='page-link shadow-sm px-3']"));
		System.out.println("Total pages: " + page.size());

		for (int currentPage = 2; currentPage <= PAGE_NUMBER.size(); currentPage++) {
			int x = 0;
			Library.threadSleep(3000);

			WebElement pages = driver
					.findElement(By.xpath("(//a[@class='page-link shadow-sm px-3'])[" + currentPage + "]"));
			pages.click();
			Library.threadSleep(3000);
			Library.sendKeys(driver, SEARCH_BAR, "searchClient", ClientName);
			Library.threadSleep(2000);
 
			List<WebElement> elements = driver.findElements(By.xpath("//a[@class='text-decoration-none fw-semibold']"));
			String searchQuery = ClientName;
			WebElement foundElement = null;
			for (WebElement element : elements) {
				String elementTxt = element.getText();

				if (elementTxt.equals(searchQuery)) {
					foundElement = element;
					break;
				}
			}
			List<WebElement> GenerateReport = driver.findElements(By.xpath("//a[text()='Generate Report']"));
			System.out.println(GenerateReport.size());

			for (int i = 1; i <= GenerateReport.size(); i++) {
				WebElement gReport = driver.findElement(By.xpath("(//a[text()='Generate Report'])[" + i + "]"));
				if (gReport.isDisplayed()) {
					gReport.click();
					// Library.seleniumWait(5, driver);
					Library.threadSleep(3000);

					x = 1;
					break;
				}
			}
			if (x == 1) {

				break;
			}
		}
	}

	public void clickOnSubmitButton() {
		Library.click(driver, SUBMIT_BUTTON, "Clicked on submit button");
	}

	public void selectCases(int num) {

		List<WebElement> Check_Box = driver.findElements(By.xpath("//input[@type='checkbox']"));
		System.out.println(Check_Box.size());

		for (int CheckBox = 1; CheckBox <= num; CheckBox++) {

			WebElement Check_box = driver.findElement(By.xpath("(//input[@type='checkbox'])[" + CheckBox + "]"));
			Library.click(driver, Check_box, "Clicked on case");

		}
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.bodyscrollTop)");
		Library.threadSleep(2000);
	}

	public void viewReport() {
		Library.click(driver, VIEW_REPORT_BUTTON, "ClickedOnViewReport");
	}

	public void verifyReportDisplay() {

		Library.threadSleep(10);
		boolean REPORT_DISPLAY = Library.waitForVisibilityOf(driver, LIBIL_LOGO).isDisplayed();
		Assert.assertTrue(REPORT_DISPLAY);
		Library.threadSleep(5000);
	}
}
