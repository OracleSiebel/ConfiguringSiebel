if( typeof( SiebelAppFacade.GoogleMapApiPR ) === "undefined" ){

    SiebelJS.Namespace( "SiebelAppFacade.GoogleMapApiPR" );
	
	define ("siebel/custom/GoogleMapAPIPR", ["siebel/jqgridrenderer"], function () {
		SiebelAppFacade.GoogleMapApiPR = ( function(){

			var siebConsts  = SiebelJS.Dependency( "SiebelApp.Constants" );
			var fontawesome={};fontawesome.markers={"MAP_MARKER":"M27.648 -41.399q0 -3.816 -2.7 -6.516t-6.516 -2.7 -6.516 2.7 -2.7 6.516 2.7 6.516 6.516 2.7 6.516 -2.7 2.7 -6.516zm9.216 0q0 3.924 -1.188 6.444l-13.104 27.864q-0.576 1.188 -1.71 1.872t-2.43 0.684 -2.43 -0.684 -1.674 -1.872l-13.14 -27.864q-1.188 -2.52 -1.188 -6.444 0 -7.632 5.4 -13.032t13.032 -5.4 13.032 5.4 5.4 13.032z"};
			
			function GoogleMapApiPR( pm ){
				SiebelAppFacade.GoogleMapApiPR.superclass.constructor.call( this, pm );
			}

			SiebelJS.Extend( GoogleMapApiPR, SiebelAppFacade.JQGridRenderer );
			
			GoogleMapApiPR.prototype.Init = function () {
				SiebelAppFacade.GoogleMapApiPR.superclass.Init.call( this );
			}
			
			GoogleMapApiPR.prototype.ShowUI = function(){

				var pm = this.GetPM();
				var placeHolder = pm.Get( "GetPlaceholder" );
				var strHTML="";

				$( "#"  + placeHolder ).append( '<iframe seamless class="map_canvas" id="map_canvas" style="width:100%; height:400px; z-index: 1; border: 0" src=""></div>' );
				$(".map_canvas").width($(".map_canvas").parent().parent().width()-4);

				strHTML+='  <head>';
				strHTML+='	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />';
				strHTML+='	<style type="text/css">';
				strHTML+='	  html { height: 100% }';
				strHTML+='	  body { height: 100%; margin: 0; padding: 0 }';
				strHTML+='	  #map_canvas { height: 100% }';
				strHTML+='	</style>';
				strHTML+='	<script type="text/javascript"';
				//Try this for testing, comment if using the API KEY below
				strHTML+='	  src="https://maps.googleapis.com/maps/api/js?sensor=true">';
				//Obtain an API key from https://developers.google.com/maps/documentation/javascript/get-api-key
				//Uncomment the following line for production use and insert your key
				//strHTML += ' src="http://maps.googleapis.com/maps/api/js?key=MY_API_KEY">';
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
				strHTML+='	  var options = {imagePath:"https://demohost.us.oracle.com:9001/siebel/images/custom/m"};';
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
				strHTML+='	  }';
				strHTML+='	  ';
				strHTML+='	  function codeAddress(address,icon) {';
				strHTML+='		geocoder.geocode( { "address": address}, function(results, status) {';
				strHTML+='		  if (status == google.maps.GeocoderStatus.OK) {';
				strHTML+='			var marker = new google.maps.Marker({';
				strHTML+='				map: map,';
				strHTML+='				position: results[0].geometry.location,';
				//strHTML+='				animation: google.maps.Animation.DROP,';
				strHTML+='				title: address,';
				strHTML+='				icon: JSON.parse(icon)';
				strHTML+='			});';
				strHTML+='			markers.push(marker);';
				strHTML+='			bounds.extend (results[0].geometry.location);';
				strHTML+='			map.fitBounds (bounds);';
				strHTML+='			if (markerClusterer) markerClusterer.clearMarkers();';
				strHTML+='			markerClusterer = new MarkerClusterer(map, markers,options);';
				strHTML+='		  } else {';
				strHTML+='			alert("Geocode was not successful for the following reason: " + status);';
				strHTML+='		  }';
				strHTML+='		});';
				strHTML+='	  }';
				strHTML+='	  function cluster() {';
				strHTML+='		markerClusterer = new MarkerClusterer(map, markers,options);';
				strHTML+='	  }';
				strHTML+='	  function clearMap() {';
				strHTML+='		for (var i=0; i<markers.length; i++) {';
				strHTML+='			markers[i].setMap(null);';
				strHTML+='			markerClusterer.clearMarkers();';
				strHTML+='		}';
				strHTML+='		markers = new Array();';
				strHTML+='		bounds = new google.maps.LatLngBounds();';
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

				$(".map_canvas")[0].contentWindow.document.write('<script>clearMap()</script>');
				var pm = this.GetPM();
				var listOfCols = pm.Get( "ListOfColumns" );
				var recordSet = pm.Get( "GetRecordSet" );
				for (var i=0; i<recordSet.length; i++) {
					record = recordSet[i];
					address=record["Full Address"];
					if (record["SSA Primary Field"] == 'Y')
						//icon = "images/custom/googlemap/marker_red.jpg";
						icon = {
							path: fontawesome.markers.MAP_MARKER,
							scale: 0.5,
							strokeWeight: 1,
							strokeColor: 'black',
							strokeOpacity: 1,
							fillColor: '#DA6161',
							fillOpacity: 1
						};
					else
						//icon = "images/custom/googlemap/marker_blue.jpg";
						icon = {
							path: fontawesome.markers.MAP_MARKER,
							scale: 0.5,
							strokeWeight: 1,
							strokeColor: 'black',
							strokeOpacity: 1,
							fillColor: '#3878C7',
							fillOpacity: 1
						};
					if (address!="" & address!="undefined") {
						//$(".map_canvas")[0].contentWindow.document.write('<script>codeAddress("' + address + '")</script>');
						$(".map_canvas")[0].contentWindow.document.write('<script>codeAddress("' + address + '",\'' + JSON.stringify(icon) + '\')</script>');
						// Uncomment to force the view to scroll to the bottom after each address is added (happens also on initial populate).
						//$('#_sweview').animate({ 
						//   scrollTop: 1000000}, 
						//   100, 
						//   "swing"
						//);
					}
				}

			};
			
			GoogleMapApiPR.prototype.resize = function( ){
				SiebelAppFacade.GoogleMapApiPR.superclass.resize.call( this );
				$(".map_canvas").width($(".map_canvas").parent().parent().width()-4);
			};

			return GoogleMapApiPR;

		}());
		
		return "SiebelAppFacade.GoogleMapApiPR";
	})
}