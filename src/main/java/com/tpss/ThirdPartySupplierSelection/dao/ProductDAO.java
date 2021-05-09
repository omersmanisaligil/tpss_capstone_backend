package com.tpss.ThirdPartySupplierSelection.dao;

import com.tpss.ThirdPartySupplierSelection.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductDAO extends JpaRepository<Product,Long> {
    Optional<Product> findById(Long id);

    Optional<Product> findByProductName(String productName);

    @Query(value = "SELECT p from Product p WHERE p.productName LIKE %:productName%")
    Page<Product> searchByProductName(@Param("productName") String productName, Pageable pageable);

    @Override
    Product save(Product product);

    @Override
    Page<Product> findAll(Pageable pageable);

    Boolean existsByProductName(String productName);
}
