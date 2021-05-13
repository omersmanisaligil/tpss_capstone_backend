package com.tpss.ThirdPartySupplierSelection.dto;

import com.tpss.ThirdPartySupplierSelection.entity.Order;
import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import com.tpss.ThirdPartySupplierSelection.entity.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DTOMapper {

    public static ProviderDTO toProviderDTO(Provider provider){
        ProviderDTO providerDTO = new ProviderDTO();
        Set<Vehicle> vehicles = provider.getVehicles();
        Set<Order> orders = provider.getOrders();
        int vehicleCount = vehicles.size();
        int greenVehicleCount = vehicles.stream().filter(
                                    vehicle->{
                                        return vehicle.getVehicleType().equalsIgnoreCase("green");
                                    }).collect(Collectors.toList()).size();

        int greenPercentage;
        try {
            greenPercentage = (greenVehicleCount / vehicleCount) * 100;
        }
        catch(ArithmeticException e){
            greenPercentage = 0;
        }

        //TODO check if we lose the id at some point
        providerDTO.setProviderId(provider.getProviderId());
        providerDTO.setProviderName(provider.getProviderName());
        providerDTO.setProviderDesc(provider.getProviderDesc());
        providerDTO.setFoundationYear(provider.getFoundationYear());
        providerDTO.setNumberOfOrders(provider.getOrders().size());
        providerDTO.setOperationArea(provider.getOperationArea());
        providerDTO.setTotalVehicleCount(vehicleCount);
        providerDTO.setGreenPercentage(greenPercentage);
        providerDTO.setVehicles(vehicles);
        providerDTO.setOrders(orders);
        providerDTO.setProducts(provider.getProducts());
        providerDTO.setCertificates(provider.getCertificates());

        return providerDTO;
    }

    public static List<ProviderDTO> toProviderDTOList(List<Provider> providerList){
        ArrayList<ProviderDTO> providerDTOList = new ArrayList<>();
        for (Provider p : providerList) {
            providerDTOList.add(toProviderDTO(p));
        }
        return providerDTOList;
    }

}
