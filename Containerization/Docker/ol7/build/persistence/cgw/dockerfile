ARG PARENTBASE=registry.local.com:5000/siebel
ARG VERSION=18.4
FROM ${PARENTBASE}/cgw:${VERSION}

COPY cgw/initCGW cgw/persistenceLayerCGW cgw/healthcheck kernelCheck /config/
COPY cgw/bashrc /home/siebel/.bashrc
HEALTHCHECK CMD bash /config/healthcheck
VOLUME ["/persistent"]
VOLUME ["/sfs"]
CMD [ "bash", "-c", "/bin/bash /config/initCGW > /persistent/containerInit.log 2>&1 ; bash" ]
