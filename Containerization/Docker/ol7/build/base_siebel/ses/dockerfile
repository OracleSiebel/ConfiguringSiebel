ARG DBCONTAINER=registry.local.com:5000/oracle/database-instantclient/32bit:12.2.0.1
FROM ${DBCONTAINER}

COPY ses/oraInst.loc ses/ses.rsp ses/setup.sh siebelkeystore.jks /config/

COPY Siebel_Enterprise_Server /mnt/Siebel_Enterprise_Server/

RUN mkdir -p -ma+rwx /siebel/ses /siebel/oraInventory  /stage \
&& groupadd -g  487 siebel && useradd -m -g siebel -G siebel -u 9999  siebel \
&& yum -y install \
 libgcc.i686 \
 libxcb.i686 \
 libX11.i686 \
 libXext.i686 \
 libXau.i686 \
 libcurl.i686 \
&& yum clean all \
&& rm -rf /var/cache/yum/x86_64  \
&& rm -rf /var/cache/yum/i686 \
&& echo Installing Siebel ... \
&& bash /config/setup.sh \
&& rm -rf /mnt/Siebel_Enterprise_Server/ \
&& echo Waiting for deployment of web archive ... \
&& sleep 30 \
&& chmod -R 755 /siebel/ses

ENV RESOLV_MULTI=off

