/*****************************************************************************
 *
 * Copyright (C) 2011 Oracle., All rights reserved.
 *
 * FILE:       msgbrdcstPM.js
 *  $Revision: 0 $
 *      $Date: 06/26/12 0:00 $
 *    $Author: Anirban
 *
 * CREATOR:    Anirban Das
 *
 * DESCRIPTION
 *    Presentation model for Message BroadCasting.
 *
 * NANPATIL: Updated for Feature: Allow/Disable Dismiss message
 *****************************************************************************/
if (typeof (SiebelAppFacade.MsgBrdCstPresentationModel) === "undefined"){
    SiebelJS.Namespace('SiebelAppFacade.MsgBrdCstPresentationModel');
    SiebelAppFacade.MsgBrdCstPresentationModel = (function(){
    // initial preference seeting
      var HTMLMessages = "";
      var HTMLMsgCount = "";
      var isMsgChanged  = false;
      var m_strNormal  = "";
      var m_strAuto    = "";
      var m_strHigh        = "";
      var m_strUrgent      = "";
      var m_strUrgentWA    = "";
      var m_normalColor  = "";
      var m_highColor  = "";
      var m_urgentColor  = "";
      var m_updateInterval = 120;   // default 120 seconds
      var m_prefixNormal    = "";
      var m_prefixHigh      = "";
      var m_prefixUrgent    = "";
      var msgArray =[]; // msgArray stores the list of message
      var consts  = SiebelJS.Dependency( "SiebelApp.Constants" );
      var utils   = SiebelJS.Dependency( "SiebelApp.Utils" );
      function MsgBrdCstPresentationModel( proxy ){
            SiebelAppFacade.MsgBrdCstPresentationModel.superclass.constructor.call( this, proxy );
            //this.Init();
      }
      SiebelJS.Extend( MsgBrdCstPresentationModel, SiebelAppFacade.PresentationModel );
    function IsMsgBarEnabled (){
      var bMsgBarEnabled = 0;
      var service = SiebelApp.S_App.GetService( "Message Bar" );
      var inPropSet = SiebelApp.S_App.NewPropertySet ();
      inPropSet.SetProperty(consts.get("SWE_BUSINESS_SERVICE"), consts.get("SWE_NUMERIC_TRUE"));
      var outPropSet = SiebelApp.S_App.NewPropertySet ();    
      if( service ){
                    outPropSet = null;
                    var lpsca = {};
                    lpsca.async =  true;//ASYNC CALL
                   // lpsca.npr = true;
                    lpsca.selfbusy = true;
                    lpsca.scope = this;
                    lpsca.cb = function(){                     
                    outPropSet = arguments[2];
                    if (outPropSet !== null){
                      var nChildCount = outPropSet.GetChildCount ();
                      for (var j = 0; j < nChildCount; j++){
                            var childPropSet = outPropSet.GetChild (j);
                            if (childPropSet !== null && childPropSet.GetType () === "ResultSet"){
                                     bMsgBarEnabled = childPropSet.GetProperty("IsMsgBarEnabled");
                             }                        
                       }                  
                  }
               }
           service.InvokeMethod ("IsMsgBarEnabled", inPropSet, lpsca );
          }
      return bMsgBarEnabled;
  }  
      MsgBrdCstPresentationModel.prototype.Init = function(){
          var bShowMsgBar = IsMsgBarEnabled.call( this );
          if ( bShowMsgBar === "1" ) {   
          this.AttachEventHandler("ShowMessageBar", function(eventData){
                CreateMessageBar.call( this );
                var that = this;
                setInterval(function(){
                  that.ExecuteMethod("UpdateMessageBar");
                }, m_updateInterval*1000);
         });
            this.AddMethod( "UpdateMessageBar", UpdateMessageBar);
            this.AddMethod( "AddNotification", AddNotification);
		      this.AddMethod( "DeleteNotification", DeleteNotification);
            this.AddMethod( "UpdateNotification", UpdateNotification);
            this.AddMethod( "ReadNotification", ReadNotification);
            this.AttachEventHandler("UpdateServer", function(usrMsgId,value){
                //Update the server
                var service = SiebelApp.S_App.GetService( "Message Bar" );
                var inPropSet = SiebelApp.S_App.NewPropertySet ();
                var outPropSet = SiebelApp.S_App.NewPropertySet ();
                inPropSet.SetProperty("User MsgId",usrMsgId);
                inPropSet.SetProperty("User MsgStatus",value);
                inPropSet.SetProperty(consts.get("SWE_BUSINESS_SERVICE"), consts.get("SWE_NUMERIC_TRUE"));
                if( service ){
                    outPropSet = null;
                    var lpsca = {};
                    lpsca.async =  true;
                    lpsca.npr = true;
                    lpsca.selfbusy = true;
                    lpsca.scope = this;
                    service.InvokeMethod ("UpdateUserMsg", inPropSet, lpsca );
                    outPropSet = arguments[2];
               }
            });
            this.AttachEventHandler("DownLoadFileFromServer", function(strDetails){
                var  hasView = false;
                //Download the file from server
                var service = SiebelApp.S_App.GetService( "Message Bar" );             
                var inPropSet = SiebelApp.S_App.NewPropertySet ();
                var outPropSet = SiebelApp.S_App.NewPropertySet ();
               var paramArrry = strDetails.split(";");
               for (var i = 0; i < paramArrry.length; i++) {            
                var sepIndex = paramArrry[i].indexOf(":");
                if (sepIndex < 0) {
                    continue;
                  }
                var propName = $.trim(paramArrry[i].substr(0, sepIndex)).toLowerCase();
                var propValue = $.trim(paramArrry[i].substr(sepIndex + 1));
                switch (propName){
                case "bo":
                   inPropSet.SetProperty("BO",propValue);
                    break;
                case "bc":
                    inPropSet.SetProperty("BC",propValue);
                    break; 
                case "view":
                    inPropSet.SetProperty("View",propValue);
                    hasView = true;
                    break; 
                case "field":
                   inPropSet.SetProperty("Field",propValue);
                    break;
                case "file ext":
                   inPropSet.SetProperty("File Ext",propValue);
                    break;
                case "id":
                      inPropSet.SetProperty("Id",propValue);
                      break;                    
                default:
                    break;
                }               
              }
                if( service ){
                    outPropSet = null;
                    var lpsca = {};
                    lpsca.async =  true;
                    lpsca.npr = false;
                    lpsca.selfbusy = true;
                    lpsca.scope = this;
                    lpsca.cb = function(){
                           if ( hasView === true){ 
                              this.GetRenderer().CloseMainDialog();
                           }
                    }
                    service.InvokeMethod ("DownLoadReport", inPropSet, lpsca );
               }
            });
          }
      };
      MsgBrdCstPresentationModel.prototype.Show = function(){
            var renderer = this.GetRenderer();
            renderer.ShowUI();
            renderer.BindEvents();
      };
      MsgBrdCstPresentationModel.prototype.Setup = function( ps ){
            SiebelAppFacade.MsgBrdCstPresentationModel.superclass.Setup.call( this, ps   );
      };
//CRUD API's
function AddNotification (inputargAdd)
{
   var Type,rowId,Body;
   var service = SiebelApp.S_App.GetService( "Message Bar" );
   var inPropSet = SiebelApp.S_App.NewPropertySet ();
   var reqField = true;
   if(inputargAdd !== null)
   {
      var nRecordCountAdd = inputargAdd.GetChildCount ();
		for (var j = 0; j < nRecordCountAdd; j++)
		{
			var inPropSet = inputargAdd.GetChild (j);
			inPropSet.GetProperty("Type", Type);
			inPropSet.GetProperty("Body", Body);
			if(Body === null || Type=== null)
			{
			   SiebelJS.Log("Type Body and Id are the required fields");
			   reqField=false;	
			}
		}
	}
	if(reqField)
	{
	   if( service )
	   {
	      var lpsca = {};
         lpsca.async =  false;
         lpsca.selfbusy = true;
         lpsca.npr = false;
         lpsca.scope = this;
         return service.InvokeMethod ("AddNotification", inputargAdd, lpsca);
      }
    }   
 }
function DeleteNotification (inputargdelete)
{
   var rowId;
	var nRecordCount=0;
   var reqField=true;
   var service = SiebelApp.S_App.GetService( "Message Bar" );
   var inPropSet = SiebelApp.S_App.NewPropertySet ();
   if(inputargdelete !== null)
   {
		nRecordCountdelete = inputargdelete.GetChildCount ();
		for (var j = 0; j < nRecordCountdelete; j++)
		{
		   var inPropSet = inputargdelete.GetChild (j);
		   inPropSet.GetProperty("Msg Id", rowId);
		   if(rowId === null)
			   {
			   SiebelJS.Log("Row id is required");
			   reqField=false;	
			   }
		 }
	}
	if(reqField)
	{
	   if( service )
	   {
	      var lpsca = {};
         lpsca.async =  false;
         lpsca.selfbusy = true;
         lpsca.npr = false;
         lpsca.scope = this;
         return service.InvokeMethod ("DeleteNotification", inputargdelete, lpsca);
       }
	}
}
function ReadNotification (inputargRead)
{
   var rowId;
   var nRecordCount=0;
   var reqField=true;
   var service = SiebelApp.S_App.GetService( "Message Bar" );
   var inPropSet = SiebelApp.S_App.NewPropertySet ();
   if(inputargRead !== null)
   {
		nRecordCountRead = inputargRead.GetChildCount ();
		for (var j = 0; j < nRecordCountRead; j++)
		{
		   var inPropSet = inputargRead.GetChild (j);
		   inPropSet.GetProperty("Msg Id", rowId);
			   if(rowId === null)
			   {
			   SiebelJS.Log("Row Id is required");
			   reqField=false;	
			   }
		}
	}
	if(reqField)
	{
	   if( service )
	   {
	      var lpsca = {};
         lpsca.async =  false;
         lpsca.npr = false;
         lpsca.selfbusy = true;
         lpsca.scope = this;
         return service.InvokeMethod ("ReadNotification", inputargRead, lpsca);
		}
	}
} 
function UpdateNotification (inputargUpdate)
{
  var rowId;
  var reqField=true;
  var service = SiebelApp.S_App.GetService( "Message Bar" );
  var inPropSet = SiebelApp.S_App.NewPropertySet ();
  if(inputargUpdate !== null)
  {
	   var nRecordCountUpdate = inputargUpdate.GetChildCount ();
		for (var j = 0; j < nRecordCountUpdate; j++)
		{
		   var inPropSet = inputargUpdate.GetChild (j);
		   inPropSet.GetProperty("Msg Id", rowId);
		   if(rowId === null)
		   {
		   SiebelJS.Log("Row Id is required");
		   reqField=false;	
		   }
		}
	}
	if(reqField){
			if( service ){
	         var lpsca = {};
             lpsca.async =  false;
             lpsca.selfbusy = true;
             lpsca.npr = false;
             lpsca.scope = this;
           return service.InvokeMethod ("UpdateNotification", inputargUpdate, lpsca);
			}
		}
}    
/*
   UpdateMessageBar is called to update the messagebar with data
 */
function UpdateMessageBar (){
    var i,len;
    var service = SiebelApp.S_App.GetService( "Message Bar" );
    var inPropSet = SiebelApp.S_App.NewPropertySet ();
    inPropSet.SetProperty(consts.get("SWE_BUSINESS_SERVICE"), consts.get("SWE_NUMERIC_TRUE"));
    var outPropSet = SiebelApp.S_App.NewPropertySet ();
    if( service ){
        outPropSet = null;
        var that = this;
        var methodName = "UpdatePrefMsg";
        var lpsca = {};
        lpsca.async =  true;
        lpsca.scope = this;
        lpsca.npr = true;
        lpsca.selfbusy = true;
        lpsca.cb = function(){
                outPropSet = arguments[2];
                if (outPropSet !== null){
                    var nChildCount = outPropSet.GetChildCount ();
                    for (var j = 0; j < nChildCount; j++){
                        var childPropSet = outPropSet.GetChild (j);
                        if (childPropSet !== null && childPropSet.GetType () === "ResultSet"){
                            var value = childPropSet.GetProperty("returnVal");
                            GetMessages.call( that , value);
                            that.GetRenderer().UpdateMsgBar();
                        }
                    }
                }
            };
        service.InvokeMethod (methodName, inPropSet, lpsca );
    }
}
/*
   Gets the details to create the messagebar with data
 */
function CreateMessageBar (){
    var i,len;
    var service = SiebelApp.S_App.GetService( "Message Bar" );
    var inPropSet = SiebelApp.S_App.NewPropertySet ();
    inPropSet.SetProperty(consts.get("SWE_BUSINESS_SERVICE"), consts.get("SWE_NUMERIC_TRUE"));
    var outPropSet = SiebelApp.S_App.NewPropertySet ();
    if( service ){
        outPropSet = null;
        var that = this;
        var methodName = "Init";
        var lpsca = {};
        lpsca.async = true;
        lpsca.selfbusy =  true;
        lpsca.scope = this;
        lpsca.cb = function(){
                    outPropSet = arguments[2];
                    if (outPropSet !== null){
                        var nChildCount = outPropSet.GetChildCount ();
                        for (var j = 0; j < nChildCount; j++){
                            var childPropSet = outPropSet.GetChild (j);
                            if (childPropSet !== null && childPropSet.GetType () === "ResultSet"){
                                var value = childPropSet.GetProperty("returnVal");
                                that.MsgInit(value);
                            }
                        }
                    }
                that.MsgBrdCstInit(service);
             };
        service.InvokeMethod (methodName, inPropSet, lpsca );
    }
}
MsgBrdCstPresentationModel.prototype.MsgBrdCstInit =function(service)
{
    var inPropSet = SiebelApp.S_App.NewPropertySet ();
    inPropSet.SetProperty(consts.get("SWE_BUSINESS_SERVICE"), consts.get("SWE_NUMERIC_TRUE"));
    var outPropSet = SiebelApp.S_App.NewPropertySet ();
    if( service ){
        var  methodName = "UpdatePrefMsg";
        var that = this;
        var lpsca = {};
        lpsca.async =  true;
        lpsca.scope = this;
        lpsca.selfbusy =  true;
        lpsca.cb = function(){
                    outPropSet = arguments[2];
                    if (outPropSet !== null){
                        var nChildCount = outPropSet.GetChildCount ();
                        for (var j = 0; j < nChildCount; j++){
                            var childPropSet = outPropSet.GetChild (j);
                            if (childPropSet !== null && childPropSet.GetType () === "ResultSet"){
                                var value = childPropSet.GetProperty("returnVal");
                                GetMessages.call( that , value);
                                that.AddProperty( "MsgCount", HTMLMsgCount );
                                that.AddProperty( "MsgObjArray", msgArray );
                                var propSet = CCFMiscUtil_CreatePropSet();
                                propSet.SetProperty( "Normal", m_strNormal );
                                propSet.SetProperty( "Automation", m_strAuto );
                                propSet.SetProperty( "High", m_strHigh );
                                propSet.SetProperty( "Urgent", m_strUrgent );
                                propSet.SetProperty( "UrgentWA", m_strUrgentWA );
                                propSet.SetProperty( "NormalColor", m_normalColor );
                                propSet.SetProperty( "HighColor", m_highColor );
                                propSet.SetProperty( "UrgentColor", m_urgentColor );
                                propSet.SetProperty( "UpdateInterval", m_updateInterval );
                                propSet.SetProperty( "PrefixNormal", m_prefixNormal );
                                propSet.SetProperty( "PrefixHigh", m_prefixHigh );
                                propSet.SetProperty( "PrefixUrgent", m_prefixUrgent );
                                that.AddProperty( "MsgUserPrefPS", propSet );
                                that.GetRenderer().CreateMsgBar();
                            }
                        }
                    }
            };
        service.InvokeMethod (methodName, inPropSet, lpsca );
     }
};
/*
   parse the initialization string passed from server side
*/
MsgBrdCstPresentationModel.prototype.MsgInit =function( rawData )
{
   // Should be consistent with CSSMessageBarService::Init
   var pos     = new Array(1);
   pos[0]      = 0;
   var tmpStr      = nextString    (rawData, pos);
   tmpStr      = nextString       (rawData, pos);
   m_strNormal      = nextString (rawData, pos);
   m_strAuto        = nextString (rawData, pos);
   m_strHigh        = nextString (rawData, pos);
   m_strUrgent      = nextString (rawData, pos);
   m_strUrgentWA    = nextString (rawData, pos);
};
/*
   Gets the message and the user preferences
 */
function GetMessages(rawData){
   isMsgChanged  = false;
   var pos     = new Array(1);
   pos[0]      = 0;
   // see if there's Init info.
   pos[0] = rawData.indexOf("-$$$-");
   if (pos[0]>=0)
   {
      if (pos[0]>0)
      {
         var initData = rawData.substring(0,pos[0]);
      }
      pos[0] += 5;
   }
   else{
      pos[0] = 0;
   }
   var pos0, pos1, pos2, numMsg=0, i=0;
   pos0 = rawData.indexOf("-|||-", pos[0]);
   pos1 = rawData.indexOf("--saMe--", pos[0]);
   pos2 = rawData.indexOf("--saMe--", pos0);
    // set user preference
   if ((pos1 === -1) || (pos1 > pos0))  // not same as before
   {
      var scrollSpeed = parseInt(nextString (rawData, pos),10);
      var fastScrollSpeed = parseInt(nextString (rawData, pos),10);
      var slowScrollSpeed = parseInt(nextString (rawData, pos),10);
      var m_bkColor       = getColorFromString (nextString (rawData, pos));
      m_normalColor   = getColorFromString (nextString (rawData, pos));
      m_highColor     = getColorFromString (nextString (rawData, pos));
      m_urgentColor   = getColorFromString (nextString (rawData, pos));
      m_updateInterval  = parseInt(nextString (rawData, pos),10);
      m_prefixNormal    = nextString (rawData, pos);
      m_prefixHigh      = nextString (rawData, pos);
      m_prefixUrgent    = nextString (rawData, pos);
   }
 //set messages
if (pos2 === -1) // not same as before
   {
       msgArray = null;
       msgArray = [];
      pos[0] = rawData.indexOf("Msg", pos0);
      while(pos0<pos[0] && pos[0]<rawData.length)
      {
         pos[0] += 3;
         msgArray[i] = this.MsgBarMessage(rawData, pos);
         i++;
      }
      isMsgChanged = true;
   }
   if ( isMsgChanged){
      this.AddProperty( "MsgChanged", isMsgChanged );
      HTMLMsgCount = msgArray.length;
      this.AddProperty( "MsgCount", HTMLMsgCount );
      this.AddProperty( "MsgObjArray", msgArray );
      }
  else{
      this.AddProperty( "MsgChanged", isMsgChanged );
      }
 }
/*
   Generate a message object.
   stream is the input string passed from server, pos is the current index at the input string
 */
MsgBrdCstPresentationModel.prototype.MsgBarMessage =function(stream, pos){
   if(stream === null){
     return;
   }
   var id = nextString (stream, pos);
   var body = nextString (stream, pos);
   var type = nextString (stream, pos);
   var strStartTime = nextString (stream, pos);
   var strEndTime = nextString (stream, pos);
   var msgAbstract = nextString (stream, pos);
   var msgStatus = nextString (stream, pos);
   var strDismiss = nextString (stream, pos);
   var p               = {};
   p.id = id;
   p.body = body;
   p.type = type;
   p.summary = msgAbstract;
   p.status = msgStatus;
   p.dismiss = strDismiss;
   return p;
};
// general functions
function getColorFromString(str)
{
  var i=0;
	if (str.charAt(0) === "#"){
      return str;
   }
   var strArray = str.split(",");
   var HTMLColor = "#", tmp;
   for (i=0; i<3; i++)
   {
      tmp = toRadix(strArray[i], 16);
      if (tmp.length === 1){ tmp = "0"+tmp;}
      HTMLColor += tmp;
   }
   return HTMLColor;
}
function toRadix(N,radix) {
 var HexN="", Q=Math.floor(Math.abs(N)), R;
 if (!Q || typeof(Q)==="undefined"){
   return "0";
 }
 while (true) {
  R=Q%radix;
  HexN = "0123456789abcdefghijklmnopqrstuvwxyz".charAt(R)+HexN;
  Q=(Q-R)/radix; if (Q===0){ break;}
 }
 return ((N<0) ? "-"+HexN : HexN);
}
function nextString(stream, pos)
{
   var stream0 = "*4*TRUE*";
   var index,str, len = null;
   if (stream.charAt(pos[0]) === '*')
   {
      pos[0]++;
      index = stream.indexOf("*", pos[0]);
      if (index > 0)
      {
         len = parseInt(stream.substring(pos[0], index),10);
         if (len===null){ return "";}
      }
      if (index < stream.length && stream.charAt(index) === '*')
      {
         str = stream.substring (index+1, len + index + 1);
         pos[0] = index + len + 1;
         return str;
      }
   }
   return "";
}
        return MsgBrdCstPresentationModel;
    }());
}
