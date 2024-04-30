package com.retailOpsV1.RetailOps.service;

import com.retailOpsV1.RetailOps.model.DTOs.SupplierDTO;
import com.retailOpsV1.RetailOps.model.DTOs.authentication.AuthenticationResponse;
import com.retailOpsV1.RetailOps.model.Employee;
import com.retailOpsV1.RetailOps.model.Supplier;
import com.retailOpsV1.RetailOps.repository.EmployeeRepository;
import com.retailOpsV1.RetailOps.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final JwtService jwtService;
     @Autowired
    //private SupplierRepository supplierRepository;

    public static List<Supplier> getAllSuppliers(String authHeader) {
        return List.of();
    }

    public Supplier createSupplier(Supplier supplier){
         return supplierRepository.save(supplier);
     }

     public Optional<Supplier> getSuppplierById(Long id){
         return supplierRepository.findBySupplierId(id);
     }

     public Supplier updateSupplier(Long id, Supplier updatedSupplier){
//         updatedSupplier.setSupplierId(id);
         return supplierRepository.save(updatedSupplier);
     }

     public void deleteSupplier(Long id){
         supplierRepository.deleteBySupplierId(id);
     }

    public AuthenticationResponse register(SupplierDTO request) {
    /* var supplier = Supplier.builder()
                .supplierName(request.getSupplierName())
                .contactPerson(request.getContactPerson())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();
        supplierRepository.save(supplier);
        var jwtToken = jwtService.generateToken((UserDetails) supplier);
        var refreshToken = jwtService.generateRefreshToken((UserDetails) supplier);
        //return AuthenticationResponse.builder();
              //  .accessToken(jwtToken)
                //.supplierName(supplier.getSupplierName())
                //.contactPerson(supplier.getContactPerson())
                //.email(supplier.getEmail())
              //  .phoneNumber(supplier.getPhoneNumber())
                .build();*/
        return null;
    }

    public Supplier getSupplier(Integer supplierId, String authHeader) {
        Supplier supplier = getValidatedSupplier(supplierId, authHeader);
       return supplier;
        //return null;
    }

    private Supplier getValidatedSupplier(Integer supplierId, String authHeader) {
       return null;
    }
}
