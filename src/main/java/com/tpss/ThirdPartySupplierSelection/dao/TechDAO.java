package com.tpss.ThirdPartySupplierSelection.dao;

import com.tpss.ThirdPartySupplierSelection.entity.Tech;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechDAO extends JpaRepository<Tech,Long>{
    Optional<Tech> findById(Long id);

    @Override
    Page<Tech> findAll(Pageable pageable);
}
