#!/bin/bash
#To remove containers use podman rm <container_name>

podman stop ses-DEV
sleep 10
podman stop cgw-DEV
sleep 10
podman stop sai-DEV
sleep 10
podman stop sidecar-DEV
sleep 10
podman stop kafka
sleep 10
podman stop --time 300 oracle19c
echo "DEV successfully stopped....!"
exit 0
