if( typeof( SiebelAppFacade.ListToCarouselPR ) === "undefined" ){
    SiebelJS.Namespace( "SiebelAppFacade.ListToCarouselPR" );
    
    SiebelAppFacade.ListToCarouselPR = ( function(){
    
        var siebConsts  = SiebelJS.Dependency( "SiebelApp.Constants" ); 
        
        function ListToCarouselPR( pm ){
            SiebelAppFacade.ListToCarouselPR.superclass.constructor.call( this, pm );
        }
        
        SiebelJS.Extend( ListToCarouselPR, SiebelAppFacade.PhysicalRenderer );
        
        ListToCarouselPR.prototype.ShowUI = function(){
            SiebelAppFacade.ListToCarouselPR.superclass.ShowUI.call( this );
            var pm = this.GetPM();
            var placeHolder = pm.Get( "GetPlaceholder" );
            var listOfCols = pm.Get( "ListOfModifiedColumns" );
            $( "#"  + placeHolder ).append( "<ul class='siebui-list-carousel jcarousel-skin-tango'></ul>" );
            var recordSet = pm.Get( "GetRecordSet" );
            var recordLength = recordSet.length;
            var markup = "";
            for( var i = 0; i < recordLength; i++ ){
                markup += "<li>" + 
                          GetCurrentCarouselItems.call( this, recordSet[i], listOfCols ) +
                          "</li>";
            }
            var that = this;
            $( "ul.siebui-list-carousel", "#" + placeHolder ).append( markup ).jcarousel({
                scroll: 1,
                itemFallbackDimension: 1500,
                itemLoadCallback: function( instance, state ){
                    if( state === "next" ){
                        that.GetPM().OnControlEvent(siebConsts.get("PHYEVENT_VSCROLL_LIST"), "pgdn");
                    }
                    else if( state === "prev" ){
                        that.GetPM().OnControlEvent(siebConsts.get("PHYEVENT_VSCROLL_LIST"), "pgup");    
                    }
                }
            }).data('jcarousel').setup();
        };
        
        function GetCurrentCarouselItems( record, listOfCols ){
            var itemMarkup = "<div class='siebui-carousel-item' style='width:100px;'>";
            for( var i = 0; i < listOfCols.length; i++ ){
                if( record[ listOfCols[i].name ] ){
                    itemMarkup += "<span class='siebui-carousel-col'>";
                    if( listOfCols[i].isLink ){
                        itemMarkup += "<a class='drilldown' href='javascript:void(0);' data-name='" + listOfCols[i].control.GetName() + "'>" + record[ listOfCols[i].name ] + "</a>";   
                    }
                    else{
                        itemMarkup += record[ listOfCols[i].name ];
                    }
                     
                    itemMarkup += "</span>";
                }
            }
            itemMarkup += "</div>";
            return itemMarkup;
        }
        
        ListToCarouselPR.prototype.BindData = function(){
            var pm = this.GetPM();
            var placeHolder = pm.Get( "GetPlaceholder" );  
            var listOfCols = pm.Get( "ListOfModifiedColumns" );
            var carousel = $( "ul.siebui-list-carousel", "#" + placeHolder ).data('jcarousel');
            if( carousel ){
                var prevSize = carousel.size();
                for( var i = 0; i < prevSize; i++ ){
                    carousel.remove( i + 1 ); 
                }
                var recordSet = pm.Get( "GetRecordSet" );
                var recordLength = recordSet.length;
                carousel.size( recordLength );
                var markup = "";
                for( var i = 0; i < recordLength; i++ ){
                    markup = GetCurrentCarouselItems.call( this, recordSet[i], listOfCols );
                    carousel.add( i + 1 , markup );
                }
                carousel.reload();
            }
        };
        
        ListToCarouselPR.prototype.BindEvents = function(){
            $( "#" + this.GetPM().Get( "GetPlaceholder" ) ).delegate("a.drilldown", "click", {ctx : this}, function( event ){
                var uiStatus = new SiebelApp.UIStatus();
                uiStatus.Busy({
                    target: SiebelApp.S_App.GetTargetViewContainer(),
                    mask: true
                });
                var self = event.data.ctx;
                var name = $( this ).attr( "data-name" );
                var rowId = Number( $(this).parents( "li" ).eq(0).attr( "jcarouselindex" ) );
                
                setTimeout( function() { 
                    if( rowId > 0 ){
                        self.GetPM().OnControlEvent(siebConsts.get("PHYEVENT_SELECT_ROW"), rowId - 1, false, false);
                        self.GetPM().OnControlEvent(siebConsts.get("PHYEVENT_DRILLDOWN_LIST"), name, rowId);
                    }
                    uiStatus.Free( );
                }, 0);
            });
        };
        
        ListToCarouselPR.prototype.SetCellEdit = function(){
            
        };
        
        ListToCarouselPR.prototype.SetCellValue = function(){
            
        };
        
        return ListToCarouselPR;
    } ());

    //Module with its dependencies
    define("siebel/samples/carouselrenderer", ["3rdParty/jcarousel/lib/jquery.jcarousel.min", "3rdParty/jquery.signaturepad.min", "siebel/phyrenderer"], function () {
        return "SiebelAppFacade.ListToCarouselPR";
    });
}