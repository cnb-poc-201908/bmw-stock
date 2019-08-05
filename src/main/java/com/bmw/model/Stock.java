package com.bmw.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {
	//经销商ID
	private String dealerId;
	//区域ID
	private String regionId;
	//集团ID
	private String groupId;
	//库存ID
	private String stockId;

	private String productionDate;
	private String vehicleSeriesCode;
	private String vehicleModelCode;
	private String vehicleModelConfig;
	private String model;
	private String color;
	private String decoration;
	private String vehicleChassisNumber;
	private String status;
	private Boolean isBelongTo;

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
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getIsBelongTo() {
		return isBelongTo;
	}
	public void setIsBelongTo(Boolean isBelongTo) {
		this.isBelongTo = isBelongTo;
	}
}