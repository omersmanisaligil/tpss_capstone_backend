package com.tpss.ThirdPartySupplierSelection.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

public class ProductDTO {

    Long productId;
    String productName;

    //TODO: bu ideal temp ve humidity bir aralık olabilir bunu hesaba katmalı
    Double idealTemp;
    String tempUnit;
    Double idealHumidity;
    String humidityUnit;

    private Set<Provider> providers = new HashSet<Provider>();

    public ProductDTO(){}

    public ProductDTO(Long productId, String productName,
		      Double idealTemp, String tempUnit, Double idealHumidity,
		      String humidityUnit, Set<Provider> providers) {
	this.productId = productId;
	this.productName = productName;
	this.idealTemp = idealTemp;
	this.tempUnit = tempUnit;
	this.idealHumidity = idealHumidity;
	this.humidityUnit = humidityUnit;
	this.providers = providers;
    }

    public Long getProductId() {
	return productId;
    }

    public void setProductId(Long productId) {
	this.productId = productId;
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

    public Set<Provider> getProviders() {
	return providers;
    }

    public void setProviders(Set<Provider> providers) {
	this.providers = providers;
    }
}
