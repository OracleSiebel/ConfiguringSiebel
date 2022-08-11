# LICENSE UPL 1.0
#
# Copyright (c) 2019 Oracle and/or its affiliates. All rights reserved.
#
# Since: Dec, 2019
# Authors: andreas.thieltges@oracle.com & duncan.ford@oracle.com
# Description: Install Siebel MDE
# DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.

chown -R siebel:siebel /mnt/Siebel_Enterprise_Server siebel
chown -R siebel:siebel /config

# Run the silent installer

su siebel -c "bash /mnt/Siebel_Enterprise_Server/Disk1/install/runInstaller.sh -silent -responseFile /config/mde.rsp -invPtrLoc /config/oraInst.loc -waitforcompletion -showProgress > /config/SiebelInstall.log 2>&1"
res=$?
more /config/SiebelInstall.log
if grep "\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*Error\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*" /config/SiebelInstall.log; then
	echo "Error during install. Check /config/SiebelInstall.log"
	exit 1
fi
exit $res
