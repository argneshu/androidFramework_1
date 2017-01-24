package Functional_Scenarios;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BugRegressionSuite.BaseTestBugRegression;

public class Messages extends BaseTestBugRegression {
	@Test
	public void messages() throws InterruptedException{
		extentInfoLogs("hi");
		Thread.sleep(5000);
		driver.startActivity("com.android.mms", "com.android.mms.ui.ComposeMessageMms"); //com.android.mms.ui.ConversationComposer
		List<WebElement> messageList =driver.findElements(By.id("com.android.mms:id/conversationList_item_root"));
		messageList.get(0).click();
	}

	@Override
    @Test(enabled = false)
	public void executeTestCase() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
