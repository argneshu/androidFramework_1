package App_Functions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.json.JSONException;

import Utility.ParamObject;
import Utility.RestClient;

public class LenskartStores {

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
		}
		}
	public static void main(String[] args) throws IOException, JSONException {
		Store();

	}

}
