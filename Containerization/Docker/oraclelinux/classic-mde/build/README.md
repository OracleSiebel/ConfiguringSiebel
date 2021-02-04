# Build

In order to build the Siebel containers, we use a containerised web server (nginx) to host installation media for the build process. To start the server for the installation media, review details in the [installerServer](installerServer) folder.

With the installation media ready and available, we then use the [multi-stage](multiStage) Docker build process to create the Siebel container

Finally, we apply a [persistence](persistence) layer to the container to create our final end product. This is kept as a separate layer to allow rapid testing of persistence layer changes without the need to fully re-build the entire container.

Once you have your container built, you can move to the [manage](../manage) folder to review details about launching and configuring your Siebel container.