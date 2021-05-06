package com.tpss.ThirdPartySupplierSelection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="ROUTE")
public class Route {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="route_id")
    Long routeId;
    @Column(name="from_dest")
    String from;
    @Column(name="to_dest")
    String to;
    @Column(name="destinations")
    String destinations;

    @ManyToOne
    @JoinColumn(name="vehicle_id", referencedColumnName = "vehicle_id", insertable = false, updatable = false)
    @JsonIgnore
    private Vehicle vehicle;

    public Route() {}

    public Route(Long routeId,
		 String from,
		 String to) {
	this.routeId = routeId;
	this.from = from;
	this.to = to;
    }

    public Route(String from,
		 String to,
		 String destinations) {
	this.from = from;
	this.to = to;
	this.destinations = destinations;
    }

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

    public void insertDestination(String destination){
       this.destinations += ";"+destination;
    }

    @Override
    public String toString() {
	return "Route{" +
	", routeId=" + routeId +
	", from='" + from + '\'' +
	", to='" + to + '\''+
	'}';
    }
}
