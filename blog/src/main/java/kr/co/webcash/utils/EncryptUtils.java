package kr.co.webcash.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class EncryptUtils {
	public static final String algorithm = "DES";
	
	public static Key generateKey(String password) {
		try{
			DESKeySpec desKeySpec = new DESKeySpec(password.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
			
			return keyFactory.generateSecret(desKeySpec);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static String encrypt(String loginUserId, Key key) {
		try{
			Cipher c = Cipher.getInstance(algorithm);
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encrypted = c.doFinal(loginUserId.getBytes());
			return toHexString(encrypted);
		}catch(Exception e){
			System.out.println("encrypt(String, Key) exception");
			return null;
		}
		
	}

	public static String descrypt(byte[] encryptedResult, Key key) {
		try{
			Cipher c = Cipher.getInstance(algorithm);
			c.init(Cipher.DECRYPT_MODE, key);
			return new String(c.doFinal(encryptedResult));
		}catch(Exception e){
			System.out.println("descrypt(byte[], Key) exception");
			return null;
		}
	}
	
	public static String encrypt(String loginId, String password) {
		return encrypt(loginId, generateKey(password));
	}

	public static String descrypt(String encryptedPassword, String password) {
		return descrypt(toBytesFromHexString(encryptedPassword), generateKey(password));
	}
	
	public static String toHexString(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		
		StringBuffer result = new StringBuffer();
		for (byte b : bytes) {
			result.append(Integer.toString((b & 0xF0) >> 4, 16));
			result.append(Integer.toString(b & 0x0F, 16));
		}
		return result.toString();
	}
	
	public static byte[] toBytesFromHexString(String digits) throws IllegalArgumentException, NumberFormatException {
		if (digits == null) {
			return null;
		}
    	int length = digits.length();
    	if (length % 2 == 1) {
    		throw new IllegalArgumentException("For input string: \"" + digits + "\"");
    	}
    	length = length / 2;
    	byte[] bytes = new byte[length];
    	for (int i = 0; i < length; i++) {
    		int index = i * 2;
    		bytes[i] = (byte)(Short.parseShort(digits.substring(index, index+2), 16));
    	}
    	return bytes;
	}
}
