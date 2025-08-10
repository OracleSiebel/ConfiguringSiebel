ARG BASECONTAINERPATH=siebel/ses
ARG BASECONTAINERVERSION=17.0
FROM ${BASECONTAINERPATH}:${BASECONTAINERVERSION}

COPY ses/ses_update.rsp ses/ses_update.sh /config/
COPY Siebel_Enterprise_Server /mnt/Siebel_Enterprise_Server/

RUN  bash /config/ses_update.sh \
&& rm -rf /mnt/Siebel_Enterprise_Server/ \
&& rm -rf /siebel/ses/ps_backup \
&& echo Waiting for deployment of web archive ... \
&& sleep 30 