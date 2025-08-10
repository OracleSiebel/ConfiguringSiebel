#!/bin/bash

getContainerHealth() {
  podman healthcheck run "$1" >/dev/null 2>&1
  sleep 5
  podman inspect --format '{{json .State.Health.Status}}' "$1"
}

waitContainer() {
  # Conditional initial wait time
  if [[ "$1" == "oracle19c" ]]; then
    echo "Waiting 7 minutes for oracle19c to settle..."
    sleep 420
  elif [[ "$1" == ses* ]]; then
    echo "Waiting 10 minutes for $1 to settle..."
    sleep 600
  else
    echo "Waiting 1 minute for $1 to settle..."
    sleep 60
  fi

  # Health check loop
  while true; do
    STATUS=$(getContainerHealth "$1")
    if [[ "$STATUS" == "\"healthy\"" ]]; then
      break
    elif [[ "$STATUS" == "\"unhealthy\"" ]]; then
      echo "Failed!"
      exit 1
    fi
    printf "."
    sleep 1
  done

  printf "\n"
}


#Check if Oracle DB container is running
if ! podman ps --format '{{.Names}}' | grep -q "oracle19c"; then
    echo "Starting DB"
    set -x
    podman start oracle19c
    set +x
    waitContainer "oracle19c"
else
    echo "Container oracle19c is already running — skipping DB start"
    date
fi

#Start kafka if not already running
if ! podman ps --format '{{.Names}}' | grep -q "kafka"; then
    echo "Starting kafka"
    set -x
    podman start kafka
else
    echo "Container kafka is already running — skipping start"
    date

fi




#Start cgw-DEV if not already running
if ! podman ps --format '{{.Names}}' | grep -q "cgw-DEV"; then
    echo "Starting cgw-DEV"
    set -x
    podman start cgw-DEV
    set +x
    waitContainer "cgw-DEV"
else
    echo "Container cgw-DEV is already running — skipping start"
    date

fi

#Start ses-DEV if not already running
if ! podman ps --format '{{.Names}}' | grep -q "ses-DEV"; then
    echo "Starting ses-DEV"
    set -x
    podman start ses-DEV
    set +x
    waitContainer "ses-DEV"
else
    echo "Container ses-DEV is already running — skipping start"
    date

fi

#Start sai-DEV if not already running
if ! podman ps --format '{{.Names}}' | grep -q "sai-DEV"; then
    echo "Starting sai-DEV"
    set -x
    podman start sai-DEV
    set +x
    waitContainer "sai-DEV"
else
    echo "Container sai-DEV is already running — skipping start"
    date

fi

#Start sidecar-DEV if not already running
if ! podman ps --format '{{.Names}}' | grep -q "sidecar-DEV"; then
    echo "Starting sidecar-DEV"
    set -x
    podman start sidecar-DEV
    set +x
    waitContainer "sidecar-DEV"
else
    echo "Container sidecar-DEV is already running — skipping start"
    date

fi

echo "DEV successfully started....!"

exit 0
