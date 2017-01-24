package Functional_Scenarios;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import App_Functions.AddToCart;
import App_Functions.GmailLogin;
import App_Functions.OpenAndroidPage;
import App_Functions.OpenHomePage;
import App_Functions.OpenNavigationDrawer;
import BugRegressionSuite.BaseTestBugRegression;
import Page_Objects.AppData;
import Page_Objects.AppVerificationChecks;
import Page_Objects.BugRegressionAppConstants;
import Page_Objects.SanitySuiteAppConstants;

//Test case info:In this test case we are  verifying multiple cases
//In first test case we are verifying store details on Cart
//In second test case we are verifying cart should get empty on changing location
//In third case 
//Author : Argneshu Gupta

public class CartScenarios extends BaseTestBugRegression {

	@Override
	@Test(enabled = false, priority = 1)
	public void executeTestCase() throws Exception {
		try {
			
			extentInfoLogs("Open Home page");
			OpenHomePage.openHomePage();
			extentInfoLogs("open navigation drawer");
			clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
			info("open navigation drawer");
			extentInfoLogs("click on Mobiles and Tablets link");
			OpenHomePage
					.clickOnCategoryByName(BugRegressionAppConstants.NavDrawer_Mobile_tablets_link);
			info("click on Mobile and tablets link");

			extentInfoLogs("Open Android phone category");
			clickByName(BugRegressionAppConstants.SmartphoneName,
					BugRegressionAppConstants.SmartphoneName);

			extentInfoLogs("click On First Android Product");
			OpenAndroidPage.clickOnFirstAndroidProduct();
			extentInfoLogs("click on Buy Now button ");
			clickXpath(BugRegressionAppConstants.ProductDetailsVarBuyNow_xpath);

			info("clicking on Buy Now button");
			///AddToCart.addToCart();
			extentInfoLogs("verifying assertion for login screen");
			Assert.assertEquals(
					findElementByName(BugRegressionAppConstants.Login_text)
							.getText(), AppVerificationChecks.Logintext);
			info("verifying assertion");
			extentInfoLogs("Login to Gmail");
			GmailLogin.gmailLogin();
			extentInfoLogs("Verifying assertion for store locality");
			boolean storeLocalityDisplayed = findElementById(
					BugRegressionAppConstants.StoreLocality_id).isDisplayed();
			Assert.assertEquals(storeLocalityDisplayed, true);
			boolean storeTitleDisplayed = findElementById(
					BugRegressionAppConstants.StoreTitle_id).isDisplayed();
			extentInfoLogs("Verifying assertion for store title");
			Assert.assertEquals(storeTitleDisplayed, true);

		} catch (Exception e) {
			e.printStackTrace();
			// takeSnapShot("D:\\takeScreenshots",
			// "Store details on Cart Page");
			throw (e);
		}

	}

	@Test(enabled = false, priority = 2)
	public void emptyCartOnChangingLocation() throws Exception {
		try {
			extentInfoLogs("Open Home page");
			OpenHomePage.openHomePage();
			extentInfoLogs("open navigation drawer");
			clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
			info("open navigation drawer");

			extentInfoLogs("click on Mobiles and Tablets link");
			OpenHomePage
					.clickOnCategoryByName(BugRegressionAppConstants.NavDrawer_Mobile_tablets_link);
			info("click on Mobile and tablets link");

			extentInfoLogs("Open Android Phone category");

			clickByName(BugRegressionAppConstants.SmartphoneName,
					BugRegressionAppConstants.SmartphoneName);

			extentInfoLogs("click On First Android Product");
			OpenAndroidPage.clickOnFirstAndroidProduct();
			extentInfoLogs("click on Buy Now button ");
			clickXpath(BugRegressionAppConstants.ProductDetailsVarBuyNow_xpath);

			extentInfoLogs("clicking on Buy Now button");
			//AddToCart.addToCart();
			extentInfoLogs("Login to Gmail");
			GmailLogin.gmailLogin();

			extentInfoLogs("Navigate back");
			backButton();
			extentInfoLogs("Navigate back");
			backButton();
			extentInfoLogs("Navigate back");
			backButton();
			extentInfoLogs("opening navigation drawer");
			Thread.sleep(2000);
			driver.swipe(0, 200, 0, 1000, 3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(BugRegressionAppConstants.OpenNavigationDrawerForMobAndTabPage)));
			Thread.sleep(8000);
			clickClassName(BugRegressionAppConstants.OpenNavigationDrawerForMobAndTabPage);
			extentInfoLogs("clicking on location icon");
			clickId(BugRegressionAppConstants.NavDrawerLocationIcon_id);

			extentInfoLogs("clicking on bangalore");
			clickName(BugRegressionAppConstants.BangaloreLocation_text);

			extentInfoLogs("clicking on BTM location");
			clickName(BugRegressionAppConstants.BTMLocation_text);
			info("clicking on BTM location");
			extentInfoLogs("clicking on cart alert");
			clickName(BugRegressionAppConstants.LocationChangeCartPrompt_name);
			info("clicking on cart alert");
			extentInfoLogs("open nav drawer");
			if (findElementsById(BugRegressionAppConstants.NavDrawerHomeIcon_id)
					.size() > 0) {
				clickId(BugRegressionAppConstants.NavDrawerHomeIcon_id);
			}
			info("clicking on Home icon");

			extentInfoLogs("clicking on cart");
			clickCart();
			info("clicking on cart");
			extentInfoLogs("Verifying assertion for empty cart");
			Assert.assertEquals(
					findElementById(BugRegressionAppConstants.NoItemInCart_id)
							.getText(), AppVerificationChecks.NoItemInCart);

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}


