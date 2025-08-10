# Preface
The scripts works well for WSL on windows but can also be used on a linux VM
PS C:\Users\xxxx>  wsl -d OracleLinux_8_10
I have used Oracle Linux  8.10 distrubution available on windows store 

the root user is opc which is default user after login

Login to WSL

```
PS C:\Users\xxxx>  wsl -d OracleLinux_8_10
```
create a new user admin and always do su -admin to this user

update packages

```
sudo dnf install -y dnf-utils
sudo dnf config-manager --enable ol8_addons
sudo dnf module reset container-tools
sudo dnf module enable container-tools:latest
sudo dnf update podman
```
for admin user add this to ~.bash_profile
```
sudo mount --make-rshared /
```

make a folder persistent and give full access to everyone C:\persistent follow instructions to create folder structure as per bookshelf Deploying siebel Containers
whole c drive is available to wsl as /mnt/c/

There is additional mount point /mnt/c/persistent/migration/ mounted as /migration folder inside containers
This serves a common mount point for synchronous migration if you plan to create another RR environment enterprise e.g PRD

after downloading images you can load them 

```
podman load -i /mnt/c/images/siebel-25.7.tar.gz
podman load -i /mnt/c/images/deploy-db-19c-25.7.tar.gz
podman load -i /mnt/c/images/database-19_3_0_0.tar
```

#  Switch to Netavark
This change will enable container-to-container name resolution (e.g. `oracle19c` → resolves from other containers) when using rootless Podman.


1. **Create or edit the containers config**:
   ```bash
   mkdir -p ~/.config/containers
   nano ~/.config/containers/containers.conf
   ```

2. **Add this setting** to switch backends:
   ```ini
   [network]
   network_backend = "netavark"
   ```

3. **Verify the change**:
   ```bash
   podman info --format '{{.Host.NetworkBackend}}'
   ```
   It should now show `netavark`.

4. **Restart your containers** to apply the new networking behavior.

###  Why This Matters:
- Netavark uses Aardvark DNS, which supports automatic container name resolution by default.
- Works best in **rootless mode** (i.e., without `sudo`).


# Launch Directory Example

The scripts here provide an example for launching and configuring a Siebel Enterprise. There is almost zero chance that you can use these files without reading, understanding, and altering the parameters to meet your needs and your environment.

## Environment Definition

Parameters for the environment are stored in ssiebent.sh If you wish to run multiple enterprises, it's expected that you will copy this file and make appropriate changes to the parameters within. In almost all cases, you will need to read and edit the parameters within.

## Launch Containers

Once the environment parameters are set, the enterprise can be set running using the following command style:

e.g.

```
bash startAll 25.7 sample-architecture.sh
```

The example startAll script will launch 4 containers. First it will launch the database engine, then the Siebel container in SAI mode, then the Siebel container in CGW mode, and finally the Siebel container in SES mode. The given setup assumes that the sample-architecture.sh parameter file will describe the base location for the persistent volumes using the variable PV. From here, the assumption is that the PV folder will contain a directory named by the ENTERPRISE parameter, and from there the assumption is that this folder will contain a subfolder for each of SES, CGW, SAI, and SFS. These folders must be created and readied before the first run, and need to be writeable by the user that will run the scripts, defined by the parameter RUNASUSER.

If the persistent folders are empty on the first run, they will be populated during the launch of the containers. If they were populated by a previous run, then the contents already present will be used. This is the primary mechanism whereby state is saved for the enterprise. 

## Launch Kafka server (optional)

Download latest image from 

https://hub.docker.com/r/apache/kafka

OR pull the image

```
podman pull docker.io/apache/kafka:4.0.1-rc0

```
Run Kafka server/broker
```
bash kafka.sh
  
```

You can Install KafkIO on windows computer to create topics and see/produce messages 
https://www.kafkio.com/download


The Bootstrap server for Kafka cluster will be 
```
 <WSL IP address>:8092
```
8092 is the local windows computer port which is exposed externally 



## Launch sidecar AI (optional)

Rum sidecar AI 
```
bash sidecar.sh
```
As per System Adminstration Guide the kafka environment varibales are to be set in both sidecar and siebel server machines , although sidecar container is run with these environment variables you can also add them inside 
```
/siebel/mde/applicationcontainer_external/bin/setenv.sh
```
add at the end 
```
SIEBEL_EVENT_PUBSUB=1 ; export SIEBEL_EVENT_PUBSUB
SIEBEL_EVENT_PUBSUB_PAYLOAD_STRUCT=2 ; export SIEBEL_EVENT_PUBSUB_PAYLOAD_STRUCT
```
inside  applicationinterface.properties  file on /siebel/mde/applicationcontainer_external/webapps/
```
KafkaServers=kafka.company.com:9092
```

Note :-  
1. 9092 port is available inside network siebelnet and 8092 outside for windows 
2. sample sidecar_sample_applicationinterface.properties file for your reference
3. copy keystore and truststore jks files for sai-DEV container to sidecar container /siebel/mde/applicationcontainer_external/siebelcerts/

To encrypt SADMIN password use 
```
/siebel/mde/jre/bin/java -jar /siebel/mde/applicationcontainer_external/webapps/siebel/WEB-INF/lib/EncryptString.jar <password>
```
Refer to System Adminstration Guide for detailed setup

## Configure Containers

Once the containers complete the launch process, all should report a healthy state, reported by docker ps. At this point, it is possible to configure the enterprise manually using SMC. SMC should be available at the IP address of the machine that launched the enterprise containers at the port specified in the PORT parameter in ent1.sh. Thus, the following URL should be available:

```
https://<machine-IP>:<PORT>/siebel/smc
```

In order to ease the burden of manual setup via SMC, a configure script is also provided which will automate setup using the scripts in the smc folder. The execute this, use:

```
bash configure sample-architecture.sh
```

## Stop Containers

To stop the containers for a specific setup, use the following syntax:
```
bash stop_DEV.sh
```
## Start Containers

To start the containers for a specific setup, use the following syntax:
```
bash start_DEV.sh
```

## To remove containers

First stop containers then 

```
podman rm <container_name>

```