package kr.co.webcash.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

public class BlogVisitHistoryTest {
	
	@Test
	public void dateRange(){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
			Date start = format.parse("2012-7-1");
			Date end = format.parse("2012-10-32");
			
			Calendar today = Calendar.getInstance();
			
			today.add(Calendar.MONTH, 10);
			
			System.out.println(today.get(Calendar.YEAR));
			System.out.println(today.get(Calendar.MONTH));
			System.out.println(today.get(Calendar.DAY_OF_MONTH));
			
			System.out.println(start);
			System.out.println(end);
		} catch (ParseException e) {
		}
		
	}

}
