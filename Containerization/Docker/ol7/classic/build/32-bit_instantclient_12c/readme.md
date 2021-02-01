# 32 bit client

Siebel requires a 32 bit Oracle client in order to establish connectivity to an Oracle database.

## Preparation

Before getting started, you need to obtain the following rpms from Oracle [here](http://www.oracle.com/technetwork/topics/linuxsoft-082809.html):

* [oracle-instantclient12.2-basic-12.2.0.1.0-1.i386.rpm](http://download.oracle.com/otn/linux/instantclient/122010/oracle-instantclient12.2-basic-12.2.0.1.0-1.i386.rpm)
* [oracle-instantclient12.2-jdbc-12.2.0.1.0-1.i386.rpm](http://download.oracle.com/otn/linux/instantclient/122010/oracle-instantclient12.2-jdbc-12.2.0.1.0-1.i386.rpm)
* [oracle-instantclient12.2-odbc-12.2.0.1.0-2.i386.rpm](http://download.oracle.com/otn/linux/instantclient/122010/oracle-instantclient12.2-odbc-12.2.0.1.0-2.i386.rpm)
* [oracle-instantclient12.2-sqlplus-12.2.0.1.0-1.i386.rpm](http://download.oracle.com/otn/linux/instantclient/122010/oracle-instantclient12.2-sqlplus-12.2.0.1.0-1.i386.rpm)
* [oracle-instantclient12.2-tools-12.2.0.1.0-1.i386.rpm](http://download.oracle.com/otn/linux/instantclient/122010/oracle-instantclient12.2-tools-12.2.0.1.0-1.i386.rpm)

You should also acquire the latest version of the docker container we are extending. In this case, it's oraclelinux:7-slim.

```
    docker pull oraclelinux:7-slim
```

## Build

You can now build the DB Client container from which subsequent containers will be built. The tag that you give to this container will be re-used in subsequent steps. Adapt this tag for your naming conventions. e.g. execute the following command from the directory containing the above files and rpms:

```
    docker build -t registry.local.com:5000/oracle/database-instantclient/32bit:12.2.0.1 --squash . 2>&1 | tee build.$(date +%F_%R).log
```

## Confirm

Confirm you have a newly built and tagger container by running the following command:

```
    docker images
```
Your output should look something like this:
```
REPOSITORY                                                    
registry.local.com:5000/oracle/database-instantclient/32bit   
TAG                 IMAGE ID            CREATED             SIZE
12.2.0.1            f28f92c14f20        13 seconds ago      656MB

```

## Verify

Verify your container functions as expected by starting the container and connecting to a database: e.g.

```
    docker run -it --rm registry.local.com:5000/oracle/database-instantclient/32bit:12.2.0.1 sqlplus user/pass@dbserver:port/servicename
```
## Video

[Watch this step](https://www.youtube.com/watch?v=MvETSsryqok&t=2145s)