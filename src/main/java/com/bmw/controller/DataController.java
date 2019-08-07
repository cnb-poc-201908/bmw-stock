package com.bmw.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmw.common.BMWPocConstants;
import com.bmw.data.StockDataBuilder;
import com.bmw.entity.response.RestResponse;
import com.bmw.model.Stock;
import com.bmw.model.StockInsight;
import com.bmw.service.DataService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/data")
public class DataController {

	private static Logger logger = LoggerFactory.getLogger(DataController.class);

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	@Autowired
	List<StockInsight> stockInsightList;

	@Autowired
	List<Stock> stockList;

	@Autowired
	DataService dataService;

	@GetMapping(value = "/refresh", produces = "application/json")
	public RestResponse<Object> refresh() throws IOException {

		RestResponse<Object> response = new RestResponse<>();

		ObjectMapper objectMapper = new ObjectMapper();

    	List<Stock> stockList1 = StockDataBuilder.buildStockList();
    	dataService.saveData(BMWPocConstants.REDIS_STOCK_LIST_KEY, objectMapper.writeValueAsString(stockList1));


    	List<StockInsight> stockInsightList1 = StockDataBuilder.buildStock360List(stockList1);
    	dataService.saveData(BMWPocConstants.REDIS_STOCKINSIGHT_LIST_KEY, objectMapper.writeValueAsString(stockInsightList1));
    	return response;
	}
}
