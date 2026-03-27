package com.ohrm.qa.abstractcomponents;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.Duration;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AbstractComponents {
	WebDriver driver;

	static String filepath = "./src/test/resources/NewUser.json";

	public AbstractComponents(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findby));
	}

	public void waitForWebElementToAppear(WebElement errorMsg) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(errorMsg));
	}

	public void waitForElementToBeClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Centers the element in the viewport to avoid header/footer overlays
		js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);

		// Brief pause to allow any 'lazy loading' to trigger
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickWithJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public static void doScorlling(WebDriver driver, int heightPixel) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + heightPixel + ")");

	}

	public static JSONObject loadJSONFile(String fileLocation) throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(new FileReader(fileLocation));
		JSONObject jsonObject = (JSONObject) obj;

		return jsonObject;
	}

	public static int generateRandomNumber(int min, int max) {

		return (int) Math.round(Math.random() * (max - min) + min);
	}

	public static void addJsonArray(String firstname, String lastname, String username, String password,
			String employeeId) throws IOException, ParseException {

		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(new FileReader(filepath));
		JSONObject userObj = new JSONObject();
		JSONArray jsonArray = (JSONArray) obj;
		userObj.put("firstname", firstname);
		userObj.put("lastname", lastname);
		userObj.put("username", username);
		userObj.put("password", password);
		userObj.put("employeeId", employeeId);

		jsonArray.add(userObj);
		FileWriter file = new FileWriter(filepath);
		file.write(jsonArray.toJSONString());
		file.flush();
		file.close();

	}

	public static JSONObject getLastAddedUser(String filePath) throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		// Read the file and parse it into a JSONArray
		FileReader reader = new FileReader(filepath);
		JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
		reader.close();

		// Grab the very last element in the array to stay in sync
		if (jsonArray.isEmpty()) {
			throw new RuntimeException("JSON file is empty. Run the creation test first!");
		}
		return (JSONObject) jsonArray.get(jsonArray.size() - 1);
	}

}
