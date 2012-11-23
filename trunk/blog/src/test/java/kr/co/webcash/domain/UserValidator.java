package kr.co.webcash.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.regex.Pattern;

import org.junit.Test;

public class UserValidator {
	
	@Test
	public void validate(){
		String regex = "[a-zA-Z0-9]+@([a-zA-Z0-9]+[.])+[a-zA-Z0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		
		assertThat(pattern.matcher("kwakyc87").find(), is(false));
		assertThat(pattern.matcher("kwakyc87@").find(), is(false));
		assertThat(pattern.matcher("kwakyc87@sample").find(), is(false));
		assertThat(pattern.matcher("7@sample").find(), is(false));
		assertThat(pattern.matcher("kwakyc87@sample.kr").find(), is(true));
		assertThat(pattern.matcher("kwakyc87@sample.kr.").find(), is(false));
		assertThat(pattern.matcher("kwakyc87@sample.co.kr").find(), is(true));
		assertThat(pattern.matcher("kwakyc87@sample.co.kr.").find(), is(false));
		assertThat(pattern.matcher("kwakyc87@sample.co.kr.abc").find(), is(true));
		assertThat(pattern.matcher("kwakyc87@sample.co.kr.abc.").find(), is(false));
		assertThat(pattern.matcher("kwakyc87@한글.co.kr").find(), is(false));
	}
	
	@Test
	public void small(){
		String name = "KwakYC87@gmail.CoM";
		
		System.out.println(name.toLowerCase());
		System.out.println(name);
	}

}
