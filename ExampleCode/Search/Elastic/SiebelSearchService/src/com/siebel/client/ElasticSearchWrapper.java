//////////////////////////////////////////////////////////////////////////////
//
// Copyright (C) 2015, Oracle , All rights reserved.
//
// FILE:       ElasticSearchWrapper.java
//
// CREATOR:    Raghuram Venkatasubramanian / Anish Alex / Kul Bhushan Srivastava
//
// DESCRIPTION
//   JAVA File containing various operations performed on ElasticSearch
//
//////////////////////////////////////////////////////////////////////////////

package com.siebel.client;

import com.siebel.customui.Record;

import com.siebel.customui.Tupple;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.Set;

import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.delete.DeleteMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.delete.DeleteMappingResponse;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.suggest.SuggestResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.hppc.cursors.ObjectObjectCursor;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;

import java.util.ResourceBundle;
//import org.apache.openjpa.jdbc.sql.Row;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.AndFilterBuilder;
import org.elasticsearch.index.query.OrFilterBuilder;
import org.elasticsearch.index.query.QueryBuilder;


public class ElasticSearchWrapper {

    private Client client;

    public Client getClient() {
        if (client == null) {
            client = createClient();
        }
        return client;
    }

    /*
     * TODO Configure URL, Port No & IndexName
     *
     */

    private static final ResourceBundle rb = ResourceBundle.getBundle("searchengine");
    private static final String serverAddress = rb.getString("SERVER_ADDRESS");
    private static final String serverPort = rb.getString("SERVER_PORT");
    private static final String indexName = rb.getString("INDEX_NAME");

    /*Create Elastic Search Client for various HostNames provided for Engine.*/

    /*public static void main(String args[]){
        ElasticSearchWrapper esw = new ElasticSearchWrapper();
        esw.getClient();
    }*/

    protected Client createClient() {
        try {
            System.out.println("bundle:: " + serverAddress + "_" + serverPort + "_" + indexName);
            TransportClient transportClient =
                new TransportClient().addTransportAddress(new InetSocketTransportAddress(serverAddress,
                                                                                         Integer.parseInt(serverPort)));
            if (transportClient.connectedNodes().size() == 0) {
                System.out.println("There are no active nodes to connect.");
            }
            client = transportClient;
            System.out.println("Clinet created :: " + client);
        } catch (Exception e) {
            System.out.println("Unable to create the client");
            e.printStackTrace();
        }
        return client;
    }

    /*
     * DESC: CompleteSuggestion Utility Functionality
     * Inputs are IndexName, suggestedField, suggestValueText.
     * Output is list of suggestions and category names.
     */
    public SuggestResponse completeSuggestion(String index, String suggestField, String value) {
        try {
            String suggestionName = "complete";
            CompletionSuggestionBuilder completionSuggestionBuilder = new CompletionSuggestionBuilder(suggestionName);
            SuggestResponse sresponse =
                client.prepareSuggest(index).setSuggestText(value).addSuggestion(completionSuggestionBuilder.field(suggestField)).execute().actionGet();
            return sresponse;
        } catch (Exception e) {
            e.printStackTrace();
        } /*finally {
            client.close();
        }*/
        return null;
    }

    /*
     * DESC: TermSuggestion Utility Functionality
     * Inputs are IndexName, suggestedField, suggestValueText.
     * Output is list of suggested texts.
     */
    public SuggestResponse termSuggestion(String index, String suggestField, String value) {
        try {
            String suggestionName = "term";
            TermSuggestionBuilder termSuggestionBuilder = new TermSuggestionBuilder(suggestionName);
            SuggestResponse sresponse =
                client.prepareSuggest(index).addSuggestion(new TermSuggestionBuilder("term").field(suggestField).text(value).size(1)).execute().actionGet();
            return sresponse;
        } catch (Exception e) {
            e.printStackTrace();
        } /*finally {
            client.close();
        }*/
        return null;
    }

    /*
     * DESC: DELETEINDEX OPERATION
     * Inputs are bcType
     * Output is status response of delete index operation..
     */

