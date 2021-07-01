package com.librarymgmt.utility;

public enum ResponseUtils {

	BAD_ISBN_NUMBER("400","BAD REQUEST");

	private String key;
	
	private String value;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	ResponseUtils(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
