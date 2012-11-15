package kr.co.webcash.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class BlogVisitHistoryUtils {
	
	@Test
	public void make(){
		Calendar cal = Calendar.getInstance();
		
		Map<String, Integer> parseResult = new HashMap<String, Integer>();
		
		String[] arr = {"2012-11-15", "2012-11-15", "2012-11-12"};
		
		for(String str : arr){
			Integer currentCount = parseResult.get(str);
			
			if(currentCount == null){
				parseResult.put(str, 1);
			}else{
				parseResult.put(str, currentCount + 1);
			}
		}
		
		System.out.println(parseResult);
		
		
		int start = 1;
		int end = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		
		for(int i = start; i <= end; i++){
			cal.set(Calendar.DAY_OF_MONTH, i);
			
		}
		
		System.out.println(result);
	}
}
