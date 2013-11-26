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

public class ExpandablesStepDefinitons {
	private static final Logger LOGGER = Logger.getLogger(ExpandablesStepDefinitons.class.getName());
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
	
	@Given("^the website is loaded with a location$")
	public void given_the_website_is_loaded_with_location() throws Exception {
		LOGGER.info("Entering: the website is loaded with a location");
			setUp();
		    driver.get(baseUrl + "/");
		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresent(By.cssSelector("span.plus"))) break; } catch (Exception e) {}
		    	Thread.sleep(1000);
		    }
		    try {
		        assertEquals("+", driver.findElement(By.cssSelector("span.more_button.pictos")).getText());
		      } catch (Error e) {
		        verificationErrors.append(e.toString());
		      }
	}
	
	@When("^I expand elements of the website$")
	public void when_I_expand_website_elements(){
		LOGGER.info("Entering: I expand elements of the website");
		 driver.findElement(By.cssSelector("span.plus")).click();
		 driver.findElement(By.cssSelector("span.more_button")).click();
	}
	
	@Then("^more information should be visible$")
	public void then_visible() throws Exception{
		LOGGER.info("Entering: more information should be visible");
		for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.cssSelector("a.more"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }
		try {
		      assertTrue(isElementPresent(By.cssSelector("div.wind > span.label")));
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
	    try {
	      assertEquals("-", driver.findElement(By.cssSelector("span.minus")).getText());
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