package com.tpss.ThirdPartySupplierSelection.payload.request;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class AddProviderRequest {
    @NotBlank
    String providerName;

    String providerDesc;

    Integer foundationYear;

    String operationArea;

    String[] productNames;

    /*Double idealTemp;

    String tempUnit;

    Double idealHumidity;

    String humidityUnit;*/

    String[] certNames;

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

    public String getOperationArea() {
	return operationArea;
    }

    public void setOperationArea(String operationArea) {
	this.operationArea = operationArea;
    }

    public String[] getProductNames() {
	return productNames;
    }

    public void setProductNames(String[] productNames) {
	this.productNames = productNames;
    }

    /*public Double getIdealTemp() {
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
    }*/

    public String[] getCertNames() {
	return certNames;
    }

    public void setCertNames(String[] certName) {
	this.certNames = certNames;
    }
}
