if (typeof (SiebelAppFacade.OracleMapsPR) === "undefined") {

    SiebelJS.Namespace("SiebelAppFacade.OracleMapsPR");

    define("siebel/custom/OracleMapsPR", ["siebel/phyrenderer", "siebel/custom/3rdParty/colorbox/jquery.colorbox-min.js", window.location.protocol + "//elocation.oracle.com/elocation/jslib/oracleelocation.js", window.location.protocol + "//elocation.oracle.com/mapviewer/fsmc/jslib/oraclemaps.js"],
        function () {
            SiebelAppFacade.OracleMapsPR = (function () {

                var mapId = "ompr_map";

                function OracleMapsPR(pm) {
                    SiebelAppFacade.OracleMapsPR.superclass.constructor.apply(this, arguments);
                }

                SiebelJS.Extend(OracleMapsPR, SiebelAppFacade.PhysicalRenderer);

                OracleMapsPR.prototype.Init = function () {
                    SiebelAppFacade.OracleMapsPR.superclass.Init.apply(this, arguments);
                }

                OracleMapsPR.prototype.ShowUI = function () {

                    SiebelAppFacade.OracleMapsPR.superclass.ShowUI.apply(this, arguments);
                    var PM = this.GetPM();
                    var oControlSet = PM.Get("GetControls");

                    //find address label
                    var sMapPlaceholder = $("#" + PM.Get("Map Icon Label").replace(/ /g, "_") + "_Label");
                    //add map icon before label
                    sMapPlaceholder.before("<a href='#" + mapId + "' id='" + mapId + "-icon'><img src='images/map.gif' height='15px' width='15px'></a>");

                    //add map div after content
                    $('#_sweclient').after("<div id='" + mapId + "' style='height:100%;width=100%;overflow:hidden;'></div>");
                    $('#' + mapId).hide();
                }

                OracleMapsPR.prototype.BindData = function (bRefresh) {
                    SiebelAppFacade.OracleMapsPR.superclass.BindData.apply(this, arguments);

                    var PM = this.GetPM();
                    var oControlSet = PM.Get("GetControls");

                    //attach colorbox frame to map link near address label
                    $("#" + mapId + "-icon").colorbox({
                        inline: true,
                        width: "65%",
                        height: "75%",
                        opacity: 0.5,
                        //on complete add map
                        onComplete: function () {
                            var currentRecord = PM.Get("GetRecordSet")[PM.Get("GetSelection")];
                            $('#' + mapId).show();
                            var oracleMaps = new OracleELocation("http://elocation.oracle.com/elocation");
                            //get customer address from siebel fields *** Adjust to your configuration ***
                            var custAddress = currentRecord[PM.Get("Street Address")] + ", " + currentRecord[PM.Get("City")] + ", " + currentRecord[PM.Get("Zip Code")] + ", " + currentRecord[PM.Get("Country")];
                            SiebelJS.Debug(custAddress);
                            //get latitude and longitude
                            oracleMaps.geocode(custAddress, function (results, address) {
                                //if found
                                if (results[0].accuracy != -1) {
                                    var mapCenterLat = results[0].y;
                                    var mapCenterLon = results[0].x;
                                    //create point in customer address
                                    var mpoint = MVSdoGeometry.createPoint(mapCenterLon, mapCenterLat, 8307);
                                    var baseURL = "http://elocation.oracle.com/mapviewer";
                                    //add map to map div
                                    var mapview = new MVMapView($("#" + mapId)[0], baseURL);
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
                                    $('#' + mapId).html("<div style='width: 80%;text-align: center;font-size: larger !important;margin: 10%;'>Address not found!</div>");
                                }
                            });
                        },
                        //when closing revert map div to hidden mode
                        onCleanup: function () {
                            mapview = null;
                            $('#' + mapId).html("").hide();
                        }
                    });

                }

                OracleMapsPR.prototype.BindEvents = function () {
                    SiebelAppFacade.OracleMapsPR.superclass.BindEvents.apply(this, arguments);
                }

                OracleMapsPR.prototype.EndLife = function () {
                    SiebelAppFacade.OracleMapsPR.superclass.EndLife.apply(this, arguments);
                    //clean-up
                    $('#' + mapId).remove();
                }

                return OracleMapsPR;
            }());
            return "SiebelAppFacade.OracleMapsPR";
        })
}