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

public class TempUnitsStepDefinitons {
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
	
	@Given("^the website is loaded and in Fahrenheit$")
	public void given_the_website_is_loaded_in_f() throws Exception {
		LOGGER.info("Entering: the website is loaded and in Fahrenheit");
			setUp();
			driver.get(baseUrl + "/");
			driver.findElement(By.xpath("//div[@id='location_list']/div[3]/span")).click();
		    driver.findElement(By.cssSelector("span.f")).click();
		    try {
		      assertTrue(isElementPresent(By.cssSelector("span.f.selected")));
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
	}
	
	@When("^I press the Celsius button$")
	public void when_I_press_the_Celsius_button(){
		LOGGER.info("Entering: I press the Celsius buttn");
		 try {
		      assertTrue(isElementPresent(By.cssSelector("span.c")));
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    driver.findElement(By.cssSelector("span.c")).click();
	}
	
	@Then("^the button should register a click and switch the temperature to Celsius$")
	public void then_switch_Celsius() throws Exception{
		LOGGER.info("Entering: the button should register a click and switch the temperature to Celsius");
		   try {
			      assertTrue(isElementPresent(By.cssSelector("span.c.selected")));
			    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }
		   try {
			      assertTrue(isElementPresent(By.cssSelector("span.f")));
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