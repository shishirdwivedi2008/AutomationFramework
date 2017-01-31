package test;

import org.testng.annotations.Test;

import common.Config;
import common.TestBase;
import pageObjects.HomePage;
import pageObjects.HomePage.BookingDate;
import pageObjects.HomePage.Class;

public class TestFlightSearch extends TestBase {
	
	@Test(dataProvider="GetTestConfig")
	public void testFlightSearch(Config testConfig){
		new HomePage(testConfig).searchFlight("BOM", "DEL", BookingDate.Todays, BookingDate.TenDaysAhead, Class.Economy, 4, 0, 0);
	}
	
	@Test(dataProvider="GetTestConfig")
	public void testValidation(Config testConfig){
		new HomePage(testConfig).veriyOneWayAndTwoWayValidation();
		
	}
}
