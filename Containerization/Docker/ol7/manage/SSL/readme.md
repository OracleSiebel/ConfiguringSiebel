## SSL Keystore Gen

From IP17 forward, Siebel requires a certificate to secure intra-tier communications and also to ensure SSL can be used when connecting to Siebel object managers. The content here is intended only to enable initial testing, and is not intended to be used as a way to circumvent the necessary discussion with corporate IT infrastructure to ensure correct SSL deployment for an enterprise. However, we are unable to even begin testing with a new version of Siebel without such a certficate and so, in order to assist with that endeavour, you can use this tool to get started quickly.

The tool assumes that keytool (a Java toolset), and openssl are already available. You can check this by simlpy attempting to execute both commands that the prompt. Obtain them in the usual fashion otherwise.

To execute the tool and generate a keystore viable for testing, which will act as both the keystore and truststore, simply drop into the Keys folder and execute the following (adapting parameters to suit your taste):

```
bash keygen -r .. -s siebel -d *.company.com -c my-custom-ca 2>&1 | tee keygen.log
```

This will give you a siebelkeystore.jks file which can be incorporated into the [build folder](../../build/base_siebel) for the containers.