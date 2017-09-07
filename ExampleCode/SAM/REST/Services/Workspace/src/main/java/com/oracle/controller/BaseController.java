package com.oracle.controller;

import java.lang.Thread.UncaughtExceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;	
import org.springframework.stereotype.Controller;
import com.oracle.model.JsonResponse;

@Controller
public class BaseController implements UncaughtExceptionHandler {    

	/** The context. */
    @Autowired
    ApplicationContext context;
    
    public void setApplicationContext(ApplicationContext context)
    {
        this.context = context;
    }
    
    
    protected JsonResponse addSuccessResponseInfo(JsonResponse jsonResponse, String message, Object data) {
    	jsonResponse = addResponseMessage(jsonResponse, message);
    	jsonResponse = addResponseData(jsonResponse, data);
    	
    	return jsonResponse;
    }
    
    protected JsonResponse addFailureResponseInfo(JsonResponse jsonResponse, String message, Object data) {
    	jsonResponse = addResponseMessage(jsonResponse, message);
    	jsonResponse = addResponseData(jsonResponse, data);
    	
    	return jsonResponse;
    }
    
    protected JsonResponse addFailureResponseInfo(JsonResponse jsonResponse, Throwable t, Object data) {
    	jsonResponse = addResponseMessage(jsonResponse, t.getMessage());
    	jsonResponse = addResponseData(jsonResponse, data);
    	
    	return jsonResponse;
    }
    
    protected JsonResponse addFailureResponseInfo(JsonResponse jsonResponse, Throwable t) {
    	return addResponseMessage(jsonResponse, t.getMessage());
    }
    
    protected JsonResponse addResponseMessage(JsonResponse jsonResponse, String message) {
    	if(jsonResponse!=null) {
    		jsonResponse.setMessage(message);
    	}
    	else {
    		jsonResponse = new JsonResponse();
    		jsonResponse.setMessage(null==message ? "" : message.trim());
    	}    	
    	
    	return jsonResponse;
    }
    
    protected JsonResponse addResponseData(JsonResponse jsonResponse, Object data) {
    	if(jsonResponse!=null) {
    		jsonResponse.setData(data);
    	}
    	else {
    		jsonResponse = new JsonResponse();
    		jsonResponse.setData(data);
    	}
    	
    	return jsonResponse;
    }
    
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("\n\n");

        System.out.println("***************Error Captured***************");

        System.out.println("\n\n");

        e.printStackTrace();

        System.out.println("\n\n");
    }
    
}
