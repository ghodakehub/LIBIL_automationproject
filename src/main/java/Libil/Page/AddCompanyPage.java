package Libil.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Libil.Utility.Library;

public class AddCompanyPage extends BasePage {

	public AddCompanyPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[text()='Add Request']")
	private WebElement ADD_REQUEST_BUTTON;

	@FindBy(xpath = "//select[@name='company']")
	private WebElement SELECT_TYPE_DROPDOWN;

	@FindBy(id = "search_name")
	private WebElement COMPANY_TAB;

	@FindBy(id = "comment")
	private WebElement COMMENTS_TAB;

	@FindBy(id = "addRequest")
	private WebElement SUBMIT_BUTTON;

	public void clickOnRequestButton() {
		Library.click(driver, ADD_REQUEST_BUTTON, "Clicked on Add Request Button");
		Library.threadSleep(3000);
	}

	public void selectCompanyType() {
		Library.dropDown(driver, SELECT_TYPE_DROPDOWN, "Company");
		Library.threadSleep(2000);
	}

	public void enterCompanyName(String CompanyName) {

		Library.sendKeys(driver, COMPANY_TAB, "Value sent in Tab", CompanyName);
		Library.threadSleep(3000);
	}

	public void enterComment(String WriteComment) {
		Library.sendKeys(driver, COMMENTS_TAB, "Comment entered", WriteComment);
		Library.threadSleep(1000);
	}

	public void clickOnSubmitButton() {
		Library.click(driver, SUBMIT_BUTTON, "Clicked on submit button");
		Library.threadSleep(3000);
	}
}
