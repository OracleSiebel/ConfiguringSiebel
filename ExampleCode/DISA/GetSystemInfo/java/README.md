# Build the Plugin with Ant

DISA Plugins can be built directly from the command line. No special IDE is required, though there's nothing to stop you using your preferred IDE if you so wish. Here, we show how the example plugin can be built from the command line using only Ant.

## Setup Build Environment

1. Install DISA 2.0.11 or higher.
2. Install [Java Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (JDK) 8 or higher
3. Install [Apache Ant](http://ant.apache.org/) and add the **bin** folder path to environment variable **PATH**

## Configure Build Script
Modify the file [build.xml](./build.xml) to change the build path, class path or other build configurations.
* **Target File Name**

    ```xml
    <property name="target.name" value="GetSystemInfo"/>
    ```
    The value will be used as the output plugin jar file name.

* **Output Folder Name**

    ```xml
    <property name="build.dir" value="build"/>
    ```
    The value will be used as the output folder name.

* **DISA Library Path**

    ```xml
    <property name="lib.dir" value="<DISA_HOME>/DesktopIntSiebelAgent/lib"/>
    ```
    Replace *&lt;DISA_HOME&gt;* with the actual DISA install path.

* **Class Path**

    ```xml
    <path id="classpath">
        <fileset dir="${lib.dir}" includes="disa-api.jar"/>
        <fileset dir="${lib.dir}" includes="gson.jar"/>
    </path>
    ```
    Add any library files that will be referenced by the plugin code in here.

## Build The Plugin

1. Open a Command Prompt window
2. Navigate to the folder which contains the build.xml file using `cd` command.
3. Run `ant buildjar` or `ant` command to build the target.