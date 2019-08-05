package com.bmw.controller;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bmw.entity.response.RestResponse;
import com.bmw.model.Stock;
import com.bmw.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {

	private static Logger logger = LoggerFactory.getLogger(StockController.class);

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private StockService stockService;

	@GetMapping(value = "", produces = "application/json")
	public RestResponse<List<Stock>> getStocks(
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "dealerId", required = false) String dealerId,
			@RequestParam(value = "regionId", required = false) String regionId,
			@RequestParam(value = "groupId", required = false) String groupId) {


		List<Stock> stockList = stockService.getStocks(startDate, endDate, keyword, dealerId, regionId, groupId);
		RestResponse<List<Stock>> response = new RestResponse<>();
		response.setData(stockList);
    	return response;
	}

	@DeleteMapping(value = "/{stockId}", produces = "application/json")
	public RestResponse<Stock> deleteStock(
			@PathVariable(value = "stockId", required = false) String stockId) {

		logger.info("enter deleteStock with param stockId:{}",
				stockId);

		return new RestResponse<>();
	}


	@PutMapping(value = "/{stockId}", produces = "application/json")
	public RestResponse<Stock> updateStock(
			@PathVariable(value = "stockId", required = false) String stockId) {

		logger.info("enter updateStock with param stockId:{}",
				stockId);

		return new RestResponse<>();
	}

}
