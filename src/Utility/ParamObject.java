package Utility;

import java.util.HashMap;

public class ParamObject {

	String methodType;
	HashMap<String, String> headers = new HashMap<>();
	String payload;
	String url;
	String auth;
	String pass;
	HashMap<String, String> autho = new HashMap<>();
	
	public void setautho(String key1, String value1){
		autho.put(key1, value1);
	}
	
	public String getauthusername(){
		return "auth";
	}
	
	public void setStringusername(String auth){
		this.auth= auth;
	}
	
	public String getauthpass(){
		return "pass";
	}
	
	public void setStringpass(String pass){
		this.pass= pass;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	public void addHeader(String key, String value){
		headers.put(key, value);
	}
}

