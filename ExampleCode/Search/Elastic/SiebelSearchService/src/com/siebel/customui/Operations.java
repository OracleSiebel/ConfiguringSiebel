package com.siebel.customui;

import com.siebel.xml.siebelsearchdoindex_input.DoIndexInputHeader;

import java.util.ArrayList;
import java.util.List;

// Object containing one or more operation objects.
// List of operation objects
public class Operations {
    protected List<Operation> _operations = new ArrayList<Operation>();
    
    public Operations() {
        super();
    }
    public Operations(SiebelSearchServiceDOINDEXInput in) {
        super ();
        List<DoIndexInputHeader> OpsList = in.getListOfDoIndexInput().getDoIndexInputHeader();
        for (int i = 0; i < OpsList.size(); i++) {
            Operation Op = new Operation (OpsList.get(i));
            _operations.add(Op);   
        }       
    }   
    public List<Operation> getOperations () {
        return _operations;
    }
}
