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

public class RefreshStepDefinitons {
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
	
	@Given("^the website is loaded$")
	public void given_the_website_is_loaded_for_refresh() throws Exception {
		LOGGER.info("Entering: the website is loaded");
			setUp();
		    driver.get(baseUrl + "/");
			for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if ("refresh".equals(driver.findElement(By.cssSelector("span.text")).getText())) break; } catch (Exception e) {}
		    	Thread.sleep(1000);
		    }
	}
	
	@When("^I press the Refresh button$")
	public void when_I_press_the_Refresh_button(){
		LOGGER.info("Entering: I press the Refresh buttn");
		driver.findElement(By.cssSelector("span.text")).click();
	}
	
	@Then("^the button should register a click and refresh$")
	public void then_refresh() throws Exception{
		LOGGER.info("Entering: the button should register a click and refresh");
		 try {
		      assert(isElementPresent(By.cssSelector("span.pictos.dotdotdot")));
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
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