package com.tpss.ThirdPartySupplierSelection.payload.request;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class AddProviderRequest {
    @NotBlank
    String providerName;

    String providerDesc;

    Integer foundationYear;

    Integer numberOfOrders;

    String operationArea;

    String productName;

    Double idealTemp;

    String tempUnit;

    Double idealHumidity;

    String humidityUnit;

    String certName;

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

    public String getProductName() {
	return productName;
    }

    public void setProductName(String productName) {
	this.productName = productName;
    }

    public Double getIdealTemp() {
	return idealTemp;
    }

    public void setIdealTemp(Double idealTemp) {
	this.idealTemp = idealTemp;
    }

    public String getTempUnit() {
	return tempUnit;
    }

    public void setTempUnit(String tempUnit) {
	this.tempUnit = tempUnit;
    }

    public Double getIdealHumidity() {
	return idealHumidity;
    }

    public void setIdealHumidity(Double idealHumidity) {
	this.idealHumidity = idealHumidity;
    }

    public String getHumidityUnit() {
	return humidityUnit;
    }

    public void setHumidityUnit(String humidityUnit) {
	this.humidityUnit = humidityUnit;
    }

    public String getCertName() {
	return certName;
    }

    public void setCertName(String certName) {
	this.certName = certName;
    }
}
