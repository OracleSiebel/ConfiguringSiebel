# Launch Directory Example

The scripts here provide an example for launching and configuring a Siebel Enterprise. There is almost zero chance that you can use these files without reading, understanding, and altering the parameters to meet your needs and your environment.

## Environment Definition

Parameters for the environment are stored in ent1.sh. If you wish to run multiple enterprises, it's expected that you will copy this file and make appropriate changes to the parameters within. In almost all cases, you will need to read and edit the parameters within.

## Launch Containers

Once the environment parameters are set, the enterprise can be set running using the following command style:

```
bash startAll <version> <parameter file>
```

e.g.

```
bash startAll 18.10 ent1.sh
```

The example startAll script will launch 4 containers. First it will launch the database engine, then the SAI container, then CGW, and finally SES. The given setup assumes that the ent1.sh parameter file will describe the base location for the persistent volumes using the variable PV. From here, the assumption is that the PV folder will contai a directory named by the ENTERPRISE parameter, and from there the assumption is that this folder will contain a subfolder for each of SES, CGW, SAI, and SFS. These folder must be created and readied before the first run, and need to be writeable by the user that will run the scripts, defined by the parameter RUNASUSER.

If the persistent folders are empty on the first run, they will be populated during the launch of the containers. If they were populated by a previous run, then the contents already present will be used. This is the primary mechanism whereby state is saved for the enterprise. 

## Configure Containers

Once the containers complete the launch process, all should should a healthy state, reported by docker ps. At this point, it is possible to configure the enterprise manually using SMC. SMC should be available at the IP address of the machine that launched the enterprise containers at the port specified in the PORT parameter in ent1.sh. Thus, the following URL should be available:

```
https://<machine-IP>:<PORT>/siebel/smc
```

In order to ease the burden of manual setup via SMC, a configure script is also provided which will automate setup using the scripts in the smc folder. The execute this, use:

```
bash configure ent1.sh
```

## Stop Containers

To stop the containers for a specific setup, use the following syntax:
```
bash stopAll <parameter file>
```

e.g.

```
bash stopAll ent1.sh
```

## Video

[Watch these steps](https://www.youtube.com/watch?v=MvETSsryqok&t=4233s)