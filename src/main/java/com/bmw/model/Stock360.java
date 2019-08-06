package com.bmw.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.bmw.common.BMWPocConstants;
import com.bmw.utils.DateUtil;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock360 extends Stock {
	// 库存年龄
	private Long stockAge;
	// 存放成本
	private long stockCost;

	public Long getStockAge() {
		return stockAge;
	}

	public long getStockCost() {
		return stockCost;
	}

	public Stock360() {
	}
	
	public Stock360(Stock s) {
		super();
		// 从入库日期开始计算
		this.stockAge = DateUtil.intervalDays(s.getStorageDate());
		// 通过库龄计算成本
		this.stockCost = stockAge * BMWPocConstants.STOCK_PRICE_PER_DAY;
	}
}