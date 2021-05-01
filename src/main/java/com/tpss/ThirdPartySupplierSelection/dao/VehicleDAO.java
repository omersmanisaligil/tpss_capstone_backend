package com.tpss.ThirdPartySupplierSelection.dao;

import com.tpss.ThirdPartySupplierSelection.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleDAO extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findById(Long id);

    @Override
    Page<Vehicle> findAll(Pageable pageable);
}
