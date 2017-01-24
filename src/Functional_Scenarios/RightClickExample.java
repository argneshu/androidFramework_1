package Functional_Scenarios;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RightClickExample {

	static WebDriver driver;
	public static String textEdit;
	
	static String URL = "http://medialize.github.io/jQuery-contextMenu/demo.html";
	
	@BeforeMethod
	public void Setup() {
		//driver = new FirefoxDriver();File chromeDriver = new File("");
		File chromeDriver = new File("");
		String path = chromeDriver.getAbsolutePath();
		String driverPath = path+"/chromedriver";
		System.out.println(driverPath);
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test(priority= 1)
	public static String rightClickTest() throws InterruptedException {
		driver.navigate().to(URL);
		By locator = By.cssSelector(".context-menu-one.btn");
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)); 
		WebElement element=driver.findElement(locator);
		rightClick(element);
		WebElement elementEdit =driver.findElement(By.cssSelector(".context-menu-item.context-menu-icon.context-menu-icon-edit"));
		elementEdit.click();
		Thread.sleep(3000);
		Alert alert=driver.switchTo().alert();
		textEdit = alert.getText();
		Assert.assertEquals(textEdit, "clicked: edit", "Failed to click on Edit link");
		return textEdit;
	}
	
	@Test(priority=2)
	public static void test(){
		String text = RightClickExample.textEdit;
		System.out.println("the value of text is "+ text);
	} 
	
	public static void rightClick(WebElement element) {
		try {
			Actions action = new Actions(driver).contextClick(element);
			action.perform();

			System.out.println("Sucessfully Right clicked on the element");
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Element " + element + " was not clickable "
					+ e.getStackTrace());
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	
}

