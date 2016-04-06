package com.siebel.customui;

import com.siebel.client.ElasticSearchWrapper;
import com.siebel.xml.siebelsearchdoindex_input.DoIndexDataRecords;
import com.siebel.xml.siebelsearchdoindex_input.DoIndexFieldAndValues;
import com.siebel.xml.siebelsearchdoindex_input.DoIndexInputHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// Helper class for the wsdl created SerachServiceImpl
// Contains the logic to understand input and call the 
// corresponding method in elastic wrapper.
//
public class ImplHelper {
    private Object e;

    public ImplHelper() {
        super();
    }
    
    
    
    // Validate the given Operations and perform
    // them on the elastic.
    //
    public String DoIndex (Operations Ops) {
        String Status = "";
        
        try {
        // Get list of operation objects
        List<Operation> OpsList = Ops.getOperations();
        // Send each operation to elastic
        for (int i = 0; i < OpsList.size(); i++) {
            Operation Op = OpsList.get(i);
            
            // Validate the Op
            String Ctgry = Op.getCategory();
            String OpType = Op.getOpType();
            
            if (Ctgry.isEmpty()) {
                Status = "Missing Ctgry Name from Operation";
                return Status;
            }
            
            if (! OpType.equalsIgnoreCase("I") 
                && ! OpType.equalsIgnoreCase("U")
                && ! OpType.equalsIgnoreCase("D")) {
                    Status = "Invalid OpType in Operation";
                    return Status;
            }
            
            // Send the op to Elastic wrapper
            ElasticSearchWrapper ElasticWrapper = new ElasticSearchWrapper();
            if (OpType.equalsIgnoreCase("I")) {
                // Status = ElasticWrapper.Insert (Op);
            } else if (OpType.equalsIgnoreCase("U")) {
                // Status = ElaticWrapper.Update (Op);
            } else {
                // delete
            }
        } // done all operations.
            
        return Status;   
        } catch (NullPointerException np){
            throw np;
        }
    } // DoIndex
}