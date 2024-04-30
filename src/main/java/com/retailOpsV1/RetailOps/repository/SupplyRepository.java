package com.retailOpsV1.RetailOps.repository;

import com.retailOpsV1.RetailOps.model.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplyRepository extends JpaRepository<Supply, Integer> {
    Optional<Supply> findBySupplyId(Integer id);
}
