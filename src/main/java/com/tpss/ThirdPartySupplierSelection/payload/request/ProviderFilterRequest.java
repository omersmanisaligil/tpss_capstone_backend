package com.tpss.ThirdPartySupplierSelection.payload.request;

import java.util.List;

public class ProviderFilterRequest {
    private String productName;
    private int greenPercentage;
    private List<String> certs;
    private String operationArea;


    public String getProductName() {
	return productName;
    }

    public void setProductName(String productName) {
	this.productName = productName;
    }

    public int getGreenPercentage() {
	return greenPercentage;
    }

    public void setGreenPercentage(int greenPercentage) {
	this.greenPercentage = greenPercentage;
    }

    public List<String> getCerts() {
	return certs;
    }

    public void setCerts(List<String> certs) {
	this.certs = certs;
    }

    public String getOperationArea() {
	return operationArea;
    }

    public void setOperationArea(String operationArea) {
	this.operationArea = operationArea;
    }


}
