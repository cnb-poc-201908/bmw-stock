package com.bmw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmw.model.Stock;
import com.bmw.service.StockService;

@RestController
@RequestMapping(value = "/interface")
public class InterfaceController {

	@Autowired
	private StockService stockService;

	@GetMapping(value = "/stock/return/{stockId}")
	public Stock returnStock(
			@PathVariable(value = "stockId", required = false) String stockId) {

		return stockService.getStock(stockId);
	}
}
