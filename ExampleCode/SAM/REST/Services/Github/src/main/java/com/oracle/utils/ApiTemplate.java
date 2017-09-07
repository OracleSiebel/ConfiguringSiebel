package com.oracle.utils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import com.oracle.utils.GitConnect;

public class ApiTemplate {
	
	private RestTemplate restTemplate = null;
	@Autowired MappingJackson2HttpMessageConverter jsonMessageConverter;
	@Autowired static GitConnect gitConnect;
	
	private TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
		public boolean isTrusted(X509Certificate[] certificateauthType, String arg1) throws CertificateException {
			return true;
		}
	};
	
	public RestTemplate createSSLBasedRestTemplateWithBasicCredentials(String host, int port, String scheme) throws Exception {

		SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context != null ? context.getAuthentication() : null;
       
        String username = (String) ((auth!=null && auth.getPrincipal()!=null) ? auth.getPrincipal() : null);
        String password = (String) ((auth!=null && auth.getCredentials()!=null) ? auth.getCredentials() : null);
        
        HttpHost httpHost = new HttpHost(host, port, scheme);
        HttpHost proxy = new HttpHost(gitConnect.getProxyHost(), gitConnect.getProxyPort(), gitConnect.getProxyScheme());
        
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        AuthScope authScope = new AuthScope(httpHost);

        
        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .useSSL()
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        
        CloseableHttpClient httpClient = 
        	      HttpClients.custom()
        	      		     .setSSLSocketFactory(csf)
        	      		     .setProxy(proxy)
        	                 .setHostnameVerifier(new AllowAllHostnameVerifier())
        	                 .build();
        	    
        ClientHttpRequestFactory clientFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
       restTemplate = new RestTemplate(clientFactory);
		restTemplate.setMessageConverters(createMessageConverter());
		//restTemplate.setErrorHandler(new DGTErrorResponseHandler());
		return restTemplate;
	}
	
	private List<HttpMessageConverter<?>> createMessageConverter() {
		if(null==jsonMessageConverter)
			jsonMessageConverter = new MappingJackson2HttpMessageConverter();
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(jsonMessageConverter);
		
		return messageConverters;
	}
}

