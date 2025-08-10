# Building Siebel for Podman

Oracle now makes available pre-built container images for Siebel. You can request these on MOS by submitting an SR. Even so, you may wish to build your own container.

Before continuing, consider that the content here has only been tested with builds after IP17. As the architecture changed significantly at that point, you should not expect to have any success with earlier versions without a great deal of effort.

Consider the content here as one way in which Siebel can be built for use with Docker rather than a formula you must obey. Since the introduction of the modular deployment engine in 21.1 and the streamlining that came with it, we've switched from creating three separate images for each tier to a single image which can be switched into the three tier modes on startup.

Here we're providing you with example content in order to allow you to build a Siebel container image. This is not kept up to date with our internal build system, but is pushed out from time to time.

While it's possible to run the image with no persistence, you would have to reconfigure the enterprise everytime you launched if you ran in this way. We have determined a minimum set of files which need to persist between executions. This subset of Siebel files is mounted to the container when it runs and knitted into the fabric of the container. Examples of persistent files include the Gateway database, MainWin registry content, user preference files, log files, UPT recordings, and so on.

We currently provide example content enabling you to build a container image for RHEL 7 derivatives.

At this time, the build process utilises Docker's multi-stage build process to build the smallest possible image.

The build process proceeds in this fashion:

* Modify the base image as required
* Install a 32-bit instant client container
* Install Siebel
* Build a 'full' container (all language safeboots plus dbsrvr content)
* Build a 'prod' container (only ENU safeboot, no dbsrvr)

While we don't cover the specifics of creating the Siebel database, it's worth mentioning the Oracle provide a 19c enterprise database engine (Google for Oracle Container Registry) and the content we provide from MOS supplies you with a pluggable SAMPLE database in 19c format. It's beyond the current scope of this repository to discuss configuring the empty Siebel database from scratch.

[Here's a video](https://www.youtube.com/watch?v=MvETSsryqok&feature=youtu.be) following the setup of OEL7 and Siebel 19.1 using [Oracle VirtualBox](https://www.virtualbox.org/)

If you have contributions to make, pull requests will be considered.
