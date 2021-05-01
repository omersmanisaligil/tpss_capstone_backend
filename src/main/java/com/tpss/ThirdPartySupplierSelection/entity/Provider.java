package com.tpss.ThirdPartySupplierSelection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="PROVIDER")
public class Provider {

    @Id @Column(name="provider_id")
    @NonNull @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long providerId;
    @NonNull @Column(name="provider_name")
    String providerName;
    @NonNull @Column(name="provider_desc")
    String providerDesc;
    @NonNull @Column(name="foundation_year")
    Integer foundationYear;
    @NonNull @Column(name="number_of_orders")
    Integer numberOfOrders;
    @NonNull @Column(name="operation_area")
    String operationArea;
    /*TODO bu üçünü entity değil DTO classlarına dahil edebilirim veya bir query/relation mantığıyla da buraya çekebilirm*/
    @Transient
    String reliabilityPercentage;
    @Transient
    Integer totalVehicleCount;
    @Transient
    Integer greenVehicleCount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name="PROVIDER_PRODUCT_MAP",
    joinColumns = @JoinColumn(name = "provider_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<Product>();

    @ManyToMany
    @JoinTable(
    name="CERT_PROVIDER_MAP",
    joinColumns = @JoinColumn(name = "provider_id"),
    inverseJoinColumns = @JoinColumn(name = "cert_id"))
    private Set<Certificate> certificates = new HashSet<Certificate>();

    @OneToMany(mappedBy="provider", orphanRemoval = true, cascade=CascadeType.PERSIST)
    private Set<Order> orders = new HashSet<Order>();

    @OneToMany(mappedBy = "provider", orphanRemoval = true, cascade=CascadeType.PERSIST)
    private Set<Vehicle> vehicles = new HashSet<Vehicle>();

    public Provider() {}

    public Provider(Long providerId,
		    String providerName,
		    String providerDesc,
		    Integer foundationYear,
		    Integer numberOfOrders,
		    String operationArea) {
	this.providerId = providerId;
	this.providerName = providerName;
	this.providerDesc = providerDesc;
	this.foundationYear = foundationYear;
	this.numberOfOrders = numberOfOrders;
	this.operationArea = operationArea;
    }

    public Provider(String providerName,
		    String providerDesc,
		    Integer foundationYear,
		    Integer numberOfOrders,
		    String operationArea) {
	this.providerName = providerName;
	this.providerDesc = providerDesc;
	this.foundationYear = foundationYear;
	this.numberOfOrders = numberOfOrders;
	this.operationArea = operationArea;
    }

    public Provider(Long providerId,
		    String providerName,
		    String providerDesc,
		    Integer foundationYear,
		    Integer numberOfOrders,
		    String operationArea,
		    String reliabilityPercentage,
		    Integer totalVehicleCount,
		    Integer greenVehicleCount) {
	this.providerId = providerId;
	this.providerName = providerName;
	this.providerDesc = providerDesc;
	this.foundationYear = foundationYear;
	this.numberOfOrders = numberOfOrders;
	this.operationArea = operationArea;
	this.reliabilityPercentage = reliabilityPercentage;
	this.totalVehicleCount = totalVehicleCount;
	this.greenVehicleCount = greenVehicleCount;
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

    public Integer getTotalVehicleCount() {
	return totalVehicleCount;
    }

    public void setTotalVehicleCount(Integer totalVehicleCount) {
	this.totalVehicleCount = totalVehicleCount;
    }

    public Integer getGreenVehicleCount() {
	return greenVehicleCount;
    }

    public void setGreenVehicleCount(Integer greenVehicleCount) {
	this.greenVehicleCount = greenVehicleCount;
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

    public void insertProduct(Product product){
        this.products.add(product);
    }

    public void insertCertificates(Certificate certificate){
        this.certificates.add(certificate);
    }

    public void insertOrder(Order order){
        this.orders.add(order);
    }

    public void insertVehicle(Vehicle vehicle){
        this.vehicles.add(vehicle);
    }

    @Override
    public String toString() {
	return "Provider{" +
	"providerId=" + providerId +
	", providerName='" + providerName + '\'' +
	", providerDesc='" + providerDesc + '\'' +
	", foundationYear=" + foundationYear +
	", numberOfOrders=" + numberOfOrders +
	", operationArea='" + operationArea + '\'' +
	", reliabilityPercentage='" + reliabilityPercentage + '\'' +
	", totalVehicleCount=" + totalVehicleCount +
	", greenVehicleCount=" + greenVehicleCount +
	'}';
    }
}
