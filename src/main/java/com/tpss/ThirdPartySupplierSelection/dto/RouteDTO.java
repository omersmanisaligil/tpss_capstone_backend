package com.tpss.ThirdPartySupplierSelection.dto;

import com.tpss.ThirdPartySupplierSelection.entity.Vehicle;

public class RouteDTO {

    Long routeId;
    String from;
    String to;
    String destinations;

    private Vehicle vehicle;


    public Long getRouteId() {
	return routeId;
    }

    public void setRouteId(Long routeId) {
	this.routeId = routeId;
    }

    public String getFrom() {
	return from;
    }

    public void setFrom(String from) {
	this.from = from;
    }

    public String getTo() {
	return to;
    }

    public void setTo(String to) {
	this.to = to;
    }

    public String getDestinations() {
	return destinations;
    }

    public void setDestinations(String destinations) {
	this.destinations = destinations;
    }

    public Vehicle getVehicle() {
	return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
	this.vehicle = vehicle;
    }
}
