package com.bmw.auth;

import java.util.Collections;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import com.bmw.auth.interceptor.OAuth2HeaderInterceptor;

@Configuration
public class OAuth2Config {
	
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    protected OAuth2TokenProvider oauth2TokenProvider() {
    	return new OAuth2TokenProvider();
    }
    
    @Bean
    public RestTemplate restTemplate(OAuth2HeaderInterceptor oAuth2HeaderInterceptor) {
      RestTemplate restTemplate = new RestTemplate();

      restTemplate.setInterceptors(Collections.singletonList(oAuth2HeaderInterceptor));
      return restTemplate;
    }
}
