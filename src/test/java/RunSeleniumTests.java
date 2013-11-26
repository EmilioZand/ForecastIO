package test.java;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;



		
public class RunSeleniumTests {

	  private static WebDriver driver;
	  private static String baseUrl;
	  private boolean acceptNextAlert;
	  private StringBuffer verificationErrors;
	  
	  @BeforeClass
	  public static void setUp() throws Exception {
	    
	    driver = new FirefoxDriver();
	    baseUrl = "http://forecast.io";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  
	  @AfterClass
	  public static void tearDown() throws Exception {
		  driver.quit();
		  }
	  
	  @Test
	  public void testPrecipMap() throws Exception {
		verificationErrors = new StringBuffer();
		acceptNextAlert = true;
	    driver.get(baseUrl + "/");
	    driver.findElement(By.cssSelector("input.location_field")).clear();
	    driver.findElement(By.cssSelector("input.location_field")).sendKeys("New York, NY");
	    driver.findElement(By.cssSelector("span.label")).click();
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.linkText("Regional"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    driver.findElement(By.linkText("Regional")).click();

	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.linkText("Local"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    driver.findElement(By.linkText("Local")).click();

	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.linkText("Global"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    driver.findElement(By.linkText("Global")).click();

	    driver.findElement(By.cssSelector("div.load_animation_small > span.pictos")).click();
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.cssSelector("div.slider"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    driver.findElement(By.linkText("Regional")).click();
	    
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
	  
	  
	  @Test
	  public void testLocation() throws Exception {
		verificationErrors = new StringBuffer();
		acceptNextAlert = true;
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
	    try {
	    	assertTrue(isElementPresent(By.xpath("//div[@id='location_list']/div[3]/span[2]")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
	  
	  @Test
	  public void testExpandables() throws Exception {
		verificationErrors = new StringBuffer();
		acceptNextAlert = true;
	    driver.get(baseUrl + "/");
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

	    try {
	      assertEquals("-", driver.findElement(By.cssSelector("span.minus")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    driver.findElement(By.cssSelector("span.minus")).click();
	    try {
	      assertEquals("+", driver.findElement(By.cssSelector("span.more_button.pictos")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    driver.findElement(By.cssSelector("span.more_button.pictos")).click();
	    try {
	      assertEquals("-", driver.findElement(By.cssSelector("span.more_button.pictos")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertTrue(isElementPresent(By.cssSelector("div.wind > span.label")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    driver.findElement(By.cssSelector("span.more_button.pictos")).click();
	    try {
	      assertEquals("+", driver.findElement(By.cssSelector("span.more_button.pictos")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
	  
	  @Test
	  public void testRefresh() throws Exception {
		verificationErrors = new StringBuffer();
		acceptNextAlert = true;
	    driver.get(baseUrl + "/");
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if ("refresh".equals(driver.findElement(By.cssSelector("span.text")).getText())) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    driver.findElement(By.cssSelector("span.text")).click();
	    try {
	      assert(isElementPresent(By.cssSelector("span.pictos.dotdotdot")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
	  
	  @Test
	  public void testTempUnits() throws Exception {
		verificationErrors = new StringBuffer();
		acceptNextAlert = true;
	    driver.get(baseUrl + "/");
	    driver.findElement(By.xpath("//div[@id='location_list']/div[3]/span")).click();
	    driver.findElement(By.cssSelector("span.f")).click();
	    try {
	      assertTrue(isElementPresent(By.cssSelector("span.f.selected")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertTrue(isElementPresent(By.cssSelector("span.c")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    driver.findElement(By.cssSelector("span.c")).click();
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
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
	  
	  @Test
	  public void testTimeMachine() throws Exception {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		Date date = new Date();
		String currentDate= dateFormat.format(date).toUpperCase();  
		verificationErrors = new StringBuffer();
		acceptNextAlert = true;
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

	    try {
	      assertEquals(currentDate, driver.findElement(By.cssSelector("div.day_time > span.val")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
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
