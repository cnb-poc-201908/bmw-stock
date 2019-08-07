package com.bmw.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.bmw.common.BMWPocConstants;
import com.bmw.utils.DateUtil;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockInsight {
	// 经销商ID
	private String dealerId;
	// 区域ID
	private String regionId;
	// 集团ID
	private String groupId;

	private String stockId;
	private String storageDate;
	private String productionDate;
	private String vehicleSeriesCode;
	private String vehicleModelCode;
	private String vehicleModelConfig;
	private String color;
	private String decoration;
	private String vehicleChassisNumber;

	// 库存年龄
	private Long stockAge;
	// 存放成本
	private Long stockCost;

	// 订金
	private Double deposit;
	// 签约日期
	private String contractDate;
	// 签约金额
	private Double contractAmount;
	// 新增成本
	private Double additionalCost;
	// 备注字段
	private String comment;

	public StockInsight() {

	}
	
	
	public StockInsight(String dealerId, String regionId, String groupId, String stockId, String storageDate,
			String productionDate, String vehicleSeriesCode, String vehicleModelCode, String vehicleModelConfig,
			String color, String decoration, String vehicleChassisNumber, Long stockAge, Long stockCost, Double deposit,
			String contractDate, Double contractAmount, Double additionalCost, String comment) {
		super();
		this.dealerId = dealerId;
		this.regionId = regionId;
		this.groupId = groupId;
		this.stockId = stockId;
		this.storageDate = storageDate;
		this.productionDate = productionDate;
		this.vehicleSeriesCode = vehicleSeriesCode;
		this.vehicleModelCode = vehicleModelCode;
		this.vehicleModelConfig = vehicleModelConfig;
		this.color = color;
		this.decoration = decoration;
		this.vehicleChassisNumber = vehicleChassisNumber;
		this.stockAge = stockAge;
		this.stockCost = stockCost;
		this.deposit = deposit;
		this.contractDate = contractDate;
		this.contractAmount = contractAmount;
		this.additionalCost = additionalCost;
		this.comment = comment;
	}


	public StockInsight(Stock s) {
		this.dealerId = s.getDealerId();
		this.regionId = s.getRegionId();
		this.groupId = s.getGroupId();

		this.stockId = s.getStockId();
		this.storageDate = s.getStorageDate();
		this.productionDate = s.getProductionDate();
		this.vehicleSeriesCode = s.getVehicleSeriesCode();
		this.vehicleModelCode = s.getVehicleModelCode();
		this.vehicleModelConfig = s.getVehicleModelConfig();
		this.color = s.getColor();
		this.decoration = s.getDecoration();
		this.vehicleChassisNumber = s.getVehicleChassisNumber();

		// 从入库日期开始计算
		this.stockAge = DateUtil.intervalDays(s.getStorageDate());
		// 通过库龄计算成本
		this.stockCost = stockAge * BMWPocConstants.STOCK_PRICE_PER_DAY;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getStorageDate() {
		return storageDate;
	}

	public void setStorageDate(String storageDate) {
		this.storageDate = storageDate;
	}

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	public String getVehicleSeriesCode() {
		return vehicleSeriesCode;
	}

	public void setVehicleSeriesCode(String vehicleSeriesCode) {
		this.vehicleSeriesCode = vehicleSeriesCode;
	}

	public String getVehicleModelCode() {
		return vehicleModelCode;
	}

	public void setVehicleModelCode(String vehicleModelCode) {
		this.vehicleModelCode = vehicleModelCode;
	}

	public String getVehicleModelConfig() {
		return vehicleModelConfig;
	}

	public void setVehicleModelConfig(String vehicleModelConfig) {
		this.vehicleModelConfig = vehicleModelConfig;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDecoration() {
		return decoration;
	}

	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}

	public String getVehicleChassisNumber() {
		return vehicleChassisNumber;
	}

	public void setVehicleChassisNumber(String vehicleChassisNumber) {
		this.vehicleChassisNumber = vehicleChassisNumber;
	}

	public Long getStockAge() {
		return stockAge;
	}

	public void setStockAge(Long stockAge) {
		this.stockAge = stockAge;
	}

	public Long getStockCost() {
		return stockCost;
	}

	public void setStockCost(Long stockCost) {
		this.stockCost = stockCost;
	}

	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public String getContractDate() {
		return contractDate;
	}

	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}

	public Double getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}

	public Double getAdditionalCost() {
		return additionalCost;
	}

	public void setAdditionalCost(Double additionalCost) {
		this.additionalCost = additionalCost;
	}

	public String getComments() {
		return comment;
	}

	public void setComments(String comment) {
		this.comment = comment;
	}

}