package com.tpss.ThirdPartySupplierSelection.mcdm;

import com.tpss.ThirdPartySupplierSelection.dto.DTOMapper;
import com.tpss.ThirdPartySupplierSelection.dto.OrderDTO;
import com.tpss.ThirdPartySupplierSelection.dto.ProviderDTO;
import com.tpss.ThirdPartySupplierSelection.entity.Order;
import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import com.tpss.ThirdPartySupplierSelection.entity.Vehicle;
import com.tpss.ThirdPartySupplierSelection.mcdm.MCDMConstants;

import java.util.*;

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

        points[MCDMConstants.INDEX_DELIVERY_SPEED] = determineC21(provider);
	points[MCDMConstants.INDEX_DISTRIBUTION_COVERAGE] = determineC22(provider);
	points[MCDMConstants.INDEX_VEHICLE_CAPACITY] = determineC23(provider);
	points[MCDMConstants.INDEX_CONDITION_ON_ARRIVAL] = determineC24(provider);
    }

    private static void determineBusinessExcellence(int[] points,ProviderDTO provider){
        points[MCDMConstants.INDEX_FINANCIAL_STABILITY] = determineC31(provider);
	points[MCDMConstants.INDEX_EXPERIENCE] = determineC32(provider);
	points[MCDMConstants.INDEX_HUMAN_RES] = determineC33();
	points[MCDMConstants.INDEX_PARTNERSHIP] = determineC34(provider);
    }

    private static void determineSustainability(int[] points,ProviderDTO provider){
	points[MCDMConstants.INDEX_ENVIRONMENTAL_AWARENESS] = determineC41(provider);
	points[MCDMConstants.INDEX_ETHICAL_RESPONSIBILITY] = determineC42(provider);
	points[MCDMConstants.INDEX_GOVERNANCE] = determineC43(points,provider);
    }


    private static void determineCost(int[] points,ProviderDTO provider){
	points[MCDMConstants.INDEX_DIRECT_COSTS] = determineC51(provider);
	points[MCDMConstants.INDEX_INDIRECT_COSTS] = determine52(provider);
    }

    private static int determine52(ProviderDTO provider) {
	return foodLoss(provider)*5;
    }

    private static int determineC51(ProviderDTO provider) {
        Set<Order> orders = provider.getOrders();
	double avgAmountPerUnit = 0;
	for(Order o : orders){
	    avgAmountPerUnit = o.getPaidAmount()/o.getAmountOrdered();
	}
	avgAmountPerUnit = avgAmountPerUnit / orders.size();
        return (int)avgAmountPerUnit;
    }

    private static int determineC43(int[] points, ProviderDTO provider) {
    	return points[MCDMConstants.INDEX_ETHICAL_RESPONSIBILITY]/2 + points[MCDMConstants.INDEX_ENVIRONMENTAL_AWARENESS]/2;
    }

    private static int determineC42(ProviderDTO provider) {
	return (int)Math.random()*5;
    }

    private static int determineC41(ProviderDTO provider) {
	double greenPercentage = provider.getGreenPercentage();
	int grade = 0;
	if(greenPercentage > 0.8)
	    grade = 5;
	else if(greenPercentage > 0.6)
	    grade = 4;
	else if(greenPercentage > 0.4)
	    grade = 3;
	else if(greenPercentage > 0.2)
	    grade = 2;
	else
	    grade = 1;

	return 0;
    }

    private static int determineC34(ProviderDTO provider) {
        return (int)Math.random()*5;
    }

    private static int determineC33() {
        return (int)Math.random()*5;
    }

    private static int determineC32(ProviderDTO provider) {
    	Set<Order> orders = provider.getOrders();
	int orderCount = orders.size();

	if(orderCount > 50)
	    return 5;
	else if(orderCount <= 50)
	    return 4;
	else if(orderCount <= 40)
	    return 3;
	else if(orderCount <= 30)
	    return 2;
	else
	    return 1;

    }

    private static int determineC31(ProviderDTO provider) {
        Set<Order> orders = provider.getOrders();
        int orderCount = orders.size();
        int completedOrders = 0;

        for(Order o : orders){
            if(o.getState().equalsIgnoreCase("complete"))
                completedOrders++;
	}

    	return 5*(completedOrders/orderCount);
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
	return Integer.valueOf((int)(((aGrade * 1 + bGrade * 0.8 + cGrade * 0.6 + dGrade * 0.4 + eGrade*0.2)/vehicleCount)*5));
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

    private static Integer determineC21(ProviderDTO provider){
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

    private static Integer determineC22(ProviderDTO provider){
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

    private static Integer determineC23(ProviderDTO provider){
	Set<Vehicle> vehicles = provider.getVehicles();
	int countCapacityXL,countCapacityL, countCapacityM, countCapacityS,countCapacityXS;
	countCapacityXL = countCapacityL = countCapacityM = countCapacityS = countCapacityXS = 0;
	for(Vehicle v : vehicles){
		if(v.getVehicleCapacity()<100)
		    countCapacityXS++;
		else if(v.getVehicleCapacity()<300)
		    countCapacityS++;
		else if(v.getVehicleCapacity()<500)
		    countCapacityM++;
		else if(v.getVehicleCapacity()<750)
		    countCapacityL++;
		else
		    countCapacityXL++;
	}

	return Integer.valueOf((int)(((countCapacityXL * 1 + countCapacityL * 0.8 + countCapacityM * 0.6 + countCapacityS * 0.4 + countCapacityXS*0.2)/vehicles.size())*5));
    }

    private static Integer determineC24(ProviderDTO provider){
	Set<Order> orders = provider.getOrders();
	Set<Vehicle> vehicles = provider.getVehicles();
        int totalAccidents = 0;

        for(Vehicle v : vehicles){
            totalAccidents += v.getAccidentCount();
	}

        return (int)(foodLoss(provider)*2.5 + (totalAccidents/orders.size())*2.5);
    }

    private static Integer foodLoss(ProviderDTO provider){
	Set<Order> orders = provider.getOrders();
	int totalDeliveredAmount = 0;
	int totalFoodLoss = 0;
	for(Order o : orders){
	    OrderDTO orderDTO = DTOMapper.toOrderDTO(o);
	    totalFoodLoss += orderDTO.getTotalLoss();
	    totalDeliveredAmount += orderDTO.getAmountDelivered();
	}

	return ((totalFoodLoss/totalDeliveredAmount));
    }
}