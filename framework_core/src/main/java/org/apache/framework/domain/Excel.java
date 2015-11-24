package org.apache.framework.domain;

import java.io.Serializable;

public class Excel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8102538874238141573L;

	private String propertyName;
	
	private String titleName;
	
	private String pattern;

	private String validateRegexPattern;
	
	public Excel(String propertyName, String titleName) {
		this.propertyName = propertyName;
		this.titleName = titleName;
	}

	public Excel(String propertyName, String titleName, String validateRegexPattern) {
		this.propertyName = propertyName;
		this.titleName = titleName;
		this.validateRegexPattern = validateRegexPattern;
	}
	
	public Excel(String propertyName, String titleName, String pattern,String validateRegexPattern) {
		this.propertyName = propertyName;
		this.titleName = titleName;
		this.pattern = pattern;
		this.validateRegexPattern = validateRegexPattern;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getValidateRegexPattern() {
		return validateRegexPattern;
	}

	public void setValidateRegexPattern(String validateRegexPattern) {
		this.validateRegexPattern = validateRegexPattern;
	}

}
