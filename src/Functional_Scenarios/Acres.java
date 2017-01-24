package Functional_Scenarios;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.mutable.Mutable;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import Utility.ExcelUtils;

public class Acres {
	
	
	// private static XSSFSheet ExcelWSheet;
	//
	// private static XSSFWorkbook ExcelWBook;
	//
	// private static XSSFCell Cell;
	//
	// private static XSSFRow Row;
	public static AndroidDriver driver;
	String sellername = "";
	String sellerco = "";
	String sellermob = "";
	String selleradd = "";
	String sellercity = "";
	private String name;
	static List li ;

	public static void Acrescom() throws Exception {
		// String Path_TestData =
		// "D:\\Zopper\\ConsumerAppBugRegression\\src\\TestData\\";
		// String File_TestData = "testData.xlsx";
		// setExcelFile(AppConstants.Path_TestData + AppConstants.File_TestData,
		// "Sheet1");

		// setCellData("pass", 1, 2);
		File appdir = new File("/Users/LP1-276/Applications/ConsumerApp/app");
		File app = new File(appdir, "99acres_com.nnacres.app.apk");
		DesiredCapabilities cap = new DesiredCapabilities();
		// cap.setCapability(CapabilityType.BROWSER_NAME, "");
		cap.setCapability("platformname", "Android");
		// cap.setCapability("deviceName", "XT1033");
		cap.setCapability("deviceName", "SM-N750");
		cap.setCapability("noReset", true);
		cap.setCapability("fullReset", false);
		cap.setCapability("platformVersion", "4.4.2");
		cap.setCapability("app-package", "com.nnacres.app");
		cap.setCapability("app-activity",
				"com.nnacres.app.activity.SplashActivity");
//		cap.setCapability("app-activity",
//				"com.nnacres.app.activity.SearchResultActivityRevamp");//com.nnacres.app.activity.SplashActivity
		cap.setCapability("app", app.getAbsolutePath());

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		// Log.info("New driver instantiated");
		// driver.get("https://www.google.com/maps/@28.6251545,77.3788139,19z");

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.findElement(By.id("com.nnacres.app:id/searchWhat")).click();
		driver.findElement(By.id("com.nnacres.app:id/selectKeyword")).click();
		driver.findElement(By.id("com.nnacres.app:id/inputSearch")).sendKeys(
				"Gurgaon");
		driver.navigate().back();
		driver.findElement(By.id("com.nnacres.app:id/autosuggestDone")).click();
		driver.findElement(By.id("com.nnacres.app:id/propertyType_apartment"))
				.click();
//		driver.findElement(By.id("com.nnacres.app:id/propertyType_house_villa"))
//				.click();
//		driver.findElement(By.id("com.nnacres.app:id/propertyType_land"))
//				.click();
		driver.findElement(By.id("com.nnacres.app:id/searchProperties"))
				.click();
		try {
			
			WebElement parent = driver.findElement(By
					.id("com.nnacres.app:id/searchResultsList"));
			List<WebElement> propList = parent.findElements(By
					.id("com.nnacres.app:id/srpTuple"));
			System.out.println("count is " + propList.size());
			@SuppressWarnings("unused")
			boolean flag = false;
			@SuppressWarnings("unused")
			int counter = 0;
			for (int i = 0; i < 500; i++) {
				for (WebElement prop : propList) {
						prop.click();
						driver.findElement(By.name("Navigate up")).click();
						flag = true;
					
				}
				if (flag = true) {
					swipeVertically();
					swipeVertically();
					swipeVertically();
				} else {
					break;

				}

			}
			
		} catch (Exception e) {
			swipeVertically();
			swipeVertically();
			swipeVertically();
			funtoos();
			
		}
	}

	public static void funtoos() {
		
		try{
		WebElement parent = driver.findElement(By
				.id("com.nnacres.app:id/searchResultsList"));
		List<WebElement> propList = parent.findElements(By
				.id("com.nnacres.app:id/srpTuple"));
		boolean flag = false;
		int counter = 0;
		for (int i = 0; i < 500; i++) {
				for (WebElement prop : propList) {
					prop.click();
					driver.findElement(By.name("Navigate up")).click();
					flag = true;
				}
				if (flag = true) {
					swipeVertically();
					swipeVertically();
					swipeVertically();
				} else {
					break;

		}

		}
		
		}catch(Exception e){
			swipeVertically();
			swipeVertically();
			swipeVertically();
			funtoos();
		}

	}
		

