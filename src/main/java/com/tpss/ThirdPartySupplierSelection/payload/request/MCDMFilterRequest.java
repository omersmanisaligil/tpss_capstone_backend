package com.tpss.ThirdPartySupplierSelection.payload.request;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestParam;

public class MCDMFilterRequest {

    @NonNull
    private Long amount;
    @NonNull
    private String unit;
    @NonNull
    private String productName;
    @NonNull
    private String arrivalDate;
    @NonNull
    private String location;
    private int greenPercentage;
    private String[] certs;
    private String operationArea;

    @NonNull
    public Long getAmount() {
	return amount;
    }

    public void setAmount(@NonNull Long amount) {
	this.amount = amount;
    }

    @NonNull
    public String getUnit() {
	return unit;
    }

    public void setUnit(@NonNull String unit) {
	this.unit = unit;
    }

    @NonNull
    public String getProductName() {
	return productName;
    }

    public void setProductName(@NonNull String productName) {
	this.productName = productName;
    }

    @NonNull
    public String getArrivalDate() {
	return arrivalDate;
    }

    public void setArrivalDate(@NonNull String arrivalDate) {
	this.arrivalDate = arrivalDate;
    }

    @NonNull
    public String getLocation() {
	return location;
    }

    public void setLocation(@NonNull String location) {
	this.location = location;
    }

    public int getGreenPercentage() {
	return greenPercentage;
    }

    public void setGreenPercentage(int greenPercentage) {
	this.greenPercentage = greenPercentage;
    }

    public String[] getCerts() {
	return certs;
    }

    public void setCerts(String[] certs) {
	this.certs = certs;
    }

    public String getOperationArea() {
	return operationArea;
    }

    public void setOperationArea(String operationArea) {
	this.operationArea = operationArea;
    }
}
