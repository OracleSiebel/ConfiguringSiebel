ARG BASECONTAINERPATH=siebel/cgw
ARG BASECONTAINERVERSION=17.0
FROM ${BASECONTAINERPATH}:${BASECONTAINERVERSION}

COPY cgw/cgw_update.rsp cgw/cgw_update.sh /config/
COPY Siebel_Enterprise_Server /mnt/Siebel_Enterprise_Server/

RUN  bash /config/cgw_update.sh \
&& rm -rf /mnt/Siebel_Enterprise_Server/ \
&& rm -rf /siebel/cgw/ps_backup \
&& echo Waiting for deployment of web archive ... \
&& sleep 30 