ARG BASECONTAINERPATH=siebel/sai
ARG BASECONTAINERVERSION=17.0
FROM ${BASECONTAINERPATH}:${BASECONTAINERVERSION}

COPY sai/sai_update.rsp sai/sai_update.sh /config/
COPY Siebel_Enterprise_Server /mnt/Siebel_Enterprise_Server/

RUN  bash /config/sai_update.sh \
&& rm -rf /mnt/Siebel_Enterprise_Server/ \
&& rm -rf /siebel/sai/ps_backup \
&& echo Waiting for deployment of web archive ... \
&& sleep 30