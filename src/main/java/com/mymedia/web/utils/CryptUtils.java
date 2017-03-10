package com.mymedia.web.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CryptUtils {

	private static final Logger LOG = LogManager.getLogger(CryptUtils.class);
	private static final String UTF8 = "UTF-8";
	private static final String MD5 = "MD5";
	private static final String SHA1 = "SHA-1";

	private CryptUtils() {
		// utility class
	}

	public static String encodeBase64(byte[] bytes){
    	return Base64.getEncoder().encodeToString(bytes);
    }
    public static byte[] decodeBase64(String s){
    	return Base64.getDecoder().decode(s);
    }
		
	public static String generateHashMD5(String str) {
		return generateHashMD5(toByteArray(str));
	}
	public static String generateHashMD5(byte[] bytes) {
		return generateHash(bytes, MD5);
	}
	public static String generateHashSHA1(String str) {
		return generateHashSHA1(toByteArray(str));
	}
	public static String generateHashSHA1(byte[] bytes) {
		return generateHash(bytes, SHA1);
	}
	
	private static String generateHash(byte[] bytes, String algorithm) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte[] hashedBytes = digest.digest(bytes);
			return toHexString(hashedBytes);

		} catch (NoSuchAlgorithmException e) {
			LOG.error("Could not generate hash", e);
			return "error";
		}
	}

	private static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			String hex = Integer.toString(Byte.toUnsignedInt(b),16);
			sb.append(hex.length() == 1 ? "0" : "").append(hex);
		}
		return sb.toString();
	}

	private static byte[] toByteArray(String str) {
		String s = (str == null) ? null : str.trim();
		if (s == null || s.isEmpty()) {
			throw new IllegalArgumentException("String '" + str + "' cannot be converted to byte array");
		}
		try {
			return s.getBytes(UTF8);
		} catch (UnsupportedEncodingException e) {
			LOG.error("Exception occured on getting bytes from '" + s + "'", e);
			return new byte[] { 0 };
		}
	}

}
