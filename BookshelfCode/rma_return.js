// JavaScript Document
if (typeof (SiebelApp.commitpartconsumed) === "undefined") {
    SiebelJS.Namespace('SiebelApp.commitpartconsumed');
    var inputArgs = {};
    var oconsts = SiebelApp.Offlineconstants;
    inputArgs[oconsts.get("DOUIREG_OBJ_NAME")]="SHCE Service FS Activity Part Movements List Applet - Mobile";
    inputArgs[oconsts.get("DOUIREG_OBJ_TYPE")]=oconsts.get("DOUIREG_OBJ_TYPEAPPLET");
    inputArgs[oconsts.get("DOUIREG_OBJ_MTHD")]="DoCanInvokeMethod";
    inputArgs[oconsts.get("DOUIREG_SRVC_NAME")]="commitpartconsumed";
    inputArgs[oconsts.get("DOUIREG_SRVC_MTDH")]="DoCanInvokeMethod";
    inputArgs[oconsts.get("DOUIREG_EXT_TYPE")]=oconsts.get("DOUIREG_EXT_TYPEOVERRIDE");
    SiebelApp.S_App.Model.ServiceRegistry(inputArgs);
    inputArgs={};
    inputArgs[oconsts.get("DOUIREG_OBJ_NAME")]="SHCE Service FS Activity Part Movements List Applet - Mobile";
    inputArgs[oconsts.get("DOUIREG_OBJ_TYPE")]=oconsts.get("DOUIREG_OBJ_TYPEAPPLET");
    inputArgs[oconsts.get("DOUIREG_OBJ_MTHD")]="DoInvokeMethod";
    inputArgs[oconsts.get("DOUIREG_SRVC_NAME")]="commitpartconsumed";
    inputArgs[oconsts.get("DOUIREG_SRVC_MTDH")]="DoInvokeMethod";
    inputArgs[oconsts.get("DOUIREG_EXT_TYPE")]=oconsts.get("DOUIREG_EXT_TYPEOVERRIDE");
    SiebelApp.S_App.Model.ServiceRegistry(inputArgs);
    inputArgs={};
    SiebelApp.commitpartconsumed = (function () {
        function commitpartconsumed(pm) {
        }
        var commitObj = new commitpartconsumed();
        commitpartconsumed.prototype.DoCanInvokeMethod = function (psInputArgs) {
            var psOutArgs = CCFMiscUtil_CreatePropSet();
            var svcMthdName = "";
            svcMthdName = psInputArgs.GetProperty("MethodName").toString() ;
            if (svcMthdName === "CommitPartMvmtClient") {
                psOutArgs.SetProperty("Invoked", true);
                psOutArgs.SetProperty("RetVal", true);
                $.setReturnValue({err:false,retVal:psOutArgs});
            }
            else if (svcMthdName === "OrderPartsRMA") {
                psOutArgs.SetProperty("Invoked", true);
                psOutArgs.SetProperty("RetVal", true);
                $.setReturnValue({err:false,retVal:psOutArgs});
            }
            else{
                psOutArgs.SetProperty("Invoked", false);
                psOutArgs.SetProperty("RetVal", false);
                $.setReturnValue({err:false,retVal:psOutArgs});
            }
        };
        commitpartconsumed.prototype.DoInvokeMethod = function (psInputArgs) {
            var svcMthdName = "";
            var psOutArgs = CCFMiscUtil_CreatePropSet();
            svcMthdName = psInputArgs.GetProperty("MethodName").toString() ;
            if (svcMthdName === "CommitPartMvmtClient") {
                this.CommitPartMvmtClient();
                $.callback(this,function(retObj){
                    psOutArgs.SetProperty("Invoked", true);
                    $.setReturnValue({err:false,retVal:psOutArgs});
                });
            }
            else{
                psOutArgs.SetProperty("Invoked", false);
                $.setReturnValue({err:false,retVal:psOutArgs});
            }
            if (svcMthdName === "OrderPartsRMA") {
                this.OrderPartsRMA();
                $.callback(this,function(retObj){
                    psOutArgs.SetProperty("Invoked", true);
                    $.setReturnValue({err:false,retVal:psOutArgs});
                });
            }
            else{
                psOutArgs.SetProperty("Invoked", false);
                $.setReturnValue({err:false,retVal:psOutArgs});
            }
        };
        commitpartconsumed.prototype.CommitPartMvmtClient = function () {
            SiebelJS.Log('Invoked CommitPartMvmtClient Method..');
            var pServiceInvBC;
            var cszCommitFlag;
            var pModel;
            pModel = SiebelApp.S_App.Model;
            pServiceInvBC = SiebelApp.S_App.GetActiveView().GetActiveApplet().GetBusComp();
            pServiceInvBC.FieldValue("Commit Txn Flag");
            $.callback(this,function(retObj){
                cszCommitFlag=retObj.retVal;
                if (cszCommitFlag === 'Y'){
                    SiebelJS.Log('Consumed Part Is Already In Committed State');
                }
                else
                {
                    pServiceInvBC.SetFieldValue("Commit Txn Flag", "Y", true);
                    $.callback(this,function(retObj){
                        pServiceInvBC.SetCommitPending(true);
                        pServiceInvBC.WriteRecord();
                        $.callback(this,function(retObj){
                            SiebelApp.OfflineAppMgr.PostActions(oconsts.get('ACTION_RPC_COMPLETED'));
                        });
                    });
                }
            });
        };
/*  Order RMA */
    commitpartconsumed.prototype.OrderPartsRMA = function () {
    //SiebelJS.Log('Invoked OrderPartsRMA Method..');
    var orderType='RMA Return';
    var sOrderId;
    var cszOrderId;
    var sAssetNum;
    var sPartNum;
    var sStatus;
    var sProductId;
    var sProductName;
    var sQuantity;
    var sActivityPartMvmtID;
    var pModel;
    var pFSActivityPartsMovementBC;
    var pActionBC;
    var sSR_Id;
    var pServiceRequestBC;
    var pOrderEntry_OrdersBC;
    var pOrderEntry_LineItemBC;
    pModel = SiebelApp.S_App.Model;
    pFSActivityPartsMovementBC = SiebelApp.S_App.GetActiveView().GetActiveApplet().GetBusComp();
    //pFSActivityPartsMovementBC.FieldValue("Order Id");
    pFSActivityPartsMovementBC.DoGetFieldValue("Order Id");
    $.callback(this,function(retObj){
        //sOrderId=retObj.retVal;
        sOrderId=retObj.retVal.GetValue();
        if (utils.IsEmpty(sOrderId))
        {
            pFSActivityPartsMovementBC.FieldValue("Product Id");
            $.callback(this,function(retObj){
                sProductId=retObj.retVal;
                pFSActivityPartsMovementBC.FieldValue("Product Name");
                $.callback(this,function(retObj){
                    sProductName=retObj.retVal;
                    pFSActivityPartsMovementBC.FieldValue("Used Quantity");
                    $.callback(this,function(retObj){
                        sQuantity=retObj.retVal;
                        pFSActivityPartsMovementBC.FieldValue("Id");
                        $.callback(this,function(retObj){
                            sActivityPartMvmtID=retObj.retVal;
                            pFSActivityPartsMovementBC.FieldValue("Status");
                            $.callback(this,function(retObj){
                                sStatus=retObj.retVal;
                                pFSActivityPartsMovementBC.FieldValue("Asset Number");
                                $.callback(this,function(retObj){
                                    sAssetNum=retObj.retVal;
                                    pFSActivityPartsMovementBC.FieldValue("Part Number");
                                    $.callback(this,function(retObj){
                                        sPartNum=retObj.retVal;
                                    });
                                });
                            });
                        });
                  });
              });
          });
		}
      });
    pActionBC = SiebelApp.S_App.GetActiveView().GetActiveApplet().GetBusComp().GetParentBuscomp();
    pActionBC.FieldValue("Activity SR Id");
        $.callback(this,function(retObj){
            sSR_Id = retObj.retVal;
        });
     if(sSR_Id==""){
     //Activity has no associated SR... Hence the operation will be aborted  //TO DO: Throw a popup error message
     return -1;
     }
     else{
         pModel = SiebelApp.S_App.Model;
         pServiceRequestBC = SiebelApp.S_App.Model.GetBusObj("Service Request").GetBusComp("Service Request");
         pOrderEntry_OrdersBC = SiebelApp.S_App.Model.GetBusObj("Service Request").GetBusComp("Order Entry - Orders");
         pOrderEntry_LineItemBC = SiebelApp.S_App.Model.GetBusObj("Service Request").GetBusComp("Order Entry - Line Items");
           /* Disabling this part as of Now --- Temporary
         pServiceRequestBC.SetFldSearchSpec("Id", sSR_Id);
         pServiceRequestBC.Execute();
         $.callback(this, function (retObj) {
             pServiceRequestBC.Home();
             $.callback(this, function (retObj) {
                 if (pServiceRequestBC.CheckActiveRow() === false) {   // Make sure that there is an existing SR
                     return 1;  // TO DO Need To Check this condition
                 }
             });
          });     */  //
          //CREATE ORDER Header.
        pOrderEntry_OrdersBC.Execute();
        $.callback(this,function(retObj){
            pOrderEntry_OrdersBC.NewRecord(true);
            $.callback(this,function(retObj){
			sLocaleVal = SiebelApp.S_App.Model.GetLovNameVal("RMA Return", "FS_ORDER_TYPE");
			pOrderEntry_OrdersBC.SetFieldValue("Order Type", sLocaleVal, true);
			$.callback(this,function(retObj){
				pOrderEntry_OrdersBC.SetCommitPending(true);
				pOrderEntry_OrdersBC.WriteRecord();
				$.callback(this,function(retObj){
					SiebelApp.OfflineAppMgr.PostActions(oconsts.get('ACTION_RPC_COMPLETED'));
					// Introducing a change
					pOrderEntry_OrdersBC.FieldValue("Id")
					$.callback(this,function(retObj){
					sOrderItemId=retObj.retVal;
					// Temporary Change
					pOrderEntry_OrdersBC.FieldValue("Id");
					$.callback(this,function(retObj){
						m_sOrderHeaderId=retObj.retVal;
						pOrderEntry_LineItemBC.Execute();
        $.callback(this,function(retObj){
            pOrderEntry_LineItemBC.NewRecord(true);
			pOrderEntry_LineItemBC.SetFieldValue("Product Id", sProductId, true);
			$.callback(this,function(retObj){
				pOrderEntry_LineItemBC.SetFieldValue("Product", sProductName, true);
				$.callback(this,function(retObj){
					pOrderEntry_LineItemBC.SetFieldValue("Quantity Requested", sQuantity, true);
					$.callback(this,function(retObj){
						if(!utils.IsEmpty(sAssetNum)){
							pOrderEntry_LineItemBC.SetFieldValue("Asset Number", sAssetNum, true);
							$.callback(this,function(retObj){
							});
						}
						if(!utils.IsEmpty(sPartNum)){
							pOrderEntry_LineItemBC.SetFieldValue("Part Number", sPartNum, true);
							$.callback(this,function(retObj){
							});
						}
						if(!utils.IsEmpty(sStatus)){
							pOrderEntry_LineItemBC.SetFieldValue("Product Status Code", sStatus, true);
							$.callback(this,function(retObj){
							});
						}
						pOrderEntry_LineItemBC.FieldValue("Id");
						$.callback(this,function(retObj){
							sOrderItemId=retObj.retVal;
							pOrderEntry_LineItemBC.SetFieldValue("Order Header Id", m_sOrderHeaderId, true);
							$.callback(this,function(retObj){
								pOrderEntry_LineItemBC.SetCommitPending(true);
								pOrderEntry_LineItemBC.WriteRecord();
								$.callback(this,function(retObj){
									pFSActivityPartsMovementBC.SetFieldValue("Order Item Id", m_sOrderHeaderId, true);
									$.callback(this,function(retObj){
										pFSActivityPartsMovementBC.SetCommitPending(true);
										pFSActivityPartsMovementBC.WriteRecord();
										$.callback(this,function(retObj){
											SiebelApp.OfflineAppMgr.PostActions(oconsts.get('ACTION_RPC_COMPLETED'));
										});
									});
								});
							});
						});
					});
				});
			});
		});
									//Temporary Change Ends
					});
				});
			});
		});
		});
		});
	}
	//CREATE ORDER Line Items...
	/*
	pOrderEntry_LineItemBC.Execute();
        $.callback(this,function(retObj){
            pOrderEntry_LineItemBC.NewRecord(True);
			pOrderEntry_LineItemBC.SetFieldValue("Product Id", sProductId, true);
			$.callback(this,function(retObj){
				pOrderEntry_LineItemBC.SetFieldValue("Product", sProductName, true);
				$.callback(this,function(retObj){
					pOrderEntry_LineItemBC.SetFieldValue("Quantity Requested", sQuantity, true);
					$.callback(this,function(retObj){
						if(sAssetNum!=""){
							pOrderEntry_LineItemBC.SetFieldValue("Asset Number", sAssetNum, true);
							$.callback(this,function(retObj){
							});
						}
						if(sPartNum!=""){
							pOrderEntry_LineItemBC.SetFieldValue("Part Number", sPartNum, true);
							$.callback(this,function(retObj){
							});
						}
						if(sStatus!=""){
							pOrderEntry_LineItemBC.SetFieldValue("Product Status Code", sStatus, true);
							$.callback(this,function(retObj){
							});
						}
						pOrderEntry_LineItemBC.FieldValue("Id");
						$.callback(this,function(retObj){
							sOrderItemId=retObj.retVal;
							pOrderEntry_LineItemBC.SetFieldValue("Order Header Id", m_sOrderHeaderId, true);
							$.callback(this,function(retObj){
								pOrderEntry_LineItemBC.SetCommitPending(true);
								pOrderEntry_LineItemBC.WriteRecord();
								$.callback(this,function(retObj){
									pFSActivityPartsMovementBC.SetFieldValue("Order Item Id", sOrderItemId, true);
									$.callback(this,function(retObj){
										pFSActivityPartsMovementBC.SetCommitPending(true);
										pFSActivityPartsMovementBC.WriteRecord();
										$.callback(this,function(retObj){
											SiebelApp.OfflineAppMgr.PostActions(oconsts.get('ACTION_RPC_COMPLETED'));
										});
									});
								});
							});
						});
					});
				});
			});
		});  */   //Order Line Item Creation has been disabled as of now
	};   
		        return commitObj;
    }());
}
