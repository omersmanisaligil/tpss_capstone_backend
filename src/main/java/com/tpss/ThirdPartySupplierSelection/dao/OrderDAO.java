package com.tpss.ThirdPartySupplierSelection.dao;

import com.tpss.ThirdPartySupplierSelection.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderDAO extends JpaRepository<Order,Long> {
    Optional<Order> findById(Long id);

    @Override
    Page<Order> findAll(Pageable pageable);
}
