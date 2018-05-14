# Building Siebel for Docker

At some point in the future, Oracle may make available pre-built containers to enable Siebel to run in a containerised way. Until then, and possibly even subsequently, you may wish to build your own container.

Consider the content here as one way in which Siebel can be built for use with Docker rather than a formula you must obey. The two seemingly obvious ways to proceed are to build a single monolithic container or to split Siebel into separate components.

Here we'll provide content in order to allow you to build Siebel as a set of 3 containers, each encapsulating a specific aspect of Siebel;

* Siebel Application Interface (SAI)
* Cloud Gateway (CGW)
* Siebel Enterprise Server (SES).

While these containers will execute correctly, any configuration we make will not persist between executions. To address that we have determined a set of files which need to persist between executions. This subset of Siebel files is mounted to the container when it runs and knitted into the fabric of the container. Examples of persistent files include the Gateway database, MainWin registry content, user preferences, log files, UPT recordings, and so on.

For each target platform you should find a directory. At present there is only content for Oracle Linux.

The build process will proceed in this order:

* Build a 32-bit instant client container
* Build an SAI container
* Build a CGW container
* Build an SES container

While we don't cover the specifics of creating the Siebel database, it's worth mentioning the Oracle make the 12c enterprise database available as a Docker container from the Docker Store. You can therefore use this container to create a Siebel database as a pluggable database. I would recommend doing that as you'll then have a self contained compact Siebel system. As mentioned previously though, it's beyond the current scope of this repository to discuss configuring the database from scratch.

If you have contributions to make, pull requests will be considered.