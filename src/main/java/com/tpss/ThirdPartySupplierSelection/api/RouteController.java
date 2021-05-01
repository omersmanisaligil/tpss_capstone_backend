package com.tpss.ThirdPartySupplierSelection.api;

import com.tpss.ThirdPartySupplierSelection.entity.Route;
import com.tpss.ThirdPartySupplierSelection.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    RouteService routeService;

    public RouteController(RouteService routeService){
        this.routeService = routeService;
    }

    @GetMapping(path="")
    public ResponseEntity<Page<Route>> getAll(
    @RequestParam(name="page", defaultValue = "0") int page,
    @RequestParam(name="size", defaultValue = "3") int size
    //,@RequestParam(name="sort", defaultValue = "id") String[] sort
    ){
        Page<Route> allRoutes = routeService.getAll(page,size);
        return ResponseEntity.status(HttpStatus.OK).body(allRoutes);
    }

    @GetMapping(path="{id}")
    public ResponseEntity<Route> getOneByID(@PathVariable("id") Long id){
        Route route = routeService.getOneByID(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(route);
    }

    @GetMapping(path="{id}/getDestinations")
    public ResponseEntity<String[]> getDestinations(@PathVariable("id") Long id){
        String[] destinations = routeService.getDestinations(id);
        return ResponseEntity.status(HttpStatus.OK).body(destinations);
    }

    @GetMapping(path="{id}/getDestCount")
    public ResponseEntity<Integer> getDestCount(@PathVariable Long id){
        Integer count = routeService.getDestCount(id);
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addRoute(@Validated @NonNull @RequestBody Route route){
        routeService.addRoute(route);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(path="{id}/insertDestination")
    public ResponseEntity<?> insertDestination(String destination,
                                               @PathVariable("id") Long id){
        routeService.insertDestination(destination,id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path="/edit/{id}")
    public ResponseEntity<Route> updateRoute(@Validated @NonNull @RequestBody Route route,
                                           @PathVariable("id") Long id){

        Route updatedRoute = routeService.updateRoute(route,id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedRoute);
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<?> deleteRoute(@PathVariable("id") Long id){
        routeService.deleteRoute(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
