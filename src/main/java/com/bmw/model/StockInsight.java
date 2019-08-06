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
	private String licensePlate;
	private String storageDate;
	private String productionDate;
	private String vehicleSeriesCode;
	private String vehicleModelCode;
	private String vehicleModelConfig;
	private String model;
	private String color;
	private String decoration;
	private String vehicleChassisNumber;

	// 库存年龄
	private Long stockAge;
	// 存放成本
	private Long stockCost;

	public StockInsight(Stock s) {
		this.dealerId = s.getDealerId();
		this.regionId = s.getRegionId();
		this.groupId = s.getGroupId();
		
		this.stockId = s.getStockId();
		this.licensePlate = s.getLicensePlate();
		this.storageDate = s.getStorageDate();
		this.productionDate = s.getProductionDate();
		this.vehicleSeriesCode = s.getVehicleSeriesCode();
		this.vehicleModelCode = s.getVehicleModelCode();
		this.vehicleModelConfig = s.getVehicleModelConfig();
		this.model = s.getModel();
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

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

}