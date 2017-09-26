package com.oracle.utils;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service("genericUtil")

public class Utility {
	
	public static boolean isNull(String text) {
		return null==text;
	}
	
	public static boolean isNullObject(Object obj) {
		return null==obj;
	}
	
	public static String getNullCheckedString(String text) {
		if(isNull(text))
			return text;
		
		return text.trim();
	}
	
	public static Object getObjectValue(Object obj) {
		Object returnObj = null;
		if(obj == null)
			return null;
		
		if(obj instanceof String){
			return getNullCheckedString((String)obj);
		}
		
		if(obj instanceof Long) {
			if(isNull((String) obj))
				return null;
			
			return obj;
		}
		
		return returnObj;
	}

	public static String getApplicationHostURL(HttpServletRequest request) throws MalformedURLException {		
		String url = "";
		
		if(null==request)
			return url;
		
		StringBuilder sb = new StringBuilder();
		URL requestURL = new URL(request.getRequestURL().toString());
	    
	    sb.append(requestURL.getProtocol())
	    	.append("://")
	    	.append(requestURL.getHost())
	    	.append(requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort());
		
		return sb.toString();		
	}
	
	public static String getApplicationContextURL(HttpServletRequest request) throws MalformedURLException {		
		String url = "";
		
		if(null==request)
			return url;
		
		StringBuilder sb = new StringBuilder();
		URL requestURL = new URL(request.getRequestURL().toString());
	    
	    sb.append(requestURL.getProtocol())
	    	.append("://")
	    	.append(requestURL.getHost())
	    	.append(requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort())
	    	.append(request.getContextPath());
		
		return sb.toString();		
	}
	
	public static String getEmailApprovalButtonCSS() {
		StringBuilder cssButtonBuilder = new StringBuilder();
		
		 cssButtonBuilder.append("-webkit-appearance: button;")
		    .append("cursor: pointer;")
		    .append("color: #fff;")
		    .append("background-color: #385427;")
		    .append("border-color: #385427;")
		    .append("display: inline-block;")
		    .append("margin-bottom: 0;")
		    .append("font-weight: 400;")
		    .append("text-align: center;")
		    .append("vertical-align: middle;")
		    .append("background-image: none;")
		    .append("border: 1px solid transparent;")
		    .append("white-space: nowrap;")
		    .append("padding: 15px 20px;")
		    .append("line-height: 1;")
		    .append("border-radius: 4px;")
		    .append("-webkit-user-select: none;")
		    .append("-moz-user-select: none;")
		    .append("-ms-user-select: none;")
		    .append("user-select: none;")
		    .append("font-family: inherit;")
		    .append("box-sizing: border-box;")
		    .append("margin: 0;")
		    .append("align-items: flex-start;")
		    .append("text-rendering: auto;")
		    .append("letter-spacing: normal;")
		    .append("word-spacing: normal;")
		    .append("text-transform: none;")
		    .append("text-indent: 0px;")
		    .append("text-shadow: none;")
		    .append("text-decoration: none;")
		    .append("font: 13.3333px Arial;")
		    .append("font-size: 18px;");
		
		return cssButtonBuilder.toString();
	
	}
}
