package com.tpss.ThirdPartySupplierSelection.dto;

import com.tpss.ThirdPartySupplierSelection.entity.Vehicle;

import java.util.HashSet;
import java.util.Set;

public class TechDTO {

    Long techId;
    String temperatureMonitoringTech;
    String humidityMonitoringTech;
    String tempMaintainingTech;
    String humidityMaintainingTech;


    private Set<Vehicle> vehicles = new HashSet<Vehicle>();

    public Long getTechId() {
	return techId;
    }

    public void setTechId(Long techId) {
	this.techId = techId;
    }

    public String getTemperatureMonitoringTech() {
	return temperatureMonitoringTech;
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

    public Set<Vehicle> getVehicles() {
	return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
	this.vehicles = vehicles;
    }
}
