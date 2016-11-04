#Quick Search

Quick search is a way to improve searching through results in a list applet. Sometimes there's just no way to avoid the need to look through 200 or so records, at which point moving a page at a time or a record at a time seems painfully slow.

So, Quick Search to the rescue.

Delivered here as a custom PR, you can choose whether to apply this to a specific list applet, or implement it as a new default list renderer. If you do choose to change the default though, don't forget to look at your existing custom PRs for list applets and re-parent them to this one if required.

Let's see it in action.

This is the default best speed of pagination by repeatedly clicking the next page button.

![](docimages/RepeatedClicking.gif)

By ten seconds, I managed to click through 400 records, so about 40 clicks; wonderful repeated strain. Now here's what happens if I just click and hold:

![](docimages/ClickAndHold.gif)

In the same time, this has taken me through 710 records, a marked speed increase. And of course, just one press and hold; that's the main thing.

To implement this then, copy the QuickSearchPR.js file to your scripts/siebel/custom folder and register it through manifest files:

![](docimages/ManifestFiles.png)

Then administer it for a list applet of your choice:

![](docimages/ManifestAdmin.png)

Happy Quick Searching!
