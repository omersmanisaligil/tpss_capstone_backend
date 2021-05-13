package com.tpss.ThirdPartySupplierSelection.payload.request;

import org.springframework.lang.NonNull;

public class ProviderOrderRequest extends ProviderFilterRequest {
    @NonNull
    private String arrivalDate;
    @NonNull
    private String orderDate;
    @NonNull
    private Long amount;
    @NonNull
    private String unit;
    @NonNull
    private String deliveryLocation;

    @NonNull
    public Long getAmount() {
	return amount;
    }

    public void setAmount(@NonNull Long amount) {
	this.amount = amount;
    }

    @NonNull
    public String getDeliveryLocation() {
	return deliveryLocation;
    }

    public void setDeliveryLocation(@NonNull String location) {
	this.deliveryLocation = deliveryLocation;
    }

    @NonNull
    public String getUnit() {
	return unit;
    }

    public void setUnit(@NonNull String unit) {
	this.unit = unit;
    }

    @NonNull
    public String getArrivalDate() {
	return arrivalDate;
    }

    public void setArrivalDate(@NonNull String arrivalDate) {
	this.arrivalDate = arrivalDate;
    }

    @NonNull
    public String getOrderDate() {
	return orderDate;
    }

    public void setOrderDate(@NonNull String orderDate) {
	this.orderDate = orderDate;
    }

}
