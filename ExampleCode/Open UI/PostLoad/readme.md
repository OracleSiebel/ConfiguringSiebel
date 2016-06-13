#Post-Load Code
The Siebel framework provides an event which is executed at the end of each view load. This is called the postload event, and we can add script to this in order to make adjustments to the UI that are, for whatever reason, not easy elsewhere. Note that, due to it happening after the view has completed loading, and large shifts in UI should be avoided as they will be visible to the user.

##Template
Here's a template for a postload event handler:

```
if (typeof(SiebelAppFacade.CustomPostload) == "undefined") {
	Namespace('SiebelAppFacade.CustomPostload');

	(function () {
		SiebelApp.EventManager.addListner("postload", OnPostload, this);
		function OnPostload() {
			
			SiebelJS.Log ("Custom Postloader is Alive!");
			// add code here
		}
	}());
}
```

Some points to note:
* You can create multiple post load scripts as a method of encapsulating behaviour
* You can put it all post load in one file and comment within the file
* If you decide to have multiple files, ensure that each one has a different replacement for the string CustomPostload in the above code

The snippets directory offers content you may wish to use in a postload event handler.