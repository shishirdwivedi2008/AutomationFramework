package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Browser;
import common.Config;
import common.Element;
import common.Element.How;

public class HomePage {

	private Config testConfig;
	@FindBy(xpath = "//input[@id='mcFrom0']")
	private WebElement startStation;
	@FindBy(xpath = "//input[@id='mcTo0']")
	private WebElement destination;
	String availStartDate = "(//div[@class='rd-month'])[1]//td[not(contains(@class,'rd-day-disabled')) and not(contains(@class,'rd-day-concealed'))]/div[1]";
	String availendDate = "(//div[@class='rd-month'])[2]//td[not(contains(@class,'rd-day-disabled')) and not(contains(@class,'rd-day-concealed'))]/div[1]";
	private String amountOnStartDate="(//div[@class='rd-month'])[1]//td[not(contains(@class,'rd-day-disabled')) and not(contains(@class,'rd-day-concealed'))]/div[2]";
	private String amountOnEndDate="(//div[@class='rd-month'])[2]//td[not(contains(@class,'rd-day-disabled')) and not(contains(@class,'rd-day-concealed'))]/div[2]";
	@FindBy(xpath = "//div[@id='tripFormBG']")
	private WebElement arrDepBox;
	@FindBy(xpath = "//div[@id='personCounter']")
	private WebElement personcounter;
	@FindBy(xpath = "//span[@data-class-label='economy']")
	private WebElement economy;
	@FindBy(xpath = "//span[@data-class-label='business']")
	private WebElement business;
	@FindBy(xpath = "//span[@data-class-label='premium']")
	private WebElement premium;
	@FindBy(xpath = "//input[@id='mcDepart0']")
	private WebElement startDate;
	@FindBy(xpath = "//input[@id='mcReturn0']")
	private WebElement returnDate;
	@FindBy(xpath = "(//div[@id='actionSearch'])[1]")
	private WebElement searchButton;
	@FindBy(xpath = "//span[contains(text(),'one-way')]")
	private WebElement oneWay;
	@FindBy(xpath = "//span[contains(text(),'round-trip')]")
	private WebElement roundTrip;
	@FindBy(xpath = "//input[@id='searchOta-ytd']")
	private WebElement yatraChkBx;
	@FindBy(xpath = "(//span[contains(@class,'selected')])[1]")
	private WebElement oneWayAndRoundTripSelected;

	private String childCounter = "//button[@data-resultid='childcounter']";
	private String adultCounter = "//button[@data-resultid='adultcounter']";
	private String infantCounter = "//button[@data-resultid='infantcounter']";

	public enum Class {
		Economy, PremiumEconomy, Business
	}

	public enum BookingDate {
		Todays, TwoDaysAhead, FiveDaysAhead, TenDaysAhead
	}

	public HomePage(Config testConfig) {
		this.testConfig = testConfig;
		Browser.openBrowser(testConfig);
		Browser.navigateTo(testConfig.property.getProperty("url"));
		Browser.waitForPageLoad(testConfig, arrDepBox);
		PageFactory.initElements(testConfig.driver, this);
	}

