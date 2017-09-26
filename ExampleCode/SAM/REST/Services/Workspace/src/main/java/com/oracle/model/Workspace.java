package com.oracle.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties
public class Workspace {
	
	@JsonProperty
	private String status;
	
	public Workspace() {}


	public Workspace(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Workspace [status=" + status + "]";
	}


}
