package com.testing;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class LoginTest {
	
	LoginPage lp = new LoginPage();
	DataFile df = new DataFile();
	SoftAssert softAssert = new SoftAssert();
  
	@Test
		public void verifylLoginWithInvalidPassword() throws InterruptedException {
			lp.enterEmail(df.validEmail);
			lp.enterPassword(df.invalidPassword);

			String actualErrMsg = lp.readPasswordErrMsg();
			String expectedErrMsg = "Wrong password. Try again or click Forgot password to reset it.";
		
			System.out.println("Invalid Password Error Message : " + actualErrMsg);
			
			softAssert.assertEquals(actualErrMsg, expectedErrMsg);
			softAssert.assertAll();
	}
		
	@Test
	public void verifylLoginWithInvalidEmail() throws InterruptedException {
		lp.enterEmail(df.invalidEmail);
		
		String actualErrMsg = lp.readEmailErrMsg();
		String expectedErrMsg = "Couldn't find your Google Account";
		
		System.out.println("Invalid Email Error Message : " + actualErrMsg);
		
		softAssert.assertEquals(actualErrMsg, expectedErrMsg);
		softAssert.assertAll();
		
	}
	
	@BeforeMethod
	public void beforeTest() throws Exception {
		lp.openBrowser();
		lp.openGmail();
	}

	@AfterMethod
	public void afterTest() {
	  lp.closeBrowser();
	}

}
