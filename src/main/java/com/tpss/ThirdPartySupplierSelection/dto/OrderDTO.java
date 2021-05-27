package com.tpss.ThirdPartySupplierSelection.dto;

import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import com.tpss.ThirdPartySupplierSelection.entity.User;

import java.util.Date;

public class OrderDTO {

    private Long orderId;
    private Long ownerId;
    private Long providerId;
    private Date promisedArrival;
    private Date actualArrival;
    private Integer amountOrdered;
    private String unit;
    private Integer amountDelivered;
    private Integer amountCrushed;
    private Integer amountSpoiled;
    private Integer totalLoss;
    private Integer paidAmount;
    private String currency;
    private String state;

    private User owner;

    private Provider provider;

    public OrderDTO(){}

    public OrderDTO(Long orderId, Long ownerId, Long providerId,
		    Date promisedArrival, Date actualArrival,
		    Integer amountOrdered, String unit,
		    Integer amountDelivered,Integer amountCrushed,
		    Integer paidAmount, String currency,
		    String state,User owner, Provider provider) {
	this.orderId = orderId;
	this.ownerId = ownerId;
	this.providerId = providerId;
	this.promisedArrival = promisedArrival;
	this.actualArrival = actualArrival;
	this.amountOrdered = amountOrdered;
	this.unit = unit;
	this.amountDelivered = amountDelivered;
	this.amountSpoiled = amountSpoiled;
	this.amountCrushed = amountCrushed;
	this.paidAmount = paidAmount;
	this.currency = currency;
	this.state = state;
	this.owner = owner;
	this.provider = provider;
    }

    public Long getOrderId() {
	return orderId;
    }

    public void setOrderId(Long orderId) {
	this.orderId = orderId;
    }

    public Long getOwnerId() {
	return ownerId;
    }

    public void setOwnerId(Long ownerId) {
	this.ownerId = ownerId;
    }

    public Long getProviderId() {
	return providerId;
    }

    public void setProviderId(Long providerId) {
	this.providerId = providerId;
    }

    public Date getPromisedArrival() {
	return promisedArrival;
    }

    public void setPromisedArrival(Date promisedArrival) {
	this.promisedArrival = promisedArrival;
    }

    public Date getActualArrival() {
	return actualArrival;
    }

    public void setActualArrival(Date actualArrival) {
	this.actualArrival = actualArrival;
    }

    public Integer getAmountOrdered() {
	return amountOrdered;
    }

    public void setAmountOrdered(Integer amountOrdered) {
	this.amountOrdered = amountOrdered;
    }

    public String getUnit() {
	return unit;
    }

    public void setUnit(String unit) {
	this.unit = unit;
    }

    public Integer getAmountDelivered() {
	return amountDelivered;
    }

    public void setAmountDelivered(Integer amountDelivered) {
	this.amountDelivered = amountDelivered;
    }

    public Integer getAmountCrushed() {
	return amountCrushed;
    }

    public void setAmountCrushed(Integer amountCrushed) {
	this.amountCrushed = amountCrushed;
    }

    public Integer getAmountSpoiled() {
	return amountSpoiled;
    }

    public void setAmountSpoiled(Integer amountSpoiled) {
	this.amountSpoiled = amountSpoiled;
    }

    public Integer getPaidAmount() {
	return paidAmount;
    }

    public void setPaidAmount(Integer paidAmount) {
	this.paidAmount = paidAmount;
    }

    public String getCurrency() {
	return currency;
    }

    public void setCurrency(String currency) {
	this.currency = currency;
    }

    public User getOwner() {
	return owner;
    }

    public void setOwner(User owner) {
	this.owner = owner;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public Provider getProvider() {
	return provider;
    }

    public void setProvider(Provider provider) {
	this.provider = provider;
    }

    public Integer getTotalLoss() {
	return totalLoss;
    }

    public void setTotalLoss(Integer totalLoss) {
	this.totalLoss = totalLoss;
    }
}
