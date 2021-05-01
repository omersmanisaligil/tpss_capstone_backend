package com.tpss.ThirdPartySupplierSelection.dao;

import com.tpss.ThirdPartySupplierSelection.entity.Role;
import com.tpss.ThirdPartySupplierSelection.entity.User;
import com.tpss.ThirdPartySupplierSelection.entity.constants.ERole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleDAO extends JpaRepository<Role,Long> {
    	Optional<Role> findByName(ERole name);

	@Query(value="SELECT r.users FROM Role r WHERE r.name=:role")
	Page<User> findUsersByRole(@Param(value="role") String role, Pageable pageable);
}
