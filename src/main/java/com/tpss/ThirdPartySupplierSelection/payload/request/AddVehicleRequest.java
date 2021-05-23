package com.tpss.ThirdPartySupplierSelection.payload.request;

import org.springframework.lang.NonNull;

import javax.persistence.Column;

public class AddVehicleRequest {

    @NonNull
    private String providerName;

    private Long providerID;

    private String vehicleType;

    private String vehicleModel;

    private Integer vehicleCapacity;

    private String capacityUnit;

    private Integer areaCoverage;

    private String coverageUnit;

    private Integer lowestTemp;

    private Integer highestTemp;

    private String tempUnit;

    private Integer lowestHumidity;

    private Integer highestHumidity;

    private String humidityUnit;

    private String temperatureMonitoringTech;

    private String humidityMonitoringTech;

    private String tempMaintainingTech;

    private String humidityMaintainingTech;

    private String fuelType;

    @NonNull
    public String getProviderName() {
	return providerName;
    }

    public Long getProviderID() {
	return providerID;
    }

    public void setProviderID(Long providerID) {
	this.providerID = providerID;
    }

    public void setProviderName(@NonNull String providerName) {
	this.providerName = providerName;
    }

    public String getVehicleType() {
	return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
	this.vehicleType = vehicleType;
    }

    public String getVehicleModel() {
	return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
	this.vehicleModel = vehicleModel;
    }

    public Integer getVehicleCapacity() {
	return vehicleCapacity;
    }

    public void setVehicleCapacity(Integer vehicleCapacity) {
	this.vehicleCapacity = vehicleCapacity;
    }

    public String getCapacityUnit() {
	return capacityUnit;
    }

    public void setCapacityUnit(String capacityUnit) {
	this.capacityUnit = capacityUnit;
    }

    public Integer getAreaCoverage() {
	return areaCoverage;
    }

    public void setAreaCoverage(Integer areaCoverage) {
	this.areaCoverage = areaCoverage;
    }

    public String getCoverageUnit() {
	return coverageUnit;
    }

    public void setCoverageUnit(String coverageUnit) {
	this.coverageUnit = coverageUnit;
    }

    public Integer getLowestTemp() {
	return lowestTemp;
    }

    public void setLowestTemp(Integer lowestTemp) {
	this.lowestTemp = lowestTemp;
    }

    public Integer getHighestTemp() {
	return highestTemp;
    }

    public void setHighestTemp(Integer highestTemp) {
	this.highestTemp = highestTemp;
    }

    public String getTempUnit() {
	return tempUnit;
    }

    public void setTempUnit(String tempUnit) {
	this.tempUnit = tempUnit;
    }

    public Integer getLowestHumidity() {
	return lowestHumidity;
    }

    public void setLowestHumidity(Integer lowestHumidity) {
	this.lowestHumidity = lowestHumidity;
    }

    public Integer getHighestHumidity() {
	return highestHumidity;
    }

    public void setHighestHumidity(Integer highestHumidity) {
	this.highestHumidity = highestHumidity;
    }

    public String getHumidityUnit() {
	return humidityUnit;
    }

    public void setHumidityUnit(String humidityUnit) {
	this.humidityUnit = humidityUnit;
    }

    public String getTemperatureMonitoringTech() {
	return temperatureMonitoringTech;
    }

    public String getFuelType() {
	return fuelType;
    }

    public void setFuelType(String fuelType) {
	this.fuelType = fuelType;
    }

    public void setTemperatureMonitoringTech(String temperatureMonitoringTech) {
	this.temperatureMonitoringTech = temperatureMonitoringTech;
    }

    public String getHumidityMonitoringTech() {
	return humidityMonitoringTech;
    }

    public void setHumidityMonitoringTech(String humidityMonitoringTech) {
	this.humidityMonitoringTech = humidityMonitoringTech;
    }

    public String getTempMaintainingTech() {
	return tempMaintainingTech;
    }

    public void setTempMaintainingTech(String tempMaintainingTech) {
	this.tempMaintainingTech = tempMaintainingTech;
    }

    public String getHumidityMaintainingTech() {
	return humidityMaintainingTech;
    }

    public void setHumidityMaintainingTech(String humidityMaintainingTech) {
	this.humidityMaintainingTech = humidityMaintainingTech;
    }
}
