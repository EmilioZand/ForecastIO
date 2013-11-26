package test.java.SeleniumTest;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.thoughtworks.selenium.SeleneseTestBase;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TimeMachine extends TestCase {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private Calendar cal = Calendar.getInstance();
  private DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
  private Date date = new Date();
  private String currentDate= dateFormat.format(date).toUpperCase();
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://forecast.io";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testTimeMachine() throws Exception {
	driver.get(baseUrl + "/#/f/40.7142,-74.0064");
	driver.manage().deleteAllCookies();
	driver.get(baseUrl + "/#/f/40.7142,-74.0064");
    driver.findElement(By.cssSelector("div.time_machine.button > span.label")).click();
    driver.findElement(By.cssSelector("input.time_field")).clear();
    driver.findElement(By.cssSelector("input.time_field")).sendKeys("date");
    driver.findElement(By.cssSelector("div.time_submit.no-select > div.inner")).click();
    try {
      assertEquals("Oops! Invalid date.", driver.findElement(By.cssSelector("div.error")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("input.time_field"))) break;} catch (Exception e) {}
    	driver.findElement(By.cssSelector("div.time_machine.button > span.label")).click();
    	Thread.sleep(1000);
    }
    driver.findElement(By.cssSelector("input.time_field")).clear();
    driver.findElement(By.cssSelector("input.time_field")).sendKeys("1/1/2001");
    driver.findElement(By.cssSelector("div.time_submit.no-select > div.inner")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("div.day_time > span.val"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    try {
      assertEquals("MONDAY, JANUARY 1, 2001", driver.findElement(By.cssSelector("div.day_time > span.val")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//div[@id='time_machine']/div[2]/div/div[5]")).click();
    try {
      assertEquals("Clear", driver.findElement(By.cssSelector("div.summary")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.get(baseUrl + "/#/f/40.7142,-74.0064");
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("span.plus"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.cssSelector("span.plus")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("a.more"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.cssSelector("a.more")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("div.day_time > span.val"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    // ERROR: Caught exception [ERROR: Unsupported command [getEval |  | ]]
    try {
      assertEquals(currentDate, driver.findElement(By.cssSelector("div.day_time > span.val")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
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
