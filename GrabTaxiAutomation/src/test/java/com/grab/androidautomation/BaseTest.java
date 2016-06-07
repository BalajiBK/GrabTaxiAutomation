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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	WebDriver driver;
	Properties ObjectsXpath;
	public static Logger GrabTaxiLogger = Logger.getLogger("MyLogger");
  @BeforeTest	
  public void Initialise()
  {
	  
	  ObjectsXpath = new Properties();
	   try {
		ObjectsXpath.load(new FileInputStream("ObjectDefinitions.properties"));
		GrabTaxiLogger.log(Level.INFO,"Loading the object definition properties files");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	  DesiredCapabilities capabilities = new DesiredCapabilities();
	  

	  File app = new File("/Applications/android-sdk-macosx/platform-tools/Grab_com.grabtaxi.passenger.apk");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "Nexus 5 API 22");


		capabilities.setCapability("platformName", "android");
		capabilities.setCapability("device ID", "emulator-5554");
		capabilities.setCapability("app",app.getAbsolutePath());
		capabilities.setCapability("appPackage","com.grabtaxi.passenger");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("fullReset", false);
		GrabTaxiLogger.log(Level.INFO,"Loading the desired capabilities to open the installed app in the Grab Taxi");
		try {
			driver = new RemoteWebDriver(new URL(
					"http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GrabTaxiLogger.log(Level.INFO,"Set the implicit wait time to wait for element as 40 seconds");
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
  }
  @AfterTest
  public void TearDown()
  {
	  try {
		Thread.sleep(6000);
		driver.quit();
		GrabTaxiLogger.log(Level.INFO,"Quit the driver");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
