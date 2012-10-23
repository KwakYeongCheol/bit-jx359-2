package kr.co.webcash.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
public class UserTest {
	
	@Test
	public void sex(){
		Sex male = Sex.valueOf("male");
		assertThat(male, is(Sex.male));
		
		Sex female = Sex.valueOf("female");
		assertThat(female, is(Sex.female));
	}
	
	@Test
	public void birthday(){
		int year = 2012;
		int month = 12;
		int day = 31;
		
		String birth = year + "-" + month + "-" + day;
		
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(birth);
			
			System.out.println(date);
		} catch (ParseException e) {
		}
		
		
		
	}

}
