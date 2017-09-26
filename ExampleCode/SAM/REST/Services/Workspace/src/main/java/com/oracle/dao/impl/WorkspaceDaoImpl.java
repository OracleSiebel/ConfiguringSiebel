package com.oracle.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.oracle.dao.WorkspaceDao;
import com.oracle.model.Tblo;


@Component
@Repository("workspaceDao")
public class WorkspaceDaoImpl implements WorkspaceDao {
	
	//@Autowired private NamedParameterJdbcTemplate jdbcNameParamTemplate;
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Tblo tblo;
    
    
    String finalStatus = null;
    Map<String, String> workspaceValidaiton1 = new HashMap<String, String>();
    
    /*
     * Description : Perform workspace validation.
     * Input Parameter - Workspace Name
     * Output - Validation Message.
     */
    
    public Map<String, String> queryWorkSpaceForValidation(String wsName){
    	final String str = wsName;
		String queryString = "SELECT STATUS_CD, PAR_WS_ID FROM "+tblo.getTblo()+".S_WORKSPACE WHERE NAME = ?";
		Map<String, String> workspaceValidaiton = new HashMap<String, String>();
		workspaceValidaiton = jdbcTemplate.query(queryString, new Object[] {wsName},
				new ResultSetExtractor<Map<String, String>>() {
					public Map<String, String> extractData(ResultSet rs) throws SQLException {
						
						String status = null, parentBranchId = null;
						if (rs.next()) {
							status = rs.getString("STATUS_CD");
							parentBranchId = rs.getString("PAR_WS_ID");
						}
						finalStatus = status;
						if(status == null){
							workspaceValidaiton1.put("valid", "N");
				        	workspaceValidaiton1.put("message", "Workspace not found.");
						}else if(parentBranchId == null){
							workspaceValidaiton1.put("valid", "N");
				        	workspaceValidaiton1.put("message", "Parent Bramch Id not found for workspace "+str);
						}else{
							String queryString1 = "SELECT NAME FROM "+tblo.getTblo()+".S_WORKSPACE WHERE ROW_ID ='"+parentBranchId.trim()+"'";
							jdbcTemplate.query(queryString1,
									new ResultSetExtractor<Map<String, String>>() {
										public Map<String, String> extractData(ResultSet rs) throws SQLException {
											String parentBranchName = null;
											if (rs.next()) {
												parentBranchName = rs.getString("NAME");
											}
											if(parentBranchName == null){
												workspaceValidaiton1.put("valid", "N");
									        	workspaceValidaiton1.put("message", "Parent Branch Name not found. Workspace cannot be validated.");
											}else{
												if(parentBranchName.equalsIgnoreCase(tblo.getIntBranchName().trim())){
													if(finalStatus.equalsIgnoreCase("submitted for delivery")){
														workspaceValidaiton1.put("valid", "Y");
											        	workspaceValidaiton1.put("message", "Workspace status is valid.");
													}else{
														workspaceValidaiton1.put("valid", "N");
											        	workspaceValidaiton1.put("message", "Workspace status is invalid.");
													}
												}else{
													workspaceValidaiton1.put("valid", "N");
										        	workspaceValidaiton1.put("message", "Integration Branch Name passed does not match Parent Branch Name. Workspace cannot be validated.");
												}
											}
											return workspaceValidaiton1;
										}
									});
							
						}
						return workspaceValidaiton1;
					}
				});
		System.out.println("Workspace Response "+workspaceValidaiton );
		return workspaceValidaiton;
	}	
}
