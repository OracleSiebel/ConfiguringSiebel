package com.oracle.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.oracle.dao.WorkspaceDao;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("")
@Configuration
@ComponentScan("com.oracle.dao")
public class RestController extends BaseController {
	
	@Autowired 
	private WorkspaceDao wsDao;
	
	//workspace branch validation
	@RequestMapping(value="/wsname/{wsname}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> getWorkspaceValidated(@PathVariable("wsname") String wsName) {
		Map<String, String> workspaceValidaiton = new HashMap<String, String>();
		try{
			workspaceValidaiton = wsDao.queryWorkSpaceForValidation(wsName);
			//workspaceValidaiton.put("message", status);
		}catch(Exception e){
			workspaceValidaiton.put("message", e.getMessage());
		}
        /*try {
            
            if (p.exitValue() == 0) {
            	workspaceValidaiton.put("valid", "Y");
            	workspaceValidaiton.put("message", "Valid");
            } else {
            	workspaceValidaiton.put("valid", "N");
            	workspaceValidaiton.put("message", "Workspace is not valid. Please check  c:\\xm3\\log\\"+bugnumber+" for more details");
            }
        } catch (Exception e) {
        	workspaceValidaiton.put("valid", "N");
        	workspaceValidaiton.put("message", "failure");
        }*/
        
		return workspaceValidaiton;
	}
	
}
