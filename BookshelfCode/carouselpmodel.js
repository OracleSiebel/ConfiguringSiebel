if( typeof( SiebelAppFacade.ConditionalColumnHide ) === "undefined" ){
    debugger;
    SiebelJS.Namespace( "SiebelAppFacade.ConditionalColumnHide" );
    
    SiebelAppFacade.ConditionalColumnHide = ( function(){
        
        function ConditionalColumnHide( proxy ){
            SiebelAppFacade.ConditionalColumnHide.superclass.constructor.call( this, proxy );
        }
        
        SiebelJS.Extend( ConditionalColumnHide, SiebelAppFacade.ListPresentationModel );

        ConditionalColumnHide.prototype.Init = function(){
            SiebelAppFacade.ConditionalColumnHide.superclass.Init.call( this );
            this.AddProperty( "ListOfModifiedColumns", GetCarouselItems );
        };

        function GetCarouselItems()
        {
            var arrayModifiedColumns = [];
            var listofCols = this.Get("ListOfColumns");
            debugger;
            for(var index=0;index<listofCols.length;index++)
            {
                if(listofCols[index].name !== "1Affiliated Full Name")
                {
                  arrayModifiedColumns.push(listofCols[index]);
                }
            }
            return arrayModifiedColumns;
        }


        return ConditionalColumnHide;
    } ());

    //Module with its dependencies
    define("siebel/samples/carouselpmodel", ["siebel/pmodel", "siebel/listpmodel", "siebel/samples/sociallyawarepmodel"], function () {
        return "SiebelAppFacade.ConditionalColumnHide";
    });
}
