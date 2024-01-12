package Libil.Page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import Libil.Utility.Library;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@class='page-link shadow-sm px-3']")
	private List<WebElement> PAGE_NUMBER;

	@FindBy(id = "searchInput")
	private WebElement SEARCH_BAR;

	@FindBy(xpath = "//button[@id='searchForRelatedCompany']")
	private WebElement SUBMIT_BUTTON;

	@FindBy(xpath = "// button[@id='viewreportenable']")
	private WebElement VIEW_REPORT_BUTTON;

	@FindBy(xpath = "//div[@class='logo p-4 border-end']")
	private WebElement LIBIL_LOGO;

	public void scrollTillBottomPage() {
		Library.scrollByPixelSize(driver, 10000);
	}

	public void serchClients(String ClientName) {

		for (int i = 2; i <= PAGE_NUMBER.size() - 1; i++) {
			int x = 0;
			Library.threadSleep(3000);
			WebElement pages = driver.findElement(By.xpath("(//a[@class='page-link shadow-sm px-3'])[" + i + "]"));
			pages.click();
			Library.threadSleep(3000);
			Library.sendKeys(driver, SEARCH_BAR, "serchClients", ClientName);
			Library.threadSleep(3000);

			List<WebElement> All_Clients_list = driver
					.findElements(By.xpath("//a[@class='text-decoration-none fw-semibold']"));

			for (int j = 0; j < All_Clients_list.size(); j++) {
				String ClientText = All_Clients_list.get(i).getText();
				System.out.println("ClientText :" + ClientText);
				if (ClientText.contains(ClientName)) {
					Library.threadSleep(3000);
					break;
				}

				WebElement GenerateReport = driver.findElement(By.xpath("(//a[text()='Generate Report'])[" + j + "]"));

				if (GenerateReport.isDisplayed()) {
					GenerateReport.click();
					Library.threadSleep(5000);
					break;
				}

			}

			if (x == 1) {
				break;
			}

		}

	}

	public void clickOnSUbmitButton() {
		Library.click(driver, SUBMIT_BUTTON, "clickOnSUbmitButton");
	}

	public void selectCases(int NoOfCases) {
		for (int i = 0; i < NoOfCases; i++) {
			Library.threadSleep(1000);
			Library.scrollByPixelSize(driver, 200);
			driver.findElement(By.xpath("(//input[@type='checkbox'])[" + i + "]")).click();
		}
	}

	public void clickOnViewReportButton() {
		Library.click(driver, VIEW_REPORT_BUTTON, "clickOnViewResultButton");
	}

	public void verifyReportDisplay() {
		Library.threadSleep(10000);
		boolean REPORT_DIPSLAY = Library.waitForVisibilityOf(driver, LIBIL_LOGO).isDisplayed();
		Assert.assertTrue(REPORT_DIPSLAY);
	}
}
