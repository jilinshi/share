package com.share.util;

public class RegexUtil {
	public static boolean checkNumber(double value) {
		String str = String.valueOf(value);
		String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
		return str.matches(regex);
	}

	public static boolean checkNumber(int value) {
		String str = String.valueOf(value);
		String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
		return str.matches(regex);
	}

	public static boolean checkNumber(String value) {
		String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
		return value.matches(regex);
	}
}
