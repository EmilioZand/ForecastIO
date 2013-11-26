package test.java;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.logging.Logger;

import cucumber.api.java.en.*;

public class TimeMachineStepDefinitons {
	private static final Logger LOGGER = Logger.getLogger(RefreshStepDefinitons.class.getName());
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "http://forecast.io";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	
	@Given("^the website is loaded and TimeMachine is clicked$")
	public void given_the_website_is_loaded_timemachine() throws Exception {
		LOGGER.info("Entering: the website is loaded and TimeMachine is clicked");
			setUp();
			driver.get(baseUrl + "/");
			  try {
			      assertTrue(isElementPresent(By.cssSelector("div.time_machine.button > span.label")));
			    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }
			  driver.findElement(By.cssSelector("div.time_machine.button > span.label")).click();
			  for (int second = 0;; second++) {
			    	if (second >= 60) fail("timeout");
			    	try { if (isElementPresent(By.cssSelector("input.time_field"))) break;} catch (Exception e) {}
			    	driver.findElement(By.cssSelector("div.time_machine.button > span.label")).click();
			    	Thread.sleep(1000);
			    }
		   
	}
	
	@When("^I enter (.+) into the date input box")
	public void when_I_enter_into_date_input(String input) throws Exception{
		LOGGER.info("Entering: I enter " + input + " into the date input box");
		 
	    driver.findElement(By.cssSelector("input.time_field")).clear();
	    if(input=="blank")
	    	input="";
	    driver.findElement(By.cssSelector("input.time_field")).sendKeys(input);
	    driver.findElement(By.cssSelector("div.time_submit.no-select > div.inner")).click();
	    
	}
	
	@Then("^I should see that the TimeMachine (.+) worked$")
	public void then_switch_Celsius(String result) throws Exception{
		LOGGER.info("Entering: I should see that the TimeMachine "+ result + " worked");
		if(result == "successfully")
		{
			  for (int second = 0;; second++) {
			    	if (second >= 60) fail("timeout");
			    	try { if (driver.findElement(By.cssSelector("div.day_time > span.val")).getText()=="MONDY, JANUARY 1, 2001") break; } catch (Exception e) {}
			    	Thread.sleep(1000);
			    }
			    try {
			      assertEquals("MONDY, JANUARY 1, 2001", driver.findElement(By.cssSelector("div.day_time > span.val")).getText());
			    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }
		} else if(result == "unsuccessfully"){
			 try {
			      assertEquals("Oops! Invalid date.", driver.findElement(By.cssSelector("div.error")).getText());
			    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }
		}
		
		 tearDown();
	}
	 @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	}

}