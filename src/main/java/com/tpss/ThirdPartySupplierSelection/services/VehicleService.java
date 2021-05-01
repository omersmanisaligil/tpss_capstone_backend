package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.VehicleDAO;
import com.tpss.ThirdPartySupplierSelection.entity.Route;
import com.tpss.ThirdPartySupplierSelection.entity.Tech;
import com.tpss.ThirdPartySupplierSelection.entity.Vehicle;
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

    public void addVehicle(Vehicle vehicle){
        vehicleDAO.save(vehicle);
    }

    public void insertRoute(Route route, Long vehicleID){
        Vehicle vehicle = vehicleDAO.getOne(vehicleID);

        vehicle.insertRoute(route);
    }

    public void addTech(Tech tech, Long id){

    }
}
