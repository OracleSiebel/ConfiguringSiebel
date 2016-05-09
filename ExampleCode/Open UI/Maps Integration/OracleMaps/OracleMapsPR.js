if (typeof(SiebelAppFacade.OracleMapsPR) === "undefined") {

    SiebelJS.Namespace("SiebelAppFacade.OracleMapsPR");

    define("siebel/custom/OracleMapsPR", ["siebel/phyrenderer", "siebel/custom/3rdParty/jquery.colorbox-min.js", window.location.protocol + "//elocation.oracle.com/elocation/jslib/oracleelocation.js", window.location.protocol + "//elocation.oracle.com/mapviewer/fsmc/jslib/oraclemaps.js"],
        function() {
            SiebelAppFacade.OracleMapsPR = (function() {

                function OracleMapsPR(pm) {
                    SiebelAppFacade.OracleMapsPR.superclass.constructor.apply(this, arguments);
                }

                SiebelJS.Extend(OracleMapsPR, SiebelAppFacade.PhysicalRenderer);

                OracleMapsPR.prototype.Init = function() {
                    SiebelAppFacade.OracleMapsPR.superclass.Init.apply(this, arguments);
                }

                OracleMapsPR.prototype.ShowUI = function() {

                    SiebelAppFacade.OracleMapsPR.superclass.ShowUI.apply(this, arguments);
                    var PM = this.GetPM();
                    var oControlSet = PM.Get("GetControls");

                    //find address label
                    var sMapPlaceholder = $("#Business_Street_Address_Label"); 

                    //add map icon before label
                    sMapPlaceholder.before("<a href='#map' id='map-icon'><img src='images/map.gif' height='15px' width='15px'></a>");

                    //add map div after content
                    $('#_sweclient').after("<div id='map' style='height:100%;width=100%;overflow:hidden;'></div>");
                    $('#map').css({
                        visibility: 'hidden',
                        height: '0px'
                    });

                }

                OracleMapsPR.prototype.BindData = function(bRefresh) {
                    SiebelAppFacade.OracleMapsPR.superclass.BindData.apply(this, arguments);

                    var PM = this.GetPM();
                    var oControlSet = PM.Get("GetControls");

                    var mapCenterLat;
                    var mapCenterLon;
                    var mapview;

                    //attach colorbox frame to map link near address label
                    $("#map-icon").colorbox({
                        inline: true,
                        width: "65%",
                        height: "75%",
                        opacity: 0.5,
                        //on complete add map
                        onComplete: function() {
                            $('#map').css({
                                visibility: 'visible',
                                height: '100%'
                            });
                            var oracleMaps = new OracleELocation("http://elocation.oracle.com/elocation");
                            //get customer address from siebel fields *** Adjust to your configuration ***
                            var custAddress = PM.ExecuteMethod("GetFieldValue", oControlSet[PM.Get("Street Address")]) + ", " + PM.ExecuteMethod("GetFieldValue", oControlSet[PM.Get("ZIP Code")]) + " " + PM.ExecuteMethod("GetFieldValue", oControlSet[PM.Get("City")]) + ", " + PM.ExecuteMethod("GetFieldValue", oControlSet[PM.Get("Country")]);
                            //get latitude and longitude
                            oracleMaps.geocode(custAddress, function(results, address) {
                                //if found
                                if (results[0].accuracy != -1) {
                                    mapCenterLat = results[0].y;
                                    mapCenterLon = results[0].x;
                                    //create point in customer address
                                    var mpoint = MVSdoGeometry.createPoint(mapCenterLon, mapCenterLat, 8307);
                                    var baseURL = "http://elocation.oracle.com/mapviewer";
                                    //add map to map div
                                    mapview = new MVMapView(document.getElementById("map"), baseURL);
                                    //set map preferences *** more info here: http://slc02okf.oracle.com/mvdemo/fsmc/apidoc/symbols/MVMapView.html
                                    mapview.addMapTileLayer(new MVBaseMap("elocation_mercator.world_map"));
                                    //center in customer address 
                                    mapview.setCenter(mpoint);
                                    var mapZoom = 14;
                                    mapview.setZoomLevel(mapZoom);
                                    //add marker to customer address
                                    var mfoi1 = MVFOI.createMarkerFOI("1", mpoint, "images/marker_red.png", 25, 35);
                                    mapview.addFOI(mfoi1);
                                    mapview.display();
                                } else {
                                    $('#map').html("<div style='width: 80%;text-align: center;font-size: larger !important;margin: 10%;'>Address not found!</div>");
                                }
                            });
                        },
                        //when closing revert map div to hidden mode
                        onCleanup: function() {
                            mapview = null;
                            $('#map').css({
                                visibility: 'hidden',
                                height: '0px'
                            });
                            $('#map').html("");
                        }
                    });

                }

                OracleMapsPR.prototype.BindEvents = function() {
                    SiebelAppFacade.OracleMapsPR.superclass.BindEvents.apply(this, arguments);
                }

                OracleMapsPR.prototype.EndLife = function() {
                    SiebelAppFacade.OracleMapsPR.superclass.EndLife.apply(this, arguments);
                    //clean-up
                    $.colorbox.remove();
                    $('#map').remove();
                }

                return OracleMapsPR;
            }());
            return "SiebelAppFacade.OracleMapsPR";
        })
}
