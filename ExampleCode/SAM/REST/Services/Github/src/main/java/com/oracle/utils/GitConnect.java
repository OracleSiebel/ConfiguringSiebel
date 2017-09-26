package com.oracle.utils;

public class GitConnect {

	public static String authToken;
	public static String sslRESTTemplateHost;
	public static int sslRESTTemplatePort;
	public static String sslRESTTemplateScheme;
	public static String githubURI;
	public static String proxyHost;
	public static int proxyPort;
	public static String proxyScheme;

	public GitConnect() {
		super();
	}

	public static String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	public static String getSslRESTTemplateHost() {
		return sslRESTTemplateHost;
	}
	public void setSslRESTTemplateHost(String sslRESTTemplateHost) {
		this.sslRESTTemplateHost = sslRESTTemplateHost;
	}
	
	public static int getSslRESTTemplatePort() {
		return sslRESTTemplatePort;
	}
	public void setSslRESTTemplatePort(int sslRESTTemplatePort) {
		this.sslRESTTemplatePort = sslRESTTemplatePort;
	}
	public static String getSslRESTTemplateScheme() {
		return sslRESTTemplateScheme;
	}
	public void setSslRESTTemplateScheme(String sslRESTTemplateScheme) {
		this.sslRESTTemplateScheme = sslRESTTemplateScheme;
	}
	
	public static String getGithubURI() {
		return githubURI;
	}
	public void setGithubURI(String githubURI) {
		this.githubURI = githubURI;
	}
	
	public static String getProxyHost() {
		return proxyHost;
	}
	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}
	
	public static int getProxyPort() {
		return proxyPort;
	}
	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}
	
	public static String getProxyScheme() {
		return proxyScheme;
	}
	public void setProxyScheme(String proxyScheme) {
		this.proxyScheme = proxyScheme;
	}
}