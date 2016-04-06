package com.siebel.customui;

import com.siebel.xml.siebelsearchdoindex_input.DoIndexDataRecords;
import com.siebel.xml.siebelsearchdoindex_input.DoIndexInputHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// A single operation
// OpType, Ctgry, and a list of Records.
public class Operation {
    protected String _optype;
    protected String _ctgry;
    protected List<Record> _records = new ArrayList<Record>();
    
    public Operation() {
        super();
    }
    public Operation(DoIndexInputHeader Op) {
        super ();
        _optype = Op.getTotalRecords();
        _ctgry = Op.getCategoryName();
        
        List<DoIndexDataRecords> Records = Op.getListOfDoIndexDataRecords().getDoIndexDataRecords();
        for (int i = 0; i < Records.size(); i++) {
            Record R = new Record (Records.get(i));
            
            _records.add (R);
        }
    }
    
    public String getCategory() {
        return _ctgry;
    }
    public String getOpType() {
        return _optype;
    }
    public List<Record> getRecords () {
        return _records;
    }
    
    public void dump () {
        System.out.println("Op = " + _optype);
        System.out.println("Ctgry = " + _ctgry);
        
        System.out.println ("<");
        for (int i = 0; i < _records.size(); i++) {
            System.out.println ("\t<");
            Record r = _records.get(i);
            r.dump();
            System.out.println ("\t>");
            
        }
        
    }
}
