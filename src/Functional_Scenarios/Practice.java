package Functional_Scenarios;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Practice {
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;


	@BeforeMethod
	public void intiliazeDriver() {
		// File chromeDriver = new File("");
		// String path = chromeDriver.getAbsolutePath();
		// System.out.println(path);
		// String driverPath = path+"/linux/chromedriver";
		System.setProperty("webdriver.chrome.driver",
				"/Users/LP1-276/Applications/ConsumerApp/chromedriver");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		actions = new Actions(driver);
	}

	@Test(enabled = false)
	public void login() {
		try {
			driver.get("http://www.99acres.com");
			driver.findElement(By.id("hmcontainer")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//a[contains(.,'Login / Register')]")));
			driver.findElement(By.xpath("//a[contains(.,'Login / Register')]"))
					.click();
			driver.findElement(By.name("username")).sendKeys(
					"argneshu1@gmail.com");
			driver.findElement(By.name("password")).sendKeys("argneshu");
			driver.findElement(By.id("submit_query1")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.xpath("//div[@class='anim']")));
			WebElement ele = driver.findElement(By
					.xpath("//div[@class='hideMobile']"));
			// ele.click();
			// if(driver.findElements(By.xpath("//div[@class='hideMobile']")).size()>0){
			// ele.click();
			// }
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].scrollIntoView()", ele);
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].click();", ele);
		} catch (Exception e) {

		}
		// Actions actions = new Actions(driver);
		// actions.moveToElement(ele).click().perform();
		// WebElement ele
		// =driver.findElement(By.xpath("//div[contains(.,'Menu')]"));
		// if(ele.isDisplayed()&& ele.isEnabled()){
		// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		// ele);
		// }
	}

	@Test(enabled = false)
	public void acreshomePagedropDownHandling() {
		driver.get("http://www.99acres.com");
		driver.findElement(By.id("pType-dd")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.id("resddli")));
		driver.findElement(By.id("resddli")).click();
		List<WebElement> list = driver.findElements(By
				.xpath("//*[@id='s_property_type']/div[2]/div/div[2]/a"));
		System.out.println(list.size());
		list.get(0).click();
		list.get(3).click();
		list.get(6).click();
		driver.findElement(By.id("keyword")).sendKeys("Noida");
		driver.findElement(By.xpath("//*[@id='suggestions_custom']/a[1]"))
				.click();
		driver.findElement(By.xpath("//*[@id='bedroom_num_wrap']/div/a"))
				.click(); // click on drop down
		WebElement par = driver.findElement(By
				.xpath("//*[@id='s_bedroom_num']/div/div[2]/div/div"));// Use
																		// parent
																		// child
																		// concept
		List<WebElement> child = par.findElements(By.tagName("a"));
		System.out.println("size of child is :" + child.size());
		WebElement ele1 = child.get(8);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", ele1);// use javascript to
															// scroll in drop
															// down
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				ele1);// click on scroll element using javascript
		driver.findElement(By.xpath("//*[@id='budget_sub_wrap']/a")).click();
		WebElement parent = driver.findElement(By
				.xpath(".//*[@id='buy_minprice']"));
		List<WebElement> children = parent.findElements(By.tagName("a"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", children.get(8));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				children.get(8));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				children.get(8));

	}

	@Test(enabled = false)
	public void fetchCount() throws InterruptedException {
		driver.get("http://www.jeevansathi.com/");
		driver.findElement(By.xpath("//*[@id='searchModify']/div[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.id("search_submit")));
		driver.findElement(By.id("search_submit")).click();
		String count = driver.findElement(
				By.xpath(".//*[@id='js-clusterHead10']/ul/li[2]/span[2]"))
				.getText();
		System.out.println("the count is " + count);
		WebElement element = driver.findElement(By
				.xpath("//*[@id='js-clusterHead10']/ul/li[2]/span[1]/input"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				element);
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='js-clusterHead10']/ul/li[2]/span[1]/input")));
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='js-clusterHead10']/ul/li[2]/span[1]/input")));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("jeevansathiLogo")));
		// ((JavascriptExecutor)
		// driver).executeScript("return document.readyState").equals("complete");
		// Thread.sleep(5000);
		waitForLoad(driver);
		String filterCount = driver.findElement(By.id("pageHeading")).getText();
		System.out.println("filter count is " + filterCount);

	}

	public void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
			}
		};
		// WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	@Test(enabled = true)
	public void jaypore() {
		driver.get("https://www.jaypore.com/#");
		WebElement ele = driver.findElement(By.linkText("CLOTHING"));
		actions.moveToElement(ele).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://www.jaypore.com/shop/kids/tops-200']")));
		driver.findElement(By.xpath("//a[@href='https://www.jaypore.com/shop/kids/tops-200']")).click();
//		List<WebElement> li= driver.findElements(By.tagName("a"));
//		for(WebElement l: li){
//			System.out.println("the text is :"+l.getText());
//			if (l.getText().equalsIgnoreCase("Tops")){
//				l.click();
//				break;
//			}
//		}

	}

}
