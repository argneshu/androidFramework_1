package Utility;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.NativeActivity;
import android.content.Intent;

import com.google.common.eventbus.DeadEvent;

public class daemonThread extends Thread {

	String appActivity = "com.zopper.inappnotification.views.InAppActivity";
	private static AndroidDriver driver;

	public daemonThread(AndroidDriver driver) {
		this.driver = driver;

		// TODO Auto-generated constructor stub
	}

	public daemonThread() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String osName = System.getProperty("os.name");
		System.out.println(osName);

		if(!osName.contains("Windows")){
		while(true){
		 try {
			 System.out.println("thread is running");
			 Thread.sleep(10000);
			InputStream is =Runtime.getRuntime().exec(new String []{"/Applications/sdk/platform-tools/adb","shell","dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'"}).getInputStream();		
			//System.out.println("is read value is :::::::::::::"+is.read());
			 BufferedReader read = new BufferedReader(new InputStreamReader(is));
			// System.out.println("is buffered read value is :::::::::::::"+is.read());
			 StringBuilder out = new StringBuilder();
			// String text =  read.readLine();
			// System.out.println("text is ::::::" +read.readLine());
			 if(read.readLine().contains(appActivity)){
				//System.out.println("***************Inside the if clause*******************");
	            String line;
	            while ((line = read.readLine()) != null) {
	                out.append(line);
	               // System.out.println("in while loop:::::::::" +out.append(line));
	            }
	           // System.out.println("after using to string method::::::::"+out.toString());
	            
	          String  act = out.toString().split("/")[1];
	        // System.out.println("split value is ::::::::::::::"+act);
	          String splitAct =act.split("/s")[0];
	          System.out.println("activity name is :" +splitAct);
	          if(splitAct.contains(appActivity)){
	        	  TouchAction tc = new TouchAction(driver);
	        	  tc.press(704, 837);
	        	//  tc.tap(704, 837);
					//((JavascriptExecutor) driver).executeScript("mobile: tap", new HashMap<String, Double>() {{ put("tapCount", (double) 1); put("touchCount", (double) 1); put("duration", 0.5); put("x", (double) 702); put("y", (double) 524); }});
	          }
	          }
	            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		}
		 else {
			while (true) {
				try {
					Thread.sleep(5000);
					System.out.println("thread is running");
					String currentActivity = driver.currentActivity();
					if (currentActivity.contains(appActivity)) {
						((JavascriptExecutor) driver).executeScript(
								"mobile: tap", new HashMap<String, Double>() {
									{
										put("tapCount", (double) 1);
										put("touchCount", (double) 1);
										put("duration", 0.5);
										put("x", (double) 715);
										put("y", (double) 745);
									}
								});
						//System.out.println("wait for 7 seconds");
						//Thread.sleep(10000);
					}

				} catch (InterruptedException e) {
					throw new RuntimeException("Thread interrupted..."+e);		           
				}
			}

		}
		// System.out.println("Name: "+Thread.currentThread().getName());
		// System.out.println("Daemon: "+Thread.currentThread().isDaemon());
	}

	private void startActivity(Intent intent) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String args[]) {
		daemonThread dt = new daemonThread();
		dt.setDaemon(true);
		dt.start();
	}

}
