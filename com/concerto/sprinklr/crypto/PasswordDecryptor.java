//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.crypto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordDecryptor {
	private SecretKeySpec key;
	byte[] salt = "1234567".getBytes();
	int iterationCount = 40000;
	int keyLength = 128;

	public static PasswordDecryptor newInstance(String configurationId) throws NoSuchAlgorithmException, InvalidKeySpecException {
		return new PasswordDecryptor(configurationId);
	}

	public PasswordDecryptor(String configurationId) throws NoSuchAlgorithmException, InvalidKeySpecException {
		this.key = this.createSecretKey(configurationId.toCharArray(), this.salt, this.iterationCount, this.keyLength);
	}

	private SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
		PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
		SecretKey keyTmp = keyFactory.generateSecret(keySpec);
		return new SecretKeySpec(keyTmp.getEncoded(), "AES");
	}

	public String decrypt(String encryptedData) throws GeneralSecurityException, IOException {
		Cipher pbeCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		pbeCipher.init(2, this.key);
		return new String(pbeCipher.doFinal(this.base64Decode(encryptedData)), StandardCharsets.UTF_8);
	}

	private byte[] base64Decode(String property) {
		return Base64.getDecoder().decode(property);
	}
}
