package Functional_Scenarios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.print.attribute.standard.Fidelity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

import App_Functions.AddToCart;
import App_Functions.ChangeZopperPassword;
import App_Functions.GmailLogin;
import App_Functions.OpenAndroidPage;
import App_Functions.OpenHomePage;
import App_Functions.OpenNavigationDrawer;
import BugRegressionSuite.BaseTestBugRegression;
import Page_Objects.AppData;
import Page_Objects.AppVerificationChecks;
import Page_Objects.BugRegressionAppConstants;
//Test case info:In this test case we are  verifying placing order scenarios
// In first test case we are verifying placing order scenario
import Page_Objects.SanitySuiteAppConstants;
import Utility.ParamObject;
import Utility.RestClient;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class PlacingOrderScenarios extends BaseTestBugRegression {
	String price = null;
	int intprice = 0;
	String verifyOrderPageOrderID;

	public void clickBuyNowBySeller_MoreSeller(String sellerName) {
		// String buildType = ".staging";
		try {
			WebElement parentLayout = driver.findElement(By.id("com.zopperapp"
					+ BugRegressionAppConstants.buildType + ":id/rv_stores"));
			List<WebElement> linearLayout = parentLayout.findElements(By
					.className("android.widget.LinearLayout"));
			for (WebElement ele : linearLayout) {
				String StoreName = ele.findElement(
						By.id("com.zopperapp"
								+ BugRegressionAppConstants.buildType
								+ ":id/tv_item_product_store_title")).getText();
				System.out.println(StoreName);
				if (StoreName.trim().toLowerCase()
						.equals(sellerName.trim().toLowerCase())) {
					List<WebElement> priceStoreList = driver
							.findElementsById("com.zopperapp.staging:id/price_store_parent");
					for (WebElement buy : priceStoreList) {
						System.out.println(buy.getText());
						ele.findElement(
								By.id("com.zopperapp"
										+ BugRegressionAppConstants.buildType
										+ ":id/tv_item_buy")).click();
					}
					int var = 0;
					while (var < 20) {
						if (ele.findElements(
								By.id("com.zopperapp"
										+ BugRegressionAppConstants.buildType
										+ ":id/tv_item_buy")).size() > 0) {
							ele.findElement(
									By.id("com.zopperapp"
											+ BugRegressionAppConstants.buildType
											+ ":id/tv_item_buy")).click();
							break;
						}
						var++;
					}
					break;
				}
			}

		} catch (Exception e) {
		}
	}

	public void moreSeller() throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			int sellerCount = findElementsById(
					BugRegressionAppConstants.More_Sellers_Store_Name_Id)
					.size();
			System.out.println(sellerCount);
			for (int j = 0; j < sellerCount; j++) {
				String storeName = findElementsById(
						BugRegressionAppConstants.More_Sellers_Store_Name_Id)
						.get(j).getText();
				System.out.println(storeName);
				System.out.println("the value of j counter" + j);
				if (storeName.trim().equals(AppData.Priyank_Store_Text)) {
					// try {
					// swipeVertically(1000);
					Thread.sleep(5000);
					findElementsById(
							BugRegressionAppConstants.MoreSellersBuyNow_id)
							.get(j).click();
					break;
				} else {
					swipeVertically_FullPage();
					swipeVertically_FullPage();
					swipeVertically_FullPage();
				}

			}
		}
	}

	public void pdPageSwipe() throws InterruptedException {
		Thread.sleep(5000);
		for (int k = 0; k < 3; k++) {
			swipeVertically_FullPage();
		}
		extentInfoLogs("Get product name");
		String strName = findElementById(
				BugRegressionAppConstants.Product_Store_Name_id).getText();
		if (!strName.trim().equals(AppData.Priyank_Store_Text)) {
			extentInfoLogs("Click on view more sellers link");

			clickId(BugRegressionAppConstants.View_More_Sellers_Id);
			for (int j = 0; j < 2; j++) {
				if (findElementsById(
						BugRegressionAppConstants.View_More_Sellers_Id).size() > 0)
					clickId(BugRegressionAppConstants.View_More_Sellers_Id);
				else
					break;
			}
		} else {
			extentInfoLogs("Click on buy now button");
			clickXpath(BugRegressionAppConstants.ProductDetailsVarBuyNow_xpath);
			AddToCart.addToCart();
		}
	}

	@Override
	@Test(enabled = true, invocationCount = 1)
	public void executeTestCase() throws Exception {
		try {

			extentInfoLogs("Open Home page");
			OpenHomePage.openHomePage();

			extentInfoLogs("Select location");
			selectCity("Delhi", "");
			extentInfoLogs("Open Navigation drawer");
			OpenNavigationDrawer.openNavigationDrawer();
			extentInfoLogs("Click on sign up  iconOpen Home page");
			clickId(BugRegressionAppConstants.SignUpIcon_id);
			extentInfoLogs("Gmail login");
			GmailLogin.gmailLogin();
			extentInfoLogs("Navigate back");
			backButton();
			extentInfoLogs("Click on cart icon");
			clickId(BugRegressionAppConstants.Prod_Cart_Id);
			extentInfoLogs("Delete all products from cart");
			deleteCartItems();
			extentInfoLogs("Navigate back");
			backButton();
			extentInfoLogs("Click on home search text box");
			clickId(BugRegressionAppConstants.Home_Search_TextBox_Id);
			extentInfoLogs("Enter product name in search field");
			sendKeysForID(BugRegressionAppConstants.Home_SearchEditBox_id,
					AppData.Priyank_Store_SearchProduct_Name);
			extentInfoLogs("Select product from auto suggestion list");
			OpenAndroidPage.clickOnCategoryByName(
					BugRegressionAppConstants.AutoSuggestSearch_id,
					AppData.Priyank_Store_Product_Name);

			Thread.sleep(5000);
			extentInfoLogs("Scroll to view store link");
			// driver.scrollTo(AppData.View_Store_Text);
			for (int k = 0; k < 3; k++) {
				swipeVertically_FullPage();
			}

			findPriyankAndBuy();

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
			extentInfoLogs("clicking on cash on delivery button");
			clickOnCategoryByName(BugRegressionAppConstants.AllPaymentMode_id,
					BugRegressionAppConstants.CashOnDelievery_name);
			extentInfoLogs("clicking on place order button");
			clickId(BugRegressionAppConstants.ProductionPlaceOrder_id);
			extentInfoLogs("Getting order id after order");
			String verifyOrderPageOrderID = findElementById(
					BugRegressionAppConstants.VerifyOrderPageOrderId_id)
					.getText();
			extentInfoLogs("clicking on shop more button");
			clickId(BugRegressionAppConstants.ProductionShopMore_id);
			extentInfoLogs("Assert for home screen");
			extentInfoLogs("open nav drawer");
			clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
			extentInfoLogs("Clicking on user profile");
			clickId(BugRegressionAppConstants.SignUpIcon_id);
			extentInfoLogs("clicking on My orderlink option");
			clickId(BugRegressionAppConstants.MyProfileOrderLink_id);
			extentInfoLogs("Getting ordr id from my orders page");
			String myOrderPageOrderID = findElementById(
					BugRegressionAppConstants.OrderID_id).getText();
			extentInfoLogs("Assert for order ids");
			Assert.assertEquals(verifyOrderPageOrderID, myOrderPageOrderID);
			clickIDByIndex(SanitySuiteAppConstants.ViewOrder_id, 0);
			swipeVertically(300);
			String orderPlaced = clickOnCategoryByName(
					BugRegressionAppConstants.OrderPlaced_id,
					AppData.OrderPlaced);
			System.out.println(orderPlaced);
			Assert.assertTrue(orderPlaced
					.equalsIgnoreCase(AppVerificationChecks.OrderStatusProcessing));
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	public static String placeOrder() throws Exception {

		extentInfoLogs("Open Home page");
		OpenHomePage.openHomePage();

		extentInfoLogs("Select location");
		selectCity("Delhi", "");
		extentInfoLogs("Open Navigation drawer");
		clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
		extentInfoLogs("Click on sign up  iconOpen Home page");
		clickId(BugRegressionAppConstants.SignUpIcon_id);
		extentInfoLogs("Gmail login");
		GmailLogin.gmailLogin();
		extentInfoLogs("Navigate back");
		backButton();
		extentInfoLogs("Click on cart icon");
		clickId(BugRegressionAppConstants.Prod_Cart_Id);
		extentInfoLogs("Delete all products from cart");
		deleteCartItems();
		extentInfoLogs("Navigate back");
		backButton();
		extentInfoLogs("Click on home search text box");
		clickId(BugRegressionAppConstants.Home_Search_TextBox_Id);
		extentInfoLogs("Enter product name in search field");
		sendKeysForID(BugRegressionAppConstants.Home_SearchEditBox_id,
				AppData.Priyank_Store_SearchProduct_Name);
		extentInfoLogs("Select product from auto suggestion list");
		OpenAndroidPage.clickOnCategoryByName(
				BugRegressionAppConstants.AutoSuggestSearch_id,
				AppData.Priyank_Store_Product_Name);

		Thread.sleep(5000);
		extentInfoLogs("Scroll to view store link");
		// driver.scrollTo(AppData.View_Store_Text);
		for (int k = 0; k < 3; k++) {
			swipeVertically_FullPage();
		}

		findPriyankAndBuy();

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
		extentInfoLogs("clicking on cash on delivery button");
		clickOnCategoryByName(BugRegressionAppConstants.AllPaymentMode_id,
				BugRegressionAppConstants.CashOnDelievery_name);
		extentInfoLogs("clicking on place order button");
		clickId(BugRegressionAppConstants.ProductionPlaceOrder_id);
		extentInfoLogs("Getting order id after order");
		String verifyOrderPageOrderID = findElementById(
				BugRegressionAppConstants.VerifyOrderPageOrderId_id).getText();
		return verifyOrderPageOrderID;

	}

	@Test(enabled = true, dependsOnMethods = { "executeTestCase" })
	public void demoAndInstallationTextVerify() {
		extentInfoLogs("Open Home page");
		OpenHomePage.openHomePage();
		extentInfoLogs("open nav drawer");
		clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
		extentInfoLogs("Clicking on user profile");
		clickId(BugRegressionAppConstants.SignUpIcon_id);
		GmailLogin.gmailLogin();
		extentInfoLogs("clicking on My orderlink option");
		clickId(BugRegressionAppConstants.MyProfileOrderLink_id);
		clickIDByIndex(SanitySuiteAppConstants.ViewOrder_id, 0);
		swipeVertically(300);
		Assert.assertTrue(BugRegressionAppConstants.demoAndInstallation_name
				.trim().contains(AppVerificationChecks.demoAndInstallationText));

	}

	@Test(enabled = true)
	public void smsVerification() throws Exception {
		try {
			extentInfoLogs("test case starts");
			boolean flag = false;
			extentInfoLogs("placing order");
			String orderID = placeOrder();
			String splitOrderId = orderID.split(": ")[1];
			System.out.println("order id is " + orderID);

			int counter = 0;
			Thread.sleep(30000);
			driver.startActivity("com.android.mms",
					"com.android.mms.ui.ComposeMessageMms");
			List<WebElement> fromNameList = findElementsById(BugRegressionAppConstants.messageFromSubject_id);
			// for (int i = 0; i < 2; i++) {
			for (WebElement fromName : fromNameList) {
				if (fromName.getText().contains("qatest")) {
					fromName.click();
					List<WebElement> textViewList = findElementsById(BugRegressionAppConstants.messageTextView_id);
					int newCounter = 0;

					for (WebElement textView : textViewList) {
						System.out.println("text is " + textView.getText());
						if (textView.getText().contains(splitOrderId)) {
							System.out.println("assert works");
							flag = true;
							Assert.assertTrue(flag);
						}

						newCounter++;
						if (newCounter == textViewList.size()) {
							swipeVertically_FullPage();
							swipeVertically_FullPage();
						}
					}
					if (flag) {
					} else {
						Assert.assertTrue(flag);
					}
					break;
				}
				counter++;
				if (counter == fromNameList.size()) {
					swipeVertically_FullPage();
					swipeVertically_FullPage();
				}
			}
			// TouchActions act = new TouchActions(driver);
			// List<WebElement> li
			// =driver.findElements(By.id(BugRegressionAppConstants.messageFromSubject_id));
			// for(WebElement l:li){
			// if(l.getText().contains("qazopper"));
			// act.longPress(l);
			// }
			// }
		} catch (Exception e) {
			throw (e);

		}

	}

	@Test(enabled = true, dependsOnMethods = { "smsVerification" })
	public void sellerConfirmationSMS() throws Exception {
		extentInfoLogs("Open Home page");
		OpenHomePage.openHomePage();
		extentInfoLogs("Select location");
		selectCity("Delhi", "");
		extentInfoLogs("Open Navigation drawer");
		clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
		extentInfoLogs("Click on sign up  iconOpen Home page");
		clickId(BugRegressionAppConstants.SignUpIcon_id);
		extentInfoLogs("Gmail login");
		GmailLogin.gmailLogin();
		clickId(BugRegressionAppConstants.MyProfileOrderLink_id);
		extentInfoLogs("Getting ordr id from my orders page");
		String orderID = findElementById(BugRegressionAppConstants.OrderID_id)
				.getText();
		String splitOrderId = orderID.split(": ")[1];
		String orderItemId = getItemId(splitOrderId);
		long id = Long.parseLong(orderItemId);
		System.out.println("id is :" + orderItemId);
		submitSellePriceValue(id);
		acceptedSellePriceValue(id);
		Thread.sleep(40000);
		smsApp(splitOrderId);
		String shipmentId = generateShipmentId(id, splitOrderId);
		Thread.sleep(40000);
		smsApp(splitOrderId);
		itemdispatched(splitOrderId, shipmentId);
		Thread.sleep(40000);
		smsApp("delivery");
		itemdelivered(splitOrderId, shipmentId);
		Thread.sleep(40000);
		smsApp("delivered");
		// closeAppiumSession();
		// if(System.getProperty("staging").equalsIgnoreCase("true")){
		// ChangeZopperPassword.openBrowser("http://52.76.238.126/#/login");
		// wait1.until(ExpectedConditions.presenceOfElementLocated(By
		// .xpath("//input[@placeholder='Username']")));
		// wDriver.findElement(By.xpath("//input[@placeholder='Username']"))
		// .sendKeys("vishal123");
		// wait1.until(ExpectedConditions.presenceOfElementLocated(By
		// .xpath("//input[@placeholder='Password']")));
		// wDriver.findElement(By.xpath("//input[@placeholder='Password']"))
		// .sendKeys("123");
		// wDriver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
		// Actions action = new Actions(wDriver);
		// WebElement order = wDriver.findElement(By
		// .xpath("//li[contains(.,'Orders')]"));
		// action.moveToElement(order).perform();
		// WebElement oManagement = wDriver.findElement(By
		// .linkText("Order Management"));
		// action.moveToElement(oManagement).click().build().perform();
		//
		// wDriver.findElement(By.id("selectedValue")).clear();
		// wDriver.findElement(By.id("selectedValue")).sendKeys(splitOrderId);
		// wDriver.findElement(By.linkText("SEARCH")).click();
		// wDriver.findElement(By.linkText(splitOrderId)).click();
		// wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Store Details')]")));
		// wDriver.findElement(By.xpath("//button[contains(.,'Store Details')]")).click();
		// wDriver.findElement(By.xpath("//*[@id='storeDetailContainer']/div[1]/div[5]/table/tbody/tr[2]/td[2]/select")).click();
		// wDriver.findElement(By.xpath("//option[contains(.,'Confirmed')]")).click();
		// wDriver.findElement(By.xpath("//*[@id='storeDetailContainer']/div[1]/div[6]/div[1]/input")).sendKeys("3200");
		// wDriver.findElement(By.xpath("//*[@id='storeDetailContainer']/div[1]/div[6]/div[2]/select")).click();
		// wDriver.findElement(By.xpath("//option[@label='0']")).click();
		// wDriver.findElement(By.xpath("//*[@id='storeDetailContainer']/div[1]/div[6]/button")).click();
		// wait1.until(ExpectedConditions.alertIsPresent());
		// Alert alert= wDriver.switchTo().alert();
		// alert.accept();
		// wDriver.findElement(By.xpath("//*[@id='storeDetailContainer']/div[1]/div[5]/table/tbody/tr[2]/td[2]/div/button[2]")).click();
		// wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='storeDetailContainer']/div[1]/span/input")));
		// wDriver.findElement(By.xpath("//*[@id='storeDetailContainer']/div[1]/span/input")).click();
		// wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@data-ng-model='deliveryType']")));
		// wDriver.findElement(By.xpath("//select[@data-ng-model='deliveryType']")).click();
		// wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[contains(.,'Self')]")));
		// wDriver.findElement(By.xpath("//option[contains(.,'Self')]")).click();
		// wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Generate Shipment')]")));
		// wDriver.findElement(By.xpath("//button[contains(.,'Generate Shipment')]")).click();
		// action.moveToElement(order).perform();
		// WebElement dManagement = wDriver.findElement(By
		// .linkText("Delivery Management"));
		// action.moveToElement(dManagement).click().build().perform();

		// }
	}

	@Test(enabled = true)
	public void smsForshipmentGeneration() throws IOException, JSONException {
		String OrderId = returnOrderId();
		String itemID = getItemId(OrderId);

	}

	public String returnOrderId() {
		extentInfoLogs("open home page");
		OpenHomePage.openHomePage();
		extentInfoLogs("Select location");
		selectCity("Delhi", "");
		extentInfoLogs("Open Navigation drawer");
		clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
		extentInfoLogs("Click on sign up  iconOpen Home page");
		clickId(BugRegressionAppConstants.SignUpIcon_id);
		extentInfoLogs("Gmail login");
		GmailLogin.gmailLogin();
		clickId(BugRegressionAppConstants.MyProfileOrderLink_id);
		extentInfoLogs("Getting ordr id from my orders page");
		String orderID = findElementById(BugRegressionAppConstants.OrderID_id)
				.getText();
		String splitOrderId = orderID.split(": ")[1];
		return splitOrderId;
	}

	@Test(enabled = true)
	public void payTmFlow() throws Exception {
		try {
			try {
				extentInfoLogs("Open Home page");
				OpenHomePage.openHomePage();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id(BugRegressionAppConstants.HomeSpinner_id)));
				// swipeWithAxis(200, 300, 100, 100, 3000);
				extentInfoLogs("Click on home search icon");
				clickId(BugRegressionAppConstants.Home_search_button_id);
				extentInfoLogs("Enter search string as "
						+ AppData.Priyank_Store_Product_Name);
				sendKeysForID(BugRegressionAppConstants.Home_SearchEditBox_id,
						AppData.Priyank_Store_SearchProduct_Name);
				extentInfoLogs("Click on auto suggest first item");
				// clickXpath(BugRegressionAppConstants.SearchSuggestedFirstProduct_xpath);
				WebElement searchList = findElementById(BugRegressionAppConstants.SearchList_id);
				// List<WebElement> linearlayout = searchList
				// .findElements(By
				// .className(BugRegressionAppConstants.SearchLinearLayout_classname));
				// linearlayout.get(0).click();
				OpenAndroidPage.clickOnCategoryByName(
						BugRegressionAppConstants.AutoSuggestSearch_id,
						AppData.Priyank_Store_Product_Name);
				List<WebElement> element = findElementsById(BugRegressionAppConstants.ProductPriceOnPrdctDetails_id);
				price = element.get(0).getText().replaceAll(",", "");
				intprice = Integer.parseInt(price);
				if (intprice <= 10000) {
					extentInfoLogs("Click on cart icon");
					clickId(BugRegressionAppConstants.Prod_Cart_Id);
					extentInfoLogs("Gmail login");
					GmailLogin.gmailLogin();
					extentInfoLogs("Delete all products from cart");
					deleteCartItems();
					extentInfoLogs("Navigate back");
					backButton();
					extentInfoLogs("Click on buy now button");
					clickXpath(BugRegressionAppConstants.ProductDetailsVarBuyNow_xpath);
					AddToCart.addToCart();
					extentInfoLogs("clicking on checkout button");
					clickId(BugRegressionAppConstants.ProductionCheckoutButton_id);

					extentInfoLogs("Checking for address fields");
					if (findElementsById(
							SanitySuiteAppConstants.Shipping_FullName_Id)
							.size() > 0) {
						extentInfoLogs("Enter user name in shipping address");
						sendKeysForID(
								SanitySuiteAppConstants.Shipping_FullName_Id,
								AppData.Shipping_UserName);
						extentInfoLogs("Enter address in shipping address");
						sendKeysForID(
								SanitySuiteAppConstants.Shipping_Address_Id,
								AppData.ShippingAddress);
						extentInfoLogs("Enter Pincode in shipping address");
						sendKeysForID(
								SanitySuiteAppConstants.Shipping_Pincode_Id,
								AppData.ShippingPincode);
						extentInfoLogs("Click on save button");
						clickId(SanitySuiteAppConstants.Shipping_SaveButton_Id);
					}

					extentInfoLogs("clicking on continue button");
					clickId(BugRegressionAppConstants.ProductionContinueButton_id);
					extentInfoLogs("clicking on cash on continue button");
					clickOnCategoryByName(
							BugRegressionAppConstants.AllPaymentMode_id,
							AppData.PayTm);
					Assert.assertTrue(BugRegressionAppConstants.PaytmpageCancel_name
							.equalsIgnoreCase(AppVerificationChecks.PaytmCancel));
				} else {
					verifyProductForPaytm();
				}
			} catch (Exception e) {
				throw (e);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(enabled = true, priority = 2, dependsOnMethods = { "executeTestCase" })
	public static void DemoInstallationInfo() {
		swipeVertically_FullPage();
		// Assert.assertTrue(findElementById(id))
	}

	public void verifyProductForPaytm() throws Exception {
		extentInfoLogs("Click on home search icon");
		clickId(BugRegressionAppConstants.Home_search_button_id);
		extentInfoLogs("Enter search string as " + AppData.Producttext10);
		sendKeysForID(BugRegressionAppConstants.Home_SearchEditBox_id,
				AppData.Producttext11);
		extentInfoLogs("Click on auto suggest first item");
		// clickXpath(BugRegressionAppConstants.SearchSuggestedFirstProduct_xpath);
		WebElement searchList = findElementById(BugRegressionAppConstants.SearchList_id);
		List<WebElement> linearlayout = searchList
				.findElements(By
						.className(BugRegressionAppConstants.SearchLinearLayout_classname));
		linearlayout.get(0).click();
		List<WebElement> element = findElementsById(BugRegressionAppConstants.ProductPriceOnPrdctDetails_id);
		price = element.get(0).getText().replaceAll(",", "");
		intprice = Integer.parseInt(price);
		extentInfoLogs("Click on cart icon");
		clickId(BugRegressionAppConstants.Prod_Cart_Id);
		extentInfoLogs("Gmail login");
		GmailLogin.gmailLogin();
		extentInfoLogs("Delete all products from cart");
		deleteCartItems();
		extentInfoLogs("Navigate back");
		backButton();
		extentInfoLogs("Click on buy now button");
		clickXpath(BugRegressionAppConstants.ProductDetailsVarBuyNow_xpath);
		AddToCart.addToCart();
		extentInfoLogs("clicking on checkout button");
		clickId(BugRegressionAppConstants.ProductionCheckoutButton_id);
		extentInfoLogs("clicking on continue button");
		clickId(BugRegressionAppConstants.ProductionContinueButton_id);
		extentInfoLogs("clicking on cash on continue button");
		clickOnCategoryByName(BugRegressionAppConstants.AllPaymentMode_id,
				AppData.PayTm);
		Assert.assertTrue(BugRegressionAppConstants.PaytmpageCancel_name
				.equalsIgnoreCase(AppVerificationChecks.PaytmCancel));

	}

	public static void findPriyankAndBuy() throws InterruptedException {
		if (findElementById(BugRegressionAppConstants.Product_Store_Name_id)
				.isDisplayed()) {

		} else {
			swipeVertically_FullPage();
			swipeVertically_FullPage();
			swipeVertically_FullPage();
		}

		if (findElementById(BugRegressionAppConstants.Product_Store_Name_id)
				.getText().contains(AppData.Priyank_Store_Text)) {
			clickId(BugRegressionAppConstants.AddToCartButton_id);
		} else {
			clickId(BugRegressionAppConstants.MoreSellers_id);
			extentInfoLogs("Select priyank store from view more sellers");
			for (int i = 0; i < 5; i++) {
				int sellerCount = findElementsById(
						BugRegressionAppConstants.More_Sellers_Store_Name_Id)
						.size();
				System.out.println(sellerCount);
				for (int j = 0; j < sellerCount; j++) {
					String storeName = findElementsById(
							BugRegressionAppConstants.More_Sellers_Store_Name_Id)
							.get(j).getText();
					System.out.println(storeName);
					System.out.println("the value of j counter" + j);
					if (storeName.trim().equals(AppData.Priyank_Store_Text)) {
						Thread.sleep(5000);
						findElementsById(
								BugRegressionAppConstants.MoreSellersBuyNow_id)
								.get(j).click();
						break;
					}
				}

				swipeVertically_FullPage();
				swipeVertically_FullPage();
				swipeVertically_FullPage();
			}

			if (findElementById(BugRegressionAppConstants.StoreTitle_id)
					.getText().contains(AppData.Priyank_Store_Text)) {

			} else {
				deleteCartItems();
				backButton();
				backButton();
				findPriyankAndBuy();
			}
		}
	}

	public static ParamObject getParamsForOrderItemId(String orderId) {
		String url = null;
		String authorizationToken = null;
		if (BugRegressionAppConstants.buildType.equalsIgnoreCase(".staging")) {
			url = "http://52.76.238.126/dashboard/accounts/sales/orders/"
					+ orderId + "/";
			authorizationToken = "Token 6ff0c243a4e8c4185caac6a35d5e86c8196528a6";
		} else {
			url = "https://dashboard.zopper.com/dashboard/accounts/sales/orders/"
					+ orderId + "/";
			authorizationToken = "Token 49c99ac72064752b3624d37cdaa52616f0da5191";
		}
		String type = "application/json";
		ParamObject obj = new ParamObject();
		obj.setUrl(url);
		obj.addHeader("authorization", authorizationToken);
		// obj.addHeader("cookie",
		// "_ga=GA1.2.1095203603.1463067968; WZRK_G=9a25c7554ead4151b0c8a9b65bd6faf6; _vwo_uuid_v2=076AC3EA7E63B10BAE481AADE0F1F7C0|02fbf31f3d35acea24702e57e847eb9f; _ceg.s=od6bej; _ceg.u=od6bej; orderDetPath=customer; sessionID=49c99ac72064752b3624d37cdaa52616f0da5191; isAdmin=true; username=rhimanshu; userRole=Admin; _gat_UA-67874333-1=1; WZRK_S_44K-KZ9-744Z=%7B%22p%22%3A1%7D");
		obj.addHeader("accept", type);
		obj.setMethodType("GET");
		return obj;

	}

	public static String getItemId(String orderId) throws IOException,
			JSONException {
		String idValue = null;
		ParamObject param = getParamsForOrderItemId(orderId);
		String json = (String) RestClient.getJSONFromParamsObject(param,
				RestClient.RETURN_JSON_STRING);
		System.out.println(json);
		JSONObject rootObject = new JSONObject(json);
		JSONArray items = rootObject.getJSONArray("items");
		for (int i = 0; i < items.length(); i++) {
			JSONObject id = items.getJSONObject(i);
			idValue = id.getString("id");
			System.out.println(idValue);
		}
		return idValue;

	}

	public static ParamObject getParamPriceSubmittedBySeller(String payload) {
		String url = null;
		String authorizationToken = null;
		if (BugRegressionAppConstants.buildType.equalsIgnoreCase(".staging")) {
			url = "http://52.76.238.126/dashboard/accounts/sales/orders/action/";
			authorizationToken = "Token 6ff0c243a4e8c4185caac6a35d5e86c8196528a6";
		} else {
			url = "https://dashboard.zopper.com/dashboard/accounts/sales/orders/action/";
			authorizationToken = "Token 49c99ac72064752b3624d37cdaa52616f0da5191";
		}
		String type = "application/json";
		ParamObject obj = new ParamObject();
		obj.setUrl(url);
		obj.addHeader("authorization", authorizationToken);
		obj.addHeader("content-type", type);
		obj.setPayload(payload);
		obj.setMethodType("POST");
		return obj;
	}

	public static String jsonPayloadForPriceSubmittedBySeller(long id)
			throws JSONException {
		// getItemId(orderId);
		JSONObject data = new JSONObject();
		JSONObject root = new JSONObject();
		data.put("fulfilled_store_id", "179053");
		data.put("fulfilled_price", 3000);
		data.put("item_id", id);
		data.put("chain_id", 0);
		data.put("diversion_count", 0);
		data.put("chain_sku_id", "NA");
		data.put("chain_store_id", "");
		data.put("reason", "Not Diverted");
		root.put("action", "retailer_status_change");
		root.put("data", data);
		return root.toString();

	}

	public static void submitSellePriceValue(long id) throws IOException,
			JSONException {
		String payload = jsonPayloadForPriceSubmittedBySeller(id);
		ParamObject params = getParamPriceSubmittedBySeller(payload);
		String json = (String) RestClient.getJSONFromParamsObject(params,
				RestClient.RETURN_JSON_STRING);
		System.out.println(json);

	}

	public static ParamObject getParamPriceAcceptedBySeller(String payload) {
		String url = null;
		String authorizationToken = null;
		if (BugRegressionAppConstants.buildType.equalsIgnoreCase(".staging")) {
			url = "http://52.76.238.126/dashboard/accounts/sales/orders/action/";
			authorizationToken = "Token 6ff0c243a4e8c4185caac6a35d5e86c8196528a6";
		} else {
			url = "https://dashboard.zopper.com/dashboard/accounts/sales/orders/action/";
			authorizationToken = "Token 49c99ac72064752b3624d37cdaa52616f0da5191";

		}
		String type = "application/json";
		ParamObject obj = new ParamObject();
		obj.setUrl(url);
		obj.addHeader("authorization", authorizationToken);
		obj.addHeader("content-type", type);
		obj.setPayload(payload);
		obj.setMethodType("POST");
		return obj;
	}

	public static String jsonPayloadForPriceAcceptedBySeller(long id)
			throws JSONException {
		// getItemId(orderId);
		JSONObject data = new JSONObject();
		JSONObject root = new JSONObject();
		data.put("fulfilled_store_id", "179053");
		data.put("fulfilled_price", 3000);
		data.put("item_id", id);
		data.put("reason", "ORDER ACCEPTED");
		data.put("retailer_status", "ACCEPTED");
		root.put("action", "retailer_status_change");
		root.put("data", data);
		return root.toString();

	}

	public static void acceptedSellePriceValue(long id) throws IOException,
			JSONException {
		String payload = jsonPayloadForPriceAcceptedBySeller(id);
		ParamObject params = getParamPriceAcceptedBySeller(payload);
		String json = (String) RestClient.getJSONFromParamsObject(params,
				RestClient.RETURN_JSON_STRING);
		System.out.println(json);

	}

	public static ParamObject getParamsForGenerateShipmentid(String payload) {
		String url = null;
		String authorizationToken = null;
		if (BugRegressionAppConstants.buildType.equalsIgnoreCase(".staging")) {
			url = "http://52.76.238.126/dashboard/accounts/sales/shipment/action/";
			authorizationToken = "Token 6ff0c243a4e8c4185caac6a35d5e86c8196528a6";
		} else {
			url = "https://dashboard.zopper.com/dashboard/accounts/sales/shipment/action/";
			authorizationToken = "Token 49c99ac72064752b3624d37cdaa52616f0da5191";
		}

		String type = "application/json";
		ParamObject obj = new ParamObject();
		obj.setUrl(url);
		obj.addHeader("authorization", authorizationToken);
		obj.addHeader("content-type", type);
		obj.setPayload(payload);
		obj.setMethodType("POST");
		return obj;
	}

	public static String jsonPayloadToGenerateShipmentId(long id, String orderId)
			throws JSONException {
		// getItemId(orderId);
		JSONObject data = new JSONObject();
		JSONObject root = new JSONObject();
		JSONArray orderItems = new JSONArray();
		orderItems.put(id);
		data.put("order_items", orderItems);
		data.put("store_id", 179053);
		data.put("shipment_type", "self");
		data.put("order_id", orderId);
		root.put("action", "generate_shipment_sales");
		root.put("data", data);
		System.out.println(root.toString());
		return root.toString();

	}

	public static String generateShipmentId(long id, String orderID)
			throws IOException, JSONException {
		String payload = jsonPayloadToGenerateShipmentId(id, orderID);
		ParamObject params = getParamsForGenerateShipmentid(payload);
		String json = (String) RestClient.getJSONFromParamsObject(params,
				RestClient.RETURN_JSON_STRING);
		System.out.println(json);
		JSONObject root = new JSONObject(json);
		String shipmentId = root.getString("shipment_id");
		return shipmentId;

	}

	public static ParamObject getParamsForItemsDispatched(String payload) {
		String url = null;
		String authorizationToken = null;
		if (BugRegressionAppConstants.buildType.equalsIgnoreCase(".staging")) {
			url = "http://52.76.238.126/dashboard/accounts/sales/shipment/action/";
			authorizationToken = "Token 6ff0c243a4e8c4185caac6a35d5e86c8196528a6";
		} else {
			url = "https://dashboard.zopper.com/dashboard/accounts/sales/shipment/action/";
			authorizationToken = "Token 49c99ac72064752b3624d37cdaa52616f0da5191";
		}
		String type = "application/json";
		ParamObject obj = new ParamObject();
		obj.setUrl(url);
		obj.addHeader("authorization", authorizationToken);
		obj.addHeader("content-type", type);
		obj.setPayload(payload);
		obj.setMethodType("POST");
		return obj;
	}

	public static String jsonPayloadForitemDispatched(String orderId,
			String shipmentID) throws JSONException {
		// getItemId(orderId);
		JSONObject data = new JSONObject();
		JSONObject root = new JSONObject();
		JSONArray shipmentId = new JSONArray();
		shipmentId.put(shipmentID);
		data.put("shipment_id", shipmentId);
		data.put("status", "OUT FOR DELIVERY");
		data.put("order_id", orderId);
		root.put("action", "update_shipment_status");
		root.put("data", data);
		System.out.println(root.toString());
		return root.toString();

	}

	public static String itemdispatched(String orderId, String shipmentId)
			throws IOException, JSONException {
		String payload = jsonPayloadForitemDispatched(orderId, shipmentId);
		ParamObject params = getParamsForItemsDispatched(payload);
		String json = (String) RestClient.getJSONFromParamsObject(params,
				RestClient.RETURN_JSON_STRING);
		System.out.println(json);
		return null;

	}

	public static ParamObject getParamsForItemsDeleivered(String payload) {
		String url = null;
		String authorizationToken = null;
		if (BugRegressionAppConstants.buildType.equalsIgnoreCase(".staging")) {
			url = "http://52.76.238.126/dashboard/accounts/sales/shipment/action/";
			authorizationToken = "Token 6ff0c243a4e8c4185caac6a35d5e86c8196528a6";
		} else {
			url = "https://dashboard.zopper.com/dashboard/accounts/sales/shipment/action/";
			authorizationToken = "Token 49c99ac72064752b3624d37cdaa52616f0da5191";
		}
		String type = "application/json";
		ParamObject obj = new ParamObject();
		obj.setUrl(url);
		obj.addHeader("authorization", authorizationToken);
		obj.addHeader("content-type", type);
		obj.setPayload(payload);
		obj.setMethodType("POST");
		return obj;
	}

	public static String jsonPayloadForitemDelivered(String orderId,
			String shipmentID) throws JSONException {
		// getItemId(orderId);
		JSONObject data = new JSONObject();
		JSONObject root = new JSONObject();
		JSONArray shipmentId = new JSONArray();
		shipmentId.put(shipmentID);
		data.put("shipment_id", shipmentId);
		data.put("status", "DELIVERED");
		data.put("order_id", orderId);
		root.put("action", "update_shipment_status");
		root.put("data", data);
		System.out.println(root.toString());
		return root.toString();

	}

	public static String itemdelivered(String orderId, String shipmentId)
			throws IOException, JSONException {
		String payload = jsonPayloadForitemDelivered(orderId, shipmentId);
		ParamObject params = getParamsForItemsDeleivered(payload);
		String json = (String) RestClient.getJSONFromParamsObject(params,
				RestClient.RETURN_JSON_STRING);
		System.out.println(json);
		return null;

	}

	public static void smsApp(String splitOrderId) {
		int counter = 0;
		boolean flag = false;
		driver.startActivity("com.android.mms",
				"com.android.mms.ui.ComposeMessageMms");
		List<WebElement> fromNameList = findElementsById(BugRegressionAppConstants.messageFromSubject_id);
		// for (int i = 0; i < 2; i++) {
		for (WebElement fromName : fromNameList) {
			if (fromName.getText().contains("qatest")) {
				fromName.click();
				List<WebElement> textViewList = findElementsById(BugRegressionAppConstants.messageTextView_id);
				int newCounter = 0;

				for (WebElement textView : textViewList) {
					System.out.println("text is " + textView.getText());
					if (textView.getText().contains(splitOrderId)) {
						System.out.println("assert works");
						flag = true;
						Assert.assertTrue(flag);
					}

					newCounter++;
					if (newCounter == textViewList.size()) {
						swipeVertically_FullPage();
						swipeVertically_FullPage();
					}
				}
				if (flag) {
				} else {
					Assert.assertTrue(flag);
				}
				break;
			}
			counter++;
			if (counter == fromNameList.size()) {
				swipeVertically_FullPage();
				swipeVertically_FullPage();
			}
		}
	}

	public static ParamObject getParamsForStore() {
		String url = null;
		// String authorizationToken = null;
		url = "http://store.lenskart.com/wp-admin/admin-ajax.php?action=store_search&lat=28.5821195&lng=77.32669910000004&max_results=200&radius=3000";
		String type = "application/json";
		ParamObject obj = new ParamObject();
		obj.setUrl(url);
		obj.addHeader("content-type", type);
		obj.addHeader("cookie", "d140d8833a02b371f97808d9e5a4930331478071756");
		obj.setMethodType("GET");
		return obj;
	}

	public static void Store() throws IOException, JSONException {
		int rownum = 0;

		ParamObject params = getParamsForStore();
		String json = (String) RestClient.getJSONFromParamsObject(params,
				RestClient.RETURN_JSON_STRING);
		// System.out.println("json is "+json);
		JSONArray rootArray = new JSONArray(json);
		Workbook book = new HSSFWorkbook();
		org.apache.poi.ss.usermodel.Sheet sheet = book.createSheet("Stores");
		for (int i = 0; i < rootArray.length(); i++) {

			String address = rootArray.getJSONObject(i).getString("address");
			String contactNo = rootArray.getJSONObject(i).getString("phone");
			System.out.println("the address is ::::::" + address);
			System.out.println("the contact no is ::::::::" + contactNo);

			Row row = sheet.createRow(i);
			Cell cell = row.createCell(0);
			cell.setCellValue(address);
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(contactNo);

			FileOutputStream out = new FileOutputStream(new File(
					"lenskartStore.xls"));
			book.write(out);
			out.close();
			System.out.println("Writesheet.xlsx written successfully");
			// Row row = ((org.apache.poi.ss.usermodel.Sheet)
			// sheet).createRow(0);
			// Cell name = row.createCell(0);
			// name.setCellValue(address);
			// Cell contact = row.createCell(1);
			// contact.setCellValue(contactNo);

			// Read more:
			// http://javarevisited.blogspot.com/2015/06/how-to-read-write-excel-file-java-poi-example.html#ixzz4OyYTjr35
			// XSSFWorkbook workbook = new XSSFWorkbook();
			// XSSFSheet spreadsheet = workbook.createSheet("Stores");
			// XSSFRow row ;
			// Map < String, Object[] > storeInfo = new TreeMap < String,
			// Object[]>();
			// storeInfo.put("1", new Object[]{address,contactNo});
			// Set < String > keyid = storeInfo.keySet();
			// int rowid = 0;
			// for (String key : keyid)
			// {
			// row = spreadsheet.createRow(rowid++);
			// Object [] objectArr = storeInfo.get(key);
			// int cellid = 0;
			// for (Object obj : objectArr)
			// {
			// Cell cell = row.createCell(cellid++);
			// cell.setCellValue((String)obj);
			// }
			// }
			// //Write the workbook in file system

		}

	}
	
	public static void matrix(){
		int row, col,matrix[][] = null ;
		Scanner sc= new Scanner(System.in);
		System.out.println("enter no of rows");
		row = Integer.parseInt(sc.next());
		System.out.println("enter no. of columns");
		col= Integer.parseInt(sc.next());
		System.out.println("enter the data");
		matrix= new int[row][col];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				matrix[i][j]=sc.nextInt();
				System.out.println("\t"+matrix[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String args[]) throws IOException, JSONException {
		// getItemId("35557109");
		// jsonPayloadForPriceSubmittedBySeller();
		// submitSellePriceValue(196408);
		// acceptedSellePriceValue(196408);
		// generateShipmentId(578748,"35557109");
		// jsonPayloadForitemDispatched();
		// itemdelivered("35557109","35557109_1");
		// System.out.println(getDeviceVersion());
		// System.out.println(getOSNameWithDeviceBrand());
		//Store();
		matrix();
	}

	// @Test(enabled = true)
	@Test(enabled = true, dependsOnMethods = { "executeTestCase" })
	public static void TrackOrder() throws Exception {
		try {
			extentInfoLogs("open Home Page");
			OpenHomePage.openHomePage();
			Assert.assertTrue(findElementsById(
					BugRegressionAppConstants.orderStatusBarId).isEmpty());
			OpenNavigationDrawer.openNavigationDrawer();
			clickId(BugRegressionAppConstants.NavDrawer_Login_button_id);
			Assert.assertTrue(findElementById(
					BugRegressionAppConstants.Login_Screen_Main).isDisplayed());
			extentInfoLogs("Click on sign up  iconOpen Home page");
			// clickId(BugRegressionAppConstants.SignUpIcon_id);
			extentInfoLogs("Gmail login");
			GmailLogin.gmailLogin();
			extentInfoLogs("Navigate back");
			backButton();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.className(BugRegressionAppConstants.orderStatusBarId)));
			Assert.assertEquals(
					findElementById(BugRegressionAppConstants.orderStatusBarId)
							.getText(), AppData.TrackBarOrderPlaced);
			String orderStatus = findElementById(
					BugRegressionAppConstants.orderStatusBarId).getText();

			swipeVertically_FullPage();
			Assert.assertTrue(findElementsById(
					BugRegressionAppConstants.orderStatusBarId).isEmpty());
			swipeVertically_FullPageUp();

			extentInfoLogs("click on Track order");
			clickId(BugRegressionAppConstants.orderTrackerId);
			Assert.assertTrue(findElementById(
					BugRegressionAppConstants.orderDetailsScreenId)
					.isDisplayed());
			String orderId = findElementById(
					BugRegressionAppConstants.orderDetailsOrderId).getText();
			backButton();
			OpenNavigationDrawer.openNavigationDrawer();
			extentInfoLogs("click on my account");
			clickId(BugRegressionAppConstants.NavDrawer_Login_button_id);
			for (int i = 0; i < 2; i++) {
				if (findElementsById(
						BugRegressionAppConstants.MyAccount_OrderMenu_Id)
						.size() > 0) {
					clickId(BugRegressionAppConstants.MyAccount_OrderMenu_Id);
					break;
				} else {
					swipeVertically_FullPage();
				}
			}
			List<WebElement> orderListItems = findElementsById(BugRegressionAppConstants.orderListItemId);
			for (WebElement orderListItem : orderListItems) {
				if (orderListItem
						.findElement(
								By.id(BugRegressionAppConstants.orderListOrderId))
						.getText().contains(orderId)) {
					Assert.assertEquals(
							findElementById(
									BugRegressionAppConstants.orderListOrderStatusId)
									.getText(), orderStatus);
					break;
				}
			}
		} catch (Exception e) {
			throw (e);
		}
	}

	@Test(enabled = true)
	public void paymentModeNetBanking() throws Exception {
		boolean flag = false;
		try {
			extentInfoLogs("open Home Page");
			OpenHomePage.openHomePage();
			clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
			extentInfoLogs("open navigation drawer");
			OpenHomePage
					.clickOnCategoryByName(BugRegressionAppConstants.NavDrawer_Mobile_tablets_link);
			extentInfoLogs("click on Mobile and tablets link");
			extentInfoLogs("click on android phones");
			// clickName(BugRegressionAppConstants.AndroidPhones_name);
			clickByName(BugRegressionAppConstants.SmartphoneName,
					BugRegressionAppConstants.SmartphoneName);

			// clickName(BugRegressionAppConstants.AndroidPhones_name);
			extentInfoLogs("click on first android phones");
			OpenAndroidPage.clickOnFirstAndroidProduct();
			clickXpath(BugRegressionAppConstants.ProductDetailsVarBuyNow_xpath);
			extentInfoLogs("clicking on Buy Now button");
			// AddToCart.addToCart();
			Assert.assertEquals(
					findElementByName(BugRegressionAppConstants.Login_text)
							.getText(), AppVerificationChecks.Logintext);
			extentInfoLogs("verifying assertion");
			extentInfoLogs("Gmail login");
			GmailLogin.gmailLogin();
			clickId(BugRegressionAppConstants.ProductionCheckoutButton_id);
			extentInfoLogs("clicking on checkout button");

			if (findElementsById(SanitySuiteAppConstants.Shipping_FullName_Id)
					.isEmpty() == false) {
				extentInfoLogs("Enter user name in shipping address");
				sendKeysForID(SanitySuiteAppConstants.Shipping_FullName_Id,
						AppData.Shipping_UserName);
				extentInfoLogs("Enter address in shipping address");
				sendKeysForID(SanitySuiteAppConstants.Shipping_Address_Id,
						AppData.ShippingAddress);
				// extentInfoLogs("Enter Pincode in shipping address");
				// sendKeysForID(SanitySuiteAppConstants.Shipping_Pincode_Id,
				// AppData.ShippingPincode);
				extentInfoLogs("Click on save button");
				clickId(SanitySuiteAppConstants.Shipping_SaveButton_Id);
			}

			clickId(BugRegressionAppConstants.ProductionContinueButton_id);
			extentInfoLogs("clicking on continue button");
			Thread.sleep(5000);
			clickOnCategoryByName(BugRegressionAppConstants.AllPaymentMode_id,
					AppData.NETBANKING);
			driver.findElement(By.id("com.zopperapp:id/spin_choose_bank"))
					.click();
			for (int i = 0; i < 5; i++) {
				List<WebElement> bankList = driver.findElements(By
						.id("android:id/text1"));
				for (WebElement bankText : bankList) {
					if (bankText.getText().contains("HDFC Bank")) {
						bankText.click();
						flag = true;
						break;

					}

				}
				if (flag == false) {
					swipeVertically_FullPage();
				} else {
					break;
				}
			}
			extentInfoLogs("clicking on place order button");
			clickId(BugRegressionAppConstants.ProductionPlaceOrder_id);
			Assert.assertTrue(driver.findElement(By.name("Net Banking"))
					.getText().equalsIgnoreCase("Net Banking"));

		} catch (Exception e) {
			throw (e);
		}
	}

	@Test(enabled = true)
	public void paymentModePayU() throws Exception {
		boolean flag = false;
		try {
			extentInfoLogs("open Home Page");
			OpenHomePage.openHomePage();
			clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
			extentInfoLogs("open navigation drawer");
			OpenHomePage
					.clickOnCategoryByName(BugRegressionAppConstants.NavDrawer_Mobile_tablets_link);
			extentInfoLogs("click on Mobile and tablets link");
			extentInfoLogs("click on android phones");
			// clickName(BugRegressionAppConstants.AndroidPhones_name);
			clickByName(BugRegressionAppConstants.SmartphoneName,
					BugRegressionAppConstants.SmartphoneName);

			// clickName(BugRegressionAppConstants.AndroidPhones_name);
			extentInfoLogs("click on first android phones");
			OpenAndroidPage.clickOnFirstAndroidProduct();
			clickXpath(BugRegressionAppConstants.ProductDetailsVarBuyNow_xpath);
			extentInfoLogs("clicking on Buy Now button");
			// AddToCart.addToCart();
			Assert.assertEquals(
					findElementByName(BugRegressionAppConstants.Login_text)
							.getText(), AppVerificationChecks.Logintext);
			extentInfoLogs("verifying assertion");
			extentInfoLogs("Gmail login");
			GmailLogin.gmailLogin();
			clickId(BugRegressionAppConstants.ProductionCheckoutButton_id);
			extentInfoLogs("clicking on checkout button");

			if (findElementsById(SanitySuiteAppConstants.Shipping_FullName_Id)
					.isEmpty() == false) {
				extentInfoLogs("Enter user name in shipping address");
				sendKeysForID(SanitySuiteAppConstants.Shipping_FullName_Id,
						AppData.Shipping_UserName);
				extentInfoLogs("Enter address in shipping address");
				sendKeysForID(SanitySuiteAppConstants.Shipping_Address_Id,
						AppData.ShippingAddress);
				// extentInfoLogs("Enter Pincode in shipping address");
				// sendKeysForID(SanitySuiteAppConstants.Shipping_Pincode_Id,
				// AppData.ShippingPincode);
				extentInfoLogs("Click on save button");
				clickId(SanitySuiteAppConstants.Shipping_SaveButton_Id);
			}
			clickId(BugRegressionAppConstants.ProductionContinueButton_id);
			extentInfoLogs("clicking on continue button");
			Thread.sleep(5000);
			clickOnCategoryByName(BugRegressionAppConstants.AllPaymentMode_id,
					AppData.PayU);

			Assert.assertTrue(driver.findElement(By.name("PayU Money"))
					.getText().equalsIgnoreCase("PayU Money"));

		} catch (Exception e) {
			throw (e);
		}
	}

	@Test(enabled = true)
	public void paymentModeSbiBuddy() throws Exception {
		boolean flag = false;
		try {
			extentInfoLogs("open Home Page");
			OpenHomePage.openHomePage();
			clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
			extentInfoLogs("open navigation drawer");
			OpenHomePage
					.clickOnCategoryByName(BugRegressionAppConstants.NavDrawer_Mobile_tablets_link);
			extentInfoLogs("click on Mobile and tablets link");
			extentInfoLogs("click on android phones");
			// clickName(BugRegressionAppConstants.AndroidPhones_name);
			clickByName(BugRegressionAppConstants.SmartphoneName,
					BugRegressionAppConstants.SmartphoneName);

			// clickName(BugRegressionAppConstants.AndroidPhones_name);
			extentInfoLogs("click on first android phones");
			OpenAndroidPage.clickOnFirstAndroidProduct();
			clickXpath(BugRegressionAppConstants.ProductDetailsVarBuyNow_xpath);
			extentInfoLogs("clicking on Buy Now button");
			// AddToCart.addToCart();
			Assert.assertEquals(
					findElementByName(BugRegressionAppConstants.Login_text)
							.getText(), AppVerificationChecks.Logintext);
			extentInfoLogs("verifying assertion");
			extentInfoLogs("Gmail login");
			GmailLogin.gmailLogin();
			clickId(BugRegressionAppConstants.ProductionCheckoutButton_id);
			extentInfoLogs("clicking on checkout button");

			if (findElementsById(SanitySuiteAppConstants.Shipping_FullName_Id)
					.isEmpty() == false) {
				extentInfoLogs("Enter user name in shipping address");
				sendKeysForID(SanitySuiteAppConstants.Shipping_FullName_Id,
						AppData.Shipping_UserName);
				extentInfoLogs("Enter address in shipping address");
				sendKeysForID(SanitySuiteAppConstants.Shipping_Address_Id,
						AppData.ShippingAddress);
				// extentInfoLogs("Enter Pincode in shipping address");
				// sendKeysForID(SanitySuiteAppConstants.Shipping_Pincode_Id,
				// AppData.ShippingPincode);
				extentInfoLogs("Click on save button");
				clickId(SanitySuiteAppConstants.Shipping_SaveButton_Id);
			}
			clickId(BugRegressionAppConstants.ProductionContinueButton_id);
			extentInfoLogs("clicking on continue button");
			Thread.sleep(5000);
			clickOnCategoryByName(BugRegressionAppConstants.AllPaymentMode_id,
					AppData.SBIBuddy);

			Assert.assertTrue(driver.findElement(By.name("SBI BUDDY"))
					.getText().equalsIgnoreCase("SBI BUDDY"));

		} catch (Exception e) {
			throw (e);
		}
	}

}