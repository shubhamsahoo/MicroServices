package com.connector.rest.util;

import org.apache.commons.codec.digest.DigestUtils;

public final class PasswordEncoderUtil {

	private PasswordEncoderUtil() {
	}

	public static String encodeToSha256(String password) {
		return DigestUtils.sha256Hex(password).toUpperCase();
	}

	public static boolean isPasswordMatch(String password, String encodedPassword) {
		return DigestUtils.sha256Hex(password).equalsIgnoreCase(encodedPassword);
	}
}