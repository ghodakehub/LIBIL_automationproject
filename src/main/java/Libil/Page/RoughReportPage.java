package Libil.Page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Libil.Utility.Library;

public class RoughReportPage extends BasePage{

	public RoughReportPage (WebDriver driver ) {
		super(driver);
	}
	
	@FindBy(id = "searchInput")
	private WebElement SEARCH_BAR;
	
	@FindBy(xpath = "//button[@class='btn btn-link']")
	private WebElement THREE_DOTS;
	
	@FindBy(xpath = "//a[@class='dropdown-item']")
	private WebElement GENERATE_REPORT;
	
	
	public void enterSearchValue(String SearchValue) {
		Library.sendKeys(driver, SEARCH_BAR, "Search value entered", SearchValue);
		//Library.threadSleep(3000);
		
		List<WebElement> elements = driver.findElements(By.xpath("tr"));
		String searchQuery = SearchValue;
		for(WebElement element : elements) {
			String elementTxt = element.getText();
			
			if(elementTxt.equals(searchQuery)) {
				System.out.println("Element found");
			}
		}

	}
	
	public void clickOnThreeDots() {
		Library.scrollLeftToRight(driver, 500);
		Library.click(driver, GENERATE_REPORT, "Clicked on 3 dots for report generartion.");
		Library.threadSleep(3000);
		
	}
	
	
	
}
