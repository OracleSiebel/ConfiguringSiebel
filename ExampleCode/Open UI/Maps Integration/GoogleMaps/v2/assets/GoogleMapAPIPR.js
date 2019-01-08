if( typeof( SiebelAppFacade.GoogleMapApiPR ) === "undefined" ){

    SiebelJS.Namespace( "SiebelAppFacade.GoogleMapApiPR" );
	
	define ("siebel/custom/GoogleMapAPIPR", ["siebel/jqgridrenderer"], function () {
		SiebelAppFacade.GoogleMapApiPR = ( function(){

			var siebConsts  = SiebelJS.Dependency( "SiebelApp.Constants" );
			var fontawesome={};
			fontawesome.markers={"MAP_MARKER":"M27.648 -41.399q0 -3.816 -2.7 -6.516t-6.516 -2.7 -6.516 2.7 -2.7 6.516 2.7 6.516 6.516 2.7 6.516 -2.7 2.7 -6.516zm9.216 0q0 3.924 -1.188 6.444l-13.104 27.864q-0.576 1.188 -1.71 1.872t-2.43 0.684 -2.43 -0.684 -1.674 -1.872l-13.14 -27.864q-1.188 -2.52 -1.188 -6.444 0 -7.632 5.4 -13.032t13.032 -5.4 13.032 5.4 5.4 13.032z"};
			var mapReady = false;
			
			function GoogleMapApiPR( pm ){
				SiebelAppFacade.GoogleMapApiPR.superclass.constructor.call( this, pm );
			}

			SiebelJS.Extend( GoogleMapApiPR, SiebelAppFacade.JQGridRenderer );
			
			GoogleMapApiPR.prototype.Init = function () {
				SiebelAppFacade.GoogleMapApiPR.superclass.Init.call( this );
			}
			
			GoogleMapApiPR.prototype.ShowSelection = function () {
				SiebelAppFacade.GoogleMapApiPR.superclass.ShowSelection.apply(this,arguments);
				var bc = this.GetPM().Get("GetBusComp");
				$(".map_canvas")[0].contentWindow.postMessage({bounce:bc.GetFieldValue("Id")},"https://"+document.location.host);
			}
			
			GoogleMapApiPR.prototype.mapComms = function (event) {
				// handle messages received from map frame
				var recordSet;
				if (this.GetPM()) recordSet = this.GetPM().Get( "GetRawRecordSet" );
				// ready notification
				if (event.data == "ready") {
					mapReady = true;
				// notification that a marker is currently visible on the map
				} else if (event.data.show) {
					for (var i=0; i<recordSet.length; i++) {
						if (recordSet[i].Id==event.data.show) {
							// assumes a standard applet in tile or list mode is present in A1 position
							// shouldn't break if they're not, though some strange results will occur if tiles are being used for something else in the first position
							$("#S_A1_tile_"+i).show();
							$("#s_S_A1_div #s_1_l.ui-jqgrid-btable #"+(i+1)).show();
						}
					}
				// notification that a marker is currently invisible on the map
				} else if (event.data.hide) {
					for (var i=0; i<recordSet.length; i++) {
						if (recordSet[i].Id==event.data.hide) {
							// assumes a standard applet in tile or list mode is present in A1 position
							// shouldn't break if they're not, though some strange results will occur if tiles are being used for something else in the first position
							$("#S_A1_tile_"+i).hide();
							$("#s_S_A1_div #s_1_l.ui-jqgrid-btable #"+(i+1)).hide();
						}
					}
				// notification that a marker was clicked (selected)
				} else if (event.data.select) {
					for (var i=0; i<recordSet.length; i++) {
						if (recordSet[i].Id==event.data.select) {
							this.GetPM().OnControlEvent(consts.get("PHYEVENT_SELECT_ROW"), i, "", "");
						}
					}					
				}
			}
						
			GoogleMapApiPR.prototype.ShowUI = function(){

				mapReady = false;
				var pm = this.GetPM();
				var placeHolder = pm.Get( "GetPlaceholder" );
				var strHTML="";
				var that=this;
				window.addEventListener("message", function(event) {that.mapComms.call(that,event)}, false);

				$( "#"  + placeHolder ).append( '<iframe seamless class="map_canvas" id="map_canvas" style="width:100%; height:800px; z-index: 1; border: 0" src=""></div>' );
				$(".map_canvas").width($(".map_canvas").parent().parent().width()-4);

				strHTML+='  <head>';
				strHTML+='	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />';
				strHTML+='	<style type="text/css">';
				strHTML+='	  html { height: 100% }';
				strHTML+='	  body { height: 100%; margin: 0; padding: 0 }';
				strHTML+='	  #map_canvas { height: 100% }';
				// add styles here to affect the info window styling		
				strHTML+='	</style>';
				strHTML+='	<script type="text/javascript"';
				// Obtain an API key from https://developers.google.com/maps/documentation/javascript/get-api-key
				strHTML += ' src="https://maps.googleapis.com/maps/api/js?key=API-KEY">';
				strHTML+='	</script>';
				strHTML+='	<script type="text/javascript"';
				strHTML+='	  src="/siebel/scripts/siebel/custom/3rdParty/markerclusterer.js">';
				strHTML+='	</script>';
				strHTML+='	<script type="text/javascript">';
				strHTML+='	  var geocoder;';
				strHTML+='	  var map;';
				strHTML+='	  var latLngList = new Array();';
				strHTML+='	  var bounds;';
				strHTML+='	  var markers = new Array();';
				strHTML+='	  var markerClusterer = null;';
				strHTML+='	  var options = {imagePath:"/siebel/images/custom/m"};';
				strHTML+='	  var details = [];';
				strHTML+='	  ';
				// handle events received
				strHTML+='	  function mapReceivedEventDispatch(event) {';
				strHTML+='	  ';
				strHTML+='	  	if (event.data.codeAddress) {';
				strHTML+='	  		codeAddress(event.data.codeAddress.detail,event.data.codeAddress.icon)';
				strHTML+='	  	} else if (event.data.clear) {';
				strHTML+='	  		clearMap();';
				strHTML+='	  	} else if (event.data.bounce) {';
				strHTML+='	  		id = event.data.bounce;';
				strHTML+='	  		for (marker in markers) {';
				strHTML+='	  			markers[marker].setAnimation(null);';
				strHTML+='	  			if (markers[marker].id == id) {';
				strHTML+='	  				markers[marker].setAnimation(google.maps.Animation.BOUNCE);';
				strHTML+='	  			}';
				strHTML+='	  		}';
				strHTML+='	  	}';
				strHTML+='	  }';
				strHTML+='	  ';
				strHTML+='	  function initialize() {';
				strHTML+='		geocoder = new google.maps.Geocoder();';
				strHTML+='		bounds = new google.maps.LatLngBounds();';
				strHTML+='		var mapOptions = {';
				strHTML+='		  center: new google.maps.LatLng(0,0),';
				strHTML+='		  zoom: 8,';
				strHTML+='		  mapTypeId: google.maps.MapTypeId.ROADMAP';
				strHTML+='		};';
				strHTML+='		map = new google.maps.Map(document.getElementById("map_canvas"),';
				strHTML+='			mapOptions);';
				strHTML+='	    window.addEventListener("message", mapReceivedEventDispatch, false);';
				strHTML+='	    parent.postMessage("ready", "*");';
				strHTML+='	  	google.maps.event.addListener(map, "idle", function() {';
				strHTML+='	  		notifyRecordVisibility();';
				strHTML+='	  	});';
				strHTML+='	  }';
				strHTML+='	  ';
				strHTML+='	  function codeAddress(details,icon) {';
				strHTML+='		geocoder.geocode( { "address": JSON.parse(details).address}, function(results, status) {';
				strHTML+='		  if (status == google.maps.GeocoderStatus.OK) {';
				strHTML+='			var marker = new google.maps.Marker({';
				strHTML+='				map: map,';
				strHTML+='				position: results[0].geometry.location,';
				strHTML+='				animation: eval(JSON.parse(details).animation),';
				strHTML+='				title: JSON.parse(details).address,';
				strHTML+='				icon: JSON.parse(icon),';
				strHTML+='				id: JSON.parse(details).id';
				strHTML+='			});';
				strHTML+='			markers.push(marker);';
				strHTML+='			bounds.extend (results[0].geometry.location);';
				strHTML+='			map.fitBounds (bounds);';
				strHTML+='			if (markerClusterer) markerClusterer.clearMarkers();';
				strHTML+='			markerClusterer = new MarkerClusterer(map, markers, options);';
				// generate info window content		
				strHTML+='			var contentString = "<div class= \'content\'><div class= \'name\'>Name: "+JSON.parse(details).name+"</div><div class= \'type\'>Type: "+JSON.parse(details).type+"</div><div class= \'status\'>Status: "+JSON.parse(details).status+"</div></div>";';
				strHTML+='			marker.infowindow = new google.maps.InfoWindow({';
				strHTML+='				content: contentString';
				strHTML+='			});';
				strHTML+='			marker.addListener("mouseover", function() {';
				strHTML+='				marker.infowindow.open(map, marker)';
				strHTML+='			});';
				strHTML+='			marker.addListener("mouseout", function() {';
				strHTML+='				marker.infowindow.close()';
				strHTML+='			});';
				// notification for record change
				strHTML+='			marker.addListener("click", function() {';
				strHTML+='				parent.postMessage({select:marker.id}, "*");';
				strHTML+='			});';
				strHTML+='		  } else {';
				strHTML+='			alert("Geocode was not successful for the following reason: " + status);';
				strHTML+='		  }';
				strHTML+='		});';
				strHTML+='	  }';
				strHTML+='	  function cluster() {';
				strHTML+='		markerClusterer = new MarkerClusterer(map, markers, options);';
				strHTML+='	  }';
				strHTML+='	  function clearMap() {';
				strHTML+='		for (var i=0; i<markers.length; i++) {';
				strHTML+='			markers[i].setMap(null);';
				strHTML+='			markerClusterer.clearMarkers();';
				strHTML+='		}';
				strHTML+='		markers = new Array();';
				strHTML+='		bounds = new google.maps.LatLngBounds();';
				strHTML+='	  }';
				strHTML+='	  ';
				// handle map movements to notify marker visibility changes
				strHTML+='	  function notifyRecordVisibility() {';
				strHTML+='	  	var bounds = map.getBounds();';
				strHTML+='	  	for (marker in markers) {';
				strHTML+='	  		if (bounds.contains(markers[marker].getPosition())) {';
				strHTML+='	    		parent.postMessage({show:markers[marker].id}, "*");';
				strHTML+='	  		} else {';
				strHTML+='	    		parent.postMessage({hide:markers[marker].id}, "*");';
				strHTML+='	  		}';
				strHTML+='	  	}';
				strHTML+='	  }';
				strHTML+='	</script>';
				strHTML+='  </head>';
				strHTML+='  <body>';
				strHTML+='	<div id="map_canvas" style="width:100%; height:100%; border-radius: 8px; -webkit-mask-image: -webkit-gradient(linear, left top, left bottom, from(rgba(0,0,0,1)), to(rgba(0,0,0,1)));"></div>';
				strHTML+='	<script>initialize()</script>';
				strHTML+='  </body>'; 
				$(".map_canvas")[0].contentWindow.document.write(strHTML);
				var that=this;
				setTimeout(function() {that.resize();},2);
			};
			
			GoogleMapApiPR.prototype.BindData = function(){

				var that=this;
				function processData () {
					if (mapReady) {
						$(".map_canvas")[0].contentWindow.postMessage({clear:"."},"https://"+document.location.host);
						var pm = that.GetPM();
						var listOfCols = pm.Get( "ListOfColumns" );
						var recordSet = pm.Get( "GetRawRecordSet" );
						activeRecord = that.GetPM().Get("GetBusComp").GetFieldValue("Id");
						icon = {
							path: fontawesome.markers.MAP_MARKER,
							scale: 0.5,
							strokeWeight: 1,
							strokeColor: 'black',
							strokeOpacity: 1,
							fillColor: 'red',
							fillOpacity: 1
						};

						for (var i=0; i<recordSet.length; i++) {
							record = recordSet[i];
							// extract data from the fields on the list applet
							detail = {
								status : record["Status"],
								address : record["Full Address"],
								type : record["Type"],
								name : record["Name"],
								id : record["Id"],
								animation : record["Id"]==activeRecord?"google.maps.Animation.BOUNCE":"null"
							}
							// alter icon color based on status
							switch(detail.status) {								
								case "Inactive":
									icon.fillColor = 'yellow';
									break;
									
								case "Active":
									icon.fillColor = 'green';
									break;
									
								default:
									break;
							}
							if (detail.address!="" & detail.address!="undefined") {
								$(".map_canvas")[0].contentWindow.postMessage(
									{codeAddress:{
										detail:JSON.stringify(detail),
										icon:JSON.stringify(icon)}
									},
									"https://"+document.location.host
								);
							}
						}
						$(".map_canvas")[0].contentWindow.postMessage({bounce:that.GetPM().Get("GetBusComp").GetFieldValue("Id")},"https://"+document.location.host);
					} else {
						// wait until map is ready for processing
						setTimeout(function(){processData()},4);
					}
				}
				processData();
			};
			
			GoogleMapApiPR.prototype.resize = function( ){
				SiebelAppFacade.GoogleMapApiPR.superclass.resize.apply(this,arguments);
				$(".map_canvas").width($(".map_canvas").parent().parent().width()-4);
			};
			
			GoogleMapApiPR.prototype.EndLife = function() {
				mapReady = false;
			}

			return GoogleMapApiPR;

		}());
		
		return "SiebelAppFacade.GoogleMapApiPR";
	})
}

