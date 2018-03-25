# REST Service for Github

1. This REST web service is used to fetch external fields details from GITHUB.
2. The github.properties file contains SSL template and proxy server details including the github URL information.
3. The Local App URI to fetch external fields details from GITHUB is following -
   - Public Repositories:  http://server:port/github/repos/{owner}/{repo}/commits/{branchName}
   - Private Repositories: http://server:port/github/repos/{org}/{repo}/commits/{branchName}
   - These URI are tokenized and reformated by the app before posting to Github
   - com.oracle.controller.RestController.java file handles URL request and response
   - User can change the URL mapping with the one of their own choice
4. The GITHUB external fields details logic can be found in the following file
   - com.oracle.github.RestController.java
   - Method getExternalSystemFields(owner, repo, branchName) takes these parameters as input and perform the logical operation to get the field details.
   - If the logic required change this method should be touched.
5. github.xml contains connection beans details under folder webapp\resources\spring\github.

# How to setup windows developer environment for Github Project

## Prerequisites:
1. Java SE 1.8
2. Maven
3. Tomcat
4. Spring Tool  Suite IDE (Eclipse IDE with Spring Add On)

## Setup Java SE 1.8
Download and install Java SE 1.8 from [http://www.oracle.com/technetwork/java/javase/downloads/index.html] and make sure ```JAVA_HOME``` system environment variable is set to Java root folder.

## Setup Maven
1. Download latest zip archive of Maven from [https://maven.apache.org] and unzip the content into any local folder. For example into ```C:\Program Files\Apache\Maven```
2. Set the following system environment variables with root folder of unzipped content as the value
```
M2_HOME
MAVEN_HOME
```
Value of these variables could be like: ```C:\Program Files\Apache\Maven\apache-maven-3.3.9```
3. Add the following value to the end of the PATH system variable value,
```
;%M2_HOME%
```

### If your server is behind a firewall and your Maven is unable to download the dependencies from remote repository, you may have to bypass firewall by following any of the known methods, one of which is explained here
1. You need to setup .m2 folder by running ```mvn install``` or ```mvn setup``` command from your projects root folder where your pom.xml is present. This will setup .m2 folder in your ```user.home``` path which usually will be ```C:\Users\<username>```
2. Create file ```settings.xml``` inside .m2 folder and paste the following content and modify host, port, username and password.
```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      https://maven.apache.org/xsd/settings-1.0.0.xsd">
  <proxies>
    <proxy>
      <id>myproxy</id>
      <active>true</active>
      <protocol>http</protocol>
      <host>www-proxy.mydomain.com</host>
      <port>80</port>
      <username></username>
      <password></password>
      <nonProxyHosts></nonProxyHosts>
    </proxy>
  </proxies>a
</settings>
```

## Setup Tomcat
1. Download and follow the instructions and setup Tomcat 8 from [https://tomcat.apache.org]
2. Try starting Tomcat by executing ```<Tomcat root>\bin\startup.bat```
3. If you face issues with ```JAVA_HOME``` or ```CATALINA_HOME``` not set errors, set those up from startup.bat
> Note: ```CATALINA_HOME``` is the root folder of Tomcat.
4. If you face port conflict issues, try to use a different port number in ```<Tomcat root>\conf\server.xml```

## Setup STS IDE
1. Download and follow the instructions and setup latest STS IDE from [https://spring.io/tools/sts/all]
2. Once you open STS IDE you can setup a Tomcat server instance within this IDE so that you can run services directly from IDE than copying and deploying war files to webapps folder
    - Select File > New > Other
    - Select Server > Server in the ‘New’ screen,
    - Click Next
    - Select Tomcat v8.0 or v8.5 Server from Apache
    - Provide the Tomcat Installation directory and click Finish
    - Once server is created it appears in the Servers pane
    - You may edit the port numbers so that it won’t conflict with existing instances of Tomcat.

## Importing Github java project and making deployable war file
1. Download or Clone this repository to a local folder
2. Start STS IDE and set the default workspace location
3. Select File > Import. Select Existing Project into Workspace and click ‘Next’
4. In the next screen provide the location of the project in the Git Clone location, choose "Copy to workspace" option and click Finish.
5. The IDE opens the project with all files within and imports dependency jar files
6. If you get an error about ‘Target runtime Tomcat not defined’, add a Tomcat server instance described in STS setup section
7. You may also find an issue with Maven library jar downloads because of firewall. Follow the instruction in ‘Setup Maven’ section to overcome
8. Before you move on to the next section make sure you do not see any errors. If there is any, try to resolve it before moving ahead
9.  Running a clean install may help remove stale errors:
   ```
   mvn clean install
   ```
10. Right click on the imported Project in Package Explorer and choose Run As > Run on Server (or the corresponding Debug options)
11. Wait for the service to come up and test the following REST API
   ```
   For public repositories: http://server:port/github/repos/{owner}/{repo}/commits/{branchName}
   For private repositories: http://server:port/github/repos/{org}/{repo}/commits/{branchName}
   ```
   The output will have a return JSON like below
   - Failure Case
      
      ```
      {
         "valid": "N",
         "message": "Workspace status is invalid."
      }
      ```
      
   - Success Case:
      
      ```
      {
         "valid":"Y",
         "message":"List of files changed"
      }
      ```

