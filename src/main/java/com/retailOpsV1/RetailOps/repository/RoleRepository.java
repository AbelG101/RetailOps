package com.retailOpsV1.RetailOps.repository;

import com.retailOpsV1.RetailOps.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleId(Integer roleId);
}
