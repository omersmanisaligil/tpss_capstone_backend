package com.tpss.ThirdPartySupplierSelection.dao;

import com.tpss.ThirdPartySupplierSelection.entity.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouteDAO extends JpaRepository<Route,Long> {
    Optional<Route> findById(Long id);

    @Override
    Page<Route> findAll(Pageable pageable);
}
