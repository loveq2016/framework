package org.apache.framework.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

public final class Config {

	private static Properties defaultConfig = null;
	
	private static Map<String, Properties>  configs = new HashMap<String, Properties>();
	
	static {
		try {
			defaultConfig = PropertiesLoaderUtils.loadAllProperties("config.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/** 
	 * 取配置文件中的值 如果没有 就 返回""
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return defaultConfig.getProperty(key, "");
	}
	
	public static String getValue(String fileName, String key) {
		Properties properties = configs.get(fileName);
		if (properties == null) {
			try {
				properties = PropertiesLoaderUtils.loadAllProperties(fileName+".properties");
				configs.put(fileName, properties);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties.getProperty(key, "");
	}
	
}

