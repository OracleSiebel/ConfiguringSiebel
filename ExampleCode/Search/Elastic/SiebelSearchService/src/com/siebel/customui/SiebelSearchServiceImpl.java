package com.siebel.customui;

import com.siebel.client.ElasticSearchWrapper;

import com.siebel.xml.siebelsearchcreateschema_input.CreateSchemaFieldDef;
import com.siebel.xml.siebelsearchcreateschema_output.CreateSchemaStatus;

import com.siebel.xml.siebelsearchdeleteindex_output.DeleteIndexStatus;
import com.siebel.xml.siebelsearchdoindex_input.DoIndexDataRecords;
import com.siebel.xml.siebelsearchdoindex_input.DoIndexFieldAndValues;
import com.siebel.xml.siebelsearchdoindex_input.DoIndexInputHeader;
import com.siebel.xml.siebelsearchdoindex_output.DoIndexStatus;

import com.siebel.xml.siebelsearchgetautosuggestions_output.GetAutoSuggestionsOutputHeader;

import com.siebel.xml.siebelsearchgetautosuggestions_output.GetAutoSuggestionsSuggestions;

import com.siebel.xml.siebelsearchsearchinall_input.SearchInAllSecureSearchDef;
import com.siebel.xml.siebelsearchsearchinall_input.SearchInAllVisibilityProps;
import com.siebel.xml.siebelsearchsearchinall_output.SearchInAllFieldAndValue;
import com.siebel.xml.siebelsearchsearchinall_output.SearchInAllOutputHeader;
import com.siebel.xml.siebelsearchsearchinall_output.SearchInAllResultRecords;
import com.siebel.xml.siebelsearchsearchincategory_input.SearchInCategorySecureSearchDef;

import com.siebel.xml.siebelsearchsearchincategory_input.SearchInCategoryVisibilityProps;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import javax.xml.bind.annotation.XmlSeeAlso;

import org.elasticsearch.client.Client;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.List;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.suggest.SuggestResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.term.TermSuggestion;

@WebService(name = "SiebelSearchService", targetNamespace = "http://siebel.com/CustomUI",
            serviceName = "SiebelSearchService", portName = "SiebelSearchService",
            wsdlLocation = "/WEB-INF/wsdl/SiebelSearchService.wsdl")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
            ObjectFactory.class, com.siebel.xml.siebelsearchcreateschema_input.ObjectFactory.class,
            com.siebel.xml.siebelsearchcreateschema_output.ObjectFactory.class,
            com.siebel.xml.siebelsearchdeleteindex_input.ObjectFactory.class,
            com.siebel.xml.siebelsearchdeleteindex_output.ObjectFactory.class,
            com.siebel.xml.siebelsearchdoindex_input.ObjectFactory.class,
            com.siebel.xml.siebelsearchdoindex_output.ObjectFactory.class,
            com.siebel.xml.siebelsearchgetautosuggestions_input.ObjectFactory.class,
            com.siebel.xml.siebelsearchgetautosuggestions_output.ObjectFactory.class,
            com.siebel.xml.siebelsearchsearchinall_input.ObjectFactory.class,
            com.siebel.xml.siebelsearchsearchinall_output.ObjectFactory.class,
            com.siebel.xml.siebelsearchsearchincategory_input.ObjectFactory.class
    })
public class SiebelSearchServiceImpl {
    public SiebelSearchServiceImpl() {
    }

