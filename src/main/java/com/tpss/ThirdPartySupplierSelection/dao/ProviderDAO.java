package com.tpss.ThirdPartySupplierSelection.dao;

import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProviderDAO extends JpaRepository<Provider,Long>, ProviderDAOCustom {
    Optional<Provider> findById(Long id);

    List<Provider> findByOperationArea(String operationArea);

    @Query(value = "SELECT p from Provider p WHERE p.providerName LIKE %:providerName%")
    List<Provider> searchByProviderName(@Param("providerName") String providerName);

    @Override
    List<Provider> findAll();
}
