package com.bmw.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmw.model.Stock;
import com.bmw.utils.DateUtil;

@Service
public class StockService {

	@Autowired
	private List<Stock> stockList;

	public List<Stock> getStocks(String startDate, String endDate,
			String keyword, String dealerId,
			String regionId, String groupId){

		List<Stock> source = stockList;

		if(StringUtils.isNotBlank(regionId)) {
			source = source.stream().filter((Stock stock) -> stock.getRegionId().equals(regionId)).collect(Collectors.toList());
		}
		if(StringUtils.isNotBlank(dealerId)) {
			source = source.stream().filter((Stock stock) -> stock.getDealerId().equals(dealerId)).collect(Collectors.toList());
		}
		if(StringUtils.isNotBlank(groupId)) {
			source = source.stream().filter((Stock stock) -> stock.getGroupId().equals(groupId)).collect(Collectors.toList());
		}

		if(StringUtils.isNotBlank(startDate)) {

			source = source.stream().filter((Stock stock) -> DateUtil.compareDateString(stock.getProductionDate(), startDate) >= 0).collect(Collectors.toList());
		}

		if(StringUtils.isNotBlank(endDate)) {
			source = source.stream().filter((Stock stock) -> DateUtil.compareDateString(stock.getProductionDate(), endDate) <= 0).collect(Collectors.toList());
		}

		if(StringUtils.isNotBlank(keyword)) {
			source = source.stream().filter((Stock stock) -> this.containsKeyword(stock, keyword)).collect(Collectors.toList());
		}

		return source;
	}

	public int updateStock(String stockId, Stock newStock) {
		int result = -1;
		for(Stock stock : stockList) {
			result = 0;
			if(stockId != null && stockId.equals(stock.getStockId())) {
				if(newStock.getStatus() != null) {
					stock.setStatus(newStock.getStatus());
				}

				if(newStock.getStorageDate() != null) {
					stock.setStorageDate(newStock.getStorageDate());
				}

				if(newStock.getLicensePlate() != null) {
					stock.setLicensePlate(newStock.getLicensePlate());
				}
			}
		}
		return result;
	}

	public int deleteStock(String stockId) {
		int result = -1;

		for(Stock stock : stockList) {
			result = 0;
			if(stockId != null && stockId.equals(stock.getStockId())
					&& stock.getDeletable()) {
				stock.setDeleted(Boolean.TRUE);
			}
		}

		return result;
	}

	private boolean containsKeyword(Stock stock, String keyword) {
		boolean result = false;

		if(stock.getVehicleSeriesCode().contains(keyword)) {
			result = true;
		}

		if(stock.getVehicleModelCode().contains(keyword)) {
			result = true;
		}

		if(stock.getVehicleModelConfig().contains(keyword)) {
			result = true;
		}

		if(stock.getModel().contains(keyword)) {
			result = true;
		}

		if(stock.getColor().contains(keyword)) {
			result = true;
		}
		if(stock.getDecoration().contains(keyword)) {
			result = true;
		}
		if(stock.getVehicleChassisNumber().contains(keyword)) {
			result = true;
		}
		return result;
	}
}
