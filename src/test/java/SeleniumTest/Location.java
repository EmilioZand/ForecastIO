package test.java.SeleniumTest;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Location extends TestCase{
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

  @Test
  public void testLocation() throws Exception {
    driver.get(baseUrl + "/");
    driver.manage().deleteAllCookies();
    driver.get(baseUrl + "/");
    driver.findElement(By.cssSelector("span.forecast_name")).click();

    driver.findElement(By.cssSelector("input.location_field")).clear();
    driver.findElement(By.cssSelector("input.location_field")).sendKeys("jkladsjkdlsfa;kladsfjkl");
    driver.findElement(By.cssSelector("div.forecast.button")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("div.error"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.cssSelector("div.saved_forecast.editable > span.forecast_name")).click();
    driver.findElement(By.xpath("//div[@id='location_list']/div[2]/span")).click();
    driver.findElement(By.xpath("//div[@id='location_list']/div[3]/span")).click();
    driver.findElement(By.id("edit_locations_button")).click();
    driver.findElement(By.xpath("//div[@id='location_list']/div[3]/span[2]")).click();
    driver.findElement(By.id("edit_locations_button")).click();
    try {
      assertFalse(isElementPresent(By.xpath("//div[@id='location_list']/div[3]/span[2]")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("add_location_button")).click();
    driver.findElement(By.id("add_location_button")).click();
    try {
    	assert(isElementPresent(By.xpath("//div[@id='location_list']/div[3]/span[2]")));
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
