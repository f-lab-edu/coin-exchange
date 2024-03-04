package com.coin;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES256Cipher {
	
	private static AES256Cipher INSTANCE;
	private final static String privateKey = "AES_PRIVATE_KEY_TEST_KEY_32BYTES";
	static String IV = "";
	
	public static AES256Cipher getInstance() {
		if (INSTANCE == null) {
            synchronized (AES256Cipher.class) {
                if (INSTANCE == null)
                    INSTANCE = new AES256Cipher();
            }
        }
        return INSTANCE;
	}
	
	private AES256Cipher() {
        IV = privateKey.substring(0, 16);
    }
	
	public static String aesEncode(String str) { 
		byte[] encrypted = null;
		try {
			SecretKeySpec secretKey = new SecretKeySpec(privateKey.getBytes("UTF-8"), "AES");
			IvParameterSpec IV = new IvParameterSpec(privateKey.substring(0, 16).getBytes());
			
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			
			c.init(Cipher.ENCRYPT_MODE, secretKey, IV);

	        encrypted = c.doFinal(str.getBytes("UTF-8"));
			
		} catch (Exception e) {
			
		}
		
		return Base64.getEncoder().encodeToString(encrypted);
	}
	
	
}
