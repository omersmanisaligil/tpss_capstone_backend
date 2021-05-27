package com.tpss.ThirdPartySupplierSelection.dto;

import com.tpss.ThirdPartySupplierSelection.entity.*;

import java.util.*;
import java.util.stream.Collectors;

public class DTOMapper {

    public static ProviderDTO toProviderDTO(Provider provider){
        ProviderDTO providerDTO = new ProviderDTO();
        Set<Vehicle> vehicles = provider.getVehicles();
        Set<Order> orders = provider.getOrders();
        int vehicleCount = vehicles.size();
        int greenVehicleCount = vehicles.stream().filter(
                                    vehicle->{
                                        return vehicle.getFuelType().equalsIgnoreCase("green");
                                    }).collect(Collectors.toList()).size();

        int greenPercentage;
        try {
            greenPercentage = (greenVehicleCount / vehicleCount) * 100;
        }
        catch(ArithmeticException e){
            greenPercentage = 0;
        }

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

    public static VehicleDTO toVehicleDTO(Vehicle vehicle){
        VehicleDTO vehicleDTO = new VehicleDTO();
        Set<Route> routes = vehicle.getRoutes();
        Provider provider = vehicle.getProvider();
        Tech tech = vehicle.getTech();

        vehicleDTO.setVehicleId(vehicle.getVehicleId());
        vehicleDTO.setProviderID(vehicle.getProviderID());
        vehicleDTO.setVehicleType(vehicle.getVehicleType());
        vehicleDTO.setVehicleModel(vehicle.getVehicleModel());
        vehicleDTO.setVehicleCapacity(vehicle.getVehicleCapacity());
        vehicleDTO.setCapacityUnit(vehicle.getCapacityUnit());
        vehicleDTO.setAreaCoverage(vehicle.getAreaCoverage());
        vehicleDTO.setCoverageUnit(vehicle.getCoverageUnit());
        vehicleDTO.setLowestTemp(vehicle.getLowestTemp());
        vehicleDTO.setHighestTemp(vehicle.getHighestTemp());
        vehicleDTO.setTempUnit(vehicle.getTempUnit());
        vehicleDTO.setLowestHumidity(vehicle.getLowestHumidity());
        vehicleDTO.setHighestHumidity(vehicle.getHighestHumidity());
        vehicleDTO.setHumidityUnit(vehicle.getHumidityUnit());
        vehicleDTO.setRoutes(routes);
        vehicleDTO.setProvider(provider);
        vehicleDTO.setTech(tech);

        return vehicleDTO;
    }

    public static OrderDTO toOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        User user = order.getOwner();
        Provider provider = order.getProvider();

        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setOwnerId(order.getOrderId());
        orderDTO.setProviderId(order.getProviderId());
        orderDTO.setPromisedArrival(order.getPromisedArrival());
        orderDTO.setActualArrival(order.getActualArrival());
        orderDTO.setAmountOrdered(order.getAmountOrdered());
        orderDTO.setUnit(order.getUnit());
        orderDTO.setAmountDelivered(order.getAmountDelivered());
        orderDTO.setAmountSpoiled(order.getAmountSpoiled());
        orderDTO.setAmountCrushed(order.getAmountCrushed());
        orderDTO.setTotalLoss(order.getAmountCrushed()+order.getAmountSpoiled());
        orderDTO.setPaidAmount(order.getPaidAmount());
        orderDTO.setCurrency(order.getCurrency());
        orderDTO.setState(order.getState());

        orderDTO.setOwner(user);
        orderDTO.setProvider(provider);

        return orderDTO;
    }

    public static ProductDTO toProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        Set<Provider> providers = new HashSet<Provider>();

        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setIdealTemp(product.getIdealTemp());
        productDTO.setTempUnit(product.getTempUnit());
        productDTO.setIdealHumidity(product.getIdealHumidity());
        productDTO.setHumidityUnit(product.getHumidityUnit());

        productDTO.setProviders(providers);

        return productDTO;
    }

    public static RouteDTO toRouteDTO(Route route){
        RouteDTO routeDTO = new RouteDTO();
        Vehicle vehicle = route.getVehicle();

        routeDTO.setRouteId(route.getRouteId());
        routeDTO.setFrom(route.getFrom());
        routeDTO.setTo(route.getTo());
        routeDTO.setDestinations(route.getDestinations());
        routeDTO.setVehicle(route.getVehicle());

        return routeDTO;
    }

    public static CertificateDTO toCertDTO(Certificate certificate){
        CertificateDTO certificateDTO = new CertificateDTO();
        Set<Provider> providers = new HashSet<Provider>();

        certificateDTO.setCertId(certificate.getCertId());
        certificateDTO.setCertName(certificateDTO.getCertName());

        certificateDTO.setProviders(providers);

        return certificateDTO;
    }

    public static WaspasDTO toWaspasDTO(HashMap<Long,double[]> q1,
                                        HashMap<Long,double[]> q2,
                                        HashMap<Long,Double> wsm,
                                        HashMap<Long,Double> wpm,
                                        HashMap<Long, Double> finalRanks,
                                        List<ProviderDTO> providerDTOList){
        WaspasDTO waspasDTO = new WaspasDTO(q1,q2,
                                            wsm,wpm,
                                            finalRanks,
                                            providerDTOList);
        return waspasDTO;
    }

    public static List<VehicleDTO> toVehicleDTOList(List<Vehicle> vehicleList){
        ArrayList<VehicleDTO> vehicleDTOList = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            vehicleDTOList.add(toVehicleDTO(v));
        }
        return vehicleDTOList;
    }

    public static List<OrderDTO> toOrderDTOList(List<Order> orderList){
        ArrayList<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order o : orderList) {
            orderDTOList.add(toOrderDTO(o));
        }
        return orderDTOList;
    }

    public static List<ProductDTO> toProductDTOList(List<Product> productList){
        ArrayList<ProductDTO> productDTOList = new ArrayList<>();
        for (Product p : productList) {
            productDTOList.add(toProductDTO(p));
        }
        return productDTOList;
    }

    public static List<RouteDTO> toRouteDTOList(List<Route> routeList){
        ArrayList<RouteDTO> routeDTOList = new ArrayList<>();
        for (Route r : routeList){
            routeDTOList.add(toRouteDTO(r));
        }
        return routeDTOList;
    }

    public static List<CertificateDTO> toCertificateDTOList(List<Certificate> certificates){
        ArrayList<CertificateDTO> certificateDTOList = new ArrayList<>();
        for (Certificate c : certificates) {
            certificateDTOList.add(toCertDTO(c));
        }
        return certificateDTOList;
    }

    public static List<ProviderDTO> toProviderDTOList(List<Provider> providerList) {
        ArrayList<ProviderDTO> providerDTOList = new ArrayList<>();
        for (Provider p : providerList) {
            providerDTOList.add(toProviderDTO(p));
        }
        return providerDTOList;
    }
}
