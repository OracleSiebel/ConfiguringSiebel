# Siebel On Docker for Oracle Linux 7

This legacy content is left here for interest. Since 21.1, Oracle switched to the modular deployment engine, so you should [review the content there](../classic-mde).

In this section, we'll cover one possible method for building and running Siebel as a set of Docker containers that will run against an Oracle Linux 7 (also Red Hat) operating system. These files have been tested using IP17 and IP18 installation media. The section is split into two parts:

* [Build](build)
* [Manage](manage)

In the Build section we cover how to build the required containers. The content here will assume you will be connecting to an Oracle database that you already have available. We won't cover the steps for creating a Siebel database. We'll first build a base; a container for the 32 bit Oracle 12c client. Once ready, we'll build separate containers for each of the Siebel elements on this base. Finally (coming soon) we'll build a layer which will allow configuration of the containers to persist between separate executions of the containers.

In the Manage section, we'll give you some scripts to automate and work with the containers. In particular we'll provide scripts to automate all the typical Siebel Management Console (SMC) tasks required to get the Siebel Enterprise to a healthy state by sending REST requests to the Siebel Application Interface. We'll also deliver some scripts for running single and multiple enterprises. Effort has been made to parameterize as much as possible, but this is an ongoing task and you may see updates over time.

