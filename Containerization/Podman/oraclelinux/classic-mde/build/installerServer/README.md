# installerServer

Before launching the server, you should first collect the required installation media. You will need:

* Oracle Instant Client for 19.15.0.0
* Any additional Oracle client content you desire
* Siebel MDE Install for the release you're installing

For Oracle 19.15, the instant client doesn't contain orapki, a crucial tool for connecting to encrypted databases (e.g. ATP). Provision in the installation process for an 'additional' folder allows us to add in the necessary content for orapki, tnsping, and few ldap tools that are useful for debugging. You can obtain these files from a full client installation. This is beyond the scope of this document, but is simply a matter of copying files from a full client (at the same version) that is installed elsewhere.

These files should be arranged in a folder as follows:

```
├── media
    ├── installers
        ├── oracle
        │   ├── client
        │       ├── additional
        │       │   ├── bin
        │       │   │   ├── ldapadd
        │       │   │   ├── ldapaddmt
        │       │   │   ├── ldapbind
        │       │   │   ├── ldapcompare
        │       │   │   ├── ldapdelete
        │       │   │   ├── ldapmoddn
        │       │   │   ├── ldapmodify
        │       │   │   ├── ldapmodifymt
        │       │   │   ├── ldapsearch
        │       │   │   ├── orapki
        │       │   │   └── tnsping
        │       │   ├── jlib
        │       │   │   ├── oraclepki.jar
        │       │   │   ├── osdt_cert.jar
        │       │   │   └── osdt_core.jar
        │       │   ├── ldap
        │       │   │   └── mesg
        │       │   │       ├── ldapus.msb
        │       │   │       └── ldapus.msg
        │       │   └── network
        │       │       └── mesg
        │       │           ├── tnsus.msb
        │       │           └── tnsus.msg
        │       ├── oracle-instantclient19.15-basic-19.15.0.0.0-1.i386.rpm
        │       ├── oracle-instantclient19.15-jdbc-19.15.0.0.0-1.i386.rpm
        │       ├── oracle-instantclient19.15-odbc-19.15.0.0.0-1.i386.rpm
        │       ├── oracle-instantclient19.15-sqlplus-19.15.0.0.0-1.i386.rpm
        │       └── oracle-instantclient19.15-tools-19.15.0.0.0-1.i386.rpm
        ├── siebel
            ├── 22.6
                ├── Siebel_Enterprise_Server
```

The siebel folder should hold the installation media for a given update within a child directory named for that update.

You should ensure all content in the media folder can be read by the nginx server. A simple way to do this is to grant global read access, but you can be more discerning if you choose, providing the content can be read.

```
cd /media/installers
chmod -R +r
```