package com.siebel.customui;

import com.siebel.xml.siebelsearchdoindex_input.DoIndexFieldAndValues;
// Holds a <fieldName, fieldValue pair
// 
// Equivalent of WSDL DoIndexFieldAndValues class
public class Tupple {
    protected String _fieldname;
    protected String _fieldvalue;
    
    public Tupple() {
        super();
    }
    
    public Tupple(DoIndexFieldAndValues in) {
        super();
        
        _fieldname = in.getFieldName();
        _fieldvalue = in.getFieldValue();
    }
    public String getFieldName() {
        return _fieldname;
    }
    public String getFieldValue() {
        return _fieldvalue;
    }
    public void dump(){
        System.out.println("Field = " + _fieldname);
        System.out.println("Value " + _fieldvalue);
    }
}
