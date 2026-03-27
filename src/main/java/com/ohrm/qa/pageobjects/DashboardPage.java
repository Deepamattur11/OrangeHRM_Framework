package com.ohrm.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import com.ohrm.qa.abstractcomponents.AbstractComponents;

public class DashboardPage extends AbstractComponents {

	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "oxd-text--span")
	public List<WebElement> menus;

	@FindBy(css = "//li[1]//a[1]//span[1]")
	WebElement Admin;

	@FindBy(css = ".oxd-sidepanel-body")
	WebElement sidePanel;

	@FindBy(css = ".oxd-userdropdown-icon")
	WebElement userdropdownIcon;

	@FindBy(xpath = "//a[text()='Logout']")
	WebElement logOutButton;

	public void isVisible() {
		sidePanel.isDisplayed();
	}
	
//	@FindBy(xpath = "//a[@href='/web/index.php/pim/viewPimModule']")
//	WebElement pimMenu;

	public void logout() {
		waitForElementToBeClickable(userdropdownIcon);
		userdropdownIcon.click();
		logOutButton.click();
	}
	
	
}
