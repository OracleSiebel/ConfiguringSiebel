1. This REST API can be used for workspace validation.
2. The URL to validate the workspace is following URL providing the actual workspace name at {workspace_name}
	- http://server:port/wsname/{workspace_name}}
	- com.oracle.controller.RestController.java file hadles URL request and response.
	- User can change the URL mapping with the one of their own choice.
3. The Workspace valication logic can be found in the following java file.
	- com.oracle.dao.impl.WorkspaceDaoImpl.java
	- Method queryWorkSpaceForValidation(wsName) takes workspace name as input, performed validation logic and finally return the validation status of workspace.
	- If workspace validation logic requires change, this method should be touched.
4. database.properties file under webapp\resources\properties can contain Database connection details and other parameters which may be required for validationg the workspace.