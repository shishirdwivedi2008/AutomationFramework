package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

/**
 * This is configuration class which will contain Configuration of WebDriver and
 * Other Framework initialization
 * 
 * @author shishir
 *
 */
public class Config {

	public WebDriver driver;
	String testEndTime;
	public String testLog;
	public Method testMethod;
	String testName;
	public boolean testResult;
	String testStartTime;
	public SoftAssert softAssert;
	public Properties property;
	public boolean logToStandardOut = true;

	public Config(Method method) {
		this.testMethod = method;
		this.testResult = true;
		this.testLog = "";
		this.softAssert = new SoftAssert();

		try {
			property=new Properties();
			System.out.println(System.getProperty("user.dir"));
			InputStream input=new FileInputStream(new File("/home/shishir/Project/AutomationFramework/Common/Config.properties"));
			property.load(input);
			logToStandardOut = (property.getProperty("logToStandardOut").equals("true")) ? true : false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will return value of key
	 * 
	 * @param key
	 *            key whose value you need to get
	 * @return {@link String}
	 * @author shishir
	 */
	public String getRuntimeProperty(String key) {
		if (key != null)
			return property.getProperty(key).toLowerCase();
		else
			return null;
	}

	public void logComment(String message) {
		Log.Comment(message, this);
	}
	public void logFail(String message)
	{
		testResult = false;
		Log.Fail(message, this);
	}
	/**
	 * Method to Assert Data/Object
	 * @param expected expected boolean value
	 * @param message Message which you want to log
	 */
	public void assertTrue(boolean expected,String message){
		softAssert.assertTrue(expected, message);
		logComment("Value Expected value is "+expected+message);
		
	}
	
	public void assertEquals(String expected, String actual, String message){
		softAssert.assertEquals(actual, expected,message);
		logComment("Expected String:"+expected+"Actual String:"+actual+"Mesage:"+message);
	}
	
}
