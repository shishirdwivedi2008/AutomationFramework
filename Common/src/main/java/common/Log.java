package common;

import org.testng.Reporter;

public class Log {

	private static Boolean escapeOutput = false;

	public static void Comment(String message, Config testConfig, String color) {
		if (testConfig.logToStandardOut)
			logToStandard(message);
		if (!escapeOutput) {
			message = "<font color='" + color + "'>" + message + "</font></br>";
		}
		Reporter.log(message);
		testConfig.testLog = testConfig.testLog.concat(message);
	}

	public static void Comment(String message, Config testConfig) {
		Comment(message, testConfig, "Black");
	}

	private static void logToStandard(String message) {
		System.out.println(message);
	}

	public static void Pass(String message, Config testConfig) {
		if (testConfig.logToStandardOut)
			logToStandard(message);
		if (!escapeOutput) {
			message = "<font color='Green'>" + message + "</font></br>";
		}
		Reporter.log(message);
		testConfig.testLog = testConfig.testLog.concat(message);
	}

	public static void Warning(String message, Config testConfig) {
		if (testConfig.logToStandardOut)
			logToStandard(message);
		if (!escapeOutput) {
			message = "<font color='Orange'>" + message + "</font></br>";
		}
		Reporter.log(message);
		testConfig.testLog = testConfig.testLog.concat(message);
	}

	public static void Fail(String message, Config testConfig) {
		failure(message, testConfig);
	}

	public static void failure(String message, Config testConfig) {
		String tempMessage = message;
		testConfig.softAssert.fail(message);
		if (testConfig.logToStandardOut)
			logToStandard(message);
		if (!escapeOutput) {
			message = "<font color='Red'>" + message + "</font></br>";
		}
		Reporter.log(message);
		testConfig.testLog = testConfig.testLog.concat(message);
	}
}
