package com.retailOpsV1.RetailOps.repository;

import com.retailOpsV1.RetailOps.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Optional<Supplier> findBySupplierId(Long id);

    void deleteBySupplierId(Long id);


}
