package com.tpss.ThirdPartySupplierSelection.dto;

import com.tpss.ThirdPartySupplierSelection.entity.Certificate;
import com.tpss.ThirdPartySupplierSelection.entity.Order;
import com.tpss.ThirdPartySupplierSelection.entity.Product;
import com.tpss.ThirdPartySupplierSelection.entity.Vehicle;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class ProviderDTO {
    private Long providerId;
    private String providerName;
    private String providerDesc;
    private Integer foundationYear;
    private Integer numberOfOrders;
    private String operationArea;
    private String reliabilityPercentage;
    private Integer totalVehicleCount;
    private Integer greenPercentage;

    private int[] criteriaPoints = new int[17];

    private Set<Product> products = new HashSet<Product>();

    private Set<Certificate> certificates = new HashSet<Certificate>();

    private Set<Order> orders = new HashSet<Order>();

    private Set<Vehicle> vehicles = new HashSet<Vehicle>();

    public ProviderDTO(){

    }

    public ProviderDTO(Long providerId, String providerName,
		       String providerDesc, Integer foundationYear,
		       Integer numberOfOrders, String operationArea, String reliabilityPercentage,
		       Integer totalVehicleCount, Integer greenPercentage, Set<Product> products,
		       Set<Certificate> certificates, Set<Order> orders, Set<Vehicle> vehicles) {
	this.providerId = providerId;
	this.providerName = providerName;
	this.providerDesc = providerDesc;
	this.foundationYear = foundationYear;
	this.numberOfOrders = numberOfOrders;
	this.operationArea = operationArea;
	this.reliabilityPercentage = reliabilityPercentage;
	this.totalVehicleCount = totalVehicleCount;
	this.greenPercentage = greenPercentage;
	this.products = products;
	this.certificates = certificates;
	this.orders = orders;
	this.vehicles = vehicles;
    }

    public Long getProviderId() {
	return providerId;
    }

    public void setProviderId(Long providerId) {
	this.providerId = providerId;
    }

    public String getProviderName() {
	return providerName;
    }

    public void setProviderName(String providerName) {
	this.providerName = providerName;
    }

    public String getProviderDesc() {
	return providerDesc;
    }

    public void setProviderDesc(String providerDesc) {
	this.providerDesc = providerDesc;
    }

    public Integer getFoundationYear() {
	return foundationYear;
    }

    public void setFoundationYear(Integer foundationYear) {
	this.foundationYear = foundationYear;
    }

    public Integer getNumberOfOrders() {
	return numberOfOrders;
    }

    public void setNumberOfOrders(Integer numberOfOrders) {
	this.numberOfOrders = numberOfOrders;
    }

    public String getOperationArea() {
	return operationArea;
    }

    public void setOperationArea(String operationArea) {
	this.operationArea = operationArea;
    }

    public String getReliabilityPercentage() {
	return reliabilityPercentage;
    }

    public void setReliabilityPercentage(String reliabilityPercentage) {
	this.reliabilityPercentage = reliabilityPercentage;
    }

    public int[] getCriteriaPoints() {
	return criteriaPoints;
    }

    public void setCriteriaPoints(int[] criteriaPoints) {
	this.criteriaPoints = criteriaPoints;
    }

    public Integer getTotalVehicleCount() {
	return totalVehicleCount;
    }

    public void setTotalVehicleCount(Integer totalVehicleCount) {
	this.totalVehicleCount = totalVehicleCount;
    }

    public Integer getGreenPercentage() {
	return greenPercentage;
    }

    public void setGreenPercentage(Integer greenPercentage) {
	this.greenPercentage = greenPercentage;
    }

    public Set<Product> getProducts() {
	return products;
    }

    public void setProducts(Set<Product> products) {
	this.products = products;
    }

    public Set<Certificate> getCertificates() {
	return certificates;
    }

    public void setCertificates(Set<Certificate> certificates) {
	this.certificates = certificates;
    }

    public Set<Order> getOrders() {
	return orders;
    }

    public void setOrders(Set<Order> orders) {
	this.orders = orders;
    }

    public Set<Vehicle> getVehicles() {
	return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
	this.vehicles = vehicles;
    }
}
