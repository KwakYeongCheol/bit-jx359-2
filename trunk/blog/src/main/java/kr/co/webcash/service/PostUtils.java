package kr.co.webcash.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostUtils {
	
	private static final String SCRAP_URL_REGEX = "@@[^[a-zA-Z]][a-zA-Z0-9]+/[0-9]+##";

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

}
