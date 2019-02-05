ARG PARENTBASE=registry.local.com:5000/siebel
ARG VERSION=18.4
FROM ${PARENTBASE}/sai:${VERSION}

COPY sai/initSAI sai/persistenceLayerSAI sai/healthcheck kernelCheck /config/
COPY sai/bashrc /home/siebel/.bashrc
HEALTHCHECK CMD bash /config/healthcheck
VOLUME ["/persistent"]
VOLUME ["/sfs"]
CMD [ "bash", "-c", "/bin/bash /config/initSAI > /persistent/containerInit.log 2>&1 ; bash" ]