    public String deleteIndex(String bcType) {
        String status = "";
        String indexName = ElasticSearchWrapper.indexName;
        try {
            if (bcType.equalsIgnoreCase("") || bcType == null) {
                status = "Error: BC Name is invalid or not provided.";
            } else {
                GetMappingsResponse resp = null;
                resp = client.admin().indices().getMappings(new GetMappingsRequest().indices(indexName)).get();
                ImmutableOpenMap<String, MappingMetaData> mappings = resp.mappings().get(indexName);
                if (mappings.containsKey(bcType)) {
                    MappingMetaData data = (MappingMetaData) mappings.get(bcType);
                    DeleteMappingRequest req = new DeleteMappingRequest(indexName).types(bcType);
                    req.types(data.type());
                    DeleteMappingResponse actionGet = client.admin().indices().deleteMapping(req).actionGet();
                    status = "Index type " + bcType + " is successfully deleted.";
                } else {
                    status = "Error: No such Index type exist.";
                }
            }
        } catch (NullPointerException np) {
            status = "Error: Internal error occured during deleting index.";
            np.printStackTrace();
        } catch (Exception e) {
            status = "Error: Internal error occured during deleting index.";
            e.printStackTrace();
        } finally {
            client.close();
        }
        return status;
    }

    /*
    //Delete Index record.
    public void deleteIndexRecord(String bcType, HashMap<Object, HashMap<Object, Object>> map){
        String indexName = ElasticSearchWrapper.indexName;
        Set mapDataSet = map.entrySet();
        Iterator itr = mapDataSet.iterator();
        DeleteResponse response = null;
        while (itr.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) itr.next();
            String recordArr = mapEntry.getKey().toString().trim();
            response = client.prepareDelete(indexName, bcType, recordArr).execute().actionGet();
        }
    }*/

    /*
     * DESC: CREATESCHEMA OPERATION
     * Inputs are bcType and mapping fileds as Map
     * Output is status response of create schema.
     */

