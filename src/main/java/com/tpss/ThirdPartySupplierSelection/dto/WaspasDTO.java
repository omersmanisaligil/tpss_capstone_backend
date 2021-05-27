package com.tpss.ThirdPartySupplierSelection.dto;

import java.util.HashMap;
import java.util.List;

public class WaspasDTO {

    private HashMap<Long,double[]> Q1;
    private HashMap<Long,double[]> Q2;

    private HashMap<Long, Double> WSM;
    private HashMap<Long, Double> WPM;
    private HashMap<Long, Double> finalRanks;

    private List<ProviderDTO> providerDTOList;

    public WaspasDTO(HashMap<Long, double[]> q1,
		     HashMap<Long, double[]> q2,
		     HashMap<Long, Double> WSM,
		     HashMap<Long, Double> WPM,
		     HashMap<Long, Double> finalRanks,
		     List<ProviderDTO> providerDTOList) {
	Q1 = q1;
	Q2 = q2;
	this.WSM = WSM;
	this.WPM = WPM;
	this.finalRanks = finalRanks;
	this.providerDTOList = providerDTOList;
    }

    public HashMap<Long, double[]> getQ1() {
	return Q1;
    }

    public HashMap<Long, Double> getFinalRanks() {
	return finalRanks;
    }

    public void setFinalRanks(HashMap<Long, Double> finalRanks) {
	this.finalRanks = finalRanks;
    }

    public void setQ1(HashMap<Long, double[]> q1) {
	Q1 = q1;
    }

    public HashMap<Long, double[]> getQ2() {
	return Q2;
    }

    public void setQ2(HashMap<Long, double[]> q2) {
	Q2 = q2;
    }

    public HashMap<Long, Double> getWSM() {
	return WSM;
    }

    public void setWSM(HashMap<Long, Double> WSM) {
	this.WSM = WSM;
    }

    public HashMap<Long, Double> getWPM() {
	return WPM;
    }

    public void setWPM(HashMap<Long, Double> WPM) {
	this.WPM = WPM;
    }

    public List<ProviderDTO> getProviderDTOList() {
	return providerDTOList;
    }

    public void setProviderDTOList(List<ProviderDTO> providerDTOList) {
	this.providerDTOList = providerDTOList;
    }
}
