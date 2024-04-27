package com.retailOpsV1.RetailOps.repository;

import com.retailOpsV1.RetailOps.model.BranchManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchManagerRepository extends JpaRepository<BranchManager, Long> {
@Override
    Optional<BranchManager> findById(Long branchManagerID);
}
