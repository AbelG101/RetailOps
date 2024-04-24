package com.retailOpsV1.RetailOps.controller;

import com.retailOpsV1.RetailOps.model.DTOs.EmployeeDTO;
import com.retailOpsV1.RetailOps.model.DTOs.SupplierDTO;
import com.retailOpsV1.RetailOps.model.DTOs.authentication.AuthenticationResponse;
import com.retailOpsV1.RetailOps.model.Supply;
import com.retailOpsV1.RetailOps.service.SupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping ("/api/v1/supplies")
@RequiredArgsConstructor

public class SupplyController {

    @Autowired
    private SupplyService supplyService;

    @PostMapping("/add")

    public Supply createSupply(@RequestBody Supply supply){
        return supplyService.createSupply(supply);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supply> getSupplyById(@PathVariable Integer id){
        Optional<Supply> supply=supplyService.getSupplyById(id);
        return supply.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Supply>updateSupply(@PathVariable Integer id, @RequestBody Supply updateSupply){
        Supply supply=supplyService.updateSupply(id, updateSupply);
        return ResponseEntity.ok(supply);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupply(@PathVariable Integer id){
        supplyService.deleteSupply(id);
        return ResponseEntity.noContent().build();
    }

}
