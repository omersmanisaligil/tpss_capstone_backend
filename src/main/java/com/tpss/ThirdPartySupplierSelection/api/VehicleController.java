package com.tpss.ThirdPartySupplierSelection.api;

import com.tpss.ThirdPartySupplierSelection.entity.Route;
import com.tpss.ThirdPartySupplierSelection.entity.Tech;
import com.tpss.ThirdPartySupplierSelection.entity.Vehicle;
import com.tpss.ThirdPartySupplierSelection.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @GetMapping(path="")
    public ResponseEntity<Page<Vehicle>> getAll(
    @RequestParam(name="page", defaultValue = "0") int page,
    @RequestParam(name="size", defaultValue = "3") int size
    //,@RequestParam(name="sort", defaultValue = "id") String[] sort
    ){
        Page<Vehicle> allVehicles = vehicleService.getAll(page,size);
        return ResponseEntity.status(HttpStatus.OK).body(allVehicles);
    }

    @GetMapping(path="{id}")
    public ResponseEntity<Vehicle> getOneByID(@PathVariable("id") Long id){
        Vehicle vehicle = vehicleService.getOneByID(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addVehicle(@Validated @NonNull @RequestBody Vehicle vehicle){
        vehicleService.addVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(path="{id}/insertRoute")
    public ResponseEntity<?> insertRoute(@Validated @NonNull @RequestBody Route route,
                                         @PathVariable("id") Long id){
        vehicleService.insertRoute(route,id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(path="{id}/addTech")
    public ResponseEntity<?> addTech(@Validated @NonNull @RequestBody Tech tech,
                                     @PathVariable("id") Long id){
        vehicleService.addTech(tech,id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path="/edit/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@Validated @NonNull @RequestBody Vehicle vehicle,
                                                 @PathVariable("id") Long id){

        Vehicle updatedVehicle = vehicleService.updateVehicle(vehicle,id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedVehicle);
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") Long id){
        vehicleService.deleteVehicle(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
