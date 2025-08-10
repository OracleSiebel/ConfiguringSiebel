ARG PARENTBASE=registry.local.com:5000/siebel
ARG VERSION=18.4
FROM ${PARENTBASE}/ses:${VERSION}

COPY ses/initSES ses/healthcheck ses/persistenceLayerSES ses/injectManualComp kernelCheck /config/
COPY ses/bashrc /home/siebel/.bashrc
HEALTHCHECK CMD bash /config/healthcheck
VOLUME ["/persistent"]
VOLUME ["/sfs"]
CMD [ "bash", "-c", "/bin/bash /config/initSES > /persistent/containerInit.log 2>&1 ; bash" ]
