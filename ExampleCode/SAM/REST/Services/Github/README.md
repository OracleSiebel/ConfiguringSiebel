1. This REST web service is used to fetch external fields details from GITHUB.
2. The github.properties file contains SSL template and proxy server details including the github URL information.
3. The URL to fetch external fields details from GITHUB is following - 
	- http://server:port/repos/{owner}/{repo}/commits/{branchName}
	- com.oracle.controller.RestController.java file hadles URL request and response.
	- User can change the URL mapping with the one of their own choice.
4. The GITHUB external fields details logic can be found in the following file.
	- com.oracle.github.RestController.java
	- Method getExternalSystemFields(owner, repo, branchName) takes these parameters as input and perform the logical operation to get the field details.
	- If the logic required change this method should be touched.
5. github.properties file under webapp\resources\properties contains details about GITHUB repository.
6. github.xml contains connection beans details under folder webapp\resources\spring\github.