package com.tpss.ThirdPartySupplierSelection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ORDERS")
public class Order {

    @Id @Column(name="order_id")
    @NonNull @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @NonNull @Column(name="owner_id")
    private Long ownerId;
    @NonNull @Column(name="provider_id")
    private Long providerId;
    @NonNull @Column(name="promised_arrival")
    private Date promisedArrival;
    @NonNull @Column(name="actual_arrival")
    private Date actualArrival;
    @NonNull @Column(name="amount_ordered")
    private Integer amountOrdered;
    @NonNull @Column(name="unit_order")
    private String unit;
    @NonNull @Column(name="delivered_amount")
    private Integer amountDelivered;
    @NonNull @Column(name="lost_amount")
    private Integer amountLost;
    @NonNull @Column(name="paid_amount")
    private Integer paidAmount;
    @NonNull @Column(name="currency")
    private String currency;

    @ManyToOne
    @JoinColumn(name="owner_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User owner;

    @ManyToOne
    @JoinColumn(name="provider_id", referencedColumnName = "provider_id", insertable = false,updatable = false)
    @JsonIgnore
    private Provider provider;

    public Order(){}

    public Order(Long orderId,
		 Date promisedArrival,
		 Date actualArrival,
		 Integer amountOrdered,
		 String unit,
		 Integer amountDelivered,
		 Integer amountLost,
		 Integer paidAmount,
		 String currency) {

	this.orderId = orderId;
	this.promisedArrival = promisedArrival;
	this.actualArrival = actualArrival;
	this.amountOrdered = amountOrdered;
	this.unit = unit;
	this.amountDelivered = amountDelivered;
	this.amountLost = amountLost;
	this.paidAmount = paidAmount;
	this.currency = currency;
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

    public Integer getAmountLost() {
	return amountLost;
    }

    public void setAmountLost(Integer amountLost) {
	this.amountLost = amountLost;
    }

    public Integer getPaidAmount() {
	return paidAmount;
    }

    public void setPaidAmount(Integer paidAmount) {
	this.paidAmount = paidAmount;
    }

    public Date getPromisedArrival() {
	return promisedArrival;
    }

    public void setPromisedArrival(Date promisedArrival) {
	this.promisedArrival = promisedArrival;
    }

    public String getCurrency() {
	return currency;
    }

    public void setCurrency(String currency) {
	this.currency = currency;
    }

    public Long getOrderId() {
	return orderId;
    }

    public void setOrderId(Long orderId) {
	this.orderId = orderId;
    }

    @Override
    public String toString() {
	return "Order{" +
	", orderId=" + orderId +
	", promisedArrival=" + promisedArrival +
	", actualArrival=" + actualArrival +
	", amountOrdered=" + amountOrdered +
	", unit='" + unit + '\'' +
	", amountDelivered=" + amountDelivered +
	", amountLost=" + amountLost +
	", paidAmount=" + paidAmount +
	", currency='" + currency + '\'' +
	'}';
    }
}
