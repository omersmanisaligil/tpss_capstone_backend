package com.tpss.ThirdPartySupplierSelection.dto;

import com.tpss.ThirdPartySupplierSelection.entity.Provider;

import java.util.HashSet;
import java.util.Set;

public class CertificateDTO{

    Long certId;

    String certName;

    public CertificateDTO(Long certId, String certName){
	this.certName=certName;
    }
    public CertificateDTO(){}

    private Set<Provider> providers = new HashSet<Provider>();


    public Long getCertId() {
	return certId;
    }

    public void setCertId(Long certId) {
	this.certId = certId;
    }

    public String getCertName() {
	return certName;
    }

    public void setCertName(String certName) {
	this.certName = certName;
    }

    public Set<Provider> getProviders() {
	return providers;
    }

    public void setProviders(Set<Provider> providers) {
	this.providers = providers;
    }
}
