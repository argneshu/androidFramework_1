package Functional_Scenarios;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class lenskart {
	static WebDriver driver;
	static WebDriverWait  wait;
	public static void main(String args[]) throws InterruptedException, ClientProtocolException, IOException{
		File chromeDriver = new File("");
		String path = chromeDriver.getAbsolutePath();
		String driverPath = path+"/chromedriver";
		System.out.println(driverPath);
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver= new ChromeDriver();
		wait= new WebDriverWait(driver,10);
		driver.get("https://www.zopper.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		List<WebElement> ele = driver.findElements(By.tagName("img"));
		System.out.println(ele.size());
		int counter= 0;
		for(WebElement el: ele){
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(el.getAttribute("src"));
			org.apache.http.HttpResponse response =  client.execute(request);
			if(response.getStatusLine().getStatusCode()!=200){
				System.out.println("image is broken");
				counter++;
			}
		}
		
		
//		driver.manage().window().maximize();
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
//		driver.findElement(By.name("q")).sendKeys("lenskart");
//		driver.findElement(By.id("searchbox-searchbutton")).click();
//		List<WebElement> stores = driver.findElements(By.className("section-result-text-content"));
//		System.out.println(stores.size());
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("section-result-text-content")));
//		for(int i=0;i<stores.size();i++){
//			System.out.println(driver.findElement(By.className("section-result-location")).getText());
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("section-result-text-content")));
//			stores.get(i).click();
//			driver.findElement(By.xpath("//span[contains(.,'Back to results')]")).click();
//			Thread.sleep(2000);
//		}
		driver.quit();
	}


}
