if( typeof ( SiebelAppFacade.SociallyAwarePM ) === "undefined" ) {
    SiebelJS.Namespace( 'SiebelAppFacade.SociallyAwarePM' );
    
    SiebelAppFacade.SociallyAwarePM = ( function(){
        
        /* 3rd Party Plugin Initialization */
        initLinkedIn = function (){
            if( !IN.User.isAuthorized() ){
                IN.User.authorize( function(){ 
                    if( IN.User.isAuthorized() ){
                        SiebelApp.EventManager.fireEvent( "linkedInReady" );    
                    }
                }, window);   
            }else{
                SiebelApp.EventManager.fireEvent( "linkedInReady" );    
            }
        }

        var linkedInTag = document.createElement("script");
        linkedInTag.src = "http://platform.linkedin.com/in.js";
        linkedInTag.type= "text/javascript";
        linkedInTag.innerHTML = "api_key: 4h9dzph4b35h\n  authorize: true\n  onLoad: initLinkedIn\n "
        document.body.appendChild(linkedInTag);

        function SociallyAwarePM( proxy ){
            SiebelAppFacade.SociallyAwarePM.superclass.constructor.call( this, proxy );
        }
        
        SiebelJS.Extend( SociallyAwarePM, SiebelAppFacade.ListPresentationModel );
        
        SociallyAwarePM.prototype.Init = function(){
            SiebelAppFacade.SociallyAwarePM.superclass.Init.call( this );
            
            this.AddProperty( "linkedINRecordSet", [] );
            this.AddProperty( "linkedINMarker", 0 );
            this.AddMethod( "QueryForRelatedContacts", QueryForRelatedContacts );
            this.AddMethod( "GetConnectionByName", GetConnectionByName );

            if( typeof( IN ) === "object" && IN.API ){
                FetchConnectionFromLinkedIn.call( this );
            }
            else{
                SiebelApp.EventManager.addListner( 
                        "linkedInReady", FetchConnectionFromLinkedIn, this );
            }
        };
        
        SociallyAwarePM.prototype.Show = function(){
            SiebelAppFacade.SociallyAwarePM.superclass.Show.call( this );
        };
        
        function FetchConnectionFromLinkedIn(){
            if( IN.User ){
                var that = this;
                IN.API.Connections( "me" )
                    .fields( "id", "firstName", "lastName", "pictureUrl", "publicProfileUrl", "headline", "location", "num-connections" )
                    .result( function( results, metadata ){
                        that.SetProperty( "linkedINRecordSet",  results.values || [] );
                        that.SetProperty( "linkedINMarker", 0 );
                        that.ExecuteMethod( "QueryForRelatedContacts" );
                });
            }
        }
        
        function GetConnectionByName( fName, lName ){
            var connection = null;
            if( fName && lName ){
                var linkedInRecSet = this.Get( "linkedINRecordSet" );
                
                for( var i = 0; i < linkedInRecSet.length; i++ ){
                    var current = linkedInRecSet[i];
                    
                    if( current.firstName === fName && current.lastName === lName ){
                        connection = current;
                        break;
                    }
                }
            }
            return connection;
        }
        
        function QueryForRelatedContacts(){
            var currentMark = this.Get( "linkedINMarker" );
            var recordSet = this.Get( "linkedINRecordSet" );
            var firstName = "";
            var lastName = "";
            for( var i = currentMark; i < currentMark + 5; i++ ){
                var current = recordSet[i];
                firstName = firstName + current[ "firstName"];
                lastName = lastName + current[ "lastName" ];
                if( i < (currentMark + 4) ){
                    firstName = firstName + " OR ";
                    lastName = lastName + " OR ";
                }
            }
            if( firstName !== "" || lastName !== "" ){
                SiebelApp.S_App.GetActiveView().ExecuteFrame( 
                        this.Get( "GetName" ), 
                        [ { field : "Last Name" , value : lastName },    
                          { field : "First Name", value : firstName}] );
            }
        };
        
        return SociallyAwarePM;
    } ());

    //Module with its dependencies
    define("siebel/samples/sociallyawarepmodel", ["siebel/pmodel", "siebel/listpmodel"], function () {
        return "SiebelAppFacade.SociallyAwarePM";
    });
}