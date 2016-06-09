package com.grab.androidautomation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.grab.utilities.AppiumDriverCommonUtilities;

public class VerifyAddingAndRemovingCreditCard extends BaseTest {
	
	@DataProvider(name="TestDataForTest")
	public Object[][] getDataForTest()
	{
		
	    Object[][] carddetails = new Object[2][3];	
	    carddetails[0][0] = "4111111111111111";
	    carddetails[0][1] = "0132";
	    carddetails[0][2] = "082";
	    
	    carddetails[0][0] = "4111111111112222";
	    carddetails[0][1] = "0132";
	    carddetails[0][2] = "082";
	    return carddetails;
		
	}
	
	
	@Test(dataProvider="TestDataForTest")
	public void AddRemoveAndVerifyCredit(String cardNumber,String cardexpiryDate,String cardCVV) throws Exception
	{
		String DisplayedCardNumber =cardNumber.substring(0,4)+" "+cardNumber.substring(4,8)+" "+cardNumber.substring(8,12)+" "+cardNumber.substring(12,16);
		String DisplayedExpiryDate =cardexpiryDate.substring(0,2)+"/"+cardexpiryDate.substring(2,4);
		
		System.out.println("Navigate to site menu");
		
		AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("SITEMENU_XPATH"));
		AppiumDriverCommonUtilities.TakeScreenshot(driver, "SiteMenuPage");
		AppiumDriverCommonUtilities.waitTilltheElementIsClickable(driver, ObjectsXpath.getProperty("SIDEMENUGRABPAY_XPATH"));
		AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("SIDEMENUGRABPAY_XPATH"));
		
		
		System.out.println("Verify the grab pay page");
		
		AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("ADDALIPAY_BTN_XPATH"));
		AppiumDriverCommonUtilities.TakeScreenshot(driver, "GrabPayPage");
		AppiumDriverCommonUtilities.verifyForTextInPageSource(driver,"Enjoy cashless ride experience and never worry about not having enough cash!","GrabPay Page");
		
		
		System.out.println("Click the Add credit card button Grab Pay page");
		AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("ADDCREDITCARD_BTN_XPATH"));
		
		System.out.println("Verify the add credit card page");
		AppiumDriverCommonUtilities.TakeScreenshot(driver, "CreditCardPage");
		AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("CARDNUMBER_TXT_XPATH"));
		AppiumDriverCommonUtilities.verifyForTextInPageSource(driver, "First card will be your primary card", "Add Credit Card page");
		AppiumDriverCommonUtilities.verifTextInElement(driver, ObjectsXpath.getProperty("CARDTERMS_TXT_XPATH"), "By adding your debit/credit card, you agree to the Terms & conditions");
		
		
		System.out.println("Add the credit card information");
		AppiumDriverCommonUtilities.findElementByXpathAndSendKeys(driver, ObjectsXpath.getProperty("CARDNUMBER_TXT_XPATH"),cardNumber);
		AppiumDriverCommonUtilities.verifTextInElement(driver, ObjectsXpath.getProperty("CARDNUMBER_TXT_XPATH"),DisplayedCardNumber);
		
		AppiumDriverCommonUtilities.findElementByXpathAndSendKeys(driver, ObjectsXpath.getProperty("CARDEXPIRYDATE_TXT_XPATH"),cardexpiryDate);
		AppiumDriverCommonUtilities.verifTextInElement(driver, ObjectsXpath.getProperty("CARDEXPIRYDATE_TXT_XPATH"),DisplayedExpiryDate);
		
		AppiumDriverCommonUtilities.findElementByXpathAndSendKeys(driver, ObjectsXpath.getProperty("CARDCVV_TXT_XPATH"),"076");
		AppiumDriverCommonUtilities.verifTextInElement(driver, ObjectsXpath.getProperty("CARDCVV_TXT_XPATH"),"076");
		
		AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("SAVECARD_BTN_XPATH"));
		
		System.out.println("Verify the add credit card successfull message and image");
		
		AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("ADDCARDSUCCESSFULLIMAGE_XPATH"));
		AppiumDriverCommonUtilities.verifyForTextInPageSource(driver, "Add Card Successful", "Add Credit Card Confirmation page");
		AppiumDriverCommonUtilities.TakeScreenshot(driver, "Credit Card Added Successfull");
		
		
		System.out.println("Navigate back from that page");
		driver.navigate().back();
		
		System.out.println("Navigating to the grab pay page");
		AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("SIDEMENUGRABPAY_XPATH"));
		
		System.out.println("Verify the Grab pay page");
//		AppiumDriverCommonUtilities.TakeScreenshot(driver, "GrabPaypage");
		AppiumDriverCommonUtilities.verifTextInElement(driver, ObjectsXpath.getProperty("CARDNUMHEADER_TXT_XPATH"),"**** "+cardNumber.substring(12,16));
		AppiumDriverCommonUtilities.verifTextInElement(driver, ObjectsXpath.getProperty("CARDPRIMARY_TXT_XPATH"),"Primary");
		AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("CARDTYPE_IMG_XPATH"));
		AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("ADDPAYMENT_BTN_XPATH"));
		AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("CARDNUMHEADER_TXT_XPATH"));
		
		System.out.println("Verify card details and delete");
//		AppiumDriverCommonUtilities.TakeScreenshot(driver, "cardDetails");
		AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("DELETECARD_BTN_XPATH"));
		AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("CARDTYPE_IMG_XPATH"));
		AppiumDriverCommonUtilities.verifTextInElement(driver, ObjectsXpath.getProperty("CARDNUM_TXT_XPATH"),"**** **** **** "+cardNumber.substring(12,16));
		AppiumDriverCommonUtilities.verifTextInElement(driver, ObjectsXpath.getProperty("CARDPRIMARY_TXT_XPATH"),"Primary");
		
		AppiumDriverCommonUtilities.verifyElementExist(driver, ObjectsXpath.getProperty("CARDEXPIRYHEADER_XPATH"));
		AppiumDriverCommonUtilities.verifTextInElement(driver, ObjectsXpath.getProperty("EXPIRYDATE_TXT_XPATH"),cardexpiryDate.substring(0,2)+"/20"+cardexpiryDate.substring(2,4));
		
		AppiumDriverCommonUtilities.verifyForTextInPageSource(driver, "Your primary card", "Card details page");
		AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("DELETECARD_BTN_XPATH"));
		
		System.out.println("Navigating back from grab pay page");
		AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("GRABPAYBACKBTN_XPATH"));
		
		System.out.println("Select the drop off location");
		AppiumDriverCommonUtilities.findElementByXpathAndClick(driver, ObjectsXpath.getProperty("DROPOFF_XPATH"));
		AppiumDriverCommonUtilities.findElementByXpathAndSendKeys(driver, ObjectsXpath.getProperty("DROPOFFTEXT_XPATH"),"Changi Airport Terminal 2");
		
		System.out.println("Verify the Selected the drop off location is reflected");
		AppiumDriverCommonUtilities.verifTextInElement(driver, ObjectsXpath.getProperty("DROPOFF_XPATH"),"Changi Airport Terminal 2");
		
	}
	

}
