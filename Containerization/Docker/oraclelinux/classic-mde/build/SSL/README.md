## SSL Keystore Generation

Siebel requires a certificate to secure intra-tier and browser communications. The content here is intended to facilitate rapid testing, but is not intended to be used as a way to circumvent the necessary discussion with corporate IT infrastructure to ensure correct SSL deployment for production use. However, we are unable to use Siebel without such a certficate and so, in order to assist with that endeavour, you can use this tool to get started quickly.

This [script](keygen) assumes that keytool (a Java tool), and openssl are already available. You can check this by simply attempting to execute both commands at the prompt. Install them if they are not present.

To execute the tool and generate a keystore viable for testing, which will act as both the keystore and truststore, simply execute the following (adapting parameters as required):

```
bash keygen -r . -s siebel -d *.company.com -c my-custom-ca 2>&1 | tee keygen.log
```

This will generate a `siebelkeystore.jks` file which can be incorporated into the [build folder](../multi-stage) for the containers.

This tool is automatically executed by the [build-all-multistage](../multi-stage/build/build-all-multistage) script.

You can also run this tool separately and use the resulting `siebelkeystore.jks` file by copying it to the persistent folder for the containers, then relaunching the containers, or restarting the Tomcat instances.

Alternatively, you could build a separately generated or obtained certificate store into new containers using a simple dockerfile. e.g.:

```
FROM registry.local.com:5000/siebel:22.6

COPY siebelkeystore.jks /siebel/mde/applicationcontainer_external/siebelcerts
COPY siebelkeystore.jks /siebel/mde/applicationcontainer_internal/siebelcerts
```