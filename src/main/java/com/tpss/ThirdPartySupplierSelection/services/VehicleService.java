package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.TechDAO;
import com.tpss.ThirdPartySupplierSelection.dao.VehicleDAO;
import com.tpss.ThirdPartySupplierSelection.entity.Route;
import com.tpss.ThirdPartySupplierSelection.entity.Tech;
import com.tpss.ThirdPartySupplierSelection.entity.Vehicle;
import com.tpss.ThirdPartySupplierSelection.payload.request.AddVehicleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    VehicleDAO vehicleDAO;

    @Autowired
    TechService techService;

    @Autowired
    TechDAO techDAO;

    public Page<Vehicle> getAll(int page, int size){
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Vehicle> vehicles= vehicleDAO.findAll(pageRequest);

        return vehicles;
    }

    public Optional<Vehicle> getOneByID(Long id){
        Optional<Vehicle> vehicle = vehicleDAO.findById(id);

        return vehicle;
    }

    public Vehicle updateVehicle(Vehicle vehicle, Long id){
        Vehicle vehicleToUpdate = null;

        vehicleToUpdate=vehicle;
        vehicle.setVehicleId(id);

        vehicleDAO.save(vehicleToUpdate);

        return vehicleToUpdate;
    }

    public void deleteVehicle(Long id){
        vehicleDAO.deleteById(id);
    }

    public Vehicle addVehicle(AddVehicleRequest vehicleRequest){

        Vehicle vehicle = new Vehicle(
            vehicleRequest.getVehicleType(),
            vehicleRequest.getVehicleModel(),
            vehicleRequest.getVehicleCapacity(),
            vehicleRequest.getProviderID(),
            vehicleRequest.getCapacityUnit(),
            vehicleRequest.getAreaCoverage(),
            vehicleRequest.getCoverageUnit(),
            vehicleRequest.getLowestTemp(),
            vehicleRequest.getHighestTemp(),
            vehicleRequest.getTempUnit(),
            vehicleRequest.getLowestHumidity(),
            vehicleRequest.getHighestHumidity(),
            vehicleRequest.getHumidityUnit(),
            vehicleRequest.getAccidentCount(),
            vehicleRequest.getFuelType()
        );

        vehicleDAO.save(vehicle);

        //todo looks ugly
        String temperatureMonitoringTech = vehicleRequest.getTemperatureMonitoringTech();

        String humidityMonitoringTech = vehicleRequest.getHumidityMonitoringTech();

        String tempMaintainingTech = vehicleRequest.getTempMaintainingTech();

        String humidityMaintainingTech = vehicleRequest.getHumidityMonitoringTech();
        if(temperatureMonitoringTech!=null
        || humidityMonitoringTech!=null
        || humidityMaintainingTech!=null
        || tempMaintainingTech!=null){

            Tech tech = new Tech(
            temperatureMonitoringTech,
            humidityMonitoringTech,
            tempMaintainingTech,
            humidityMaintainingTech
            );

            techService.addTech(tech);
            tech.insertVehicle(vehicle);

            techService.addTech(tech);
        }

        return vehicle;
    }

    public void insertRoute(Route route, Long vehicleID){
        Vehicle vehicle = vehicleDAO.getOne(vehicleID);

        vehicle.insertRoute(route);
    }

    public void addTech(Tech tech, Long id){
        Vehicle vehicle = vehicleDAO.getOne(id);

        techDAO.save(tech);
        tech.insertVehicle(vehicle);
        vehicle.setTech(tech);
        vehicleDAO.save(vehicle);
    }
}
