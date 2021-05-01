package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.RouteDAO;
import com.tpss.ThirdPartySupplierSelection.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    RouteDAO routeDAO;

    public Page<Route> getAll(int page, int size){
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Route> routes= routeDAO.findAll(pageRequest);

        return routes;
    }

    public Optional<Route> getOneByID(Long id){
        Optional<Route> route = routeDAO.findById(id);

        return route;
    }

    public Route updateRoute(Route route, Long id){
        Route routeToUpdate = null;

        routeToUpdate=route;
        route.setRouteId(id);

        routeDAO.save(routeToUpdate);

        return routeToUpdate;
    }

    public void insertDestination(String destination,Long id){
        Route route = routeDAO.getOne(id);

        route.insertDestination(destination);
    }

    public Integer getDestCount(Long id){
        Route route = routeDAO.getOne(id);

        Integer count = route.getDestinations().split(";").length;
        return count;
    }

    public String[] getDestinations(Long id){
        Route route = routeDAO.getOne(id);

        String[] destinations = route.getDestinations().split(";");

        return destinations;
    }

    public void deleteRoute(Long id){
        routeDAO.deleteById(id);
    }

    public void addRoute(Route route){
        routeDAO.save(route);
    }
}
