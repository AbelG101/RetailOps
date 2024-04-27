package com.retailOpsV1.RetailOps.repository;

import com.retailOpsV1.RetailOps.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
@Override
     Optional<Branch> findById(Integer integer);

}
