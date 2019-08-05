package com.bmw.auth;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class OAuth2TokenProvider {

	private static Logger logger = LoggerFactory.getLogger(OAuth2TokenProvider.class);
	
	@Value("${security.oauth2.client.accessTokenUri}")
	private String accessTokenUri; 
	
	@Value("${security.oauth2.client.clientSecret}")
	private String clientSecret;
	
	@Value("${security.oauth2.client.clientId}")
	private String clientId;
	
	@Value("${security.oauth2.client.grantType}")
	private String grantType;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	
	private String accessToken;
	
	private Date validTime;
	
	public String getAccessToken() {
		this.refreshToken();
		return this.accessToken;
	}
	
	private void refreshToken() {
		if(this.shouldRefresh()) {
			this.retrieveTokenFromServer();
		}
	}
	
	private void retrieveTokenFromServer() {
		this.validTime = new Date();
    	RestTemplate restTemplate = new RestTemplate();
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> oauth2Settings = new HashMap<>();
        oauth2Settings.put("client_id", clientId);
        oauth2Settings.put("client_secret", clientSecret);
        oauth2Settings.put("grant_type", grantType);
        try {
			String payload = objectMapper.writeValueAsString(oauth2Settings);
			HttpEntity<String> request = new HttpEntity<>(payload, headers);
			String result = restTemplate.postForObject(accessTokenUri, request, String.class);
			JsonNode root = objectMapper.readTree(result);
			this.accessToken = root.findValue("access_token").asText();
			int lifeTime = root.findValue("expires_in").asInt();
			this.setValidTime(lifeTime);
			logger.info("token provider refreshed accessToken:{}", accessToken);
		} catch (IOException e) {
			logger.error("Failed to retrieve token from server", e);
		}
	}
	
	private void setValidTime(int lifeTime) {
		Calendar c = Calendar.getInstance(); 
		if(null == this.validTime) {
			this.validTime = new Date();
		}
		c.setTime(this.validTime);
		c.add(Calendar.SECOND, lifeTime - 60);
		this.validTime = c.getTime();
		
	}
	
	private boolean shouldRefresh() {
		boolean result = true;
		if(null != this.accessToken && Calendar.getInstance().getTime().compareTo(this.validTime) < 0) {
			result = false;
		}
		return result;
	}
}
