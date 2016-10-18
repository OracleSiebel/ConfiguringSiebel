#Busy Animation

Siebel IP16 came with a new busy animation featuring the Oracle O. This is actually completely built using CSS; essentially a border around a box with rounded corners. The point is really that, like much of the rest of the application, this can be themed according to a developer's or client's requirement.

![](docimages/oracle-o.png)

To convert to a rotating clock, as per the IP15 look and feel, add the contents of clock-busy-animation.css to your custom theme CSS file.

![](docimages/clock.png)


## SVG Animations
If you want something a little more unusual, take a look at the loading icons from [loading.io](http://loading.io). These are all made as SVG files, which are supported in most gears and, in this case, the SVG animation is built into the SVG file itself, so we don't need to implement CSS keyframe animation.

I've provided here a "gears.svg" created using loading.io. Place this on the webserver in public/images/custom and then add gears-busy-animation.css to your custom CSS file.

![](docimages/gears.png)

There are many more loading icons out there. All of them can be incoporated, and perhaps your company has it's own special logo you could do something with?