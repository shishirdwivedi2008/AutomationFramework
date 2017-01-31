package common;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.http.util.ExceptionUtils;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * This is browser Class to support Browwser Level Configuration
 * 
 * @author shishir
 *
 */
public class Browser {

	private static Config Config;

	/**
	 * Method to open Browser
	 * 
	 * @param testConfig
	 *            Config Object
	 * @return {@link WebDriver}
	 * @author shishir
	 */
	public static WebDriver openBrowser(Config testConfig) {
		String browserName = testConfig.property.getProperty("Browser").toLowerCase();
		switch (browserName) {
		case "firefox":
			FirefoxBinary binary = new FirefoxBinary(new File("/opt/firefox/firefox-sdk/bin/firefox"));
			FirefoxProfile profile = new FirefoxProfile();
			testConfig.driver = new FirefoxDriver(binary, profile);
			testConfig.driver.manage().window().maximize();
			break;
		case "chrome":
			testConfig.driver = new ChromeDriver();

		default:
			Assert.assertTrue(false, "Not able to get The browser");
			break;
		}
		Config = testConfig;
		return testConfig.driver;
	}

	public static void quitBrowser(Config testConfig) {
		try {
			if (testConfig.driver != null) {
				testConfig.logComment("Quit the browser");
				testConfig.driver.quit();
			}
		} catch (UnreachableBrowserException e) {
			testConfig.logComment("Failed To Quit Browser");
		}
	}

	public static void navigateTo(String url) {
		if (url != null) {
			Config.driver.navigate().to(url);
		} else {
			Config.driver.navigate().to(Config.property.getProperty("url").toLowerCase());
		}

	}

	public static void waitForPageLoad(Config testConfig, WebElement element) {
		try {
			waitForPageLoad(testConfig, element, testConfig.property.getProperty("ObjectWaitTime"));
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Waits for the given WebElement to appear on the specified browser
	 * instance
	 * 
	 * @param Config
	 *            test config instance
	 * @param element
	 *            element to be searched
	 * @param ObjectWaitTime
	 *            - max time to wait for the object
	 */
	public static void waitForPageLoad(Config testConfig, WebElement element, String objectWaitTime)
			throws TimeoutException {
		Long ObjectWaitTime = Long.parseLong(objectWaitTime);
		testConfig.driver.manage().timeouts().implicitlyWait(ObjectWaitTime, TimeUnit.SECONDS);

	}
}
