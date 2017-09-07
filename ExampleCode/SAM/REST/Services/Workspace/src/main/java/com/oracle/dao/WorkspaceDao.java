package com.oracle.dao;

import java.util.Map;

public interface WorkspaceDao {
	
	public Map<String, String> queryWorkSpaceForValidation(String wsName);
	
}
