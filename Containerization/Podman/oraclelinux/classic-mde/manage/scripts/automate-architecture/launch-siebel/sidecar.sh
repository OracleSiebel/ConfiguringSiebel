#!/bin/bash

podman run \
	-d -it \
	-h sidecar-DEV \
	--network siebelnet \
	--network-alias sidecar-DEV  \
	--network-alias sidecar-dev \
	--network-alias sidecar-DEV.company.com \
	--name sidecar-DEV \
	-e "containerMode=SAI" \
	-e dbServerName=oracle19c \
	-e SIEBEL_EVENT_PUBSUB=1 \
	-e SIEBEL_EVENT_PUBSUB_PAYLOAD_STRUCT=2 \
	-e dbPort=1521 \
	-e dbServiceName=DEV \
	-p 9443:4430 \
	-v /mnt/c/persistent/DEV/sidecar:/persistent \
	-v /mnt/c/persistent/DEV/SFS:/sfs \
    -u 1000 \
	localhost/store/oracle/siebel:25.7