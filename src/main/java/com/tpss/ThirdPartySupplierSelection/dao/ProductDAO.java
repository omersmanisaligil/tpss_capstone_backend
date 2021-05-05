package com.tpss.ThirdPartySupplierSelection.dao;

import com.tpss.ThirdPartySupplierSelection.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductDAO extends JpaRepository<Product,Long> {
    Optional<Product> findById(Long id);

    Optional<Product> findByProductName(String productName);

    @Override
    Product save(Product product);

    @Override
    Page<Product> findAll(Pageable pageable);

    Boolean existsByProductName(String productName);
}
