package com.tpss.ThirdPartySupplierSelection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="CERTIFICATE")
public class Certificate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cert_id")
    Long certId;

    @NonNull @Column(name="cert_name")
    String certName;

    public Certificate(String certName){
        this.certName=certName;
    }
    public Certificate(){}

    @ManyToMany(mappedBy = "certificates", cascade=CascadeType.PERSIST)
    private Set<Provider> providers = new HashSet<Provider>();

    @NonNull
    public String getCertName() {
	return certName;
    }

    public void setCertName(@NonNull String certName) {
	this.certName = certName;
    }

    public Long getCertId() {
	return certId;
    }

    public void setCertId(Long certId) {
	this.certId = certId;
    }

    @Override
    public String toString() {
	return "Certificate{" +
	"certId=" + certId +
	", certName='" + certName + '\'' +
	'}';
    }
}
