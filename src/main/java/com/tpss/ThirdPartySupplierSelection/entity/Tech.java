package com.tpss.ThirdPartySupplierSelection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="TECH")
public class Tech {

    @Id @NonNull @Column(name="tech_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long techId;
    @Column(name="temp_monitor")
    String temperatureMonitoringTech;
    @Column(name="humidity_monitor")
    String humidityMonitoringTech;
    @Column(name="temp_maintaining")
    String tempMaintainingTech;
    @Column(name="humidity_maintaining")
    String humidityMaintainingTech;

    @OneToMany(mappedBy="tech", orphanRemoval=true, cascade=CascadeType.PERSIST)
    @JsonIgnore
    private Set<Vehicle> vehicles = new HashSet<Vehicle>();

    public Tech(){}

    public Tech(Long techId,
		String temperatureMonitoringTech,
		String humidityMonitoringTech,
		String tempMaintainingTech,
		String humidityMaintainingTech) {
	this.techId = techId;
	this.temperatureMonitoringTech = temperatureMonitoringTech;
	this.humidityMonitoringTech = humidityMonitoringTech;
	this.tempMaintainingTech = tempMaintainingTech;
	this.humidityMaintainingTech = humidityMaintainingTech;
    }

    public Tech(String temperatureMonitoringTech,
		String humidityMonitoringTech,
		String tempMaintainingTech,
		String humidityMaintainingTech) {
	this.temperatureMonitoringTech = temperatureMonitoringTech;
	this.humidityMonitoringTech = humidityMonitoringTech;
	this.tempMaintainingTech = tempMaintainingTech;
	this.humidityMaintainingTech = humidityMaintainingTech;
    }

    @NonNull
    public Long getTechId() {
	return techId;
    }

    public void setTechId(@NonNull Long techId) {
	this.techId = techId;
    }

    public Long getVehicleId() {
	return techId;
    }

    public void setVehicleId(Long vehicleId) {
	this.techId = vehicleId;
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

    public void insertVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    @Override
    public String toString() {
	return "Tech{" +
	"techId=" + techId +
	", temperatureMonitoringTech='" + temperatureMonitoringTech + '\'' +
	", humidityMonitoringTech='" + humidityMonitoringTech + '\'' +
	", tempMaintainingTech='" + tempMaintainingTech + '\'' +
	", humidityMaintainingTech='" + humidityMaintainingTech + '\'' +
	'}';
    }
}
