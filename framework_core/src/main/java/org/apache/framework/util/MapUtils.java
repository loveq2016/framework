package org.apache.framework.util;

import java.util.Map;

public class MapUtils {

	public static String getValue(Map<String, Object> map, String key) {
		return getValue(map, key, null);
	}

	public static String getValue(Map<String, Object> map, String key, String defaultValue) {
		for(Map.Entry<String, Object> entry: map.entrySet()) {
			if (entry.getKey().equals(key)) {
				return entry.getValue().toString();
			}
		}
		return defaultValue;
	}
}
