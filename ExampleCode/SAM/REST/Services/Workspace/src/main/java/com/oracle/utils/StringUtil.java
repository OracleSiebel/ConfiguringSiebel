package com.oracle.utils;

public class StringUtil {

	public static boolean isValidString(String val) {
		if (val != null && !val.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isNull(String val) {
		return val==null;
	}
	
	public static Object assignDataType(String obj){
		if((obj.equalsIgnoreCase("true")) ||
		  (obj.equalsIgnoreCase("Yes")) ||
		  (obj.equalsIgnoreCase("Y")) ||
		  (obj.equalsIgnoreCase("t")) ||
		  (obj.equalsIgnoreCase("1"))){
			  return new Boolean(true);
		  }
		if((obj.equalsIgnoreCase("false")) ||
				  (obj.equalsIgnoreCase("No")) ||
				  (obj.equalsIgnoreCase("N")) ||
				  (obj.equalsIgnoreCase("F")) ||
				  (obj.equalsIgnoreCase("0"))){
					  return new Boolean(false);
		}
		try{
			return Integer.parseInt(obj, 10);
		}catch(NumberFormatException nfe){
			//TODO exception handling;
		}
		return obj;
	}

}
