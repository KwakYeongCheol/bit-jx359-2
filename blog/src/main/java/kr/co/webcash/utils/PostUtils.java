package kr.co.webcash.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostUtils {
	
	private static final String SCRAP_URL_REGEX = "@@[a-zA-Z0-9]+/[0-9]+##";
	
	public static List<Map<String, String>> parseToMap(String data){
		return parseToMap(getScrapURLFromContents(data));
	}

	public static List<String> getScrapURLFromContents(String data) {
		Set<String> set = new HashSet<String>();
		
		Pattern p = Pattern.compile(SCRAP_URL_REGEX);
		
		Matcher m = p.matcher(data);
		
		boolean find = false;
		
		while(find = m.find()){
			set.add(m.group());
		}
		
		return new ArrayList(set);
	}

	public static List<Map<String, String>> parseToMap(List<String> scrapURLList) {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		
		for(String scrapURL : scrapURLList){
			Map<String, String> map = parseToBlogIdAndPostIdPair(scrapURL);
			if(map == null)		continue;
			
			resultList.add(map);
		}
		
		return resultList;
	}
	
	public static Map<String, String> parseToBlogIdAndPostIdPair(String scrapURL){
		String[] arr = scrapURL.split("/");
		
		if(arr.length != 2)		return null;
		
		String prefix = "@@";
		String suffix = "##";
		
		if(!arr[0].contains(prefix)  || !arr[1].contains(suffix))		return null;
		
		String blogId = arr[0].substring(prefix.length());
		String postDisplayId = arr[1].substring(0, arr[1].length() - suffix.length());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("blogId", blogId);
		map.put("postDisplayId", postDisplayId);
		
		return map;
	}
}
