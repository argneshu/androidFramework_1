package Utility;

import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.remote.RemoteTouchScreen;

import io.appium.java_client.android.AndroidDriver;

public class SelendroidDriver extends AndroidDriver implements HasTouchScreen{

	public SelendroidDriver(URL remoteAddress, Capabilities desiredCapabilities) {
		super(remoteAddress, desiredCapabilities);
		RemoteTouchScreen rts = new RemoteTouchScreen(getExecuteMethod());
	//	rts.longPress(where);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TouchScreen getTouch() {
		// TODO Auto-generated method stub
		return null;
	} 

}
