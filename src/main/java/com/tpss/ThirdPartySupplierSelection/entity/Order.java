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
    @NonNull @Column(name="spoiled_amount")
    private Integer amountSpoiled;
    @NonNull @Column(name="crushed_amount")
    private Integer amountCrushed;
    @NonNull @Column(name="paid_amount")
    private Integer paidAmount;
    @NonNull @Column(name="currency")
    private String currency;
    @NonNull @Column(name="state")
    private String state;

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
		 Long ownerId,
		 Long providerId,
		 Date promisedArrival,
		 Date actualArrival,
		 Integer amountOrdered,
		 String unit,
		 Integer amountDelivered,
		 Integer amountCrushed,
		 Integer amountSpoiled,
		 Integer paidAmount,
		 String currency) {

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
    }

    public Order(Long ownerId,
		 Long providerId,
		 Date promisedArrival,
		 Date actualArrival,
		 Integer amountOrdered,
		 String unit,
		 Integer amountDelivered,
		 Integer amountCrushed,
		 Integer amountSpoiled,
		 Integer paidAmount,
		 String currency) {

	this.ownerId = ownerId;
	this.providerId = providerId;
	this.promisedArrival = promisedArrival;
	this.actualArrival = actualArrival;
	this.amountOrdered = amountOrdered;
	this.unit = unit;
	this.amountDelivered = amountDelivered;
	this.amountCrushed = amountCrushed;
	this.paidAmount = paidAmount;
	this.amountSpoiled = amountSpoiled;
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

    public Integer getAmountSpoiled() {
	return amountSpoiled;
    }

    public void setAmountSpoiled(Integer amountSpoiled) {
	this.amountSpoiled = amountSpoiled;
    }
    public Integer getAmountCrushed() {
	return amountCrushed;
    }

    public void setAmountCrushed(Integer amountCrushed) {
	this.amountCrushed = amountCrushed;
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

    @NonNull
    public String getState() {
	return state;
    }

    public void setState(@NonNull String state) {
	this.state = state;
    }

    @NonNull
    public Long getOwnerId() {
	return ownerId;
    }

    public void setOwnerId(@NonNull Long ownerId) {
	this.ownerId = ownerId;
    }

    @NonNull
    public Long getProviderId() {
	return providerId;
    }

    public void setProviderId(@NonNull Long providerId) {
	this.providerId = providerId;
    }

    public User getOwner() {
	return owner;
    }

    public void setOwner(User owner) {
	this.owner = owner;
    }

    public Provider getProvider() {
	return provider;
    }

    public void setProvider(Provider provider) {
	this.provider = provider;
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
	", amountSpoiled=" + amountSpoiled +
	", amountCrushed=" + amountCrushed +
	", paidAmount=" + paidAmount +
	", currency='" + currency + '\'' +
	'}';
    }
}
