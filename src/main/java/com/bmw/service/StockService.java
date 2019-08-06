package com.bmw.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmw.common.BMWPocConstants;
import com.bmw.model.Stock;
import com.bmw.model.StockInsight;
import com.bmw.utils.DateUtil;

@Service
public class StockService {

	@Autowired
	private List<Stock> stockList;
	@Autowired
	private List<StockInsight> stockInsightList;

	public List<StockInsight> getStock360List(String dealerId, String regionId, String groupId) {
		List<StockInsight> source = stockInsightList;

		if (regionId != null) {
			source = source.stream().filter((StockInsight s360) -> s360.getRegionId().equals(regionId))
					.collect(Collectors.toList());
		}
		if (dealerId != null) {
			source = source.stream().filter((StockInsight s360) -> s360.getDealerId().equals(dealerId))
					.collect(Collectors.toList());
		}
		if (groupId != null) {
			source = source.stream().filter((StockInsight s360) -> s360.getGroupId().equals(groupId))
					.collect(Collectors.toList());
		}

		return source;
	}

	public List<Stock> getStocks(String startDate, String endDate, String keyword, String dealerId, String regionId,
			String groupId) {

		@SuppressWarnings("unchecked")
		List<Stock> source = stockList;

		if (regionId != null) {
			source = source.stream().filter((Stock stock) -> stock.getRegionId().equals(regionId))
					.collect(Collectors.toList());
		}
		if (dealerId != null) {
			source = source.stream().filter((Stock stock) -> stock.getDealerId().equals(dealerId))
					.collect(Collectors.toList());
		}
		if (groupId != null) {
			source = source.stream().filter((Stock stock) -> stock.getGroupId().equals(groupId))
					.collect(Collectors.toList());
		}

		if (startDate != null) {

			source = source.stream()
					.filter((Stock stock) -> DateUtil.compareDateString(stock.getProductionDate(), startDate) >= 0)
					.collect(Collectors.toList());
		}

		if (endDate != null) {
			source = source.stream()
					.filter((Stock stock) -> DateUtil.compareDateString(stock.getProductionDate(), endDate) <= 0)
					.collect(Collectors.toList());
		}

		if (keyword != null) {
			source = source.stream().filter((Stock stock) -> this.containsKeyword(stock, keyword))
					.collect(Collectors.toList());
		}

		return source;
	}

	private boolean containsKeyword(Stock stock, String keyword) {
		boolean result = false;

		if (stock.getVehicleSeriesCode().contains(keyword)) {
			result = true;
		}

		if (stock.getVehicleModelCode().contains(keyword)) {
			result = true;
		}

		if (stock.getVehicleModelConfig().contains(keyword)) {
			result = true;
		}

		if (stock.getModel().contains(keyword)) {
			result = true;
		}

		if (stock.getColor().contains(keyword)) {
			result = true;
		}
		if (stock.getDecoration().contains(keyword)) {
			result = true;
		}
		if (stock.getVehicleChassisNumber().contains(keyword)) {
			result = true;
		}
		return result;
	}
}
