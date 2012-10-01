package kr.co.webcash.utils;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import kr.co.webcash.domain.Trackback;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class TrackbackUtils {

	public static boolean trackback(String trackbackURL, Trackback trackback) {
		try{
			DOMParser parser = new DOMParser();
			
			URL url = new URL(trackbackURL);
			
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.connect();
			
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());
			StringBuilder builder = new StringBuilder();
			builder.append(getEncodingString("url")).append("=").append(getEncodingString(trackback.getUrl()))
				.append("&").append(getEncodingString("blog_name")).append("=").append(getEncodingString(trackback.getBlogId()))
				.append("&").append(getEncodingString("title")).append("=").append(getEncodingString(trackback.getTitle()))
				.append("&").append(getEncodingString("excerpt")).append("=").append(getEncodingString(trackback.getTitle()));
			
			String data = builder.toString();
			
			writer.write(data);
			writer.flush();
			
			parser.parse(new InputSource(connection.getInputStream()));
			Document doc = parser.getDocument();
			
			NodeList list = doc.getChildNodes().item(0).getChildNodes();
			
			for (int i = 0; i < list.getLength(); i++) {
				Node item = list.item(i);
				if("error".equals(item.getNodeName())){
					if("0".equals(item.getTextContent())){
						return true;
					}
				}
			}
		}catch(Exception e){
		}
		
		return false;
	}
	
	private static String getEncodingString(String str) {
		try{
			return URLEncoder.encode(str, "UTF-8");
		}catch(Exception e){
			return "";
		}
	}


}
