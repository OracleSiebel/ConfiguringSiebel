#!/bin/bash
# This command should be launched via the command line. e.g.
#
# docker run --rm -u 1000 --network siebelnet --env-file PostInstallDBSetup.list -v /var/lib/docker/volumes/PV/ENT/SES:/persistent --entrypoint /bin/bash registry.local.com:5000/siebel/ses:20.7 /config/PostInstallDBSetup.sh
#

# Define primary variables

persistencePath="/persistent"

# Handle case for multiple instances

if [ ! -z $HOST ] ; then
	mkdir -p $persistencePath/$HOST
	persistencePath="/persistent/${HOST}"
fi

sourcePath="/siebel/mde"

# Ensure persistent volumes are writable

bash /config/pvCheck $persistencePath $sfsPath
if [ ! $? -eq 0 ] ; then
        exit 1;
fi

# Source the persistance layer for this container

source /config/persistenceLayerSiebel-SES

# Source migration routines

source /config/persistenceMigration

# Migrate persistent content

migrateFoldersToPersistenceVolume
migrateFilesToPersistenceVolume

# Copy any patch content into the container prior to boot

if [ -d ${persistencePath}/hotfix/ses/ ] ;
then
        # copies folder structure into the container to apply patch
        cp -R ${persistencePath}/hotfix/ses/* ${sourcePath}
fi

# Add required ODBC driver
# If ODBC file not present, system has not been initialised - exit

ODBCINI="/siebsrvr/sys/.odbc.ini"

if ! grep ^${ODBC_DSN} ${sourcePath}${ODBCINI}; then sed -i "/^ENT_DSN/ a ${ODBC_DSN}=MERANT 7.1 Oracle 12 Driver" ${sourcePath}${ODBCINI}; fi
if ! grep ^\\[${ODBC_DSN}\\] ${sourcePath}${ODBCINI}; then sed -i "$ a [${ODBC_DSN}]\nDriver=${sourcePath}/siebsrvr/lib/SEor827.so\nColumnSizeAsCharacter=1\nColumnsAsChar=1\nArraySize=160000\nServerName=${ODBC_DSN}" ${sourcePath}${ODBCINI}; fi

# Populate PostInstallDBSetup.ini

sed -i "s/<ODBC_DSN>/${ODBC_DSN}/" /config/PostInstallDBSetup.ini
sed -i "s/<TBLO>/${TBLO}/" /config/PostInstallDBSetup.ini
sed -i "s/<TBLOUSER>/${TBLOUSER}/" /config/PostInstallDBSetup.ini
sed -i "s/<SIEBUSER>/${SIEBUSER}/" /config/PostInstallDBSetup.ini
sed -i "s/<SSE_ROLE>/${SSE_ROLE}/" /config/PostInstallDBSetup.ini
sed -i "s/<INDSPC>/${INDSPC}/" /config/PostInstallDBSetup.ini
sed -i "s/<TBLSPC>/${TBLSPC}/" /config/PostInstallDBSetup.ini
sed -i "s/<PRIMARY_LANG_CD>/${PRIMARY_LANG_CD}/" /config/PostInstallDBSetup.ini
sed -i "s/<OTHER_LANG_CD>/${OTHER_LANG_CD}/" /config/PostInstallDBSetup.ini

echo ${ODBC_DSN}'=(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = '${DBHOST}')(PORT = '${DBPORT}'))(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = '${DBINST}')))' > /config/tnsnames.ora

if [ -f ${sourcePath}/siebsrvr/siebenv.sh ] ; then
        source ${sourcePath}/siebsrvr/siebenv.sh
else
        source ${sourcePath}/siebsrvr/cfgenv.sh
fi

# Final prep if coming from pre-21.2
rm -rf /siebel/ses
ln -s /siebel/mde /siebel/ses

# Execute Post Install

time ${sourcePath}/siebsrvr/bin/PostInstallDBSetup -i /config/PostInstallDBSetup.ini -p ${TBLOPASS} -z ${SIEBELUSERPASS}