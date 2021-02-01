chown -R siebel /mnt/Siebel_Enterprise_Server
chgrp -R siebel /mnt/Siebel_Enterprise_Server
chown -R siebel siebel
chgrp -R siebel siebel

su siebel -c "/mnt/Siebel_Enterprise_Server/Disk1/install/runInstaller.sh -silent -responseFile /config/sai.rsp -invPtrLoc /config/oraInst.loc -waitforcompletion -showProgress -oneclick"
