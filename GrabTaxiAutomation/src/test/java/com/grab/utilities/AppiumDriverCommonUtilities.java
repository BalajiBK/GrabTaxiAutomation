package com.grab.utilities;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.SeleniumException;
import com.grab.androidautomation.BaseTest;

public class AppiumDriverCommonUtilities {
	public static void findElementByXpathAndClick(WebDriver driver,String XPATH) 
	{
		
		
		try{
			driver.findElement(By.xpath(XPATH)).click();	
			BaseTest.GrabTaxiLogger.log(Level.INFO,"Clicked on Element with xpath "+XPATH+" was clicked");
		}
		catch(WebDriverException ex)
		{
			BaseTest.GrabTaxiLogger.log(Level.SEVERE, "Exception while clicking on element with xpath "+XPATH);
			ex.printStackTrace();
			throw ex;
		}
		
		
	}
	
	public static void findElementByXpathAndSendKeys(WebDriver driver,String XPATH,String textToSet)
	{
		
		try{
			driver.findElement(By.xpath(XPATH)).clear();
			driver.findElement(By.xpath(XPATH)).sendKeys(textToSet);	
			BaseTest.GrabTaxiLogger.log(Level.INFO,"The text "+textToSet+" was set in the Element with xpath "+XPATH);
		}
		catch(WebDriverException ex)
		{
			BaseTest.GrabTaxiLogger.log(Level.SEVERE, "Exception while findElementByXpathAndSendKeys on element with xpath "+XPATH);
			ex.printStackTrace();
			throw ex;
		}
		
		
	}
	
	public static boolean verifyForTextInPageSource(WebDriver driver,String textToVerify,String PageName)
	{
		boolean Status=false;
		String pagesource;
		try{
			pagesource = driver.getPageSource();
			
			if(pagesource.toLowerCase().contains(textToVerify.toLowerCase()))
			{
				BaseTest.GrabTaxiLogger.log(Level.INFO,"The text "+textToVerify+" was verified in the page "+PageName);
				Status=true;
			}
			else
			{
				BaseTest.GrabTaxiLogger.log(Level.SEVERE,"The text "+textToVerify+" was not verified in the page "+PageName+" the actual page source is "+pagesource);
			}
		}
		catch(WebDriverException ex)
		{
			BaseTest.GrabTaxiLogger.log(Level.SEVERE, "Exception while verifyForTextInPageSource on the page "+PageName);
			ex.printStackTrace();
			throw ex;
		}
		return Status;
	}
	
	public static boolean verifTextInElement(WebDriver driver,String XPATH,String textToVerify)
	{
		String elementText;
		boolean Status=false;
		try{
			elementText = driver.findElement(By.xpath(XPATH)).getAttribute("text");	
			if(elementText.toLowerCase().contains(textToVerify.toLowerCase()))
			{
				BaseTest.GrabTaxiLogger.log(Level.INFO,"The text "+textToVerify+" was verified in the Element with xpath "+XPATH);
				Status=true;
			}
			else
			{
				BaseTest.GrabTaxiLogger.log(Level.SEVERE,"The text "+textToVerify+" was not verified in the Element with xpath "+XPATH+" the actual value is "+elementText);
			}
		}
		catch(WebDriverException ex)
		{
			BaseTest.GrabTaxiLogger.log(Level.SEVERE, "Exception while verifTextInElement on element with xpath "+XPATH);
			ex.printStackTrace();
			throw ex;
		}
		return Status;
	}
	
	public static boolean verifyElementExist(WebDriver driver,String XPATH) throws Exception {
		try {
			driver.findElement(By.xpath(XPATH));
			BaseTest.GrabTaxiLogger.log(Level.INFO,"The Element with xpath "+XPATH+" is displayed");
		} catch (NoSuchElementException e) {
			BaseTest.GrabTaxiLogger.log(Level.SEVERE,"The Element with xpath "+XPATH+" is not displayed "+e.getLocalizedMessage());
			return false;
		}
		catch(WebDriverException ex)
		{
			BaseTest.GrabTaxiLogger.log(Level.SEVERE,"The Element with xpath "+XPATH+" is not displayed "+ex.getLocalizedMessage());
			throw ex;
			
		}
		return true;
	}
	
	public static boolean waitTilltheElementIsClickable(WebDriver driver,String XPATH) throws Exception
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
			BaseTest.GrabTaxiLogger.log(Level.INFO,"The Element with xpath "+XPATH+" is now clickable");
		} catch (NoSuchElementException e) {
			BaseTest.GrabTaxiLogger.log(Level.SEVERE,"The Element with xpath "+XPATH+" is not clickable "+e.getLocalizedMessage());
			throw e;
		}
		catch(WebDriverException ex)
		{
			BaseTest.GrabTaxiLogger.log(Level.SEVERE,"The Element with xpath "+XPATH+" is not clickable "+ex.getLocalizedMessage());
			throw ex;
		}
		return true;	
	}
	
	public static void TakeScreenshot(WebDriver driver,String filename)
	{
		String screenshotpath = System.getProperty("user.dir")+"/test-output/Screenshots/"+filename+".jpg";
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 try {
			FileUtils.copyFile(screenshotFile, new File(screenshotpath));
			BaseTest.GrabTaxiLogger.log(Level.INFO,"The screenshot was taken and is at the below location "+screenshotpath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
