package com.tpss.ThirdPartySupplierSelection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="VEHICLE")
public class Vehicle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vehicle_id")
    private Long vehicleId;
    @NonNull @Column(name="provider_id")
    private Long providerID;
    @Column(name="vehicle_type")
    private String vehicleType;
    @Column(name="vehicle_model")
    private String vehicleModel;
    @Column(name="vehicle_capacity")
    private Integer vehicleCapacity;
    @Column(name="capacity_unit")
    private String capacityUnit;
    @Column(name="area_coverage")
    private Integer areaCoverage;
    @Column(name="coverage_unit")
    private String coverageUnit;
    @Column(name="temperature_lowest")
    private Integer lowestTemp;
    @Column(name="temperature_highest")
    private Integer highestTemp;
    @Column(name="temperature_unit")
    private String tempUnit;
    @Column(name="humidity_lowest")
    private Integer lowestHumidity;
    @Column(name="humidity_highest")
    private Integer highestHumidity;
    @Column(name="humidity_unit")
    private String humidityUnit;
    @Column(name="fuel_type")
    private String fuelType;
    @Column(name="accident_count")
    private Integer accidentCount;

    @ManyToOne
    @JoinColumn(name="tech_id", referencedColumnName="tech_id", insertable=false, updatable=false)
    @JsonIgnore
    private Tech tech;

    @OneToMany(mappedBy = "vehicle", orphanRemoval = true, cascade=CascadeType.PERSIST )
    @JsonIgnore
    private Set<Route> routes = new HashSet<Route>();

    @ManyToOne
    @JoinColumn(name="provider_id", referencedColumnName="provider_id", insertable = false, updatable = false)
    @JsonIgnore
    private Provider provider;

    public Vehicle(){}

    public Vehicle(Long vehicleId,
		   String vehicleType,
		   String vehicleModel,
		   Integer vehicleCapacity,
		   String unit,
		   Integer areaCoverage,
		   String coverageUnit,
		   Integer lowestTemp,
		   Integer highestTemp,
		   String tempUnit,
		   Integer lowestHumidity,
		   Integer highestHumidity,
		   String humidityUnit,
		   String fuelType) {
	this.vehicleId = vehicleId;
	this.vehicleType = vehicleType;
	this.vehicleModel = vehicleModel;
	this.vehicleCapacity = vehicleCapacity;
	this.capacityUnit = unit;
	this.areaCoverage = areaCoverage;
	this.coverageUnit = coverageUnit;
	this.lowestTemp = lowestTemp;
	this.highestTemp = highestTemp;
	this.tempUnit = tempUnit;
	this.lowestHumidity = lowestHumidity;
	this.highestHumidity = highestHumidity;
	this.humidityUnit = humidityUnit;
	this.fuelType = fuelType;
    }

    public Vehicle(String vehicleType,
		   String vehicleModel,
		   Integer vehicleCapacity,
		   String unit,
		   Integer areaCoverage,
		   String coverageUnit,
		   Integer lowestTemp,
		   Integer highestTemp,
		   String tempUnit,
		   Integer lowestHumidity,
		   Integer highestHumidity,
		   String humidityUnit,
		   String fuelType) {
	this.vehicleType = vehicleType;
	this.vehicleModel = vehicleModel;
	this.vehicleCapacity = vehicleCapacity;
	this.capacityUnit = unit;
	this.areaCoverage = areaCoverage;
	this.coverageUnit = coverageUnit;
	this.lowestTemp = lowestTemp;
	this.highestTemp = highestTemp;
	this.tempUnit = tempUnit;
	this.lowestHumidity = lowestHumidity;
	this.highestHumidity = highestHumidity;
	this.humidityUnit = humidityUnit;
	this.fuelType = fuelType;
    }

    public Tech getTech() {
	return tech;
    }

    public void setTech(Tech tech) {
	this.tech = tech;
    }

    public Long getVehicleId() {
	return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
	this.vehicleId = vehicleId;
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

    public void setCapacityUnit(String unit) {
	this.capacityUnit = unit;
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

    public Integer getAccidentCount() {
	return accidentCount;
    }

    public void setAccidentCount(Integer accidentCount) {
	this.accidentCount = accidentCount;
    }

    @NonNull
    public Long getProviderID() {
	return providerID;
    }

    public void setProviderID(@NonNull Long providerID) {
	this.providerID = providerID;
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

    public void setProviderCompany(Provider provider) {
	this.provider = provider;
    }

    public void insertRoute(Route route){
        this.routes.add(route);
    }

    public String getFuelType() {
	return fuelType;
    }

    public void setFuelType(String fuelType) {
	this.fuelType = fuelType;
    }

    public void setProvider(Provider provider) {
	this.provider = provider;
    }

    @Override
    public String toString() {
	return "Vehicle{" +
	", vehicleId=" + vehicleId +
	", vehicleType='" + vehicleType + '\'' +
	", vehicleModel='" + vehicleModel + '\'' +
	", vehicleCapacity=" + vehicleCapacity +
	", capacityUnit='" + capacityUnit + '\'' +
	", areaCoverage='" + areaCoverage + '\'' +
	", coverageUnit=" + coverageUnit +
	", lowestTemp=" + lowestTemp +
	", highestTemp=" + highestTemp +
	", tempUnit='" + tempUnit + '\'' +
	", lowestHumidity=" + lowestHumidity +
	", highestHumidity=" + highestHumidity +
	", humidityUnit='" + humidityUnit + '\'' +
	", fuelType='" + fuelType + '\'' +
	'}';
    }
}
