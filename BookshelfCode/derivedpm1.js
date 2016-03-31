if( typeof( SiebelAppFacade.derivedpm1 ) === "undefined" ){

    SiebelJS.Namespace( "SiebelAppFacade.derivedpm1" );
    
    define( "siebel/custom/derivedpm1", [], function(){
    
        SiebelAppFacade.derivedpm1 = ( function(){
            
            var siebConsts      = SiebelJS.Dependency( "SiebelApp.Constants" ),
                CANCEL_OPR      = consts.get( "SWE_EXTN_CANCEL_ORIG_OP" ),
                STOP_PROP       = consts.get( "SWE_EXTN_STOP_PROP_OP" ); 
            

            function derivedpm1(){
                SiebelAppFacade.derivedpm1.superclass.constructor.apply( this, arguments );
            }
 
            SiebelJS.Extend( derivedpm1, SiebelAppFacade.ListPresentationModel );
    
            derivedpm1.prototype.Init = function(){
                SiebelAppFacade.derivedpm1.superclass.Init.call( this );
                
                
                this.AttachEventHandler( siebConsts.get("PHYEVENT_COLUMN_FOCUS"), function()
                {
                    SiebelJS.Log("Control focus 1");
                    arguments[arguments.length - 1][consts.get( "SWE_EXTN_CANCEL_ORIG_OP" ] = false;
                });
                
                this.AddValidator(siebConsts.get("PHYEVENT_COLUMN_FOCUS"), function(){

     	            return true;
                });
                
               
            };
            
            derivedpm1.prototype.Setup = function(propSet){
                SiebelAppFacade.derivedpm1.superclass.Setup.call( this, propSet );
            };
           
              
            return derivedpm1;
        } ());
        return "SiebelAppFacade.derivedpm1";
    });
}
