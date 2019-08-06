package com.bmw.controller;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bmw.entity.response.RestResponse;
import com.bmw.model.StockInsight;
import com.bmw.service.StockService;

@RestController
@RequestMapping("/stock-insights")
public class StockInsightController {

	private static Logger logger = LoggerFactory.getLogger(StockInsightController.class);

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private StockService stockService;

	@GetMapping(value = "", produces = "application/json")
	public RestResponse<List<StockInsight>> getStocks(
			@RequestParam(value = "dealerId", required = false) String dealerId,
			@RequestParam(value = "regionId", required = false) String regionId,
			@RequestParam(value = "groupId", required = false) String groupId) {

		logger.info("get stock insight list");
		List<StockInsight> s360List = stockService.getStockInsightList(dealerId, regionId, groupId);
		RestResponse<List<StockInsight>> response = new RestResponse<>();
		response.setData(s360List);
    	return response;
	}
}
