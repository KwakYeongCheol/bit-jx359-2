package kr.co.webcash.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.xerces.impl.dv.util.Base64;

public class EncryptUtils {
	private static final String ALGORITHM = "AES";
	
	private static final byte[] key = new byte[] {
		'S', 'h', 'o', 'w', 'M', 'e', 'T', 'h', 
		'e', 'M', 'o', 'n', 'e', 'y', 'o', 'h'
	};
	
	public static String encrypt(String data){
		try{
			Key key = generateKey();
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encValue = c.doFinal(data.getBytes());
			return new Base64().encode(encValue);
		}catch(Exception e){
			return null;
		}
	}
	
	private static Key generateKey(){
		return new SecretKeySpec(key, ALGORITHM);
	}
}
