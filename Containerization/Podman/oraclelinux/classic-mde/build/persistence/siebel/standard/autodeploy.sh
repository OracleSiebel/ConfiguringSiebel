#!/bin/bash

if [[ $1 == "" ]]; then
    echo "Usage: $0 -u USER -p PWD -a saihost -b saiport -d serverprofile"
    exit 1
fi
set -u

optnum=0

while getopts u:p:a:b:d: option
do
 case "${option}"
 in
 u)  USER=${OPTARG};;
 p)  PW=${OPTARG};;
 a)  SAIHOST=${OPTARG};;
 b)  SAIPORT=${OPTARG};;
 d)  PROFILE=${OPTARG};;
# e)  SRVNAME=${OPTARG};;
# f)  SRVDESC=${OPTARG};;
# h)  SRVHOST=${OPTARG};;
# i)  SRVPORT=${OPTARG};;
# l)  LANGS=${OPTARG};;
 esac
 let optnum=optnum+1
done

if [[ $optnum != 5 ]]
then
echo "Usage: $0 -u USER -p PWD -a saihost -b saiport -d serverprofile"
    exit 1
fi

if [[ $HOSTNAME =~ "." ]]
then
    # FQDN is in the hostname
    SRVNAME=`echo $HOSTNAME | cut -d "." -f1`
    SRVHOST=$HOSTNAME
else
    # Short hostname, which happens with Openshift
    SRVNAME=$HOSTNAME
    STS=$containerMode
    SRVHOST=$HOSTNAME"."$STS"."$POD_NAMESPACE".svc.cluster.local"
fi
SRVPORT=$SAIPORT

SRVDESC=$SRVNAME
LANGS=ENU

JSON='{
"DeploymentInfo":
{
"PhysicalHostIP":"'$SRVHOST':'$SRVPORT'",
"ProfileName":"'$PROFILE'",
"Action":"Deploy"
},
"ServerDeployParams":
{
"SiebelServer":"'$SRVNAME'",
"SiebelServerDesc":"'$SRVDESC'",
"DeployedLanguage":"'$LANGS'"
}
}'

echo
echo $JSON
echo

echo $JSON |  curl -v  -k -H "Content-Length: ${#JSON}"  \
 --user $USER:$PW \
 -H "Content-Type: application/json" \
 -X POST \
https://$SAIHOST:$SAIPORT/siebel/v1.0/cloudgateway/deployments/servers/ \
-d @-

sleep 10

n=0
while ! curl -s  -k -H "Accept: application/json"  --user $USER:$PW -H "Content-Type: application/json" -X GET https://$SAIHOST:$SAIPORT
/siebel/v1.0/cloudgateway/deployments/servers/$SRVNAME | grep Status.*Deployed
    do
        echo "Deployment of server $SRVNAME in Progress: "$n seconds
        sleep 20
        if [ "$n" -gt "600" ]
        then
            echo "Deployment taking more than 10 minutes; check server logs"
            echo "Exiting now"
            exit 1
        fi
        let n=n+20
    done

echo "server has been created; waiting 2 minutes to come up"
sleep 120
echo "server should be ready now"


exit 0