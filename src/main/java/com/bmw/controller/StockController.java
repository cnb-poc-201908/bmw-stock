package com.bmw.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bmw.entity.response.RestResponse;
import com.bmw.model.Stock;

@RestController
@RequestMapping("/stocks")
public class StockController {

	private static Logger logger = LoggerFactory.getLogger(StockController.class);

	private final ObjectMapper objectMapper = new ObjectMapper();

	@GetMapping(value = "", produces = "application/json")
	public RestResponse<Stock> getStocks(
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "keyword", required = false) String keyword) {

		logger.info("enter getStocks with params startDate:{}, endDate:{}, keyword:{}",
				startDate, endDate, keyword);

		RestResponse<Stock> response = new RestResponse<>();
    	return response;
	}

	@DeleteMapping(value = "/{stockId}", produces = "application/json")
	public RestResponse<Stock> deleteStock(
			@PathVariable(value = "stockId", required = false) String stockId) {

		logger.info("enter deleteStock with param stockId:{}",
				stockId);

		RestResponse<Stock> response = new RestResponse<>();
    	return response;
	}


	@PutMapping(value = "/{stockId}", produces = "application/json")
	public RestResponse<Stock> updateStock(
			@PathVariable(value = "stockId", required = false) String stockId) {

		logger.info("enter updateStock with param stockId:{}",
				stockId);

		RestResponse<Stock> response = new RestResponse<>();
    	return response;
	}

}
