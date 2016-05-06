# REST Load Balancing

The example script stored here will enable you to generate a load balancing script for REST services. Contained within are scripts for Windows and Linux, along with a Webex Replay Format [demo video] to demonstrate how to use the script.

## How to use the script

- Open command prompt and go to bin folder inside perl installation location, then  run the LoadBalancingConfig.pl script with below arguments.
- The arguments are,
    * Path to java container where all java container folders are available
    * Path to httpd.conf file
    * Host name

## Example 
```sh
perl.exe
    C:\build\16_11\Siebel\javacontainer\LoadBalancingConfig.pl
    C:\build\16_11\Siebel\javacontainer
    C:\build\16_11\Siebel\javacontainer\httpd\conf
    containerhost.us.oracle.com
```
In this example we use:

##### Load Balancing Script Location
```sh
C:\build\16_11\Siebel\javacontainer\LoadBalancingConfig.pl
```
##### Parent folder path where all tomcat folders are available.
```sh
C:\build\16_11\Siebel\javacontainer
```
##### Parent folder path where httpd. Conf file is available.
```sh
C:\build\16_11\Siebel\javacontainer\httpd\conf
```
##### Target machine where httpd.exe is running
```sh
containerhost.us.oracle.com
```

[//]: #
[demo video]: <./demo/loadbalancingautomation.wrf>