    public String createSchema(String bcType, HashMap<Object, Object> map) {
        String status = "";
        try {
            if (bcType.equalsIgnoreCase("") || bcType == null) {
                return status = "Error: BC Name is invalid or not provided.";
            } else if (map.isEmpty()) {
                return status = "Error: No Mapping fileds are provided for creating schema.";
            }
        } catch (NullPointerException np) {
            np.printStackTrace();
        }

        String indexName = ElasticSearchWrapper.indexName;
        Set mapDataSet = map.entrySet();
        Iterator itr = mapDataSet.iterator();

        // Checking the existance of Index into the searchengine.
        IndicesExistsResponse index = client.admin().indices().prepareExists(indexName).execute().actionGet();
        if (index.isExists()) {
            GetMappingsResponse resp = null;
            try {
                resp = client.admin().indices().getMappings(new GetMappingsRequest().indices(indexName)).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ImmutableOpenMap<String, MappingMetaData> mappings = resp.mappings().get(indexName);

            if (mappings.containsKey(bcType)) {
                /*
                 * Delete the existing mapping if exist & Put the latest mapping for that document type(Bus Comp).
                 */
                MappingMetaData data = (MappingMetaData) mappings.get(bcType);
                DeleteMappingRequest req = new DeleteMappingRequest(indexName).types(bcType);
                req.types(data.type());
                DeleteMappingResponse actionGet = client.admin().indices().deleteMapping(req).actionGet();
                // Put the latest mapping for that document type(Bus Comp).
                try {
                    XContentBuilder mappingBuilder =
                        XContentFactory.jsonBuilder().startObject().startObject(bcType).startObject("properties");
                    String[] mapValArr = null;
                    while (itr.hasNext()) {
                        Map.Entry mapEntry = (Map.Entry) itr.next();
                        mappingBuilder.startObject(mapEntry.getKey().toString().trim());
                        mapValArr = mapEntry.getValue().toString().split(",");
                        mappingBuilder.field("type", mapValArr[0]);
                        //Visibility Code
                        if (mapValArr[3].equalsIgnoreCase("y")) {
                            mappingBuilder.field("index", "not_analyzed");
                        }
                        mappingBuilder.endObject();
                        if (mapValArr[1].equalsIgnoreCase("y")) {
                            mappingBuilder.startObject("suggest_" + mapEntry.getKey().toString().trim());
                            mappingBuilder.field("type", "completion");
                            mappingBuilder.field("analyzer", "standard");
                            mappingBuilder.field("preserve_separators", "true");
                            mappingBuilder.field("payloads", "true");
                            mappingBuilder.endObject();
                        }
                    }
                    mappingBuilder.endObject().endObject().endObject();
                    PutMappingResponse response =
                        client.admin().indices().preparePutMapping(indexName).setType(bcType).setSource(mappingBuilder).execute().actionGet();
                    status = "Schema created for Bus Comp : " + bcType;
                } catch (Exception e) {
                    status = "Error: Internal Error occured during schema creation for Bus Comp : " + bcType;
                    e.printStackTrace();
                } finally {
                    client.close();
                }
            } else {
                /*
                 * If mapping does not exist put mapping for that document type(Bus Comp) straight away.
                 */
                try {
                    XContentBuilder mappingBuilder =
                        XContentFactory.jsonBuilder().startObject().startObject(bcType).startObject("properties");
                    String[] mapValArr = null;
                    while (itr.hasNext()) {
                        Map.Entry mapEntry = (Map.Entry) itr.next();
                        mappingBuilder.startObject(mapEntry.getKey().toString().trim());
                        mapValArr = mapEntry.getValue().toString().split(",");
                        mappingBuilder.field("type", mapValArr[0]);
                        //Visibility Code
                        if (mapValArr[3].equalsIgnoreCase("y")) {
                            mappingBuilder.field("index", "not_analyzed");
                        }
                        mappingBuilder.endObject();
                        if (mapValArr[1].equalsIgnoreCase("y")) {
                            mappingBuilder.startObject("suggest_" + mapEntry.getKey().toString().trim());
                            mappingBuilder.field("type", "completion");
                            mappingBuilder.field("analyzer", "standard");
                            mappingBuilder.field("preserve_separators", "true");
                            mappingBuilder.field("payloads", "true");
                            mappingBuilder.endObject();
                        }
                    }
                    mappingBuilder.endObject().endObject().endObject();
                    PutMappingResponse response =
                        client.admin().indices().preparePutMapping(indexName).setType(bcType).setSource(mappingBuilder).execute().actionGet();
                    status = "Schema created for Bus Comp : " + bcType;
                } catch (Exception e) {
                    status = "Error: Internal Error occured during schema creation for Bus Comp : " + bcType;
                    e.printStackTrace();
                } finally {
                    client.close();
                }
            }
        } else {
            /*
             * If index does not exist create the index and add the mapping straight away.
             */
            try {
                XContentBuilder mappingBuilder =
                    XContentFactory.jsonBuilder().startObject().startObject(bcType).startObject("properties");
                String mapValArr[] = null;
                while (itr.hasNext()) {
                    Map.Entry mapEntry = (Map.Entry) itr.next();
                    mappingBuilder.startObject(mapEntry.getKey().toString().trim());
                    mapValArr = mapEntry.getValue().toString().split(",");
                    mappingBuilder.field("type", mapValArr[0]);
                    //Visibility Code
                    if (mapValArr[3].equalsIgnoreCase("y")) {
                        mappingBuilder.field("index", "not_analyzed");
                    }
                    mappingBuilder.endObject();
                    if (mapValArr[1].equalsIgnoreCase("y")) {
                        mappingBuilder.startObject("suggest_" + mapEntry.getKey().toString().trim());
                        mappingBuilder.field("type", "completion");
                        mappingBuilder.field("analyzer", "standard");
                        mappingBuilder.field("preserve_separators", "true");
                        mappingBuilder.field("payloads", "true");
                        mappingBuilder.endObject();
                    }
                }
                mappingBuilder.endObject().endObject().endObject();
                CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices().prepareCreate(indexName);
                createIndexRequestBuilder.addMapping(bcType, mappingBuilder);
                createIndexRequestBuilder.execute().actionGet();
                status = "Schema created for Bus Comp : " + bcType;
            } catch (IOException e) {
                status = "Error: Internal Error occured during schema creation for Bus Comp : " + bcType;
                e.printStackTrace();
            } catch (Exception e) {
                status = "Error: Internal Error occured during schema creation for Bus Comp : " + bcType;
                e.printStackTrace();
            } finally {
                client.close();
            }
        }
        return status;
    }

    /*
     * DESC: GetCategoryList Function.
     * There are no inputs
     * Output is list of categories.
     */
    public List<String> getCategoryList() {
        String indexName = ElasticSearchWrapper.indexName;
        List<String> catList = new ArrayList<String>();
        // Code to get list of document types in particular index
        try {
            GetMappingsResponse res =
                client.admin().indices().getMappings(new GetMappingsRequest().indices(indexName)).get();
            ImmutableOpenMap<String, MappingMetaData> mapping = res.mappings().get(indexName);
            for (ObjectObjectCursor<String, MappingMetaData> c : mapping) {
                //System.out.println(c.key+" = "+c.value.source());
                catList.add(c.key.toString());
            }
        } catch (Exception e) {
            catList.add("Error");
            e.printStackTrace();
        } /*finally{
            client.close();
        }*/
        return catList;
    }

    /*
     * DESC: Get Mapping Objects of particular mapping.
     * Input is bcType
     * Output is list of mapping objects.
     */
    public List<String> getMappingObject(String bcType) {
        String indexName = ElasticSearchWrapper.indexName;
        List<String> objectPropertyList = new ArrayList<String>();
        try {
            GetMappingsResponse res =
                client.admin().indices().getMappings(new GetMappingsRequest().indices(indexName)).get();
            System.out.println("Search data is :: \n");
            System.out.println(res.toString());
            ImmutableOpenMap<String, MappingMetaData> mapping = res.mappings().get(indexName);
            MappingMetaData data = (MappingMetaData) mapping.get(bcType);
            HashMap<String, Object> map1 = new HashMap<String, Object>();
            map1 = (HashMap<String, Object>) data.getSourceAsMap();
            Set setItr = map1.entrySet();
            Iterator itr = setItr.iterator();
            String arr[] = null;
            String arr1[] = null;

            while (itr.hasNext()) {
                Map.Entry mapEntry = (Map.Entry) itr.next();
                arr = mapEntry.getValue().toString().split(",");
                for (int x = 0; x <= arr.length - 1; x++) {
                    System.out.println(arr[x]);
                    arr1 = arr[x].split("=");
                    objectPropertyList.add(arr1[0].replace("{", "").trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } /*finally{
            client.close();
        }*/
        return objectPropertyList;
    }
    
    private void toJSONRecord (String Ctgry, Record R, XContentBuilder JSONRecord) throws IOException {
        try {
            
            JSONRecord.field("RowID", R.getRowId());
            
            // add each field and value
            for (int j = 0; j < R.getTupples().size();j++) {                   
                Tupple T = R.getTupples().get(j);
                JSONRecord.field (T.getFieldName(), T.getFieldValue());
                // If this field has a corresponding suggest mapping for elastic
                // completion suggester, add that
                
                }      
            JSONRecord.endObject ();
        } catch (IOException e) {
            throw e;
        }
    }
    
    /* Put (Insert/Update) the given records in elastic.
     * Input: An Operation object containing Ctgry and a list of Records
     * Output: Status Message
     */
    public String putRecords (String ctgry, List<Record> records) throws IOException {
        String Status = "";
        
        for (int i = 0; i < records.size(); i++) {
            // Build a json object for each record.
            Record R = records.get(i);
            try {
                XContentBuilder JSONRecord = XContentFactory.jsonBuilder().startObject(); 
                toJSONRecord (ctgry, R, JSONRecord);
                JSONRecord.endObject();
                
                } catch (IOException e) {
                throw e;
            }
        }
        
        return Status;
    }

    /*
     * DESC: DoIndex Function. Same function for Full Indexing and Incremental Indexing.
     * Input are bcType and Indexing data as map.
     * Output is status of Indexing operation.
     */
    public String doIndex(HashMap<Object, HashMap<Object, HashMap<Object, Object>>> map) {
        String status = "";
        String rowID = "";
        String indexName = ElasticSearchWrapper.indexName;
        ElasticSearchWrapper esw = new ElasticSearchWrapper();
        Client ct = esw.getClient();
        try {
            Set parentMapDataSet = map.entrySet();
            Iterator parentItr = parentMapDataSet.iterator();
            while (parentItr.hasNext()) {
                Map.Entry parentMapEntry = (Map.Entry) parentItr.next();
                String[] str = parentMapEntry.getKey().toString().split("##");
                //str[0] = bcName, //str[1] = flagOperation, //str[3] = total records.
                HashMap<Object, HashMap<Object, Object>> childMap = new HashMap<Object, HashMap<Object, Object>>();
                childMap = (HashMap<Object, HashMap<Object, Object>>) parentMapEntry.getValue();
                List<String> objectPropertyList = esw.getMappingObject(str[0]);
                try {
                    if (str[0].equalsIgnoreCase("") || str[0] == null) {
                        status = status + "Error: BC Name is invalid or not provided." + " ## ";
                        continue;
                    } else if (str[1].equalsIgnoreCase("") || str[1] == null) {
                        status =
                            status + "Error: No incremental indexing operation is provided for BC Name:: " + str[0] +
                            " ## ";
                        continue;
                    } else if (!str[1].equalsIgnoreCase("I") && !str[1].equalsIgnoreCase("U") &&
                               !str[1].equalsIgnoreCase("D")) {
                        status =
                            status + "Error: Invalid incremental indexing operation is provided for BC Name:: " +
                            str[0] + " ## ";
                        continue;
                    } else if (childMap.isEmpty()) {
                        status =
                            status + "Error: No data has been provided to get index for BC Name::" + str[0] + " ## ";
                        continue;
                    }
                    if (str[1].equalsIgnoreCase("I")
                        || str[1].equalsIgnoreCase("U")) { //INSERT
                        Set childMapDataSet = childMap.entrySet();
                        Iterator childItr = childMapDataSet.iterator();
                        while (childItr.hasNext()) {
                            XContentBuilder indexBuilder = XContentFactory.jsonBuilder().startObject();
                            Map.Entry childMapEntry = (Map.Entry) childItr.next();
                            rowID = childMapEntry.getKey().toString();
                            HashMap<Object, Object> grandChildMap = new HashMap<Object, Object>();
                            grandChildMap = (HashMap<Object, Object>) childMapEntry.getValue();
                            Set grandChildMapDataSet = grandChildMap.entrySet();
                            Iterator grandChildItr = grandChildMapDataSet.iterator();
                            indexBuilder.field("RowID", rowID);
                            while (grandChildItr.hasNext()) {
                                Map.Entry grandChildMapEntry = (Map.Entry) grandChildItr.next();
                                indexBuilder.field(grandChildMapEntry.getKey().toString(),
                                                   grandChildMapEntry.getValue());
                                if (objectPropertyList.contains("suggest_" +
                                                                grandChildMapEntry.getKey().toString().trim())) {
                                    indexBuilder.startObject("suggest_" +
                                                             grandChildMapEntry.getKey().toString().trim());
                                    indexBuilder.field("input", grandChildMapEntry.getValue());
                                    indexBuilder.field("output", grandChildMapEntry.getValue());
                                    indexBuilder.field("payload", str[0]);
                                    indexBuilder.field("weight", "0");
                                    indexBuilder.endObject();
                                }
                            }
                            indexBuilder.endObject();
                            IndexRequestBuilder indexRequestBuilder = client.prepareIndex(indexName, str[0], rowID);
                            indexRequestBuilder.setSource(indexBuilder);
                            indexRequestBuilder.execute().actionGet();
                        }
                    } else if (str[1].equalsIgnoreCase("U")) { // UPDATE
                        Set childMapDataSet = childMap.entrySet();
                        Iterator childItr = childMapDataSet.iterator();
                        while (childItr.hasNext()) {
                            Map.Entry childMapEntry = (Map.Entry) childItr.next();
                            rowID = childMapEntry.getKey().toString();
                            HashMap<Object, Object> grandChildMap = new HashMap<Object, Object>();
                            grandChildMap = (HashMap<Object, Object>) childMapEntry.getValue();
                            
                            XContentBuilder indexBuilder = XContentFactory.jsonBuilder().startObject();
                            Set grandChildMapDataSet = grandChildMap.entrySet();
                            Iterator grandChildItr = grandChildMapDataSet.iterator();
                            indexBuilder.field("RowID", rowID);
                            while (grandChildItr.hasNext()) {
                                Map.Entry grandChildMapEntry = (Map.Entry) grandChildItr.next();
                                indexBuilder.field(grandChildMapEntry.getKey().toString(),
                                                   grandChildMapEntry.getValue());
                                if (objectPropertyList.contains("suggest_" +
                                                                grandChildMapEntry.getKey().toString().trim())) {
                                    indexBuilder.startObject("suggest_" +
                                                             grandChildMapEntry.getKey().toString().trim());
                                    indexBuilder.field("input", grandChildMapEntry.getValue());
                                    indexBuilder.field("output", grandChildMapEntry.getValue());
                                    indexBuilder.field("payload", str[0]);
                                    indexBuilder.field("weight", "0");
                                    indexBuilder.endObject();
                                }
                            }
                            indexBuilder.endObject();
                            /*
                            //For Update Suggest
                            while (grandChildItr.hasNext()) {
                                Map.Entry grandChildMapEntry = (Map.Entry) grandChildItr.next();
                                if (objectPropertyList.contains("suggest_" + grandChildMapEntry.getKey().toString().trim())) {
                                    String st1r = "{\"input\":\""+ grandChildMapEntry.getValue() +"\",\"output\":\""+ grandChildMapEntry.getValue() +"\",\"payload\":\"" +str[0]+ "\",\"weight\":\"0\"}";
                                    grandChildMap.put("suggest_" + grandChildMapEntry.getKey().toString().trim(),st1r);
                                }
                            }
                            //For Update Suggest
                            */
                            client.prepareUpdate(indexName, str[0], rowID).setDoc(indexBuilder).setRefresh(true).execute().actionGet();
                        }
                    } else { //DELETE
                        Set childMapDataSet = childMap.entrySet();
                        Iterator childItr = childMapDataSet.iterator();
                        DeleteResponse response = null;
                        while (childItr.hasNext()) {
                            Map.Entry childMapEntry = (Map.Entry) childItr.next();
                            rowID = childMapEntry.getKey().toString().trim();
                            response = client.prepareDelete(indexName, str[0], rowID).execute().actionGet();
                        }
                    }
                } catch (NullPointerException np) {
                    status = "Error: Internal Error occured during Indexing! ";
                    np.printStackTrace();
                }
                status = status + "Indexing completed successfully for BC Name::" + str[0] + " ## ";
            }
        } catch (IOException e) {
            status = "Error: Internal Error occured during Indexing! ";
            e.printStackTrace();
        } catch (Exception e) {
            status = "Error: Internal Error occured during Indexing!";
            e.printStackTrace();
        } finally {
            ct.close();
        }
        return status;
    }

    /*
     * DESC: Search In All Function.
     * Inputs are searchText and other searching criterias.
     * Output is search response.
     */
    public ArrayList<SearchResponse> searchInAll(String searchText, int from, int size, String sortField,
                                                 String sortOrder, HashMap<Object, Object> map) {
        ElasticSearchWrapper esw = new ElasticSearchWrapper();
        Client ct = esw.getClient();
        SearchResponse response = null;
        ArrayList<SearchResponse> list = new ArrayList<SearchResponse>();
        try {
            String indexName = ElasticSearchWrapper.indexName;
            Set chiilDatadSet = map.entrySet();
            Iterator parentItr = chiilDatadSet.iterator();
            String bcType = "";
            String[] visib = null;
            //int length = 0, left = 0;

            //Create List of BC.
            List<String> listofBC = new ArrayList<String>();
            while (parentItr.hasNext()) {
                Map.Entry childMapEntry = (Map.Entry) parentItr.next();
                bcType = childMapEntry.getKey().toString();
                listofBC.add(bcType);
            }
            String[] arr = new String[listofBC.size()];
            arr = listofBC.toArray(arr);

            //Prepare filter builders based on different filter criterias.
            parentItr = chiilDatadSet.iterator();
            OrFilterBuilder orFb = FilterBuilders.orFilter();
            while (parentItr.hasNext()) {
                Map.Entry childMapEntry = (Map.Entry) parentItr.next();
                bcType = childMapEntry.getKey().toString();
                visib = childMapEntry.getValue().toString().split("#");
                AndFilterBuilder andFb = FilterBuilders.andFilter();
                andFb.add(FilterBuilders.regexpFilter("_type", ".*" + bcType + ".*"));
                if (visib.length <= 1) {
                    response =
                        ct.prepareSearch(indexName).setTypes(bcType).setFrom(from).setSize(size).setQuery(QueryBuilders.queryString("*" +
                                                                                                                                    searchText +
                                                                                                                                    "*")).execute().actionGet();
                }
                else if (visib.length <= 2) {
                    if (!visib[0].equalsIgnoreCase("null") && !visib[1].equalsIgnoreCase("null"))
                        andFb.add(FilterBuilders.regexpFilter(visib[0], ".*" + visib[1] + ".*"));
                } else {
                    OrFilterBuilder orFb1 = FilterBuilders.orFilter();
                    for (int i = 0; i < visib.length - 1; i += 2) {
                        if (!visib[i].equalsIgnoreCase("null") && !visib[i + 1].equalsIgnoreCase("null")){
                            String s[]=visib[i+1].split("\\$");
                            for(int a=0;a<s.length;a++){
                                if(s[a].length()>0){
                                    orFb1.add(FilterBuilders.regexpFilter(visib[i], ".*" + s[a] + ".*"));
                                }
                            }
                            //orFb1.add(FilterBuilders.regexpFilter(visib[i], ".*" + visib[i + 1] + ".*"));
                        }
                    }
                    andFb.add(orFb1);
                }
                orFb.add(andFb);
            }

            //Search and get the response.
            response =
                ct.prepareSearch(indexName).setTypes(arr).setFrom(from).setSize(size).setQuery(QueryBuilders.queryString("*" +
                                                                                                                         searchText +
                                                                                                                         "*")).setPostFilter(orFb).execute().actionGet();

            list.add(response);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ct.close();
        }
        return list;
    }

    /*
     * DESC: Search In Category Function.
     * Inputs are bcType and other searching criterias.
     * Output is search response.
     */
    public ArrayList<SearchResponse> searchInCategory(String searchText, int from, int size, String sortField,
                                                      String sortOrder, HashMap<Object, Object> map) {
        ElasticSearchWrapper esw = new ElasticSearchWrapper();
        Client ct = esw.getClient();
        SearchResponse response = null;
        ArrayList<SearchResponse> list = new ArrayList<SearchResponse>();
        try {
            String indexName = ElasticSearchWrapper.indexName;
            Set chiilDatadSet = map.entrySet();
            Iterator parentItr = chiilDatadSet.iterator();
            String bcType = "";
            String[] visib = null;
            int length = 0, left = 0;
            while (parentItr.hasNext()) {
                Map.Entry childMapEntry = (Map.Entry) parentItr.next();
                bcType = childMapEntry.getKey().toString();
                visib = childMapEntry.getValue().toString().split("#");
                if (length < size) {
                    left = size - length;
                    
                        if (visib.length <= 1) {
                            response =
                                ct.prepareSearch(indexName).setTypes(bcType).setFrom(from).setSize(left).setQuery(QueryBuilders.queryString("*" +
                                                                                                                                            searchText +
                                                                                                                                            "*")).execute().actionGet();
                        }
                        else if (visib.length <= 2) {
                        if (!visib[0].equalsIgnoreCase("null") && !visib[1].equalsIgnoreCase("null")) {
                            response =
                                ct.prepareSearch(indexName).setTypes(bcType).setFrom(from).setSize(left).setQuery(QueryBuilders.queryString("*" +
                                                                                                                                            searchText +
                                                                                                                                            "*")).setPostFilter(FilterBuilders.regexpFilter(visib[0],
                                                                                                                                                                                            ".*" +
                                                                                                                                                                                            visib[1] +
                                                                                                                                                                                            ".*")).execute().actionGet();
                        } else {
                            response =
                                ct.prepareSearch(indexName).setTypes(bcType).setFrom(from).setSize(left).setQuery(QueryBuilders.queryString("*" +
                                                                                                                                            searchText +
                                                                                                                                            "*")).execute().actionGet();

                        }
                    }
                    else{
                        boolean hasVisib=false;
                        /*OrFilterBuilder orFb1 = FilterBuilders.orFilter();
                        for(int i=0;i<visib.length-1; i+=2){
                            if (!visib[i].equalsIgnoreCase("null") && !visib[i+1].equalsIgnoreCase("null")){
                                orFb1.add(FilterBuilders.regexpFilter(visib[i], ".*" + visib[i+1] + ".*"));
                                hasVisib=true;
                            }
                        }*/
                        OrFilterBuilder orFb1 = FilterBuilders.orFilter();
                        for (int i = 0; i < visib.length - 1; i += 2) {
                            if (!visib[i].equalsIgnoreCase("null") && !visib[i + 1].equalsIgnoreCase("null")){
                                String s[]=visib[i+1].split("\\$");
                                for(int a=0;a<s.length;a++){
                                    if(s[a].length()>0){
                                        orFb1.add(FilterBuilders.regexpFilter(visib[i], ".*" + s[a] + ".*"));
                                        hasVisib=true;
                                    }
                                }
                                //orFb1.add(FilterBuilders.regexpFilter(visib[i], ".*" + visib[i + 1] + ".*"));
                            }
                        }
                        if (hasVisib) {
                            response =
                                ct.prepareSearch(indexName).setTypes(bcType).setFrom(from).setSize(left).setQuery(QueryBuilders.queryString("*" +
                                                                                                                                            searchText +
                                                                                                                                            "*")).setPostFilter(orFb1).execute().actionGet();
                        } else {
                            response =
                                ct.prepareSearch(indexName).setTypes(bcType).setFrom(from).setSize(left).setQuery(QueryBuilders.queryString("*" +
                                                                                                                                            searchText +
                                                                                                                                            "*")).execute().actionGet();

                        }
                    }
                } else {
                    break;
                }

                if (response.getHits().getHits().length == left) {
                    list.add(response);
                    length = length + response.getHits().getHits().length;
                } else {
                    length = length + response.getHits().getHits().length;
                    list.add(response);
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ct.close();
        }
        return null;
    }


    public MultiSearchResponse searchAcrossMultiBC(String searchText, int from, int size, String sortField,
                                                   String sortOrder, String bcType) {
        ElasticSearchWrapper esw = new ElasticSearchWrapper();
        Client ct = esw.getClient();
        String indexName = ElasticSearchWrapper.indexName;
        SearchResponse response = null;
        MultiSearchResponse multiResponse = null;
        String str[] = null;
        if (bcType.contains(",")) {
            str = bcType.split(",");
        }
        SearchRequestBuilder srb1, srb2 = null;
        //System.out.println(indexName + "::" + bcType + "::" + searchText + "_" + str[0] + "_" + str[1] + "_" + from +"_" + size);
        try {
            //below does not give results
            /*srb1 = ct.prepareSearch().setIndices(indexName)
                                                .setTypes(str[0])
                                                .setFrom(from)
                                                .setSize(size)
                                                .setQuery(QueryBuilders.queryString("*"+searchText+"*"))
                                                .setPostFilter(FilterBuilders.termFilter("RowID", "1-3JS2"));
            srb2 = ct.prepareSearch(indexName)
                                                .setTypes(str[1])
                                                .setFrom(from)
                                                .setSize(size)
                                                .setQuery(QueryBuilders.queryString("*"+searchText+"*"))
                                                .setPostFilter(FilterBuilders.termFilter("Name", "1-3JSA"));*/
            //does not work as well

            /*response = client.prepareSearch(indexName)
                                                .setTypes(bcType)
                                                .setFrom(from)
                                                .setSize(size)
                                                .setQuery(QueryBuilders.queryString("*"+searchText+"*")).setPostFilter(FilterBuilders.regexpFilter("OrganizationId",".*0-R9NH.*")).execute().actionGet();*/
            /*srb1 =
                ct.prepareSearch().setIndices(indexName).setTypes("Account").setFrom(from).setSize(size).setQuery(QueryBuilders.queryString("*"+searchText+"*")).setPostFilter(FilterBuilders.regexpFilter("OrganizationId",".*1SIA-4ZED.*"));

            srb2 =
                ct.prepareSearch(indexName).setTypes("Account").setFrom(from).setSize(size).setQuery(QueryBuilders.queryString("*"+searchText+"*")).setPostFilter(FilterBuilders.regexpFilter("PositionId",".*1-2WXT.*"));*/
            List<String> list = new ArrayList<String>();
            list = esw.getCategoryList();
            String[] arr = new String[list.size()];
            arr = list.toArray(arr);
            srb1 =
                ct.prepareSearch().setIndices(indexName).setTypes(arr).setFrom(from).setSize(size).setQuery(QueryBuilders.queryString("*" +
                                                                                                                                      searchText +
                                                                                                                                      "*")).setPostFilter(FilterBuilders.orFilter(FilterBuilders.andFilter(FilterBuilders.regexpFilter("_type",
                                                                                                                                                                                                                                       ".*Account.*"),
                                                                                                                                                                                                           FilterBuilders.regexpFilter("OrganizationId",
                                                                                                                                                                                                                                       ".*0-R.*"))));

            /*srb2 =
                            ct.prepareSearch(indexName).setTypes("Account").setFrom(from).setSize(size).setQuery(QueryBuilders.queryString("*"+searchText+"*")).setPostFilter(FilterBuilders.regexpFilter("_type",".*Account.*"));*/
            //it seems this is due to the filters- remove the filters and the concat works
            //multiResponse = ct.prepareMultiSearch().add(srb1).add(srb2).execute().actionGet();
            multiResponse = ct.prepareMultiSearch().add(srb1).execute().actionGet();
            //            for(MultiSearchResponse.Item item : response.getResponses()){
            //                SearchResponse res = item.getResponse();
            //                System.out.println(res.toString());
            //            }
            //System.out.println(response.toString());
            System.out.println(multiResponse.toString());
            return multiResponse;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ct.close();
        }
        return null;
    }

    /*
     *             while (parentItr.hasNext()) {
                Map.Entry childMapEntry = (Map.Entry) parentItr.next();
                bcType = childMapEntry.getKey().toString();
                visib = childMapEntry.getValue().toString().split("#");
                if(length < size){
                    left  = size - length;
                    response = client.prepareSearch(indexName)
                                                .setTypes(bcType)
                                                .setFrom(from)
                                                .setSize(left)
                                                .setQuery(QueryBuilders.queryString("*"+searchText+"*")).setPostFilter(FilterBuilders.regexpFilter(visib[0],".*"+visib[1]+".*")).execute().actionGet();
                    //FilterBuilders.andFilter(FilterBuilders.regexpFilter("_type",".*Account.*"),FilterBuilders.regexpFilter("OrganizationId",".*0-R.*"))
                }else{
                    break;
                }

                if(response.getHits().getHits().length == left){
                    list.add(response);
                    length = length + response.getHits().getHits().length;
                }else{
                    length = length + response.getHits().getHits().length;
                    list.add(response);
                }
            }
     */

}

