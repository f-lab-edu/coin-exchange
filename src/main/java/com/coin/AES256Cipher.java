package com.coin;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES256Cipher {
	
	private final static String privateKey = "AES_PRIVATE_KEY_TEST_KEY_32BYTES";
	
	public static String aesEncode(String str) throws Exception{
		SecretKeySpec secretKey = new SecretKeySpec(privateKey.getBytes("UTF-8"), "AES");
		IvParameterSpec IV = new IvParameterSpec(privateKey.substring(0, 16).getBytes());
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		c.init(Cipher.ENCRYPT_MODE, secretKey, IV);

        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));

		return Base64.getEncoder().encodeToString(encrypted);
	}
	
}
