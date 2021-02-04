# installerServer

Before launching the server, you should first collect the required installation media. You will need:

* Oracle Instant Client for 12.2.0.1
* Siebel MDE Install for the release you're installing

These files should be arranged in a folder as follows:

```
media
    oracle
        client
            oracle-instantclient12.2-basic-12.2.0.1.0-1.i386.rpm
            oracle-instantclient12.2-jdbc-12.2.0.1.0-1.i386.rpm
            oracle-instantclient12.2-odbc-12.2.0.1.0-2.i386.rpm
            oracle-instantclient12.2-sqlplus-12.2.0.1.0-1.i386.rpm
            oracle-instantclient12.2-tools-12.2.0.1.0-1.i386.rpm
    siebel
        21.2
            Siebel_Enterprise_Server
```

The siebel folder should hold the installation media for a given update within a child directory for that update.

You should ensure all content in the media folder can be read by the nginx server. A simple way to do this is to grant global read access, but you can be more discerning if you choose, providing the content can be read.

```
cd /media/installers
chmod -R +r
```