package com.ohrm.qa.tests;



import java.io.IOException;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.ohrm.qa.pageobjects.DashboardPage;
import com.ohrm.qa.pageobjects.PIMPage;
import com.ohrm.qa.testcomponents.BaseTest;
import com.ohrm.qa.abstractcomponents.AbstractComponents;
import com.github.javafaker.Faker;

public class EmployeeTest extends BaseTest{

	DashboardPage dashboardPage;
	 @BeforeTest
	    public void doLogin() throws IOException {
		 launchApplication();
		  dashboardPage=loginPage.ValidUserlogin();
	    }
	 
	 @Test(priority =1,description = "Creating first employee")
	    public void createEmployee1() throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException {
		 PIMPage pimPage=new PIMPage(driver);
	        String username="Deepa"+AbstractComponents.generateRandomNumber(1000,9999);
	        String password="Deeps@1995xyz";
	        String employeeId= "0" +AbstractComponents.generateRandomNumber(100,999);
	        Faker faker=new Faker();
	        String firstname=faker.name().firstName();
	        String lastname=faker.name().lastName();
	        
	        pimPage.addNewEmployee(firstname,lastname,username,password,employeeId);
	        AbstractComponents.addJsonArray(firstname,lastname,username,password,employeeId);

	    }
	 
	 @Test(priority =2,description = "Searching for last created user by EmpId")
	 public void searchNewUserByValidID() throws Exception {
		 PIMPage pimPage=new PIMPage(driver);
	     // 1. Get the last created user dynamically
	     JSONObject lastUser = AbstractComponents.getLastAddedUser("./src/test/resources/NewUser.json");
	     String employeeId = lastUser.get("employeeId").toString();
	     // 4. Validate the result
	     Assert.assertTrue(pimPage.SearchEmployeeByNewId(employeeId));
	     dashboardPage.logout();
	 }
}