	public SearchPage searchFlight(String currentStn, String arrivalStn, BookingDate depDate, BookingDate arrivalDate,
			Class flyingClass, int adult, int child, int infant) {
		List<WebElement> listOfStartDate = null;
		List<WebElement> ListOfEndDate = null;
		List<WebElement> list = null;
		int requiredClick = 0;
		Element.sendKeys(testConfig, startStation, currentStn);
		Element.pressTab(testConfig, startStation);
		Element.sendKeys(testConfig, destination, arrivalStn);
		Element.pressTab(testConfig, destination);
		Element.click(testConfig, startDate);
		Element.wait(testConfig, 10);
		switch (depDate) {
		case Todays:
			Element.click(testConfig, Element.findAllElements(testConfig, How.XPATH, availStartDate).get(0));
			break;
		case TwoDaysAhead:
			listOfStartDate = Element.findAllElements(testConfig, How.XPATH, availStartDate);
			if (listOfStartDate.size() >= 3) {
				Element.click(testConfig, listOfStartDate.get(3));
			}
			testConfig.logFail("Mentioned Date is not Available");
			;
			break;
		case FiveDaysAhead:
			listOfStartDate = Element.findAllElements(testConfig, How.XPATH, availStartDate);
			if (listOfStartDate.size() >= 6) {
				Element.click(testConfig, listOfStartDate.get(6));
			}
			testConfig.logFail("Mentioned Date is not Available");
			;
			break;
		case TenDaysAhead:
			listOfStartDate = Element.findAllElements(testConfig, How.XPATH, availStartDate);
			if (listOfStartDate.size() >= 11) {
				Element.click(testConfig, listOfStartDate.get(11));
			}
			testConfig.logFail("Mentioned Date is not Available");
			;
			break;

		default:
			break;
		}
		Element.click(testConfig, returnDate);
		switch (arrivalDate) {
		case Todays:
			Element.click(testConfig, Element.findAllElements(testConfig, How.XPATH, availendDate).get(0));
			break;
		case TwoDaysAhead:
			ListOfEndDate = Element.findAllElements(testConfig, How.XPATH, availendDate);
			if (ListOfEndDate.size() >= 3) {
				Element.click(testConfig, ListOfEndDate.get(3));
			}
			testConfig.logFail("Mentioned Date is not Available");
			;
			break;
		case FiveDaysAhead:
			ListOfEndDate = Element.findAllElements(testConfig, How.XPATH, availendDate);
			if (ListOfEndDate.size() >= 6) {
				Element.click(testConfig, ListOfEndDate.get(6));
			}
			testConfig.logFail("Mentioned Date is not Available");
			;
			break;
		case TenDaysAhead:
			ListOfEndDate = Element.findAllElements(testConfig, How.XPATH, availendDate);
			if (ListOfEndDate.size() >= 11) {
				Element.click(testConfig, ListOfEndDate.get(11));
			}
			testConfig.logFail("Mentioned Date is not Available");
			;
			break;

		default:
			break;
		}
		Element.click(testConfig, personcounter);
		switch (flyingClass) {
		case Economy:
			Element.click(testConfig, economy);
			if (adult > 2)
				requiredClick = adult - 2;
			requiredClick = (adult == 2) ? 0 : -1;
			list = Element.findAllElements(testConfig, How.XPATH, adultCounter);
			if (requiredClick < 0) {
				while (requiredClick == 0) {
					Element.click(testConfig, list.get(0));
				}
				requiredClick++;
			} else {
				while (requiredClick == 0) {
					Element.click(testConfig, list.get(1));
					requiredClick--;
				}
			}
			while (child > 0) {
				Element.click(testConfig, Element.findAllElements(testConfig, How.XPATH, childCounter).get(1));
				child--;
			}
			while (infant > 0) {
				Element.click(testConfig, Element.findAllElements(testConfig, How.XPATH, infantCounter).get(1));
				infant--;
			}
			break;
		case PremiumEconomy:
			Element.click(testConfig, premium);
			if (adult > 2)
				requiredClick = adult - 2;
			requiredClick = (adult == 2) ? 0 : -1;
			list = Element.findAllElements(testConfig, How.XPATH, adultCounter);
			if (requiredClick < 0) {
				while (requiredClick == 0) {
					Element.click(testConfig, list.get(0));
				}
				requiredClick++;
			} else {
				while (requiredClick == 0) {
					Element.click(testConfig, list.get(1));
					requiredClick--;
				}
			}
			while (child > 0) {
				Element.click(testConfig, Element.findAllElements(testConfig, How.XPATH, childCounter).get(1));
				child--;
			}
			while (infant > 0) {
				Element.click(testConfig, Element.findAllElements(testConfig, How.XPATH, infantCounter).get(1));
				infant--;
			}
			break;
		case Business:
			Element.click(testConfig, business);
			if (adult > 2)
				requiredClick = adult - 2;
			requiredClick = (adult == 2) ? 0 : -1;
			list = Element.findAllElements(testConfig, How.XPATH, adultCounter);
			if (requiredClick < 0) {
				while (requiredClick == 0) {
					Element.click(testConfig, list.get(0));
				}
				requiredClick++;
			} else {
				while (requiredClick == 0) {
					Element.click(testConfig, list.get(1));
					requiredClick--;
				}
			}
			while (child > 0) {
				Element.click(testConfig, Element.findAllElements(testConfig, How.XPATH, childCounter).get(1));
				child--;
			}
			while (infant > 0) {
				Element.click(testConfig, Element.findAllElements(testConfig, How.XPATH, infantCounter).get(1));
				infant--;
			}
		default:
			break;
		}
		String currentWindowHandle = testConfig.driver.getWindowHandle();
		Element.click(testConfig, searchButton);
		Element.closeNewWindow(testConfig, currentWindowHandle);

		return new SearchPage(testConfig);
	}

	/**
	 * This method will validate validation for one way and round trip searches.
	 * 
	 * @return {@link HomePage}
	 * @author shishir
	 */
	public HomePage veriyOneWayAndTwoWayValidation() {
		List<WebElement> list=null;
		testConfig.assertTrue(roundTrip.isDisplayed(), "RoundTrip is displayed");
		testConfig.assertTrue(oneWay.isDisplayed(), "One Way Booking Option displayed");
		testConfig.assertTrue(oneWayAndRoundTripSelected.isDisplayed(), "Round Trip is by defauly selected");
		Element.click(testConfig, oneWay);
		testConfig.assertTrue(oneWayAndRoundTripSelected.isDisplayed(), "One Way is selected");
		testConfig.assertTrue(startStation.isDisplayed(), "Start station is displayed");
		testConfig.assertTrue(destination.isDisplayed(), "Destination  station is displayed");
		testConfig.assertEquals(Element.getText(testConfig, startStation),
				"Bangalore, India - Kempegowda International airport(BLR)",
				"Default Text of start station must get matched");
		testConfig.assertTrue(Element.findElement(testConfig, How.XPATH, availStartDate).isDisplayed(),
				"travel start date is displayed");
		testConfig.assertTrue(Element.findElement(testConfig, How.XPATH, availendDate).isDisplayed(),
				"travel end date is displayed");
		testConfig.assertTrue(personcounter.isDisplayed(), "Class and Adul,kid,infant counter is displayed");
		testConfig.assertTrue(searchButton.isDisplayed(), "Search button displayed");
		//Verifying amount is displayed or not
		Element.click(testConfig, startDate);
		list=Element.findAllElements(testConfig, How.XPATH, amountOnStartDate);
		testConfig.assertTrue(validateAmount(list), "Amount and Dashes shown for Start Date");
		Element.click(testConfig, destination);
		list=Element.findAllElements(testConfig, How.XPATH, amountOnEndDate);
		testConfig.assertTrue(validateAmount(list), "Amount and Dashes shown for Start Date");
		
		
		return this;
	}
	
	public boolean validateAmount(List<WebElement> list){
		boolean flag=false;
		int i=0;
		while(i<=list.size()){
			if(list.get(i).getText().trim()!=null || list.get(i).getText().trim()=="-"){
				flag=true;
			}else{
				flag=false;
				break;
			}
		}
		return flag;
	}
}
