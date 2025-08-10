# Persistence

We use the notion of 'persistence' to give containers a long term memory. Many containerised applications function in an ephemeral way only, which is to say that once the container is stopped and restarted, it has no memory of what occurred the last time it ran. This is unworkable for a Siebel server, which needs to be able to recover its previous state. The most obvious example of this is the Siebel File System (SFS); what good would it be if attachments were lost?

## Docker Volumes

A docker volume is simply a directory containing files. These are not deleted when the application exists. It would also be possible to use some network attached storage. Docker does not care where the directory you attach resides, as long as it has appropriate access rights to the content. In the examples following, we're focussing on getting a working Siebel enterprise in the shortest possible time. Once you've familiarised yourself with the layout and operation of Siebel as a set of Docker containers, you can adjust the location of the mounted volumes to suit your goals.

## SES, SAI, CGW, and SFS

For a simple enterprise (a single instance of the Siebel container for each tier - SAI, CGW, and SES), we require 4 directories to function as our persistent volumes. Each instance of a Siebel container; SES (Siebel Enterprise Server), SAI (Siebel Application Interface), and CGW (Siebel Gateway) will require their own volume, and then the SFS volume is mounted to each container.

## Methodology

It's important to understand the approach to persistence taken so far as it may guide you in tweaking the process to meet your specific needs. The approach taken is to run the container and, after performing a number of tests to simulate usage (e.g. configuring the enterprise through SMC), determine which files change during the course of use. This is easily done through the use of this command:

```
docker diff <container-name>
```

Once we determine the list of files that Siebel system could touch during usage, we then need to look through those and determine if we should persist them. For example, we may want recorded upt tracking data to persist, but not a temporary shared memory file. The ideal approach would be to run a suite of tests which mirror expected user activity and then to review the results of the above command. While we can attempt to isolate this list for a vanilla Siebel application, customers have access to a rich array of customization capabilities with the Siebel platform and will need to take responsibility to ensure persistence is working well for their customised system.

## Healthcheck

Applying health checks to the non-persistent versions of the base-siebel and updated-siebel containers doesn't seem to make much sense. However, when launching containers that have access to persistent state content, the entire Siebel enterprise can automatically resume full function, ready and waiting for users to login. Adding a healthcheck script to test the readiness of the container for users therefore makes sense at this stage. For each mode of a Siebel container, we employ a slightly different check; you can alter this script further yourself if you determine a better method than the one chosen (or better still you could submit a pull request and share your method with everyone). At present, the method used is as follows.

#### SAI

Check for access to the Siebel Managenment Console

#### CGW

Check for full function of the name server

#### SES

Check for full function of the enterprise server.

These checks will ultimately result in a reported status of healthy for each container once the indicated checks are successful. e.g.:

```
[root@demohost launch-siebel]# docker ps
IMAGE                                STATUS                    PORTS                     NAMES
registry.local.com:5000/siebel:22.6  Up 35 minutes (healthy)                             ses-ENT
registry.local.com:5000/siebel:22.6  Up 36 minutes (healthy)                             cgw-ENT
registry.local.com:5000/siebel:22.6  Up 36 minutes (healthy)   0.0.0.0:443->4430/tcp     sai-ENT

```

## Persistence

Unlike the siebel build, we don't need any additional media content to build the persistence layer. Before you begin the build, ensure you folder structure looks something like this:

```
[demoadmin@demohost persistence]$ ls -l
total 24
drwxr-xr-x 2 demoadmin demoadmin 4096 Jan 15 11:37 build
-rwxrwxrwx 1 demoadmin demoadmin 1425 Jan 13 12:24 kernelCheck
-rwxr-xr-x 1 demoadmin demoadmin 1206 Jan 13 12:23 persistenceMigration
-rwxrwxrwx 1 demoadmin demoadmin  868 Jan 13 12:24 pvCheck
-rwxrwxrwx 1 demoadmin demoadmin 6241 Jan 15  2020 README.md
drwxr-xr-x 3 demoadmin demoadmin   40 Jan 13 16:50 siebel
```

## Build

To build, descend into the build folder and execute build-all-persistence using the following syntax:

```
bash build-siebel-persistence 22.6 registry.local.com:5000 siebel registry.local.com:5000/siebel standard 2>&1 | tee build.$(date +%F_%R).log

```
This will build the final Siebel containers with persistence functionality; the version of the container that you will actually use.

## Verify

Once the process completes, review the state of your images using:

```
docker images
```

You should see the newly built container alongside the original versions:

```
REPOSITORY                          TAG       SIZE
registry.local.com:5000/siebel      22.6      3.4GB
registry.local.com:5000/siebel      22.6np    3.4GB

```

We're now in a position to stop and start the siebel container and to retain the enterprise configuration. One emergent capability of persistence is that we're also able to stop, for example, version 22.6 containers, and then start 22.7 containers against the same persistent volume content. This should help to reduce downtime between regular updates. With only a little more effort, this could be automated: i.e. watch for new versions to become available as they are built to your local registry, then automatically (or at a planned time, or simply manually), stop and start the appropriate containers to move to the next version.