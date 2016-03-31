if( typeof( SiebelAppFacade.SociallyAwarePR ) === "undefined" ){
    SiebelJS.Namespace( "SiebelAppFacade.SociallyAwarePR" );
    
    SiebelAppFacade.SociallyAwarePR = ( function(){
    
        function SociallyAwarePR( pm ){
            SiebelAppFacade.SociallyAwarePR.superclass.constructor.call( this, pm );
        }
        
        SiebelJS.Extend( SociallyAwarePR, SiebelAppFacade.PhysicalRenderer );
        
        SociallyAwarePR.prototype.ShowUI = function(){
            SiebelAppFacade.SociallyAwarePR.superclass.ShowUI.call( this );
            var pm = this.GetPM();
            var placeHolder = pm.Get( "GetPlaceholder" );            
            $( "#"  + placeHolder ).append( "<ul class='siebui-list-carousel'></ul>" );
            var recordSet = pm.Get( "GetRecordSet" );
            var recordLength = recordSet.length;
            var linkedInRecSet = pm.Get( "linkedINRecordSet" );
            var markup = "";
            for( var i = 0; i < recordLength; i++ ){
                markup += "<li>" + GetCurrentCarouselItems.call( 
                        this,
                        recordSet[i], 
                        ( pm.ExecuteMethod( "GetConnectionByName", recordSet[i][ "First Name" ], recordSet[i][ "Last Name" ] ) || {})) + "</li>";
                        
                
            }
            $( "ul.siebui-list-carousel", "#" + placeHolder ).html( "" ).append( markup ).jcarousel({
                itemFallbackDimension: 300
            });
        };
        
        function GetCurrentCarouselItems( dbRec, linkedInRec ){
            var pm = this.GetPM();
            var listCols = pm.Get( "ListOfColumns" );
            var itemMarkup = "<div>";
            itemMarkup += "<img src='" + ( linkedInRec[ "pictureUrl" ] || "http://static02.linkedin.com/scds/common/u/img/icon/icon_no_photo_80x80.png" ) + "'>";
            itemMarkup += "</div><div>"
            for( var key in dbRec ){
                if( dbRec.hasOwnProperty( key ) ){
                    var controlIndex = GetControlIndex.call( this, key, listCols );
                    if( controlIndex !== -1 ){
                        itemMarkup += "<span class='siebui-col-value " + key.replace( / /g, "_" ) + "' >" + dbRec[key] + "</span>";
                    }
                }
            }
            itemMarkup += "<span class='siebui-col-value linkedin-header'>" + ( linkedInRec[ "headline" ] || "" ) + "</span></div>";
            return itemMarkup;
        }
        
        function GetControlIndex( key, listCols ){
            var result = -1;
            for( var i = 0; i < listCols.length; i++){
                if( listCols[i].name === key ){
                    result = i;
                    break;
                }
            }
            return result;
        }
        
        SociallyAwarePR.prototype.BindData = function(){
            var pm = this.GetPM();
            var placeHolder = pm.Get( "GetPlaceholder" );
            var recordSet = pm.Get( "GetRecordSet" );
            var recordLength = recordSet.length;
            var linkedInRecSet = pm.Get( "linkedINRecordSet" );
            var markup = "";
            for( var i = 0; i < recordLength; i++ ){
                markup += "<li>" + GetCurrentCarouselItems.call( 
                        this,
                        recordSet[i], 
                        ( pm.ExecuteMethod( "GetConnectionByName", recordSet[i][ "First Name" ], recordSet[i][ "Last Name" ] ) || {})) + "</li>";
                        
                
            }
            $( "ul.siebui-list-carousel", "#" + placeHolder ).html( "" ).append( markup ).jcarousel({
                itemFallbackDimension: 300
            });
        };
        
        SociallyAwarePR.prototype.SetCellEdit = function(){
            
        };
        
        SociallyAwarePR.prototype.SetCellValue = function(){
            
        };
        
        return SociallyAwarePR;
    } ());

    //Module with its dependencies
    define("siebel/samples/sociallyawarerenderer", [], function () {
        return "SiebelAppFacade.SociallyAwarePR";
    });
}