// This sample shows how to write a custom PW. The intention here is to decorate a Dropdown Field to show a Color Box,
// which changes colors based on the value in the box. 

// First, define the custom PW's namespace.
if (typeof (SiebelAppFacade.ColorBoxPW) === "undefined") {
    SiebelJS.Namespace('SiebelAppFacade.ColorBoxPW');

    // Define the module and add any dependencies (including 3rd party files the PW may use) here.
    define("siebel/colorboxpw", ["siebel/basepw"], function () {
        SiebelAppFacade.ColorBoxPW = (function () {

            function ColorBoxPW() {
                // The constructor. Initializations and declrations go here. Just a superclass call in our case.
                SiebelAppFacade.ColorBoxPW.superclass.constructor.apply(this, arguments);
            }

            // Make sure to extend from the right PW. 
            SiebelJS.Extend(ColorBoxPW, SiebelAppFacade.DropDownPW);

            // ShowUI is a lifecylce method that gets called when the PW is being instantiated. 
            // It is responsible for constructing the DOM that corresponds to the control.
            ColorBoxPW.prototype.ShowUI = function (control) {
                // Call the super class function, so that the dropdown is built. 
                // Avoid this call if you want the super class's showing to not happen.
                // Here, we are only trying to decorate, not override how the dropdown is shown.
                SiebelAppFacade.ColorBoxPW.superclass.ShowUI.call(this, control);

                // Get the original element - which is an input type. We'll decorate it. 
                var el = this.GetEl();
                if (el && el.length) {
                    parent = el.parent();
                    // Create a div, give it some sizing and coloring properties and 
                    // attach it after the original control (ie, inside the parent)
                    parent.append("<div id='colorbox_" + el.attr("name") + "' ></div>");
                    parent.find("div[id^=colorbox]").addClass("siebui-colorbox-base");
                }
            };

            // BindEvents is a lifecycle method that gets called when the PW is being instantiated.
            // It is responsible for attaching event handlers for the control, so that right actions can occur when the user does different actions on the control.
            ColorBoxPW.prototype.BindEvents = function () {

                // Again, we only want to attach some events to the new box. Not affect the original dropdown itself.
                SiebelAppFacade.ColorBoxPW.superclass.BindEvents.call(this);

                // Get the new box we have created, and the Event Helper objects.
                var colorbox = this.GetEl().parent().find("div[id^=colorbox]"),
                    evHelper = this.Helper("EventHelper");

                if (colorbox && colorbox.length && evHelper) {
                    // We will attach three event handlers. Using the Event Helper homogenizes events between different platforms. 
                    // For example, "click" event will work as "touchend" for touch devices.
                    // Custom handlers are methods that are defined in the PW itself.
                    evHelper
                        .Manage(colorbox, "mouseenter", { ctx: this }, OnMouseEnter)
                        .Manage(colorbox, "mouseleave", { ctx: this }, OnMouseLeave)
                        .Manage(colorbox, "click", { ctx: this }, OnClick)
                }
            };

            function OnMouseEnter() {
                // This is our handler for when the user hovers on the box. Let's inform them that it's clickable.
                $(this).append("<div id='info'>Click for Info...</div>");
            }
            function OnMouseLeave() {
                // This is our handler for when the user stops the hovering. Remove the information!
                $(this).find("#info").remove();
            }
            function OnClick() {
                // This is our handler for when the user takes the bait and clicks on the box.
                // We're going to construct a dialog that tells the user what the colors mean.

                // So first, we create a div with the correct html and attach it to the parent.
                var parent = $(this).parent(),
                    html = "<div id='legend' title='Legend'>"
                         + "<br><br>"
                         + "<div style='width: 200px; height: 20px; background-color: rgb(255, 0, 0);'>&emsp;&emsp;Do Not Pursue</div><br>"
                         + "<div style='width: 200px; height: 20px; background-color: orange;'>&emsp;&emsp;Pursue If Time Permits</div><br>"
                         + "<div style='width: 200px; height: 20px; background-color: rgb(255, 255, 0);'>&emsp;&emsp;Pursue</div><br>"
                         + "<div style='width: 200px; height: 20px; background-color: rgb(0, 128, 0);'>&emsp;&emsp;Pursue Aggressively</div><br>"
                         + "</div>";
                parent.append(html);

                // Then we make the div into a jQuery-UI dialog; which puts the content into modal a popup. 
                // More documentation about this api can be found in jQuery-UI docs.
                parent.find("#legend").dialog({
                    resizeable: false,
                    height: 275,
                    width: 225,
                    modal: true,
                    buttons: {
                        Cancel: function () {
                            $(this).dialog("close");
                        }
                    }
                });
            }

            // SetValue is an API that's called when a value change has occured on that control.
            // This could either be due to a change by the user or by the Siebel system.
            ColorBoxPW.prototype.SetValue = function (value, index) {

                // As usual, let the actual dropdown do its job.
                SiebelAppFacade.ColorBoxPW.superclass.SetValue.call(this, value, index);

                var colorbox = this.GetEl(index).parent().find("div[id^=colorbox]");
                if (colorbox && colorbox.length) {

                    // 'value' is a string, we need to first convert it to a number.
                    var val = parseInt(value),
                        cssClass = "";  //Default class to add.

                    // As long as it's a valid number...
                    if (!isNaN(val)) {
                        // Let's give it different colors based on the value.
                        // .css() is a jQuery API that sets styling on DOM elements.
                        if (val >= 0 && val < 25) {
                            cssClass = " siebui-colorbox-red";
                        }
                        else if (val < 50) {
                            cssClass = " siebui-colorbox-orange";                            
                        }
                        else if (val < 75) {
                            cssClass = " siebui-colorbox-yellow";
                        }
                        else {
                            cssClass = " siebui-colorbox-green";
                        }
                    }
                    
                    // Remove all existing classes from the colorbox; and add the default one + the newly decided one.
                    colorbox.removeClass();
                    colorbox.addClass("siebui-colorbox-base" + cssClass);
                }
            };

            // That's it, that's all the customization we need. 
            return ColorBoxPW;
        }());

        // Now this bit governs how or where this custom PW applies. The AttachPW API attaches this PW to 
        // a specific type of control, which in our case is a combo box.
        SiebelApp.S_App.PluginBuilder.AttachPW(consts.get("SWE_CTRL_COMBOBOX"), SiebelAppFacade.ColorBoxPW, function (control) {
            // Every combo box encountered is run against this method definition, and returning true will do the attachment. 
            // The control object itself is at our disposal to make a sound choice. Conditions can be as complex or simple as required.
            // In this case, we return true only if the control's repository name is "Probability2".
            return (control.GetName() === "Probability2");
        });
        return SiebelAppFacade.ColorBoxPW;
    });
}