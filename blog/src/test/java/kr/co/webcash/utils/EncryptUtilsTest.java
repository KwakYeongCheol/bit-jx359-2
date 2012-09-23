package kr.co.webcash.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class EncryptUtilsTest {
	
	@Test
	public void encrypt(){
		
		String loginId = "kwakyc87";
		String password = "12345678";
		
		String encryptedPassword = EncryptUtils.encrypt(loginId, password);
		assertNotNull(encryptedPassword);
		
		String decryptedPassword = EncryptUtils.descrypt(encryptedPassword, password);
		assertThat(decryptedPassword, is(loginId));
	}
	
}
