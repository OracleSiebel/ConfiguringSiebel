In a recent post on SiebelHub about the IP16 (http://goo.gl/7kmI7U) 
between the introduced usability enhancements there is one (Collapsible Menu Bar) that I thought to backport on IP14.
Basically I inserted a bunch of code lines in the postload.js that insert a png incon after the Oracle log on the menu bar.
It uses also a transform rotate to animate that png
the code snippet in lines 10-34 can be used in the postload.js for IP14.
the menu.png image must be placed, as usual, under the ${SWEAPP_ROOT}\PUBLIC\${LANG_CD}\images\custom\

I tested it only on IP14 but it can be easily adapted for the IP15 and perhaps the IP13-

Thanks,
Luigi Monaco.