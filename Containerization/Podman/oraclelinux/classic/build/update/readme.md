# Update Siebel Base Containers

The contents here allow you to update your Siebel containers.

## Prerequisites

You should already have built your base Siebel containers before you arrive here. In order to update the containers, the only additional item you will need is the installation media for the update in the form of the Siebel_Enterprise_Server folder which you will need to generate from the content you download from [My Oracle Support](https://support.oracle.com).


Ultimately you should have these directory contents:

```
[root@demohost update]# ls
build  cgw  sai  ses  Siebel_Enterprise_Server
```

## Build

In order to build, descend into the build folder in order to execute the build-all script. The script's purpose is to build and tag containers ready to use and import to your local docker registry. Therefore before you start, you need to know the location of your local docker registry and you may also have a plan for naming the containers. The build script takes variables for you to specify this information. Before we go further, consider the a given update will be for a specific Siebel version, e.g. 18.4 . Ultimately to prepare containers to be uploaded to a local registry, we will want a naming convention along the following lines:

```
    registry.local.com:5000/siebel/sai:19.01np
```    
The SAI, CGW, and SES containers each need to be updated. Rather than updating the 17.0 containers, we build three new containers which take as a base the previous version. At the end of the build process we retain the original versions, and also obtain the 3 updated versions. As we're build on top of existing container, we need to specify the path to those containers. So to update our Siebel containers, we will run something like this command e.g:
```
    bash build-all-update 19.01np registry.local.com:5000 siebel registry.local.com:5000/siebel 17.0np 2>&1 | tee build.$(date +%F_%R).log
```
This translates into english as: build updated siebel docker containers using the installation media in Siebel_Enterprise_Server. Build on top of existing containers that are tagged like ```registry.local.com:5000/siebel/sai:17.0np``` and tag the final results as ```registry.local.com:5000/siebel/ses:19.01np```

Once executed, this will generate a log file (build.date.log) containing all output from the build process to enable you to review what happened at a later date.

## Verify

Once the build process completes, review the state of your images using:
```
    docker images
```
You should see something like this:
```
    REPOSITORY                           TAG      SIZE
    registry.local.com:5000/siebel/ses   19.01np  3.34GB
    registry.local.com:5000/siebel/cgw   19.01np  1.45GB
    registry.local.com:5000/siebel/sai   19.01np  1.44GB
    registry.local.com:5000/siebel/ses   17.0np   2.24GB
    registry.local.com:5000/siebel/cgw   17.0np   1.16GB
    registry.local.com:5000/siebel/sai   17.0np   948MB
```

## Test

Testing the Siebel containers is too complex to detail in this short space. You can run a quick test of the SAI container however just to make sure the build worked correctly:
```
    docker run -it -p 443:4430 registry.local.com:5000/siebel/sai:19.01np bash
```
To start the SAI in lite mode, now do this:
```
    cd /siebel/sai/applicationcontainer/bin
    ./startup.sh
```
You should now be able to connect to https://<machine-ip>/siebel/smc and see the login screen for SMC.

Further details about working with Siebel in containers will be posted in the near future.


## Video

[Watch this step](https://www.youtube.com/watch?v=MvETSsryqok&t=3528s)