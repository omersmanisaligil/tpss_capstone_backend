package com.tpss.ThirdPartySupplierSelection.dao;

import com.tpss.ThirdPartySupplierSelection.entity.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderDAO extends JpaRepository<Provider,Long> {
    Optional<Provider> findById(Long id);

    @Override
    Page<Provider> findAll(Pageable pageable);
}
