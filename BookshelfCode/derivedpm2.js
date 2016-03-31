if( typeof( SiebelAppFacade.derivedpm2 ) === "undefined" ){

    SiebelJS.Namespace( "SiebelAppFacade.derivedpm2" );
    
    define( "siebel/custom/derivedpm2", ["siebel/custom/derivedpm1"], function(){
    
        SiebelAppFacade.derivedpm2 = ( function(){
            
            var siebConsts      = SiebelJS.Dependency( "SiebelApp.Constants" ),
                CANCEL_OPR      = consts.get( "SWE_EXTN_CANCEL_ORIG_OP" ),
                STOP_PROP       = consts.get( "SWE_EXTN_STOP_PROP_OP" );
            

            function derivedpm2(){
                SiebelAppFacade.derivedpm2.superclass.constructor.apply( this, arguments );
            }
 
            SiebelJS.Extend( derivedpm2, SiebelAppFacade.derivedpm1 );
    
            derivedpm2.prototype.Init = function(){
                SiebelAppFacade.derivedpm2.superclass.Init.call( this );
                
                
                this.AttachEventHandler( siebConsts.get("PHYEVENT_COLUMN_FOCUS"), function()
                {
                    SiebelJS.Log("Control focus 2");
                });
                
                this.AddValidator(siebConsts.get("PHYEVENT_COLUMN_FOCUS"), function(row, ctrl, val){

     	            if(ctrl.GetDisplayName() === "Account" && val === "Hibbing Mfg"){
     	                return true;
     	            }
                });
                
            };
            
            derivedpm2.prototype.Setup = function(propSet){
                SiebelAppFacade.derivedpm2.superclass.Setup.call( this, propSet );
            };
           
              
            return derivedpm2;
        } ());
        return "SiebelAppFacade.derivedpm2";
    });
}
