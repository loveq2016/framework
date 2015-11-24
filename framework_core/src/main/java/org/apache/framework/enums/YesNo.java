package org.apache.framework.enums;

import java.util.TreeMap;
import java.util.Map;

/**
 * 订单状态 code
 * @author Administrator
 *
 */
public enum YesNo {

	YES ("Y", "是"),   
    NO("N", "否"); 
	
	private String code;   
	private String text;   // in meters   

	YesNo(String code, String text)   
    {   
        this.code = code;   
        this.text = text;   
    }
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static Map<String,String> getMap() {
		YesNo[] array = values();
		Map<String,String> map = new TreeMap<String, String>();
		for (YesNo payStatus : array) {
			map.put(payStatus.getCode(), payStatus.getText());
		}
		return map;
	}
	
	public static String getText(String code) {
		YesNo[] array = values();
		Map<String,String> map = new TreeMap<String, String>();
		for (YesNo payStatus : array) {
			map.put(payStatus.getCode(), payStatus.getText());
		}
		return map.get(code);
	}
	
	public static String getCode(String text) {
		YesNo[] array = values();
		Map<String, String> map = new TreeMap<String, String>();
		for (YesNo payStatus : array) {
			map.put(payStatus.getText(), payStatus.getCode());
		}
		return map.get(text);
	}
}
