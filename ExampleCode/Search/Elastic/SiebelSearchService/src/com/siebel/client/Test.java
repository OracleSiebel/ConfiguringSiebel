package com.siebel.client;

import java.util.HashMap;

import org.elasticsearch.client.Client;

public class Test {
    public Test() {
        super();
    }

    public static void main(String[] args) {
        Test test = new Test();
        ElasticSearchWrapper esw = new ElasticSearchWrapper();
        HashMap<Object, HashMap<Object, Object>> parentMap = new HashMap<Object, HashMap<Object, Object>>();
        Client ct = esw.getClient();
        esw.searchAcrossMultiBC("siebel", 0, 10, "", "", "Contact");
        /*HashMap<Object, Object> cmap = new HashMap<Object, Object>();
        cmap.put("Solution Type","Resolution Item");
        cmap.put("Res File Name","Aracid Description");
        cmap.put("Updated","07/01/2015 14:08:51");
        cmap.put("Res File Rev","1-6A");
        cmap.put("Created","02/23/2000 14:08:51");
        cmap.put("Updated By","KulBhushan");
        cmap.put("Created By","NightOwls");
        cmap.put("_id","1-XXXX");
        
        parentMap.put("1-XXXX", cmap);
        */
        //esw.doIndex("SRResolutionItem", parentMap);
        //esw.deleteIndexRecord("SRResolutionItem", parentMap);
    }
}
