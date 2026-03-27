package com.ohrm.qa.pageobjects;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ohrm.qa.abstractcomponents.AbstractComponents;



public class LoginPage extends AbstractComponents {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver); 
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void goTo() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
	@FindBy(css="div[class='orangehrm-login-form']")
	WebElement loginForm;

	@FindBy(xpath = "//p[text()='Username : Admin']")
	WebElement usernameText;

	@FindBy(xpath = "//p[text()='Password : admin123']")
	WebElement pwdText;
	
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(css="button[type='submit']")
	WebElement loginButton;
	
//	@FindBy(css=".oxd-alert-content-text")
//	WebElement loginError;
	
	private By loginError = By.cssSelector(".oxd-alert-content-text");

	public String getUsername() {
		return usernameText.getText().split(":")[1].trim();

	}
	
	public String getPwd() {
		return pwdText.getText().split(":")[1].trim();
	}
	

	public DashboardPage ValidUserlogin() {
		waitForWebElementToAppear(loginForm);
		username.sendKeys(getUsername());
		password.sendKeys(getPwd());
		loginButton.click();
		return new DashboardPage(driver);
		
	}
	
	public String InvaliduserLogin(HashMap<String, String> userCreds) {
		waitForWebElementToAppear(loginForm);
		username.sendKeys(userCreds.get("username"));
		password.sendKeys(userCreds.get("password"));
		loginButton.click();
		waitForElementToAppear(loginError);
		return driver.findElement(loginError).getText();
	}
	
	
	
}