    @WebResult(name = "SiebelSearchService_CREATESCHEMA_Output", partName = "SiebelSearchService_CREATESCHEMA_Output",
               targetNamespace = "http://siebel.com/CustomUI")
    @WebMethod(operationName = "SiebelSearchService_CREATESCHEMA",
               action = "document/http://siebel.com/CustomUI:SiebelSearchService_CREATESCHEMA")
    public SiebelSearchServiceCREATESCHEMAOutput siebelSearchServiceCREATESCHEMA(@WebParam(name =
                                                                                           "SiebelSearchService_CREATESCHEMA_Input",
                                                                                           partName =
                                                                                           "SiebelSearchService_CREATESCHEMA_Input",
                                                                                           targetNamespace =
                                                                                           "http://siebel.com/CustomUI")
                                                                                 SiebelSearchServiceCREATESCHEMAInput siebelSearchServiceCREATESCHEMAInput) {
        SiebelSearchServiceCREATESCHEMAOutput outputStatus = new SiebelSearchServiceCREATESCHEMAOutput();
        //return outputStatus;
        CreateSchemaStatus status = new CreateSchemaStatus();
        String statusResponse = null;
        Client ct = null;
        try {
            ElasticSearchWrapper esw = new ElasticSearchWrapper();
            ct = esw.getClient();
            HashMap<Object, Object> map = new HashMap<Object, Object>();
            String bcName =
                siebelSearchServiceCREATESCHEMAInput.getCreateSchemaInputHeader().getCategoryName().toString();
            ArrayList<CreateSchemaFieldDef> childList =
                (ArrayList<CreateSchemaFieldDef>) siebelSearchServiceCREATESCHEMAInput.getCreateSchemaInputHeader().getCreateSchemaFieldDef();

            for (int i = 0; i < childList.size(); i++) {
                CreateSchemaFieldDef childValue = childList.get(i);
                //map.put(childValue.getFieldName(), "string"+ "," + childValue.getAnalyzedFields() + "," + childValue.getWeightage());
                //For Visibility implementation.
                map.put(childValue.getFieldName(),
                        "string" + "," + childValue.getAnalyzedFields() + "," + childValue.getWeightage() + "," +
                        childValue.getVisibilityField() + "," + childValue.getKeyField());
            }
            statusResponse = esw.createSchema(bcName, map);
            status.setErrorStatus(statusResponse);
            outputStatus.setCreateSchemaStatus(status);
        } catch (Exception e) {
            status.setErrorStatus("Internal Error occured in the Web Service Connection.");
            outputStatus.setCreateSchemaStatus(status);
            e.printStackTrace();
        } finally {
            if (ct != null) {
                ct.close();
            }
        }
        return outputStatus;
    }

    @WebResult(name = "SiebelSearchService_DOINDEX_Output", partName = "SiebelSearchService_DOINDEX_Output",
               targetNamespace = "http://siebel.com/CustomUI")
    @WebMethod(operationName = "SiebelSearchService_DOINDEX",
               action = "document/http://siebel.com/CustomUI:SiebelSearchService_DOINDEX")
    public SiebelSearchServiceDOINDEXOutput siebelSearchServiceDOINDEX(@WebParam(name =
                                                                                 "SiebelSearchService_DOINDEX_Input",
                                                                                 partName =
                                                                                 "SiebelSearchService_DOINDEX_Input",
                                                                                 targetNamespace =
                                                                                 "http://siebel.com/CustomUI")
                                                                       SiebelSearchServiceDOINDEXInput siebelSearchServiceDOINDEXInput) {
        SiebelSearchServiceDOINDEXOutput indexOutput = new SiebelSearchServiceDOINDEXOutput();
        Client ct = null;
        DoIndexStatus status = new DoIndexStatus();
        try {
            
            ImplHelper Imp = new ImplHelper ();
            Operations Ops = new Operations (siebelSearchServiceDOINDEXInput);
            Imp.DoIndex(Ops);

            ElasticSearchWrapper esw = new ElasticSearchWrapper();
            ct = esw.getClient();
            HashMap<Object, HashMap<Object, HashMap<Object, Object>>> parentMap =
                new HashMap<Object, HashMap<Object, HashMap<Object, Object>>>();
            ArrayList<DoIndexInputHeader> parentList =
                (ArrayList<DoIndexInputHeader>) siebelSearchServiceDOINDEXInput.getListOfDoIndexInput().getDoIndexInputHeader();          
            for (int i = 0; i < parentList.size(); i++) {
                DoIndexInputHeader pic = parentList.get(i);
                ArrayList<DoIndexDataRecords> childList =
                    (ArrayList<DoIndexDataRecords>) pic.getListOfDoIndexDataRecords().getDoIndexDataRecords();
                HashMap<Object, HashMap<Object, Object>> childMap = new HashMap<Object, HashMap<Object, Object>>();
                for (int j = 0; j < childList.size(); j++) {
                    DoIndexDataRecords cic = childList.get(j);
                    ArrayList<DoIndexFieldAndValues> grandChildList =
                        (ArrayList<DoIndexFieldAndValues>) cic.getListOfDoIndexFieldAndValues().getDoIndexFieldAndValues();
                    HashMap<Object, Object> grandChildMap = new HashMap<Object, Object>();
                    for (int k = 0; k < grandChildList.size(); k++) {
                        DoIndexFieldAndValues gic = grandChildList.get(k);
                       /* if(gic.getFieldName().startsWith("Catalog_") || gic.getFieldName().startsWith("Group_")){
                            grandChildMap.put(gic.getFieldName(), "");
                        }
                        else{*/
                            grandChildMap.put(gic.getFieldName(), gic.getFieldValue());
                        //}
                       /* if(gic.getFieldName().startsWith("Catalog_") || gic.getFieldName().startsWith("Group_")){
                            System.out.println(gic.getFieldName()+":"+ gic.getFieldValue().length());
                        }*/
                       
                    }
                    childMap.put(cic.getRowID(), grandChildMap);
                }
                parentMap.put(pic.getCategoryName() + "##" + pic.getOpType() + "##" + pic.getTotalRecords(), childMap);
                System.out.println(parentMap.size() + "\n" + parentMap);
                String statusResponse = esw.doIndex(parentMap);
                status.setErrorStatus(statusResponse);
                indexOutput.setDoIndexStatus(status);
            }
             System.out.println(parentMap.size() + "\n" + parentMap);
            //Put the record into ICFlagMap.put(ICFlag, parentMap).
            //End of For Loop for List of IC flags.
            //String statusResponse = esw.doIndex(bcName, parentMap);
            //Call the doIndex method based on latest IC Flag Map.
            //String statusResponse = esw.doIndex(parentMap);
            //status.setErrorStatus(statusResponse);
            //indexOutput.setDoIndexStatus(status);
        } catch (Exception e) {
            status.setErrorStatus("Internal Error occured in the Web Service Connection.");
            indexOutput.setDoIndexStatus(status);
            e.printStackTrace();
        } finally {
            if (ct != null) {
                ct.close();
            }
        }
        return indexOutput;
    }

