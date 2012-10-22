package kr.co.webcash.utils;

import org.junit.Test;

public class EncryptUtilsTest {
	
	@Test
	public void encrypt(){
		
		String loginId = "kwakyc87";
		String password = "12345678";
		
//		String encryptedPassword = EncryptUtils.encrypt(loginId, password);
//		assertNotNull(encryptedPassword);
//		
//		String decryptedPassword = EncryptUtils.descrypt(encryptedPassword, password);
//		assertThat(decryptedPassword, is(loginId));
	}
	
	@Test
	public void lengthTest(){
		String[] passwords = {
				"12345678",
				"123456789",
				"1234567891",
				"12345678912",
				"123456789123",
				"12345678912345",
				"123456789123456",
				"1234567891234567",
				"12345678912345671234567891234567",
				"1234567891234567123456789123456712345678912345671234567891234567"
		};
		
		for(String password : passwords){
			
			System.out.println(EncryptUtils.encrypt(password));
			
		}
		
		System.out.println(EncryptUtils.encrypt("password"));
	}
}