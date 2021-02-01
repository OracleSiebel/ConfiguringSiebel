# Build Siebel Base Containers

The contents here allow you to build three Siebel containers, one for each of the Siebel Application Interface (SAI), Cloud Gateway (CGW), and the Siebel Enterprise Server (SES).

## Prerequisites

Before you get started you will need a base Siebel installation, e.g. IP17 GA, or any installer which is able to build the complete Siebel platform. To be clear, you do not run patchsets using these files; this is a separate build. The dockerfiles for the containers expect that you will place a folder called Siebel_Enterprise_Server in the same folder you copy the above files to.  In addition, you will need a [keystore](../../manage/SSL) and a truststore to permit the tiers to converse securely and also to enable https security when connecting the to SAI. The Siebel_Enterprise_Server folder should be generated from the zipped files you download from the [Oracle Software Delivery Cloud](https://edelivery.oracle.com) or [My Oracle Support](https://support.oracle.com). After obtaining the zips, you will need to extract the contents and run the [Siebel Image Creator Utility](https://docs.oracle.com/cd/E88140_01/books/SiebInstWIN/SiebInstCOM_Image9.html) to generate the actual install media. From that installtion media, copy the Siebel Enterprise_Server content to the Siebel_Enterprise_Server folder.

Ultimately you should have these directory contents:

```
[root@demohost base_version]# ls
build  cgw  sai  ses  Siebel_Enterprise_Server  siebelkeystore.jks
```

## Build

In order to build, descend into the build folder and first read the build-all script. The script's purpose is to build and tag containers ready to import to your local docker registry. Therefore before you start, you need to know the location of your local docker registry and you may also have a plan for naming the containers. The build script takes variables for you to specify these details. Before we go further, consider the a given build will be for a specific Siebel version, e.g. IP 17.0. Ultimately to prepare containers to be uploaded to a local registry, we will want a naming convention along the following lines:

```
    registry.local.com:5000/siebel/sai:17.0np
```    
Also consider that the CGW and SES containers need db access and are thus built from the DB client container you previously build. Ultimately, for functional Siebel containers, we will need the concept of persistence, so we will tag this build as non-persistent (np). So to build our Siebel container, we will pass in the name we used to tag our dbclient container along with the naming details, e.g:
```
    bash build-all-base 17.0np registry.local.com:5000 siebel registry.local.com:5000/oracle/database-instantclient/32bit:12.2.0.1 2>&1 | tee build.$(date +%F_%R).log

```
This will generate a log file (build.date.log) containing all output from the build process to enable you to review what happened at a later date.

## Verify

Once the build process completes, review the state of your images using:
```
    docker images
```
You should see something like this:
```
REPOSITORY                                                    TAG                 IMAGE ID            CREATED             SIZE
registry.local.com:5000/siebel/ses                            17.0np              03e4794c53f5        39 seconds ago      2.26GB
registry.local.com:5000/siebel/cgw                            17.0np              a2178b251e59        9 minutes ago       1.18GB
registry.local.com:5000/siebel/sai                            17.0np              f522350e8222        15 minutes ago      948MB
```

## Test

Testing the Siebel containers is too detailed for this short space. You can run a quick test of the SAI container however just to make sure the build worked correctly:
```
    docker run -it -p 443:4430 registry.local.com:5000/siebel/sai:17.0 bash
```
To start the SAI in lite mode, now do this:
```
    cd /siebel/sai/applicationcontainer/bin
    ./startup.sh
```
You should now be able to connect to https://<machine-ip>/siebel/smc and see the login screen for SMC.

Further details about working with Siebel in containers will be posted in the near future.

## Video

[Watch this step](https://www.youtube.com/watch?v=MvETSsryqok&t=2536s)