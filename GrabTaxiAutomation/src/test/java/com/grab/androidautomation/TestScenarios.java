package com.grab.androidautomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.grab.utilities.AppiumDriverCommonUtilities;

public class TestScenarios extends BaseTest {
	
  @Test(priority=1)
  public void EditProfileNameAndSave() throws Exception {
	  System.out.println("Started test "+"EditProfileNameAndSave");
	  //Click on the side menu on the top
	  AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("SITEMENU_XPATH"));
	  System.out.println("Modify the profile name and click Save button");
	  AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("EDITPROFILENAMEIMAGE_XPATH"));
	  AppiumDriverCommonUtilities.findElementByXpathAndSendKeys(driver, ObjectsXpath.getProperty("EDITPROFILENAMETEXTFIELD_XPATH"), "testName");
	  AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("EDITPROFILESAVEBTN_XPATH"));
	  System.out.println("Verify the profile activation page");
	  AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("EDITPROFILEACTIVATION_XPATH"));
	  AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("EDITPROFILEACTIVATION_XPATH"));
	  System.out.println("Naviagte back from activation page by clicking on back arrow");
	  AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("ACTIVATIONBACKBTN_XPATH"));
	  System.out.println("Verify the edited text remains in the same text box");
	  AppiumDriverCommonUtilities.verifTextInElement(driver, ObjectsXpath.getProperty("EDITPROFILENAMETEXTFIELD_XPATH"), "testName");
	  System.out.println("Verify the profile Image");
	  AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("PROFILEIMAGE_XPATH"));
	  AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("EDITPROFILEBACKBTN_XPATH"));
	  
  }
  
  @Test(priority=2)
  public void VerifySideMenuOptions() throws Exception
  {
	
	  System.out.println("Started test "+"VerifySideMenuOptions");
	  AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("SITEMENU_XPATH"));
	  System.out.println("Verify all the side menu History,Favourites,Scheduled,Notifications,Support,Drive with Grab");
	  AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("SIDEMENUHISTORY_XPATH"));
	  AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("SIDEMENUFavourites_XPATH"));
	  AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("SIDEMENUScheduled_XPATH"));
	  AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("SIDEMENUNotifications_XPATH"));
	  AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("SIDEMENUSupport_XPATH"));
	  AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("SIDEMENUDrivewithgrab_XPATH"));
	  System.out.println("Navigate back from the side menu");
	  driver.navigate().back();
  }
  
  @Test(priority=3)
  public void VerifyHistoryPage() throws Exception
  {
	
	  System.out.println("Started test "+"VerifyHistoryPage");
	  AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("SITEMENU_XPATH"));
	  AppiumDriverCommonUtilities.waitTilltheElementIsClickable(driver, ObjectsXpath.getProperty("SIDEMENUHISTORY_XPATH"));
	  AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("SIDEMENUHISTORY_XPATH"));
	  System.out.println("Verify there is no message in history page");
	  AppiumDriverCommonUtilities.verifTextInElement(driver, ObjectsXpath.getProperty("MSGINHISTORYPAGE_XPATH"), "No bookings yet.");
	  AppiumDriverCommonUtilities.verifTextInElement(driver, ObjectsXpath.getProperty("MSGINHISTORYPAGE_XPATH"), "Make one today!");
	  AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("HISTORYBACKBTN_XPATH"));
	  
  }
  
  @Test(priority=4)
  public void VerifyDriveWithGrabBrowser() throws Exception
  {
	  WebDriverWait wait=new WebDriverWait(driver, 30);
	  System.out.println("Started test "+"VerifyDriveWithGrabBrowser");
	  AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("SITEMENU_XPATH"));
	  AppiumDriverCommonUtilities.waitTilltheElementIsClickable(driver, ObjectsXpath.getProperty("SIDEMENUDrivewithgrab_XPATH"));
	  AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("SIDEMENUDrivewithgrab_XPATH"));
	  System.out.println("Verify the url to which it navigates");
	  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(ObjectsXpath.getProperty("BROWSERURL_XPATH"))));
	  boolean status=  AppiumDriverCommonUtilities.verifTextInElement(driver, ObjectsXpath.getProperty("BROWSERURL_XPATH"), "www.grab.com/sg/driver/");

	  Assert.assertTrue(status, "Grab the browser is navigating to the right page");
	  System.out.println("Verify the sign up now button exists on browser");
	  AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("BROWSERSIGNUPNOW_BTN"));
	  
  }
  
  
}
