package kr.co.webcash.utils;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class URLUtilsTest {
	
	@Test
	public void getRequestURL(){
		String requestURL = URLUtils.getRequestURL("/1", "/");
		assertThat(requestURL, is("/1"));
		
		requestURL = URLUtils.getRequestURL("/1/admin", "/");
		assertThat(requestURL, is("/1/admin"));
		
		requestURL = URLUtils.getRequestURL("/webcash/1/visitor", "/webcash");
		assertThat(requestURL, is("/1/visitor"));
		
		requestURL = URLUtils.getRequestURL("/webcash/1/admin", "/webcash");
		assertThat(requestURL, is("/1/admin"));
	}
	
	@Test
	public void getBlogId(){
		assertThat(URLUtils.getBlogId("/", "/"), is(""));
		
		assertThat(URLUtils.getBlogId("/1", "/"), is("1"));
		assertThat(URLUtils.getBlogId("/1/visitor", "/"), is("1"));
		
		assertThat(URLUtils.getBlogId("/webcash/1", "/webcash"), is("1"));
		assertThat(URLUtils.getBlogId("/webcash/1/admin", "/webcash"), is("1"));
	}

}
