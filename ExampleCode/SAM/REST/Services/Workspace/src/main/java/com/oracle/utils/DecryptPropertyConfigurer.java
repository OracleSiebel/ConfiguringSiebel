package com.oracle.utils;

import java.util.Enumeration;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.ObjectUtils;


public class DecryptPropertyConfigurer extends PropertyPlaceholderConfigurer
{	
	public DecryptPropertyConfigurer() {
		
	}

	AESCrypter decrypter;
	public static String version = "";
 @Override
 protected void convertProperties(Properties props)
 {
  Enumeration<?> propertyNames = props.propertyNames();
  while (propertyNames.hasMoreElements()) {
   String propertyName = (String) propertyNames.nextElement();
   String propertyValue = props.getProperty(propertyName);
   //System.out.println("Property: " + propertyName + ", Values: " + propertyValue);
   if(propertyName.equals("jdbc.password")) {
	   String convertedValue;
	try {
			decrypter = new AESCrypter();
			convertedValue = decrypter.decrypt(propertyValue);
			if (!ObjectUtils.nullSafeEquals(propertyValue, convertedValue)) {
				props.setProperty(propertyName, convertedValue);
		   }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   //Bug 25721766 - VERSION NUMBER SHOULD BE DISPLAYED IN THE APPLICATION 
   else if(propertyName.equals("sam.version")) {
	   version = propertyValue;
   }
   else {
	   props.setProperty(propertyName, propertyValue);
   }
  }
 }
 
 public String getVersion() {
	 return version;
 }
}