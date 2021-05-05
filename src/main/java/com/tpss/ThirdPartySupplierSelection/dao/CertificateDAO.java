package com.tpss.ThirdPartySupplierSelection.dao;

import com.tpss.ThirdPartySupplierSelection.entity.Certificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificateDAO extends JpaRepository<Certificate,Long> {
    Optional<Certificate> findById(Long id);

    Optional<Certificate> findByCertName(String certName);

    Boolean existsByCertName(String certName);

    @Override
    Page<Certificate> findAll(Pageable pageable);
}
