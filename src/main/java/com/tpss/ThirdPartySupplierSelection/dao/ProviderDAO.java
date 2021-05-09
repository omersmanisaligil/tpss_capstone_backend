package com.tpss.ThirdPartySupplierSelection.dao;

import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProviderDAO extends JpaRepository<Provider,Long> {
    Optional<Provider> findById(Long id);

    Page<Provider> findByOperationArea(String operationArea,Pageable pageable);

    @Query(value = "SELECT p from Provider p WHERE p.providerName LIKE %:providerName%")
    Page<Provider> searchByProviderName(@Param("providerName") String providerName, Pageable pageable);

    @Override
    Page<Provider> findAll(Pageable pageable);
}
