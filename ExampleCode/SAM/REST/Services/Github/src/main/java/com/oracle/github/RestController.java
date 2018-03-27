package com.oracle.github;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.oracle.utils.ApiTemplate;
import com.oracle.utils.GitConnect;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("")
@Configuration
public class RestController {
	
	@Autowired static GitConnect gitConnect;
	
	@RequestMapping(value = "/repos/{owner}/{repo}/commits/{branchName}", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> getExternalSystemFields(
				@PathVariable("owner") String owner,
				@PathVariable("repo") String repo,
				@PathVariable("branchName") String branchName
			) throws IOException, Exception {
		
		Map<String, String> githubData = new HashMap<String, String>();
		List<String> filteredGithubData = new ArrayList<String>();
		Set<String> tempFilteredGithubData = new HashSet<String>();		
		RestTemplate restTemplate = null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "token " + gitConnect.getAuthToken());
		
		ApiTemplate apiTemplate = new ApiTemplate();
		restTemplate = apiTemplate.createSSLBasedRestTemplateWithBasicCredentials(gitConnect.getSslRESTTemplateHost(), gitConnect.getSslRESTTemplatePort(), gitConnect.getSslRESTTemplateScheme());
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		String githubCommitUri = gitConnect.getGithubURI() + "/repos/"+owner+"/"+repo+"/commits";
		Object responseObjects = null;
		try {
			//objects =  (Object)dgtRestTemplate.getObject(uri);`
			
			String uri = githubCommitUri + "?sha=" + branchName;
			ResponseEntity<Object> commitsResponseEntity = restTemplate.exchange(uri, HttpMethod.GET, request, Object.class);
			
			responseObjects = commitsResponseEntity.getBody();
			
			//parsing through Github object to read the data that we need,
			List<Object> commits = (List<Object>)responseObjects;
			for(Object commit : commits){
				LinkedHashMap com = (LinkedHashMap)commit;
				String eachCommitUri = githubCommitUri + "/" + com.get("sha");
				ResponseEntity<Object> commitResponseEntity = restTemplate.exchange(eachCommitUri, HttpMethod.GET, request, Object.class);
				
				Object commitResponseObject = commitResponseEntity.getBody();
				LinkedHashMap aCommit = (LinkedHashMap)commitResponseObject;
				List<Object> files = (List<Object>)aCommit.get("files");
				
				for(Object file : files){
					LinkedHashMap fileObj = (LinkedHashMap)file;
					tempFilteredGithubData.add((String)fileObj.get("filename"));
				}
				
			}	
			int index = 0; 
			for(Object file : tempFilteredGithubData){
				filteredGithubData.add((++index)+"."+file);
			}
			System.out.println("==============="+filteredGithubData);
			
		} catch (Exception e) {						
			githubData.put("valid", "N");
			githubData.put("message","Either repo name or username or branch name are not correct");
			return githubData;
		}
		githubData.put("valid", "Y");
		String fileListString = "";
		for(String file: filteredGithubData){
			fileListString = fileListString+file+System.getProperty("line.separator");
		}
		githubData.put("message", fileListString);
        return githubData;
	}	
}
