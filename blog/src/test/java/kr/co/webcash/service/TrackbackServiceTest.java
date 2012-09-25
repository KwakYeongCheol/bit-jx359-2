package kr.co.webcash.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.junit.Test;

public class TrackbackServiceTest {
	
	@Test
	public void main() throws Exception{
		URL url = new URL("http://kwakyc87.tistory.com/trackback/149");
		
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		
		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		
		StringBuilder builder = new StringBuilder();
		builder.append(getEncodingString("url")).append("=").append(getEncodingString("http://localhost:8080/1/1"))
			.append("&").append(getEncodingString("blog_name")).append("=").append(getEncodingString("블로그네임"))
			.append("&").append(getEncodingString("title")).append("=").append(getEncodingString("안녕하세요"))
			.append("&").append(getEncodingString("excerpt")).append("=").append(getEncodingString("짧은 요약~~ :D"));
		
		String data = builder.toString();
		System.out.println(data);
		writer.write(data);
		writer.flush();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		String line;
		
		while((line = br.readLine()) != null){
			System.out.println(line);
		}
		
		
		
	}

	private String getEncodingString(String str) {
		try{
			return URLEncoder.encode(str, "UTF-8");
		}catch(Exception e){
			return "";
		}
	}

}