    @WebResult(name = "SiebelSearchService_DELETEINDEX_Output", partName = "SiebelSearchService_DELETEINDEX_Output",
               targetNamespace = "http://siebel.com/CustomUI")
    @WebMethod(operationName = "SiebelSearchService_DELETEINDEX",
               action = "document/http://siebel.com/CustomUI:SiebelSearchService_DELETEINDEX")
    public SiebelSearchServiceDELETEINDEXOutput siebelSearchServiceDELETEINDEX(@WebParam(name =
                                                                                         "SiebelSearchService_DELETEINDEX_Input",
                                                                                         partName =
                                                                                         "SiebelSearchService_DELETEINDEX_Input",
                                                                                         targetNamespace =
                                                                                         "http://siebel.com/CustomUI")
                                                                               SiebelSearchServiceDELETEINDEXInput siebelSearchServiceDELETEINDEXInput) {
        String bcName = siebelSearchServiceDELETEINDEXInput.getDeleteIndexInputParam().getCategoryName();
        SiebelSearchServiceDELETEINDEXOutput delIndexOutput = new SiebelSearchServiceDELETEINDEXOutput();
        DeleteIndexStatus status = new DeleteIndexStatus();
        Client ct = null;
        try {
            ElasticSearchWrapper esw = new ElasticSearchWrapper();
            ct = esw.getClient();
            String statusResponse = esw.deleteIndex(bcName);
            status.setErrorStatus(statusResponse);
            delIndexOutput.setDeleteIndexStatus(status);
        } catch (Exception e) {
            status.setErrorStatus("Internal Error occured in the Web Service Connection.");
            delIndexOutput.setDeleteIndexStatus(status);
            e.printStackTrace();
        } finally {
            if (ct != null) {
                ct.close();
            }
        }
        return delIndexOutput;
    }

