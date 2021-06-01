package com.tpss.ThirdPartySupplierSelection.dto;

import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import com.tpss.ThirdPartySupplierSelection.entity.Route;
import com.tpss.ThirdPartySupplierSelection.entity.Tech;

import java.util.HashSet;
import java.util.Set;

public class VehicleDTO {

    private Long vehicleId;
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
    private Integer accidentCount;

    private Tech tech;

    private Set<Route> routes = new HashSet<Route>();

    private Provider provider;

    public VehicleDTO() { }

    public VehicleDTO(Long vehicleId, Long providerID, String vehicleType,
		      String vehicleModel, Integer vehicleCapacity,
		      String capacityUnit, Integer areaCoverage,
		      String coverageUnit, Integer lowestTemp,
		      Integer highestTemp, String tempUnit,
		      Integer lowestHumidity, Integer highestHumidity,
		      Integer accidentCount,String humidityUnit) {
	this.vehicleId = vehicleId;
	this.providerID = providerID;
	this.vehicleType = vehicleType;
	this.vehicleModel = vehicleModel;
	this.vehicleCapacity = vehicleCapacity;
	this.capacityUnit = capacityUnit;
	this.areaCoverage = areaCoverage;
	this.coverageUnit = coverageUnit;
	this.lowestTemp = lowestTemp;
	this.highestTemp = highestTemp;
	this.tempUnit = tempUnit;
	this.accidentCount = accidentCount;
	this.lowestHumidity = lowestHumidity;
	this.highestHumidity = highestHumidity;
	this.humidityUnit = humidityUnit;
    }

    public VehicleDTO(Long vehicleId, Long providerID, String vehicleType,
		      String vehicleModel, Integer vehicleCapacity,
		      String capacityUnit, Integer areaCoverage,
		      String coverageUnit, Integer lowestTemp,
		      Integer highestTemp, String tempUnit,
		      Integer lowestHumidity, Integer highestHumidity,
		      String humidityUnit, Tech tech,
		      Set<Route> routes, Provider provider) {
	this.vehicleId = vehicleId;
	this.providerID = providerID;
	this.vehicleType = vehicleType;
	this.vehicleModel = vehicleModel;
	this.vehicleCapacity = vehicleCapacity;
	this.capacityUnit = capacityUnit;
	this.areaCoverage = areaCoverage;
	this.coverageUnit = coverageUnit;
	this.lowestTemp = lowestTemp;
	this.highestTemp = highestTemp;
	this.tempUnit = tempUnit;
	this.lowestHumidity = lowestHumidity;
	this.highestHumidity = highestHumidity;
	this.humidityUnit = humidityUnit;
	this.tech = tech;
	this.routes = routes;
	this.provider = provider;
    }

    public Long getVehicleId() {
	return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
	this.vehicleId = vehicleId;
    }

    public Long getProviderID() {
	return providerID;
    }

    public void setProviderID(Long providerID) {
	this.providerID = providerID;
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

    public Tech getTech() {
	return tech;
    }

    public void setTech(Tech tech) {
	this.tech = tech;
    }

    public Set<Route> getRoutes() {
	return routes;
    }

    public void setRoutes(Set<Route> routes) {
	this.routes = routes;
    }

    public Provider getProvider() {
	return provider;
    }

    public void setProvider(Provider provider) {
	this.provider = provider;
    }

    public Integer getAccidentCount() {
	return accidentCount;
    }

    public void setAccidentCount(Integer accidentCount) {
	this.accidentCount = accidentCount;
    }
}
