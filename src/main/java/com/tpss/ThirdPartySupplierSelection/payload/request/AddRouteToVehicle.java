package com.tpss.ThirdPartySupplierSelection.payload.request;

import org.springframework.lang.NonNull;

import javax.persistence.Column;

public class AddRouteToVehicle {

    @NonNull
    private Long vehicleId;

    private String from;

    private String to;

    private String[] destinations;

    @NonNull
    public Long getVehicleId() {
	return vehicleId;
    }

    public void setVehicleId(@NonNull Long vehicleId) {
	this.vehicleId = vehicleId;
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

    public String[] getDestinations() {
	return destinations;
    }

    public void setDestinations(String[] destinations) {
	this.destinations = destinations;
    }
}
