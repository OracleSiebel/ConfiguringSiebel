# Oracle Maps Integration

## Overview

This integration will add an Oracle Maps modal window from the customer form applet using [colorbox](http://www.jacklmoore.com/colorbox/) plug in. It took me a while to understand how to use the Oracle Maps framework. I hope this is helpful for others.

## Instructions:

- Place OracleMapsPR.js and OracleMapsPM.js under custom script folder
- Download colorbox and place the jquery.colorbox-min.js under /custom/3rdParty and colorbox.css in custom css folder. Be sure to add this css file to your template
- Set PM user properties for Street Address/ZIP Code/City/Country for appropriate controls (see [here](https://docs.oracle.com/cd/E14004_01/books/config_open_ui/customizing10.html) more info)
![Printscreen of PM User Properties](https://raw.githubusercontent.com/carlosmlribeiro/ConfiguringSiebel/master/ExampleCode/Open%20UI/Maps%20Integration/OracleMaps/user-properties.jpg)
- Register the files in the manifest to the desired applet
![Printscreen of Manifest Administration](https://raw.githubusercontent.com/carlosmlribeiro/ConfiguringSiebel/master/ExampleCode/Open%20UI/Maps%20Integration/OracleMaps/manifest.jpg)
 
## General Remarks:

- Address location and map rendering are assynchronous 
- Icon is added before the label for Business Street Address control. Change the jQuery selector in the ShowUI method to define another location
- Latitude/Longitude is calculated from Street Address + Postal Code + City + Country
- If the address can't be found the colorbox pop-up will display: "Address not found!"
 
## Contact:

Feel free to contact me via GitHub or [LinkedIn](http://www.linkedin.com/in/carlosmlribeiro) if you have questions or suggestions