	@Test(enabled = false, priority = 3)
	public void itemVerificationInCart() throws Exception {
		try {
			extentInfoLogs("open Home Page");
			OpenHomePage.openHomePage();
			Thread.sleep(4000);
			clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
			extentInfoLogs("open navigation drawer");
			clickId(SanitySuiteAppConstants.NavDrawer_Login_button_id);
			extentInfoLogs("clicking on Login text on nav drawer");
			GmailLogin.gmailLogin();
			extentInfoLogs("appying gmail functions");
			clickId(BugRegressionAppConstants.Prod_Cart_Id);
			extentInfoLogs("Opening cart");
			while (true) {
				if (findElementsById(
						BugRegressionAppConstants.NoItemInCartLabel_id).size() > 0) {
					clickId(BugRegressionAppConstants.CartGoHome_Id);
					break;
				} else {
					if (findElementsById(
							BugRegressionAppConstants.CartDeleteButton_Id)
							.size() > 0) {
						clickId(BugRegressionAppConstants.CartDeleteButton_Id);
						clickId(BugRegressionAppConstants.OkButton_Id);
					}
				}
			}
			Thread.sleep(4000);
			extentInfoLogs("open navigation drawer");
			clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
			info("open navigation drawer");

			extentInfoLogs("click on Mobiles and Tablets link");
			OpenHomePage.clickOnCategoryByName(BugRegressionAppConstants.NavDrawer_Mobile_tablets_link);
			info("click on Mobile and tablets link");

			extentInfoLogs("Open Android Phone category");
			// clickName(BugRegressionAppConstants.AndroidPhones_name);
			clickByName(BugRegressionAppConstants.SmartphoneName,BugRegressionAppConstants.SmartphoneName);
			extentInfoLogs("click On First Android Product");
			OpenAndroidPage.clickOnFirstAndroidProduct();
			extentInfoLogs("click on Buy Now button ");
			clickXpath(BugRegressionAppConstants.ProductDetailsVarBuyNow_xpath);
			info("clicking on Buy Now button");
			AddToCart.addToCart();
			for(int h=0;h<5;h++)
			{
				extentInfoLogs("click on Increase Item Count");
				clickId(BugRegressionAppConstants.ProductItemCountIncrease_Id);
			}
			String ItemCount=findElementById(BugRegressionAppConstants.ProductItemCount_Id).getText();
			Assert.assertEquals(ItemCount, "5");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw (e);
		}
	}
	@Test(enabled = false, priority = 4)
	public void sortingInTrendingProducts() throws Exception {
		try {
			extentInfoLogs("open Home Page");
			OpenHomePage.openHomePage();
			Thread.sleep(4000);
			clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
			extentInfoLogs("open navigation drawer");
			clickId(SanitySuiteAppConstants.NavDrawer_Login_button_id);
			extentInfoLogs("clicking on Login text on nav drawer");
			GmailLogin.gmailLogin();
			extentInfoLogs("appying gmail functions");
			clickId(BugRegressionAppConstants.Prod_Cart_Id);
			extentInfoLogs("Opening cart");
			while (true) {
				if (findElementsById(
						BugRegressionAppConstants.NoItemInCartLabel_id).size() > 0) {
					clickId(BugRegressionAppConstants.CartTrendingProduct_Id);
					break;
				} else {
					if (findElementsById(
							BugRegressionAppConstants.CartDeleteButton_Id)
							.size() > 0) {
						clickId(BugRegressionAppConstants.CartDeleteButton_Id);
						clickId(BugRegressionAppConstants.OkButton_Id);
					}
				}
			}
//			clickClassName(BugRegressionAppConstants.BackButton_class);
//			extentInfoLogs( "clicking back button");
			Thread.sleep(4000);
			clickId(BugRegressionAppConstants.Seller_More_Seller_Sorting);
			Assert.assertTrue(findElementByName(AppData.SortingText_Alert).isDisplayed());
			List<WebElement> list = findElementsById(BugRegressionAppConstants.Sorting_orders_Id);
			int count =list.size();
			Assert.assertEquals(count, 2);
		}
		catch(Exception e)
		{
			// clickClassName(BugRegressionAppConstants.BackButton_class);
			// extentInfoLogs( "clicking back button");
			e.printStackTrace();
			throw (e);
		}
	}
	
