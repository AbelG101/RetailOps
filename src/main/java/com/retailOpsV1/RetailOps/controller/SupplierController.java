package com.retailOpsV1.RetailOps.controller;

import com.retailOpsV1.RetailOps.model.DTOs.SupplierDTO;
import com.retailOpsV1.RetailOps.model.DTOs.authentication.AuthenticationResponse;
import com.retailOpsV1.RetailOps.model.Employee;
import com.retailOpsV1.RetailOps.model.Supplier;
import com.retailOpsV1.RetailOps.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor

public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/")
    public ResponseEntity<List<Supplier>> getAllSuppliers(@RequestHeader("Authorization") String authHeader) {
        List<Supplier> suppliers = SupplierService.getAllSuppliers(authHeader);
        return ResponseEntity.ok(suppliers);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody SupplierDTO request) {
        return ResponseEntity.ok(supplierService.register(request));
    }
    //public Supplier createSupplier(@RequestBody Supplier supplier){
        //return supplierService.createSupplier(supplier);
   // }

    @GetMapping("/{supplierId}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id){
        Optional<Supplier> supplier = supplierService.getSuppplierById(id);
        return supplier.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    public ResponseEntity<Supplier> getSupplier(@PathVariable Integer supplierId, @RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(supplierService.getSupplier(supplierId, authHeader));
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier updatedSupplier){
        Supplier supplier=supplierService.updateSupplier(id, updatedSupplier);
        return ResponseEntity.noContent().build();
    }

}
