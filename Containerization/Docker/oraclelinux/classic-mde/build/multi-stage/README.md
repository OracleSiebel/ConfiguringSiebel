# Multi-Stage

Here you'll find content to allow you to build Siebel as a single container which encapsulates all aspects of Siebel, each of which is available via a launch parameter;

* Siebel Application Interface (SAI)
* Cloud Gateway (CGW)
* Siebel Enterprise Server (SES).

Once built, you'll want to add [persistence](../persistence) to the container to make it fit for final use

At present we're providing content only for Oracle Linux under the oraclelinux folder. We may add a Windows folder at a later date.

## oraclelinux

### Installation Media Server

To serve installation media to the build process navigate to the `oraclelinux/build/installerServer` folder and execute the following command:

```
bash launchInstallerServer
```

By default, this will use an instance of nginx to serve installation media from /media/installers. You can find [further details here](../build/installerServer).

### Build

To build the Siebel container, navigate to the `oraclelinux/build/multi-stage/build` folder and execute the following command:

```
bash build-mde-multistage 22.6 registry.local.com:5000 siebel *.siebel.local 2>&1 | tee build.$(date +%F_%R).log
```

This will build Siebel 22.6 and tag the resulting container in the form `registry.local.com:5000/siebel:22.6np`, embedding a self-signed wildcard certificate for `*.siebel.local` into the build. Details of the build will be available in a log file for review. By altering the parameters, you can tag the container so that it can be later easily pushed to your own local repository using the naming structure you prefer. You can also easily alter the domain for which the self-signed SSL certificate is built. If you need to add multiple domains to the self-signed ceritifcate, supply them as a comma-separated list. It's also possible to replace the SSL cert post-build.

A ['multi-stage'](https://docs.docker.com/develop/develop-images/multistage-build/) build process is used to create the container image. The script chains together the following steps:

* Obtain latest oraclelinux:8 base image
* Download the installation media for Oracle and Siebel into the container
* Install the Oracle 19.15.0.0 client
* Install the Siebel release
* Create a clean empty container
* Copy only the required content to the final containers

Ensure that you have enough file space to store the resulting container. Once the build completes, you should have the following container are your disposal:

```
REPOSITORY                                               TAG                    IMAGE ID            CREATED             SIZE
registry.local.com:5000/siebel                           22.6np                 68788f770ed4        2 weeks ago         2.75GB

```

If you don't see this (your image ID will differ), review the log file to look for issues during the build.