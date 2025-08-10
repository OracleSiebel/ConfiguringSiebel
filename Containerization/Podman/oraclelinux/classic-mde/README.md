# Siebel On Podman for Oracle Linux 8

In this section, we'll cover one possible method for building and running Siebel as a set of Docker containers that will run against an Oracle Linux  operating system. The section is split into two parts:

* [Build](build)
* [Manage](manage)

In the Build section we cover how to build the Siebel image. The first step uses Docker's multi-stage build process to chain together the requisite steps to build the Siebel container. The process is as follows:

* Update oraclelinux
* Build a self-signed SSL certificate
* Install the Siebel MDE release requested
* Collate all log content for later review

In the Manage section, we provide some starter scripts to automate and work with the containers. In particular we provide scripts to launch the containers, and to automate all the typical Siebel Management Console (SMC) tasks required to get the Siebel Enterprise to a healthy state by sending REST requests to the Siebel Application Interface. Effort has been made to parameterize as much as possible, but this is an ongoing task and you may see updates over time.
