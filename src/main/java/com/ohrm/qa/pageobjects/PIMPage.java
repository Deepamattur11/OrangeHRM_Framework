package com.ohrm.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ohrm.qa.abstractcomponents.AbstractComponents;

public class PIMPage extends AbstractComponents  {

	WebDriver driver;

	public PIMPage(WebDriver driver) {
		super(driver); 
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='Add Employee']")
	WebElement AddEmployeeButton;
	
	@FindBy(name="firstName")
	WebElement firstName;
	
	@FindBy(name="lastName")
	WebElement lastName;
	
	@FindBy(css="button[type='submit']")
	WebElement SaveBtn;
	
	@FindBy(xpath="//button[normalize-space()='Search']")
	WebElement searchBtn;
	
	@FindBy(xpath="//a[normalize-space()='Employee List']")
	WebElement employeeList;
	
	 @FindBy(className = "oxd-switch-input--active")
	    public
	    WebElement toggleBtn;
	
	@FindBy(xpath="(//input[@class='oxd-input oxd-input--active'])[3]")
	WebElement userName;
	
	@FindBy(xpath="(//input[@type='password'])[1]")
	WebElement pwd;
	
	@FindBy(xpath="(//input[@type='password'])[2]")
	WebElement confirmPwd;
	
	@FindBy(xpath="//label[text()='Employee Id']/parent::div/following-sibling::div/input")
	WebElement empId;
	
	@FindBy(xpath="//span[contains(@class, 'oxd-text')][contains(., 'Record Found')]")
	WebElement RecordFoundText;
	
	@FindBy(xpath="//a[@href='/web/index.php/pim/viewPimModule']")
	WebElement pimMenu;
	
	private By personalDetails = By.xpath("//h6[text()='Personal Details']");
	
	private By employeeForm = By.className("orangehrm-employee-form");
	
	@FindBy(xpath="(//button[@type='submit'][normalize-space()='Save'])[2]")
	WebElement saveBtn2;
	
	@FindBy(xpath="(//button[@type='submit'][normalize-space()='Save'])[1]")
	WebElement saveBtn1;
	
	
	
	public void addNewEmployee(String firstname, String lastname, String username, String password, String employeeId) throws InterruptedException {
		Thread.sleep(5000);
		pimMenu.click();
		AddEmployeeButton.click();
		waitForElementToAppear(employeeForm);
		firstName.sendKeys(firstname); 
		lastName.sendKeys(lastname);
		waitForElementToDisappear(driver.findElement(By.className("oxd-form-loader")));
		empId.sendKeys(Keys.CONTROL + "a");
		empId.sendKeys(employeeId);
		waitForElementToBeClickable(toggleBtn);
		toggleBtn.click();
		userName.sendKeys(username);
		pwd.sendKeys(password);
		confirmPwd.sendKeys(password);
		SaveBtn.click();
		waitForElementToAppear(personalDetails);
		scrollToElement(saveBtn1);  // Keep the scroll to ensure it's in view
		//doScorlling(driver, 200);
		clickWithJS(saveBtn1);  // Bypass the interception
		clickWithJS(saveBtn2);
	}
	

	 public boolean SearchEmployeeByNewId(String userEmpId) throws InterruptedException {
		 employeeList.click();
		 empId.sendKeys(userEmpId);
        waitForElementToBeClickable(searchBtn);
        searchBtn.click();
        try {
            // Call your existing generic void method
            waitForWebElementToAppear(RecordFoundText); 
            // If the line above doesn't throw a TimeoutException, return true
            return RecordFoundText.isDisplayed(); 
        } catch (Exception e) {
            // If the wait fails or times out, return false
            return false;
        }
    }
	
	
	
	
	
	
	
}