	public static void swipeVertically() {
		try{
		Dimension screenSize = driver.manage().window().getSize();
		int screenWidth = screenSize.getWidth() / 2;
		int screenHight = screenSize.getHeight();
		screenHight = screenHight - screenHight * 20 / 100;
		driver.swipe(screenWidth, screenHight, screenWidth, 0, 1000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void func() throws Exception {
		// setExcelFile(AppConstants.Path_TestData + AppConstants.File_TestData,
		// "Sheet2");

		driver.findElement(By.id("com.nnacres.app:id/footerAddContactButton"))
				.click();
		if (driver.findElements(By.id("com.nnacres.app:id/eoi_seller_name"))
				.size() > 0)
			sellername = driver.findElement(
					By.id("com.nnacres.app:id/eoi_seller_name")).getText();
		System.out.println(sellername);
		// setCellData(sellername, 2, 1);
		// /Yahan per issue aa raha yeh setCellData nhi chal raha h

		if (driver.findElements(By.id("com.nnacres.app:id/eoi_seller_company"))
				.size() > 0)
			sellerco = driver.findElement(
					By.id("com.nnacres.app:id/eoi_seller_company")).getText();
		System.out.println(sellerco);

		if (driver.findElements(By.id("com.nnacres.app:id/ac_mobile")).size() > 0)
			sellermob = driver.findElement(
					By.id("com.nnacres.app:id/ac_mobile")).getText();
		System.out.println(sellermob);
		if (driver.findElements(By.id("com.nnacres.app:id/ac_address")).size() > 0)
			selleradd = driver.findElement(
					By.id("com.nnacres.app:id/ac_address")).getText();
		System.out.println(selleradd);
		if (driver.findElements(By.id("com.nnacres.app:id/ac_city")).size() > 0)
			sellercity = driver
					.findElement(By.id("com.nnacres.app:id/ac_city")).getText();
		System.out.println(sellercity);

		driver.findElement(
				By.id("com.nnacres.app:id/btn_cancelAddContactLayer")).click();
		driver.findElement(By.id("com.nnacres.app:id/back")).click();
	}

	public void func1() throws Exception {
		driver.findElement(
				By.xpath("//android.widget.FrameLayout /android.widget.ListView /android.widget.RelativeLayout[1] /android.widget.TextView[1]"))
				.click();

		func();

		driver.findElement(
				By.xpath("//android.widget.FrameLayout /android.widget.ListView /android.widget.RelativeLayout[2] /android.widget.TextView[1]"))
				.click();

		func();
		driver.findElement(
				By.xpath("//android.widget.FrameLayout /android.widget.ListView /android.widget.RelativeLayout[3] /android.widget.TextView[1]"))
				.click();
		func();
		driver.findElement(
				By.xpath("//android.widget.FrameLayout /android.widget.ListView /android.widget.RelativeLayout[4] /android.widget.TextView[1]"))
				.click();
		func();
		driver.swipe(100, 1200, 100, 100, 5000);

	}
    private String random;
	public Acres(String s){
		this.random = s;
	}
	
	public String getName(){
		return random;
	}
	
	public Acres(){
		System.out.println("hi");
	}
	
	public static String hello(){
		String a = null;
		String b="h";
		String c="d";
		String tr= "try";
		String cat = "catch";
		String fin= "finally";
		int z=0;
		try{
			a= "hello";
			int m= 2/z;
			//System.exit(1);
			return tr;
			
			
//		}finally{
		}catch(Exception e){
			return cat;
		}
		finally{
			return fin;
		}
	
		
		
	}
	
	public static int getANumber(){
	    try{
	    	
//	    	int a=0;
//	    	int b=2;
//	    	int c=b/a;
//	    	System.out.println("calling system exit function");
	     //	System.exit(1);
	        return 7;
	    
	         
	        
	    } catch(ArithmeticException e){
	    	
	    	return 23;
	    }
	   
	    	finally {
	    
	        return 43;
	    }
	}
	
	public static String mutuable(){
		HashMap<String , String > hm = new HashMap<String, String >();
		hm.put("a", "a");
		hm.put("b", "b");
		hm.put("c", "c");
		hm.put("c", "c");
		hm.put(null, null);
		hm.put("d", null);
//		hm.put("t",null);
//		
		
		for(Entry<String, String> e : hm.entrySet()){
			System.out.println(e.getKey()+":::::"+e.getValue());
			
		}
		System.out.println("the size is :" +hm.size());
		
		String a2 = new String ("abc");
		String a1 = "abc";
		System.out.println(a1);
		String a= "abc";
		System.out.println(a1);
		if(a==a2){
			System.out.println("this is true");
		}else{
			System.out.println("this is fail");
		}
	    a.concat("dwefwfwefwf");
		return a;
		
	}
	
//	@Override
//	public String toString(){
//		return li==null? "li is empty":li.toString();
//		
//	}
	
	public static void main(String args[]) throws Exception {
		
		String tav ="Hello Word";
		String reversew = "";
		String reverse= "";
		String[] myString = new String[3];
		String[] tav1= tav.split(" ");
		myString[0] = tav.split(" ")[0];
		String str1= myString[0];
		myString[1] = tav.split(" ")[1];
		for(int k=0;k<tav1.length;k++){
			String tavant= tav1[k];
	    
	    for(int i= tavant.length()-1;i>=0;i--){
	    reverse= reverse + tavant.charAt(i)   ;
	    	
	    }
		}
	    
	    
		System.out.println("the reverse part is " + " "+ reversew  + reverse + " "+ " ");
		String text = hello();
		System.out.println(text);
		mutuable();
System.out.println("*******************************************************************");

		int no= getANumber();
		System.out.println(no);
		

	// String reverse logic......................
		System.out.println("*******************************************************************");
		String reverse1= "";
		String st = "nitin";
		System.out.println(st.length());
		//char[] ch= st.toCharArray();
		for(int i=st.length()-1;i>=0;i--){
			reverse1= reverse1 + st.charAt(i);
			System.out.println(reverse1);
		}
		
		System.out.println("*******************************************************************");
		
//		for(int r=0; r<5;r++){
//			for(int k=0;k<5-r;k++){
//				System.out.print("");
//			}
//			for(int t=0;t<=r;t++){
//				System.out.print("* ");
//			}
//			System.out.println();
//		}

System.out.println("*******************************************************************");

	//Pyramid prgramming logic........................	
		  for(int i=0;i<5;i++) {
		         for(int j=0;j<5-i;j++) {
		             System.out.print(" ");
		         }
		        for(int k=0;k<=i;k++) {
		            System.out.print("$ ");
		        }
		        System.out.println();  
		    }

//		
System.out.println("*******************************************************************");
		
  // How to add mutiple value for Single key using lIst
		HashMap<String,List<Acres>> hpv = new HashMap<String,List<Acres>>();
		
	    li = new ArrayList<Acres>();
	    li.add(new Acres("argneshu"));
		li.add(new Acres("shishir"));
		System.out.println(li.size());
		
		hpv.put("keygen", li);
		System.out.println("Logging hpv");
		
		for(Entry<String, List<Acres>> h: hpv.entrySet()){
			System.out.println(h.getKey() );
			for(int index= 0; index < ((List)h.getValue()).size(); index++){
				System.out.println(h.getValue().get(index).getName());
			}
			
		}
System.out.println("*******************************************************************");
	
		
		
//		Map<String, List<Acres>> peopleByForename = new HashMap<String, List<Acres>>();    
//
//		// populate it
//		List<Acres> people = new ArrayList<Acres>();
//		people.add(new Acres("Bob Smith"));
//		people.add(new Acres("Bob Jones"));
//		peopleByForename.put("Bob", people);
//
//		// read from it
//		List<Acres> bobs = peopleByForename["Bob"];
//		Acres bob1 = bobs[0];
//		Acres bob2 = bobs[1];
		
System.out.println("*******************************************************************");
		
	// Selection Sort...........	
		mutuable();
		int z = getANumber();
		System.out.println("z is :"+z);
		
		int[] a= {23,21,24,22,1,3,44};
		
		for (int i=0;i<a.length-1;i++){
			for(int j=i+1;j<a.length;j++){
				if(a[j]<a[i]){
					int temp = a[i];
					a[i]= a[j];
					a[j]= temp;
				}
			
			}

			System.out.println(a[i]);
		}
System.out.println("*******************************************************************");
	
		String s= "argneshu";
		System.out.println((s.charAt(0)) + s.substring(1));

		s=(s.charAt(0)) + s.substring(1);
		System.out.println((s.charAt(0)) + s.substring(1));
		
System.out.println("*******************************************************************");
		
//		List<String> li = new ArrayList<String>();
//		
//		int[] i = new int[2];
//		System.out.println(i[0]);
//		
//		String arr[]= {"1","2"};
//		li= Arrays.asList(arr);
//		Collections.reverse(li);
//	Iterator it =li.iterator();
//	while(it.hasNext()){
//		System.out.println(it.next());
//	}
//		short [] arr = new short[] {101,102,104,105,21,22,1,4,5,22,1000,1001,1002,999,9999};
//		Arrays.sort(arr);
//		short[] top4 = Arrays.copyOfRange(arr,0,10);
//		System.out.println(Arrays.toString(top4));
////		for (int i=0;i<li.size();i++){
//			System.out.println( li);
//		}
//		String a = mutuable();
//		System.out.println(a);
	//	System.out.println(Math.max(23, 24));
System.out.println("*******************************************************************");

// prgramming logic to find duplicates in array ..................	

	String  arr[] ={"1","2","3","1"};
	List li = Arrays.asList(arr);
	System.out.println("size of list is :" + li.size());
	Set set = new HashSet(li);
	System.out.println("size of set is : " + set.size());
	if(set.size()<li.size()){
		System.out.println("duplicates in array");
	}else{
		System.out.println("no duplicates in array");
	}
	
System.out.println("*******************************************************************");
	
// Second programmming logic to find duplicates in array	
System.out.println("*******************************************************************");
	
	Set set1 = new HashSet();
	for(String s1: arr){
		if(!set1.add(s1)){
		System.out.println("set1 is :"+set1);
	}else{
		System.out.println("in else clause");
	}
	}
	
System.out.println("*******************************************************************");
	
//	
//	int new1[] = {1,2,3,4,4,5,5,6,5};
//	Arrays.sort(new1);
//	for(int i: new1){
//		System.out.println(i);
//	}
//	int count = 0;
//	for(int i=1; i<new1.length;i++){
//		if(new1[i]==new1[i-1]){
//			System.out.println("duplicate element is " + new1[i]);
//			count++;
//		}
//		System.out.println(count);
//	}
//	int large= 0;
//	int second= 0;
//	 for(int i=1; i<a.length;i++){
//		 if (a[i]>large){
//			 second= large;
//			 large= a[i];
//		 }else if(a[i]>large){
//			 second = a[i];
//		 }
//	 }
//	 Arrays.sort(a);
	// System.out.println(a(a.length-2));
	// System.out.println("large no is " + large);
	// System.out.println("second small no is " + second);
     
	 
//		int a= getANumber();
//		System.out.println(a);
//	
System.out.println("*******************************************************************");

		Acres ac= new Acres("hello");
		Acres ac1= new Acres("hello");
		
		String ac2= "hello";
		String ac3= "hello";
		
		System.out.println("the ac is " +ac);
		System.out.println("the ac1 is " +ac1);
		try{
			System.out.println("in try");
		if(ac==ac1){
			System.out.println("compare");
		}
		if(ac.equals(ac1)){
			System.out.println("equals");
		}if(ac2==ac3){
			System.out.println("String literal compare");
		}if(ac2.equals(ac3)){
			System.out.println("String literal equals");
		}
		if(ac.equals(ac2)){
			System.out.println("literal and object compare");
		}
		
		}catch(Exception e){
			System.out.println("in catch");
		}
		
System.out.println("*******************************************************************");

	}

	// public static void setCellData(String Result, int RowNum, int ColNum)
	// throws Exception {
	// System.out.println(RowNum);
	// System.out.println(ColNum);
	// try{
	//
	//
	// Row = ExcelWSheet.getRow(RowNum);
	// System.out.println(Row);
	//
	// Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
	// System.out.println(Cell);
	//
	// if (Cell == null) {
	//
	//
	// Cell = Row.createCell(ColNum);
	//
	// Cell.setCellValue(Result);
	//
	// } else {
	//
	// Cell.setCellValue(Result);
	//
	// }
	//
	// // Constant variables Test Data path and Test Data file name
	//
	// FileOutputStream fileOut = new
	// FileOutputStream(AppConstants.Path_TestData +
	// AppConstants.File_TestData);
	//
	// ExcelWBook.write(fileOut);
	//
	// fileOut.flush();
	//
	// fileOut.close();
	//
	// }catch(Exception e){
	//
	// throw (e);
	//
	// }
	//
	// }

	public static void setExcelFile(String Path, String SheetName)
			throws Exception {

		try {

			// Open the Excel file

			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet

			// ExcelWBook = new XSSFWorkbook(ExcelFile);
			//
			// ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} catch (Exception e) {

			throw (e);

		}

	}

	public static void main1(String args[]) {
		int arrayBeforeSort[] = { 22, 1, 3, 1, 45, 12, 11, 98, 71 };
		for (int i = 0; i < arrayBeforeSort.length; i++) {
			System.out.println(arrayBeforeSort[i]);

			int n = arrayBeforeSort.length;
		}
		int temp;
		int n = arrayBeforeSort.length;
		for (int y = 0; y < n; y++) {
			for (int z = 1; z < n - y; z++) {
				if (arrayBeforeSort[z - 1] > arrayBeforeSort[z]) {
					temp = arrayBeforeSort[z - 1];
					arrayBeforeSort[z - 1] = arrayBeforeSort[z];
					arrayBeforeSort[z] = temp;
					System.out.println(arrayBeforeSort[z]);
				}
			}

		}

	}

}
