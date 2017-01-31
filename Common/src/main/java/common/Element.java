package common;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.lift.TestContext;

/**
 * Wrapper class for organic method of Selenium to Support Logging as well
 * 
 * @author shishir
 *
 */
public class Element {

	private static Config config;

	public enum How {
		CSS, XPATH, ID
	}

	public static void sendKeys(Config testConfig, WebElement element, String data) {
		config = testConfig;
		element.clear();
		element.sendKeys(data);
		config.logComment("Enetring Data to text Box" + data);
	}

	public static void click(Config testConfig, WebElement element) {
		config = testConfig;
		try {
			element.click();
		} catch (ElementNotVisibleException e) {
			testConfig.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			element.click();
		}

	}

	/**
	 * This method will find All the webelement which is associated with Parent
	 * Node
	 * 
	 * @param testConfig
	 * @param how
	 *            Strategy to find Webelement
	 * @param xpath
	 *            xpath, id, css according to strategy
	 * @return {@link List}
	 * @author shishir
	 */
	public static List<WebElement> findAllElements(Config testConfig, How how, String xpath) {
		List<WebElement> listOfWebElement = null;
		config = testConfig;
		switch (how) {
		case XPATH:
			listOfWebElement = config.driver.findElements(By.xpath(xpath));
			break;

		default:
			break;
		}
		if (listOfWebElement.size() == 0) {
			config.logComment("Web elements found. Total element present is " + listOfWebElement.size());
		}
		return listOfWebElement;
	}
	/**
	 * This method will 
	 * @param testConfig
	 * @param currentWindowHandle
	 */
	public static void closeNewWindow(Config testConfig, String currentWindowHandle) {
		for(String window:testConfig.driver.getWindowHandles()){
		testConfig.driver.switchTo().window(window).close();
		}
		testConfig.driver.switchTo().window(currentWindowHandle);

	}
	
	public static void pressTab(Config testConfig,WebElement element){
		element.sendKeys(Keys.TAB);
		
	}
	/**
	 * Generic method to wait.Metod waits for Seconds hence for minute multiply value by 60
	 * @param testConfig
	 * @param time
	 */
	public static void wait(Config testConfig,int time){
		testConfig.driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		testConfig.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	/**
	 * Method to Return Text
	 * @param testConfig Object of Config Class
	 * @param element {@link WebElement}
	 * @return {@link String}
	 * @author shishir
	 */
	public static String getText(Config testConfig, WebElement element){
		config=testConfig;
		return element.getText().trim();
	}
	
	public static WebElement findElement(Config testConfig, How how, String element){
		WebElement ele=null;
		config=testConfig;
		switch (how) {
		case XPATH:
			ele=config.driver.findElement(By.xpath(element));
			break;

		default:
			break;
		}
		return ele;
		
	}
}