	@Test(enabled = true)
	public static void inGramBundling() throws Exception
	{
		try
		{
			extentInfoLogs("open homepage");
			OpenHomePage.openHomePage();
			OpenNavigationDrawer.openNavigationDrawer();
			clickId(BugRegressionAppConstants.NavDrawer_Login_button_id);
            Assert.assertTrue(findElementById(BugRegressionAppConstants.Login_Screen_Main).isDisplayed());
//            extentInfoLogs("Click on sign up  iconOpen Home page");
//			clickId(BugRegressionAppConstants.SignUpIcon_id);
			extentInfoLogs("Gmail login");
			GmailLogin.gmailLogin();
			extentInfoLogs("Navigate back");
			backButton();
			clickId(BugRegressionAppConstants.Prod_Cart_Id);
			deleteCartItems();
			extentInfoLogs("Navigate back");
			backButton();
			extentInfoLogs("click on mobile & tablets");
			clickName(BugRegressionAppConstants.MobileAndTablets_text);
			extentInfoLogs("click on Smartphones");
			clickName(BugRegressionAppConstants.SmartphoneName);
			addProductMoreThan10k();
			extentInfoLogs("clicking on checkout button");
			clickId(BugRegressionAppConstants.ProductionCheckoutButton_id);

			extentInfoLogs("Checking for address fields");
			if (findElementsById(SanitySuiteAppConstants.Shipping_FullName_Id)
					.size() > 0) {
				extentInfoLogs("Enter user name in shipping address");
				sendKeysForID(SanitySuiteAppConstants.Shipping_FullName_Id,
						AppData.Shipping_UserName);
				extentInfoLogs("Enter address in shipping address");
				sendKeysForID(SanitySuiteAppConstants.Shipping_Address_Id,
						AppData.ShippingAddress);
				extentInfoLogs("Enter Pincode in shipping address");
				sendKeysForID(SanitySuiteAppConstants.Shipping_Pincode_Id,
						AppData.ShippingPincode);
				extentInfoLogs("Click on save button");
				clickId(SanitySuiteAppConstants.Shipping_SaveButton_Id);
			}

			extentInfoLogs("clicking on continue button");
			clickId(BugRegressionAppConstants.ProductionContinueButton_id);
			extentInfoLogs("clicking on Bajaj Finserv payment");
			clickOnCategoryByName(BugRegressionAppConstants.AllPaymentMode_id,
					BugRegressionAppConstants.BajajFinservCard_name);
			List<WebElement> BFLRules = findElementsById(BugRegressionAppConstants.BajajRulesId);
			for(WebElement BFLRule: BFLRules)
			{
				String IngramWarranty = BFLRule.findElement(By.id(BugRegressionAppConstants.BFLBundlingId)).getText();
				if(IngramWarranty.contains("Ingram"))
				{
					BFLRule.click();
					break;
				}
			}
			clickId(BugRegressionAppConstants.BFLNextId);
			for(int j=0;j<4;j++)
			{
				backButton();
			}
			List<WebElement> cartProductNames = findElementsById(BugRegressionAppConstants.CartProductNameId);
			for(WebElement cartProductName: cartProductNames)
			{
				if(cartProductName.getText().contains("Ingram"))
				{
					Assert.assertTrue(true);
					break;
				}
				else
				{
					continue;
				}
			}
			
		}
		catch(Exception e)
		{
			throw(e);
		}
	}
	
	public static void addProductMoreThan10k()
	{
		for(int i=0 ; i<3 ; i++)
		{
			List<WebElement> productPrices = findElementsById(BugRegressionAppConstants.productListingPriceId);
			for (WebElement product : productPrices)
			{
				String priceString = product.getText();
				int priceInt = priceStringtoInteger(priceString);
				if(priceInt > 10000)
				{
					extentInfoLogs("clicking on product of price more than 10000");
					product.click();
					Assert.assertTrue(findElementById(BugRegressionAppConstants.ProductPrice_id).isDisplayed());
					String productPricePD = findElementById(BugRegressionAppConstants.ProductPrice_id).getText();
					int productPricePDInt = priceStringtoInteger(productPricePD);
					if(productPricePDInt > 10000)
					{
						clickId(BugRegressionAppConstants.AddToCartButton_id);
						break;
					}
					else
					{
						backButton();
					}
				}
				break;
			}
		}
	}
}
