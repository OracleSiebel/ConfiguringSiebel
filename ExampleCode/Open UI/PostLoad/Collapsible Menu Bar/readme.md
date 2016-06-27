#Collapsible Menu Bar

In a recent post on SiebelHub about IP16 (http://goo.gl/7kmI7U) between the introduced usability enhancements there is one (Collapsible Menu Bar) that I thought to backport on IP14. Basically I used a postload.js to insert a png icon after the Oracle logo on the menu bar.
I also used a CSS rotation transform to animate that png in lines 10-34 of the snippet. The menu.png image must be placed, as usual, under the ${SWEAPP_ROOT}\PUBLIC\${LANG_CD}\images\custom\.

####Expanded

![](visibleMenuBar.png)

####Collapsed

![](collapsedMenuBar.png)

I tested it only on IP14 but it can be easily adapted for IP15 and perhaps IP13.

Thanks,
Luigi Monaco.