package Page_Objects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class setPasswordPageObjects {
	static WebDriver driver;
	@FindBy(how= How.ID, using = "com.zopperapp.staging:id/confirm_password")
	@CacheLookup
	public static WebElement confirmPasswordText;
	
	@FindBy(how= How.ID, using = "com.zopperapp.staging:id/new_password")
	public static WebElement newPasswordText;
	
	@FindBy(how= How.ID, using = "com.zopperapp.staging:id/button_set_password")
	public static WebElement setPassword;

	 public setPasswordPageObjects(WebDriver driver) {
 
		this.driver = driver;
		//PageFactory.initElements(driver, this);
		}
	
	

//	@Override
//	//@Test
//	public void executeTestCase() throws Exception {
//		// TODO Auto-generated method stub
//		
//	}

}
