package com.tpss.ThirdPartySupplierSelection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="PRODUCT")
public class Product {

    @Id @NonNull
    @Column(name="product_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productId;
    @NonNull @Column(name="product_name")
    String productName;
    //TODO: bu ideal temp ve humidity bir aralık olabilir bunu hesaba katmalıyız
    @NonNull @Column(name="temp_ideal")
    Double idealTemp;
    @NonNull @Column(name="temp_unit")
    String tempUnit;
    @NonNull @Column(name="humidity_ideal")
    Double idealHumidity;
    @NonNull @Column(name="humidity_unit")
    String humidityUnit;

    @ManyToMany(mappedBy="products", cascade=CascadeType.PERSIST)
    private Set<Provider> providers = new HashSet<Provider>();

    public Product(){}

    public Product(Long productId,
		   String productName,
		   Double idealTemp,
		   String tempUnit,
		   Double idealHumidity,
		   String humidityUnit) {
	this.productId = productId;
	this.productName = productName;
	this.idealTemp = idealTemp;
	this.tempUnit = tempUnit;
	this.idealHumidity = idealHumidity;
	this.humidityUnit = humidityUnit;
    }

    public Product(String productName,
		   Double idealTemp,
		   String tempUnit,
		   Double idealHumidity,
		   String humidityUnit) {
	this.productName = productName;
	this.idealTemp = idealTemp;
	this.tempUnit = tempUnit;
	this.idealHumidity = idealHumidity;
	this.humidityUnit = humidityUnit;
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

    @NonNull
    public Double getIdealHumidity() {
	return idealHumidity;
    }

    public void setIdealHumidity(@NonNull Double idealHumidity) {
	this.idealHumidity = idealHumidity;
    }

    @NonNull
    public String getHumidityUnit() {
	return humidityUnit;
    }

    public void setHumidityUnit(@NonNull String humidityUnit) {
	this.humidityUnit = humidityUnit;
    }
    @Override
    public String toString() {
	return "Product{" +
	"productId=" + productId +
	", productName='" + productName + '\'' +
	", idealTemp=" + idealTemp +
	", tempUnit='" + tempUnit + '\'' +
	", idealHumidity=" + idealHumidity +
	'}';
    }
    public void insertProvider(Provider provider){
        this.providers.add(provider);
    }
}
