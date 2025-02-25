package Libil.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Libil.Utility.Library;

public class AddIndividualPage extends BasePage {

	public AddIndividualPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[text()='Add Request']")
	private WebElement ADD_REQUEST_BUTTON;

	@FindBy(xpath = "//select[@name='company']")
	private WebElement SELECT_TYPE_DROPDOWN;

	@FindBy(id = "search_name")
	private WebElement NAME_TAB;

	@FindBy(id = "individual_email")
	private WebElement EMAIL_TAB;

	@FindBy(id = "individual_phone")
	private WebElement PHONE_TAB;

	@FindBy(id = "individual_address")
	private WebElement ADDRESS_TAB;

	@FindBy(id = "individual_district")
	private WebElement DISTRICT_TAB;

	@FindBy(id = "individual_state")
	private WebElement STATE_DROPDOWN;

	@FindBy(id = "individual_pincode")
	private WebElement PINCODE_TAB;

	@FindBy(id = "addRequest")
	private WebElement SUBMIT_BUTTON;

	@FindBy(xpath = "(//button[@type='button'])[3]")
	private WebElement CLOSE_BUTTON;

	@FindBy(id = "navbarDropdownMenuLink")
	private WebElement PROFILE;

	@FindBy(xpath = "//a[@class='dropdown-item']")
	private WebElement LOGOUT;

	//
//Methods now	
	//

	public void clickOnRequestButton() {
		Library.click(driver, ADD_REQUEST_BUTTON, "Clicked on Add Request Button");
		Library.seleniumWait(20, driver);
	}

	public void selectType() {
		Library.seleniumWait(20, driver);
		Library.dropDown(driver, SELECT_TYPE_DROPDOWN, "Individual");
		System.out.println("Individual selected");
		       }

	public void enterName(String IndividualName) {
		Library.sendKeys(driver, NAME_TAB, "Value entered in NameTab", IndividualName);
	}

	public void enterEmail(String EmailId) {
		Library.sendKeys(driver, EMAIL_TAB, "Email entered ", EmailId);
		Library.seleniumWait(20, driver);
	}

	public void enterPhoneNo(String Number) {
		Library.sendKeys(driver, PHONE_TAB, "Phone Number entered", Number);
		Library.seleniumWait(20, driver);
	}

	public void enterAddress(String Address) {
		Library.sendKeys(driver, ADDRESS_TAB, "Address entered", Address);
		Library.seleniumWait(20, driver);

	}

	public void enterDistrict(String District) {
		Library.sendKeys(driver, DISTRICT_TAB, "District entered", District);
		Library.seleniumWait(20, driver);
	}

	public void selectState(String State) {
		Library.dropDown(driver, STATE_DROPDOWN, State);
		Library.seleniumWait(20, driver);

	}

	public void entePincode(String Pincode) {
		Library.sendKeys(driver, PINCODE_TAB, "Pincode entered", Pincode);
	}

	public void clickOnSubmitButton() {
		Library.click(driver, SUBMIT_BUTTON, "Clicked on submit button");
		Library.seleniumWait(20, driver);
	}

	public void clickOnCloseButton() {
		Library.click(driver, CLOSE_BUTTON, "Clicked on close button");
		Library.seleniumWait(20, driver);
	}

	public void clickOnProfile() {
		Library.click(driver, PROFILE, "Clicked on profile");
		Library.seleniumWait(20, driver);
	}

	public void clickOnLogOut() {
		Library.click(driver, LOGOUT, "Clicked on LogOut. TestCase Passed");
		Library.threadSleep(3000);
	}
}
