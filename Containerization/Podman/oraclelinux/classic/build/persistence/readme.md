# Persistence

For a Siebel container, the notion of persistence is giving the container a memory. Many containerised applications function in an ephemeral way only, which is to say that once the container is stopped and restarted, it has no memory of what occurred the last time it ran. This is not an ideal situation for a Siebel server. Ideally we need persistence. The mosy obvious example of this is the Siebel File System (SFS); what good would it be if attachments were lost?

## Docker Volumes To The Rescue

A docker volume is a directory containing files. Docker volumes are not deleted when the application exists and are thus our chosen path to persistence. It would also be possible to simply use some network attached storage, which is often the right solution for production Siebel systems. Ultimately, Docker does not care where the directory you attach sits, as long as it has appropriate access rights to the content. In the examples following, we're focussing on getting a working Siebel enterprise in the shortest possible time. Once you've familiarised yourself with the layout and operation of Siebel as a set of Docker containers, you can adjust the location of the mounted volumes to suit your goals.

## SES, SAI, CGW, and SFS

For any given enterprise, we require 4 directories to function as our persistent memory for the Siebel enterprise configuration. Each Siebel container; SES (Siebel Enterprise Server), SAI (Siebel Application Interface), and CGW (Siebel Gateway) require their own volume, and then the SFS volume is mounted to each one.

## Methodology

It's important to understand the approach to persistence taken so far as it may need tweaking to meet your specific needs. The approach taken is to run the standard base or base+update container and, after performing a number of tests to simulate usage, determine which files change during the course of use. This is easily done through the use of this command:

```
docker diff <container-name>
```

Once we determine the list of files that Siebel system could touch during usage, we then need to look through those and determine if we should persist them. For example, we may want recorded upt tracking data to persist, but not a temporary shared memory file. The ideal approach would be to run a suite of tests which mirror expected user activity and then to review the results of the above command. While we can attempt to isolate this list for a vanilla Siebel application, customers have access to a rich array of customization capabilities with the Siebel platform and will need to take responsibility to ensure persistence is working well for their customised system.

## Healthcheck

Applying health checks to the non-persistent versions of the base-siebel and updated-siebel containers doesn't seem to make much sense. However, when launching containers that have access to persistent state content, the entire Siebel enterprise can be bought to full function, ready and waiting for users to login. Adding a healthcheck script to test the readiness of the container for users therefore makes sense at this stage. For each Siebel container, a different check makes sense, and you can alter this script further yourself if you determine a better method than the one chosen (or better still you could submit a pull request and share your method with everyone). At present, the method used is as follows.

#### SAI

Check for access to the Siebel Managenment Console

#### CGW

Check for full function of the name server

#### SES

Check for full function of the enterprise server.

These checks will ultimately result in a reported status of healthy for each container once the indicated checks are successful. e.g.:

```
[demoadmin@demohost persistence]$ docker ps
IMAGE                                         STATUS                   PORTS                   NAMES
registry.local.com:5000/siebel/ses/pv:18.4    Up 6 minutes (healthy)                           ses-ent1
registry.local.com:5000/siebel/cgw/pv:18.4    Up 7 minutes (healthy)                           cgw-ent1
registry.local.com:5000/siebel/sai/pv:18.4    Up 7 minutes (healthy)   0.0.0.0:443->4430/tcp   sai-ent1
```

## Prerequisites

Unlike the base and update builds, we don't need any additional content to build the persistence layer. Before you begin the build, ensure you folder structure looks something like this:

```
[demoadmin@demohost persistence]$ ls -l
total 4
drwxr-xr-x 2 demoadmin demoadmin 4096 May 29 08:19 build
drwxr-x--- 2 demoadmin demoadmin   72 May 29 08:16 cgw
drwxr-x--- 2 demoadmin demoadmin   72 May 29 08:07 sai
drwxr-x--- 2 demoadmin demoadmin   72 May 29 08:11 ses
```

## Build

To build, descend into the build folder and execute build-all-persistence using the following syntax:

```
bash build-all-persistence 19.01 registry.local.com:5000 siebel registry.local.com:5000/siebel 19.01np 2>&1 | tee build.$(date +%F_%R).log

```
This will very quickly build the persistent version of the Siebel container on top of the specified non-persistent build. This produces the final version of the container that you will actually use. You can build a persistent version of the base container or any updated container.

## Verify

Once the process complete, review the state of your images using:

```
docker images
```

You should see the newly built final containers alongside the non-persistent versions:

```
REPOSITORY                             TAG       SIZE
registry.local.com:5000/siebel/ses     19.01     3.34GB
registry.local.com:5000/siebel/cgw     19.01     1.45GB
registry.local.com:5000/siebel/sai     19.01     1.44GB
registry.local.com:5000/siebel/ses     19.01np   3.34GB
registry.local.com:5000/siebel/cgw     19.01np   1.45GB
registry.local.com:5000/siebel/sai     19.01np   1.44GB

```
We're now in a position to stop and start these docker containers while retaining the full enterprise configuration. One particularly useful thing about this persistence capability as that we're able to stop, for example, version 18.12 containers, and then start 19.01 containers against the same persistent volume content. This is the aspect which should help to reduce downtime between regular updates. With only a little more effort, this could be automated: i.e. watch for new versions to become available as they are built to your local registry (and ultimately the docker store), then automatically (or at a planned time, or simply manually) stop and start the appropriate containers to move to the next version.

## Video

[Watch this step](https://www.youtube.com/watch?v=MvETSsryqok&t=4135s)