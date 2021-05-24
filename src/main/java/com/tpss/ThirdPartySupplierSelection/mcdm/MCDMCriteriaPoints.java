package com.tpss.ThirdPartySupplierSelection.mcdm;

import com.tpss.ThirdPartySupplierSelection.dto.DTOMapper;
import com.tpss.ThirdPartySupplierSelection.dto.ProviderDTO;
import com.tpss.ThirdPartySupplierSelection.entity.Order;
import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import com.tpss.ThirdPartySupplierSelection.entity.Vehicle;
import com.tpss.ThirdPartySupplierSelection.mcdm.MCDMConstants;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MCDMCriteriaPoints {

    public static List<ProviderDTO> determineCriteriaPoints(List<Provider> providers){
        List<ProviderDTO> providerDTOs = DTOMapper.toProviderDTOList(providers);
	providerDTOs.forEach(
		provider -> {
			int[] points = provider.getCriteriaPoints();

			determineTechCapabilities(points,provider);
			determineServiceLevelCapabilities(points,provider);
			determineBusinessExcellence(points,provider);
			determineSustainability(points,provider);
			determineCost(points,provider);

			provider.setCriteriaPoints(points);
		}
	);

        return providerDTOs;
    }

    private static void determineTechCapabilities(int[] points,ProviderDTO provider){
     	int vehicleCount =provider.getTotalVehicleCount();
	HashMap <String, Integer> parmPoint = new HashMap<String, Integer>();
	String[] attr = {"tempMonitor", "humidityMonitor", "tempMaintaining","humidityMaintaining"};
	for (String s: attr) {
	    parmPoint.put(s,determineC11andC12(s,vehicleCount,provider));
	}

	points[MCDMConstants.INDEX_COLD_TRACEABILITY] = parmPoint.get("tempMonitor")/2 + parmPoint.get("humidityMonitor")/2;
	points[MCDMConstants.INDEX_TEMPERATURE_MANAGEMENT] = parmPoint.get("tempMaintaining")/2 + parmPoint.get("humidityMaintaining")/2;

	Set<Order> orders = provider.getOrders();
	int orderCount = orders.size();
	double totalCrushLossPercentage=0;
	for (Order o : orders){
		totalCrushLossPercentage += (o.getAmountCrushed()/o.getAmountDelivered());
	}

	totalCrushLossPercentage += (totalCrushLossPercentage/(100*orderCount));
	points[MCDMConstants.INDEX_PACKAGING_LABELING] = determineC13(totalCrushLossPercentage);

	points[MCDMConstants.INDEX_LOGISTICS_APPROACH] = determineC14(provider);

    }


    private static void determineServiceLevelCapabilities(int[] points,ProviderDTO provider){

        points[MCDMConstants.INDEX_DELIVERY_SPEED] = determineC15(provider);
	points[MCDMConstants.INDEX_DISTRIBUTION_COVERAGE] = determineC14(provider); //bu ikisini ayır
	points[MCDMConstants.INDEX_VEHICLE_CAPACITY] = determineC17(provider);

    }

    private static void determineBusinessExcellence(int[] points,ProviderDTO provider){

    }

    private static void determineSustainability(int[] points,ProviderDTO provider){

    }

    private static void determineCost(int[] points,ProviderDTO provider){

    }

    private static Integer determineC11andC12(String attr,int vehicleCount,ProviderDTO provider){
	int aGrade, bGrade, cGrade, dGrade, eGrade;
	aGrade = bGrade = cGrade = dGrade = eGrade = 0;
	String parm;
	for (Vehicle v : provider.getVehicles()) {
	    if(attr == "tempMonitor"){
	        parm = v.getTech().getTemperatureMonitoringTech();
	    }
	    else if(attr == "humidityMonitor"){
	        parm = v.getTech().getHumidityMonitoringTech();
	    }
	    else if(attr == "tempMaintaining"){
	        parm = v.getTech().getTempMaintainingTech();
	    }
	    else{
	        parm = v.getTech().getHumidityMaintainingTech();
	    }

	    switch(parm.toLowerCase()){
		case "a grade":
		    aGrade++;
		    break;
		case "b grade":
		    bGrade++;
		    break;
		case "c grade":
		    cGrade++;
		    break;
		case "d grade":
		    dGrade++;
		    break;
		case "e grade":
		    eGrade++;
		    break;
	    }
	}
	return Integer.valueOf((int)(((aGrade * 1 + bGrade * 0.8 + cGrade * 0.6 + dGrade * 0.4 + eGrade*0.2)/vehicleCount)*100));
    }

    private static Integer determineC13(double totalCrushLossPercentage){
        int grade = 0;

	if(totalCrushLossPercentage>=0.8)
	    grade=1;
	else if(totalCrushLossPercentage>=0.6)
	    grade=2;
	else if(totalCrushLossPercentage>=0.4)
	    grade=3;
	else if(totalCrushLossPercentage>=0.2)
	    grade=4;
	else
	    grade=5;

	return grade;
    }

    private static Integer determineC14(ProviderDTO provider){
        int grade = 0;
	Integer totalCoverage = 0;
        for (Vehicle v : provider.getVehicles()){
            totalCoverage += v.getAreaCoverage();
	}

        if(totalCoverage <= 1000){
            grade = 1;
	}
        else if(totalCoverage <= 2500){
            grade = 2;
	}
        else if(totalCoverage <= 5000){
            grade = 3;
	}
        else if(totalCoverage <= 7500){
            grade = 4;
	}
        else
            grade = 5;

        return grade;
    }

    private static Integer determineC15(ProviderDTO provider){
	int onTime = 0;
	int lateDelivery = 0;
	Integer grade = 0;
	Set<Order> orders = provider.getOrders();

	for (Order o : orders){
	    Date expectedArrival = o.getPromisedArrival();
	    Date actualArrival = o.getActualArrival();
	    int compare = actualArrival.compareTo(expectedArrival);
	    if( compare == 0 || compare < 0 )
		onTime++;
	    else
		lateDelivery++;
	}

	double onTimePercentage = (onTime/(onTime+lateDelivery))*100;

	if(onTimePercentage >= 0.8)
	    grade = 5;
	else if(onTimePercentage >= 0.6)
	    grade = 4;
	else if(onTimePercentage >= 0.4)
	    grade = 3;
	else if(onTimePercentage >= 0.2)
	    grade = 2;
	else
	    grade = 1;

	return grade;
    }

    private static Integer determineC17(ProviderDTO provider){
	Set<Vehicle> vehicles = provider.getVehicles();

	for(Vehicle v : vehicles){

	}
    }
}
