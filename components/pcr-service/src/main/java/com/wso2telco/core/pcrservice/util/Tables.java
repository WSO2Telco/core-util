package com.wso2telco.core.pcrservice.util;

public enum Tables {
	
	TABLE_PCTUSER("pctuser"), 
	TABLE_PCTAPPLICATION("pctapplication"), 
	TABLE_PCTSECTOR("pctsector"),
	TABLE_PCTUSERASSIGNMENT("pctuserassignment");

	private final String value;

	Tables(String val) {
		value = val;
	}

	@Override
	public String toString() {
		return value;
	}
}