package com.ohrm.qa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ohrm.qa.pageobjects.DashboardPage;
import com.ohrm.qa.testcomponents.BaseTest;

public class LoginTest extends BaseTest {

	@BeforeMethod
    public void setup() throws IOException {
        launchApplication();
    }
	@Test
	public void validLoginUser() {
		DashboardPage dashboardPage = loginPage.ValidUserlogin();
		dashboardPage.logout();
	}

	@Test(dataProvider = "getInvalidloginData")
	public void InvalidLoginUser(HashMap<String, String> userCreds) {
		String loginerror = loginPage.InvaliduserLogin(userCreds);
		Assert.assertEquals(loginerror, "Invalid credentials");

	}

	@DataProvider
	public Object[][] getInvalidloginData() throws IOException {

		List<HashMap<String, String>> data = getJSONDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\ohrm\\qa\\data\\InvalidLoginData.json");
		Object[][] dataArray = new Object[data.size()][1];

		for (int i = 0; i < data.size(); i++) {
			dataArray[i][0] = data.get(i);
		}

		return dataArray;
	}
	
	@AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        tearDown(); // Closes browser after EVERY method
    }
}
