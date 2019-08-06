package com.bmw.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bmw.common.BMWPocConstants;
import com.bmw.data.StockDataBuilder;
import com.bmw.model.Stock;
import com.bmw.model.StockInsight;

@Configuration
public class RestConfig implements WebMvcConfigurer {

	private static Logger logger = LoggerFactory.getLogger(RestConfig.class);

	private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converters.add(converter);
    }

	@Autowired
	RestTemplate restTemplate;

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
    public Map<String, Object> stockData() {
    	Map<String, Object> data = new HashMap<>();
    	List<Stock> stockList = StockDataBuilder.buildStockList();
    	
    	data.put(BMWPocConstants.KEY_NAME_STOCK_LIST, stockList());
    	data.put(BMWPocConstants.KEY_NAME_STOCK360_LIST, 
    			StockDataBuilder.buildStock360List(stockList));
    	return data;
    }
    
    @Bean
    public List<Stock> stockList() {
    	List<Stock> stockList = StockDataBuilder.buildStockList();
    	return stockList;
    }
    
    @Bean
    public List<StockInsight> stock360List(List<Stock> stockList) {
    	List<StockInsight> stock360List = StockDataBuilder.buildStock360List(stockList);
    	return stock360List;
    }
}
