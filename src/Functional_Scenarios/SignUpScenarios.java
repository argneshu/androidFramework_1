package Functional_Scenarios;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import App_Functions.OpenHomePage;

import App_Functions.randomNumber;
import BugRegressionSuite.BaseTestBugRegression;
import Page_Objects.AppData;
import Page_Objects.BugRegressionAppConstants;
import Page_Objects.SanitySuiteAppConstants;

public class SignUpScenarios extends BaseTestBugRegression {
	
	@SuppressWarnings("deprecation")
	@Test(enabled= true)
	public void callAndVerifyButtonVerification(){
		try{
		extentInfoLogs("open Home Page");
		OpenHomePage.openHomePage();
		extentInfoLogs("Open Nav drawer");
		clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
		extentInfoLogs("Clicking login icon");
		clickId(SanitySuiteAppConstants.NavDrawer_Login_button_id);
		extentInfoLogs("Click on sign up link");
		clickName(BugRegressionAppConstants.SignUP_name);
		String contactNumber = randomNumber.generate10DigitNumber();
		String user = "SignUp_User_" + contactNumber;
		String email = "SignUp_User_" + contactNumber + "@gmail.com";
		extentInfoLogs("Enter user name : " + user);
		sendKeysForID(SanitySuiteAppConstants.SignUp_FullName_Edit_Id, user);
		extentInfoLogs("Enter Email Id : " + email);
		sendKeysForID(SanitySuiteAppConstants.SignUp_Email_Edit_Id, email);
		extentInfoLogs("Enter Contact no : " + contactNumber);
		sendKeysForID(SanitySuiteAppConstants.SignUp_ContactNo_Edit_Id, contactNumber);
		extentInfoLogs("Enter password : " + AppData.password);
		sendKeysForID(SanitySuiteAppConstants.SignUp_Password_Edit_Id,AppData.password);
		clickId(SanitySuiteAppConstants.SignUp_SignUp_Button_Id);
		clickId(SanitySuiteAppConstants.OTP_PopUp_Submit_Button_Id);
		Assert.assertTrue(findElementById(SanitySuiteAppConstants.OTP_Verify_Button_Id).isDisplayed());
		Assert.assertTrue(findElementByName(SanitySuiteAppConstants.otpCallMeButton_name).isDisplayed());
		}catch(Exception e){
			throw(e);
		}
	}
	
	@Test(enabled= false)
	public void callMeButtonVerify() throws InterruptedException{
		extentInfoLogs("open Home Page");
		OpenHomePage.openHomePage();
		extentInfoLogs("Open Nav drawer");
		clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
		extentInfoLogs("Clicking login icon");
		clickId(SanitySuiteAppConstants.NavDrawer_Login_button_id);
		extentInfoLogs("Click on sign up link");
		clickName(BugRegressionAppConstants.SignUP_name);
		String contactNumber = randomNumber.generate10DigitNumber();
		System.out.println("random number is :" + contactNumber);
		String user = "SignUp_User_" + contactNumber;
		String email = "SignUp_User_" + contactNumber + "@gmail.com";
		extentInfoLogs("Enter user name : " + user);
		sendKeysForID(SanitySuiteAppConstants.SignUp_FullName_Edit_Id, user);
		extentInfoLogs("Enter Email Id : " + email);
		sendKeysForID(SanitySuiteAppConstants.SignUp_Email_Edit_Id, email);
		extentInfoLogs("Enter Contact no : " + contactNumber);
		sendKeysForID(SanitySuiteAppConstants.SignUp_ContactNo_Edit_Id, contactNumber);
		extentInfoLogs("Enter password : " + AppData.password);
		sendKeysForID(SanitySuiteAppConstants.SignUp_Password_Edit_Id,AppData.password);
		clickId(SanitySuiteAppConstants.SignUp_SignUp_Button_Id);
		clickId(SanitySuiteAppConstants.OTP_PopUp_Submit_Button_Id);
		WebElement ele= findElementByName(SanitySuiteAppConstants.otpCallMeButton_name);
		System.out.println("is clickable " +isClickable(ele));
		String result=driver.findElement(By.name(SanitySuiteAppConstants.otpCallMeButton_name)).getAttribute("clickable");
		if(result.equalsIgnoreCase("false")){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		
		Thread.sleep(35000);
		String resultAfterTimeOut=driver.findElement(By.name(SanitySuiteAppConstants.otpCallMeButton_name)).getAttribute("clickable");
		System.out.println(driver.findElement(By.name(SanitySuiteAppConstants.otpCallMeButton_name)).getAttribute("clickable"));
		if(resultAfterTimeOut.equalsIgnoreCase("true")){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		
		//System.out.println(isElementClickable(By.name(SanitySuiteAppConstants.otpCallMeButton_name), driver));
		//System.out.println(driver.findElement(By.name(SanitySuiteAppConstants.otpCallMeButton_name)).isEnabled());
		
	}

	@Override
	@Test(enabled= false)
	public void executeTestCase() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
