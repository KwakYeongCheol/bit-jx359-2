package kr.co.webcash.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class HttpRequestorTest {
	
	@Test
	public void test() throws IOException, JSONException{
		URL url = new URL("http://117.17.100.142:9000/image_hosting/upload");
		HttpRequestor requestor = new HttpRequestor(url);
		
		requestor.addParameter("userId", "kwakyc87");
		requestor.addFile("image", new File("/Users/kwakyc87/suji.jpeg"));
		
		InputStream is = requestor.sendMultipartPost();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		StringBuilder builder = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null){
			builder.append(line);
		}
		
		JSONObject json = new JSONObject(builder.toString());
		System.out.println(json.get("success"));
		System.out.println(json.get("image_url"));
		System.out.println(json.get("thumbnail_url"));
	}

}
