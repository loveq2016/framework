package org.apache.framework.util;

public class NullUtils {

	public static <T> T replaceNull(T value, T defaultValue) {
		if (value == null) {
			return defaultValue;
		} else {
			return value;
		}
	}
}
