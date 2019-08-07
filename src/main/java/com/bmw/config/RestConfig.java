package com.bmw.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bmw.common.BMWPocConstants;
import com.bmw.controller.StockController;
import com.bmw.model.Stock;
import com.bmw.model.StockInsight;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RestConfig implements WebMvcConfigurer {


	private static Logger logger = LoggerFactory.getLogger(StockController.class);

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converters.add(converter);
    }

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	RedisTemplate<String, String> redisTemplate;

    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
          //放行哪些原始域
          config.addAllowedOrigin("*");
          //是否发送Cookie信息
          config.setAllowCredentials(true);
          //放行哪些原始域(请求方式)
          config.addAllowedMethod("*");
          //放行哪些原始域(头部信息)
          config.addAllowedHeader("*");

        //2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public List<Stock> stockList() {
    	ValueOperations<String, String> ops = redisTemplate.opsForValue();
    	ObjectMapper objectMapper = new ObjectMapper();
    	List<Stock> list;
		try {
			list = objectMapper.readValue(ops.get(BMWPocConstants.REDIS_STOCK_LIST_KEY),
					new TypeReference<List<Stock>>(){});
		} catch (IOException e) {
			logger.error("failed to get stocklist cache from redis.");
			list = new ArrayList<>();
		}
    	return list;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public List<StockInsight> stockInsightList(List<Stock> stockList) throws IOException {
    	List<StockInsight> list;
    	ValueOperations<String, String> ops = redisTemplate.opsForValue();
    	ObjectMapper objectMapper = new ObjectMapper();
		list = objectMapper.readValue(ops.get(BMWPocConstants.REDIS_STOCKINSIGHT_LIST_KEY),
				new TypeReference<List<StockInsight>>(){});
		logger.info("has {} stock insights in redis.", list.size());
    	return list;
    }
}
