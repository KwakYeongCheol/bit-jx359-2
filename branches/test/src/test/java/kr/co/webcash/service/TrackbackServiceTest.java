package kr.co.webcash.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.xerces.parsers.DOMParser;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class TrackbackServiceTest {
	
	@Test
	public void xmlParserTest() throws Exception{
		DOMParser parser = new DOMParser();
		
		URL url = new URL("http://kwakyc87.tistory.com/trackback/151");
//		URL url = new URL("http://localhost:8080/trackback/1/100");
		
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.connect();
		
		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		StringBuilder builder = new StringBuilder();
		builder.append(getEncodingString("url")).append("=").append(getEncodingString("http://127.0.0.1:8080/5/1"))
			.append("&").append(getEncodingString("blog_name")).append("=").append(getEncodingString("BlogName ::"))
			.append("&").append(getEncodingString("title")).append("=").append(getEncodingString("TitleMessage :("))
			.append("&").append(getEncodingString("excerpt")).append("=").append(getEncodingString("Excerpt :D"));
		
		String data = builder.toString();
		System.out.println(data);
		
		writer.write(data);
		writer.flush();
		
		
		parser.parse(new InputSource(connection.getInputStream()));
		Document doc = parser.getDocument();
		
		NodeList list = doc.getChildNodes().item(0).getChildNodes();
		
		for(int i = 0; i < list.getLength(); i++){
			Node item = list.item(i);
			System.out.println(item.getNodeName() + " | " + item.getTextContent());
		}
	}
	
	public void main() throws Exception{
		URL url = new URL("http://kwakyc87.tistory.com/trackback/149");
		
		URLConnection connection = url.openConnection();
		
		connection.setDoOutput(true);
		connection.setDoInput(true);
//		connection.setRequestMethod("POST");
		
//		connection.setRequestProperty("Content-Type", "text/plain");
//		connection.setRequestProperty("charset", "utf-8");
		connection.connect();
		
		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		
		StringBuilder builder = new StringBuilder();
		builder.append(getEncodingString("url")).append("=").append(getEncodingString("http://127.0.0.1:8080/1/1"))
			.append("&").append(getEncodingString("blog_name")).append("=").append(getEncodingString("BlogName ::"))
			.append("&").append(getEncodingString("title")).append("=").append(getEncodingString("TitleMessage :("))
			.append("&").append(getEncodingString("excerpt")).append("=").append(getEncodingString("Excerpt :D"));
		
		String data = builder.toString();
		System.out.println(data);
		
		writer.write(data);
		writer.flush();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		String line;
		
		while((line = br.readLine()) != null){
			System.out.println(line);
		}
		
//		connection.disconnect();
	}

	private String getEncodingString(String str) {
		try{
			return URLEncoder.encode(str, "UTF-8");
		}catch(Exception e){
			return "";
		}
	}

}
