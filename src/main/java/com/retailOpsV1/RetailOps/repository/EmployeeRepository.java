package com.retailOpsV1.RetailOps.repository;

import com.retailOpsV1.RetailOps.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUserName(String userName);
}
