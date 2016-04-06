package com.siebel.customui;

import com.siebel.xml.siebelsearchdoindex_input.DoIndexDataRecords;

import com.siebel.xml.siebelsearchdoindex_input.DoIndexFieldAndValues;

import java.util.ArrayList;
import java.util.List;
// Holds a Siebel Record
// RowId and a list of <key, value> tupples.
//
// Equivalent of wsdl DoIndexDataRecords class.
//
public class Record {
    private String _rowid;
    private List<Tupple> _tupples = new ArrayList<Tupple>();
    
    public Record() {
        super();
    }
    
    public Record(DoIndexDataRecords Record){
        _rowid = Record.getRowID();
        
        List<DoIndexFieldAndValues> Tupples = Record.getListOfDoIndexFieldAndValues().getDoIndexFieldAndValues();
        for (int i = 0; i < Tupples.size(); i++) {
            Tupple T = new Tupple (Tupples.get(i));
            _tupples.add (T);
        }
    }
    
    public String getRowId () {
        return _rowid;
    }
    public List<Tupple> getTupples () {
        return _tupples;
    }
    public void dump (){
        System.out.println ("Rowid = " + _rowid);
        
        System.out.println ("<");
        for (int i = 0; i < _tupples.size(); i++) {
            System.out.println ("\t<");
            Tupple t = _tupples.get(i);
            t.dump();
            System.out.println("\t>");
        }
        System.out.println(">");
    }
}