    @WebResult(name = "SiebelSearchService_GETAUTOSUGGESTIONS_Output",
               partName = "SiebelSearchService_GETAUTOSUGGESTIONS_Output",
               targetNamespace = "http://siebel.com/CustomUI")
    @WebMethod(operationName = "SiebelSearchService_GETAUTOSUGGESTIONS",
               action = "document/http://siebel.com/CustomUI:SiebelSearchService_GETAUTOSUGGESTIONS")
    public SiebelSearchServiceGETAUTOSUGGESTIONSOutput siebelSearchServiceGETAUTOSUGGESTIONS(@WebParam(name =
                                                                                                       "SiebelSearchService_GETAUTOSUGGESTIONS_Input",
                                                                                                       partName =
                                                                                                       "SiebelSearchService_GETAUTOSUGGESTIONS_Input",
                                                                                                       targetNamespace =
                                                                                                       "http://siebel.com/CustomUI")
                                                                                             SiebelSearchServiceGETAUTOSUGGESTIONSInput siebelSearchServiceGETAUTOSUGGESTIONSInput) {
        String mytext = siebelSearchServiceGETAUTOSUGGESTIONSInput.getGetAutoSuggestionsInputHeader().getSearchText();
        String mycategory =
            siebelSearchServiceGETAUTOSUGGESTIONSInput.getGetAutoSuggestionsInputHeader().getCategoryName();
        String mynumofsuggestions =
            siebelSearchServiceGETAUTOSUGGESTIONSInput.getGetAutoSuggestionsInputHeader().getNumOfSuggestions();
        SiebelSearchServiceGETAUTOSUGGESTIONSOutput autoSuggestOP = new SiebelSearchServiceGETAUTOSUGGESTIONSOutput();
        Client ct = null;

        try {
            ElasticSearchWrapper esw = new ElasticSearchWrapper();
            ct = esw.getClient();
            String siggestField = "";
            SuggestResponse response;
            List<String> suggestPropertyList = new ArrayList<String>();
            if (!mycategory.equalsIgnoreCase("all")) {
                //ct = esw.getClient();
                suggestPropertyList = esw.getMappingObject(mycategory);
            } else {
                //ct = esw.getClient();
                List<String> ListofCat = new ArrayList<String>();
                ListofCat = esw.getCategoryList();
                //ListofCat.add("MyAccount");
                int j = 0;
                while (j < ListofCat.size()) {
                    String curCat = ListofCat.get(j).toString().trim();
                    //esw = new ElasticSearchWrapper();
                    //ct = esw.getClient();
                    List<String> tmpList = esw.getMappingObject(curCat);
                    suggestPropertyList.addAll(tmpList);
                    j++;
                }
                //ct.close();
            }
            if (suggestPropertyList.size() <= 0) {
                suggestPropertyList.add(siggestField);
            }
            GetAutoSuggestionsOutputHeader autoSuggestParentOP = new GetAutoSuggestionsOutputHeader();
            ArrayList<GetAutoSuggestionsSuggestions> childList =
                (ArrayList<GetAutoSuggestionsSuggestions>) autoSuggestParentOP.getGetAutoSuggestionsSuggestions();
            int intSize = 0;
            /*
                                 * defaults to minimum 10 suggestions if user doesn't provide an upper limit
                                 */
            int myintnumofsuggestions = 10;
            if (mynumofsuggestions.length() > 0) {
                myintnumofsuggestions = Integer.parseInt(mynumofsuggestions);
            }
            /*
                                 * Try to find complete suggestion first and then go to term suggestion for auto correction.
                                 * This is done for all the analysed fields until the upper limit it reached.
                                 * For complete suggest category name is deduced from the payload while for term suggest no category name is added.
                                 */
            int i = 0;
            while (i < suggestPropertyList.size() && intSize < myintnumofsuggestions) {
                //esw = new client.ElasticSearchWrapper();
                //ct = esw.getClient();
                if (suggestPropertyList.get(i).contains("suggest_")) {
                    siggestField = suggestPropertyList.get(i).toString().trim();
                } else {
                    i++;
                    continue;
                }
                response = esw.completeSuggestion("siebel", siggestField, mytext);
                CompletionSuggestion compSuggest = response.getSuggest().getSuggestion("complete");
                List<CompletionSuggestion.Entry> entryList = compSuggest.getEntries();
                if (entryList != null) {
                    CompletionSuggestion.Entry entry = entryList.get(0);
                    Iterator<CompletionSuggestion.Entry.Option> listofSuggestions = entry.getOptions().iterator();
                    while (listofSuggestions.hasNext() && intSize < myintnumofsuggestions) {
                        GetAutoSuggestionsSuggestions child = new GetAutoSuggestionsSuggestions();
                        CompletionSuggestion.Entry.Option currentChildOption = listofSuggestions.next();
                        child.setSuggestedText(currentChildOption.getText().toString());
                        child.setCategoryName(currentChildOption.getPayloadAsString().toString());
                        childList.add(child);
                        intSize++;
                    }
                }
                i++;
            }
            i = 0;
            while (i < suggestPropertyList.size() && intSize < myintnumofsuggestions) {
                //esw = new client.ElasticSearchWrapper();
                //ct = esw.getClient();
                if (!suggestPropertyList.get(i).contains("suggest_")) {
                    siggestField = suggestPropertyList.get(i).toString().trim();
                } else {
                    i++;
                    continue;
                }
                response = esw.termSuggestion("siebel", siggestField, mytext);
                TermSuggestion termSuggest = response.getSuggest().getSuggestion("term");
                List<TermSuggestion.Entry> termentryList = termSuggest.getEntries();
                if (termentryList != null) {
                    TermSuggestion.Entry entry = termentryList.get(0);
                    Iterator<TermSuggestion.Entry.Option> listofSuggestions = entry.getOptions().iterator();
                    while (listofSuggestions.hasNext() && intSize < myintnumofsuggestions) {
                        GetAutoSuggestionsSuggestions child = new GetAutoSuggestionsSuggestions();
                        TermSuggestion.Entry.Option currentChildOption = listofSuggestions.next();
                        child.setSuggestedText(currentChildOption.getText().toString());
                        child.setCategoryName("");
                        childList.add(child);
                        intSize++;
                    }
                }
                i++;
            }
            autoSuggestParentOP.setNumOfSuggestions(Integer.toString(intSize));
            autoSuggestOP.setGetAutoSuggestionsOutputHeader(autoSuggestParentOP);
            //ct.close(); //Finally closing the elastic client before returning the autosuggest output.
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ct != null) {
                ct.close();
            }
        }
        return autoSuggestOP;
    }

    @WebResult(name = "SiebelSearchService_SEARCHINCATEGORY_Output",
               partName = "SiebelSearchService_SEARCHINCATEGORY_Output", targetNamespace = "http://siebel.com/CustomUI")
    @WebMethod(operationName = "SiebelSearchService_SEARCHINCATEGORY",
               action = "document/http://siebel.com/CustomUI:SiebelSearchService_SEARCHINCATEGORY")
    public SiebelSearchServiceSEARCHINCATEGORYOutput siebelSearchServiceSEARCHINCATEGORY(@WebParam(name =
                                                                                                   "SiebelSearchService_SEARCHINCATEGORY_Input",
                                                                                                   partName =
                                                                                                   "SiebelSearchService_SEARCHINCATEGORY_Input",
                                                                                                   targetNamespace =
                                                                                                   "http://siebel.com/CustomUI")
                                                                                         SiebelSearchServiceSEARCHINCATEGORYInput siebelSearchServiceSEARCHINCATEGORYInput) {
        SiebelSearchServiceSEARCHINCATEGORYOutput searchInCategoryOutput =
            new SiebelSearchServiceSEARCHINCATEGORYOutput();
        SiebelSearchServiceSEARCHINALLInput SiebelSearchServiceSEARCHINALL1Input =
            new SiebelSearchServiceSEARCHINALLInput();
        Client ct = null;
        try {
            ElasticSearchWrapper esw = new ElasticSearchWrapper();
            ct = esw.getClient();
            int from =
                Integer.parseInt(siebelSearchServiceSEARCHINCATEGORYInput.getSearchInCategoryInputHeader().getFrom());
            int to =
                Integer.parseInt(siebelSearchServiceSEARCHINCATEGORYInput.getSearchInCategoryInputHeader().getTo());

            //Get the list of BCNAME and read the visibility field and value for that BC.
            ArrayList<SearchInCategorySecureSearchDef> list =
                (ArrayList<SearchInCategorySecureSearchDef>) siebelSearchServiceSEARCHINCATEGORYInput.getSearchInCategoryInputHeader().getSearchInCategorySecureSearchDef();

            //Put BC name as key and visibility fields for that BC as value for that key.
            HashMap<Object, Object> smap = new HashMap<Object, Object>();

            for (int i = 0; i < list.size(); i++) {
                SearchInCategorySecureSearchDef childic = list.get(i);
                List<SearchInCategoryVisibilityProps> visProps = childic.getSearchInCategoryVisibilityProps();
                String vis = "";
                for (int a = 0; a < visProps.size(); a++) {
                    vis =vis+ visProps.get(a).getVisibilityField() + "#" + visProps.get(a).getVisibilityValue() + "#";
                }
                //smap.put(childic.getCategoryName().toString(), childic.getVisibilityField()+"#"+childic.getVisibilityValue());
                smap.put(childic.getCategoryName().toString(), vis);
            }

            ArrayList<SearchResponse> srchResponseList =
                esw.searchInCategory(siebelSearchServiceSEARCHINCATEGORYInput.getSearchInCategoryInputHeader().getSearchText(),
                                     from, to - from + 1,
                                     siebelSearchServiceSEARCHINCATEGORYInput.getSearchInCategoryInputHeader().getSortField(),
                                     siebelSearchServiceSEARCHINCATEGORYInput.getSearchInCategoryInputHeader().getSortOrder(),
                                     smap);

            //Iterate over list of search responses.

            int size = 0;
            for (int x = 0; x < srchResponseList.size(); x++) {
                size = size + (int) srchResponseList.get(x).getHits().getTotalHits();
            }

            SearchInAllOutputHeader searchOutputParentIC = new SearchInAllOutputHeader();
            if (size > 0) {
                searchOutputParentIC.setTo((size - 1) + "");
            } else {
                searchOutputParentIC.setTo((size) + "");
            }
            searchOutputParentIC.setTotalRecords(size + "");
            searchOutputParentIC.setEOF("Y");

            if ((size - (to + 1)) > 0) {
                searchOutputParentIC.setEOF("N");
            }
            searchOutputParentIC.setFrom("0");

            ArrayList<SearchInAllResultRecords> childList =
                (ArrayList<SearchInAllResultRecords>) searchOutputParentIC.getSearchInAllResultRecords();
            for (int i = 0; i < srchResponseList.size(); i++) {
                for (int j = 0; j < srchResponseList.get(i).getHits().getHits().length; j++) {
                    SearchHit[] sh = srchResponseList.get(i).getHits().getHits();
                    SearchInAllResultRecords searchOutputChildIC = new SearchInAllResultRecords();
                    HashMap<String, Object> cMap = (HashMap<String, Object>) sh[j].getSource();
                    searchOutputChildIC.setCategoryName(sh[j].getType());
                    searchOutputChildIC.setRowID(cMap.get("RowID").toString());
                    searchOutputChildIC.setDocumentType("Buscomp Record");
                    childList.add(searchOutputChildIC);

                    ArrayList<SearchInAllFieldAndValue> grandChildList =
                        (ArrayList<SearchInAllFieldAndValue>) searchOutputChildIC.getSearchInAllFieldAndValue();
                    Iterator it = cMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        System.out.println(pair.getKey() + " = " + pair.getValue());
                        if (!pair.getKey().toString().equalsIgnoreCase("RowID")) {
                            if (!pair.getKey().toString().startsWith("suggest_")) {
                                SearchInAllFieldAndValue searchOutputGrandChildIC = new SearchInAllFieldAndValue();
                                searchOutputGrandChildIC.setFieldName(pair.getKey().toString());
                                searchOutputGrandChildIC.setFieldValue(pair.getValue().toString());
                                grandChildList.add(searchOutputGrandChildIC);
                            }
                        }
                    }
                }
            }
            searchInCategoryOutput.setSearchInAllOutputHeader(searchOutputParentIC);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ct != null) {
                ct.close();
            }
        }
        return searchInCategoryOutput;
    }

    @WebResult(name = "SiebelSearchService_SEARCHINALL_Output", partName = "SiebelSearchService_SEARCHINALL_Output",
               targetNamespace = "http://siebel.com/CustomUI")
    @WebMethod(operationName = "SiebelSearchService_SEARCHINALL",
               action = "document/http://siebel.com/CustomUI:SiebelSearchService_SEARCHINALL")
    public SiebelSearchServiceSEARCHINALLOutput siebelSearchServiceSEARCHINALL(@WebParam(name =
                                                                                         "SiebelSearchService_SEARCHINALL_Input",
                                                                                         partName =
                                                                                         "SiebelSearchService_SEARCHINALL_Input",
                                                                                         targetNamespace =
                                                                                         "http://siebel.com/CustomUI")
                                                                               SiebelSearchServiceSEARCHINALLInput siebelSearchServiceSEARCHINALLInput) {
        SiebelSearchServiceSEARCHINALLOutput searchInAllOutput = new SiebelSearchServiceSEARCHINALLOutput();
        Client ct = null;
        try {
            ElasticSearchWrapper esw = new ElasticSearchWrapper();
            ct = esw.getClient();

            int from = Integer.parseInt(siebelSearchServiceSEARCHINALLInput.getSearchInCategoryInputHeader().getFrom());
            int to = Integer.parseInt(siebelSearchServiceSEARCHINALLInput.getSearchInCategoryInputHeader().getTo());

            //Get the list of BCNAME and read the visibility field and value for that BC.
            ArrayList<SearchInAllSecureSearchDef> list =
                (ArrayList<SearchInAllSecureSearchDef>) siebelSearchServiceSEARCHINALLInput.getSearchInCategoryInputHeader().getSearchInAllSecureSearchDef();

            //Put BC name as key and visibility fields for that BC as value for that key.
            HashMap<Object, Object> smap = new HashMap<Object, Object>();

            for (int i = 0; i < list.size(); i++) {
                SearchInAllSecureSearchDef childic = list.get(i);
                List<SearchInAllVisibilityProps> visProps = childic.getSearchInAllVisibilityProps();
                String vis = "";
                for (int a = 0; a < visProps.size(); a++) {
                    vis =vis+ visProps.get(a).getVisibilityField() + "#" + visProps.get(a).getVisibilityValue() + "#";
                }
                //smap.put(childic.getCategoryName().toString(), childic.getVisibilityField()+"#"+childic.getVisibilityValue());
                smap.put(childic.getCategoryName().toString(), vis);
                //smap.put(childic.getCategoryName().toString(), childic.getVisibilityField()+"#"+childic.getVisibilityValue());
            }

            //Finally pass that map to the search engine wrapper.

            ArrayList<SearchResponse> srchResponseList =
                esw.searchInAll(siebelSearchServiceSEARCHINALLInput.getSearchInCategoryInputHeader().getSearchText(),
                                from, to - from + 1,
                                siebelSearchServiceSEARCHINALLInput.getSearchInCategoryInputHeader().getSortField(),
                                siebelSearchServiceSEARCHINALLInput.getSearchInCategoryInputHeader().getSortOrder(),
                                smap);
            //Iterate over list of search responses.

            int size = 0;
            for (int x = 0; x < srchResponseList.size(); x++) {
                size = size + (int) srchResponseList.get(x).getHits().getTotalHits();
            }

            SearchInAllOutputHeader searchOutputParentIC = new SearchInAllOutputHeader();
            if (size > 0) {
                searchOutputParentIC.setTo((size - 1) + "");
            } else {
                searchOutputParentIC.setTo((size) + "");
            }
            searchOutputParentIC.setTotalRecords(size + "");
            searchOutputParentIC.setEOF("Y");

            if ((size - (to + 1)) > 0) {
                searchOutputParentIC.setEOF("N");
            }
            searchOutputParentIC.setFrom("0");


            ArrayList<SearchInAllResultRecords> childList =
                (ArrayList<SearchInAllResultRecords>) searchOutputParentIC.getSearchInAllResultRecords();
            for (int i = 0; i < srchResponseList.size(); i++) {
                for (int j = 0; j < srchResponseList.get(i).getHits().getHits().length; j++) {
                    SearchHit[] sh = srchResponseList.get(i).getHits().getHits();
                    SearchInAllResultRecords searchOutputChildIC = new SearchInAllResultRecords();
                    HashMap<String, Object> cMap = (HashMap<String, Object>) sh[j].getSource();
                    searchOutputChildIC.setCategoryName(sh[j].getType());
                    searchOutputChildIC.setRowID(cMap.get("RowID").toString());
                    searchOutputChildIC.setDocumentType("Buscomp Record");
                    childList.add(searchOutputChildIC);

                    ArrayList<SearchInAllFieldAndValue> grandChildList =
                        (ArrayList<SearchInAllFieldAndValue>) searchOutputChildIC.getSearchInAllFieldAndValue();
                    Iterator it = cMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        System.out.println(pair.getKey() + " = " + pair.getValue());
                        if (!pair.getKey().toString().equalsIgnoreCase("RowID")) {
                            if (!pair.getKey().toString().startsWith("suggest_")) {
                                SearchInAllFieldAndValue searchOutputGrandChildIC = new SearchInAllFieldAndValue();
                                searchOutputGrandChildIC.setFieldName(pair.getKey().toString());
                                searchOutputGrandChildIC.setFieldValue(pair.getValue().toString());
                                grandChildList.add(searchOutputGrandChildIC);
                            }
                        }
                    }
                }
            }
            searchInAllOutput.setSearchInAllOutputHeader(searchOutputParentIC);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ct != null) {
                ct.close();
            }
        }
        return searchInAllOutput;
        //return null;
    }
}

