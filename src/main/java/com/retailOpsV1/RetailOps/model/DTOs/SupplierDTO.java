package com.retailOpsV1.RetailOps.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
    private String supplierName;
    private String contactPerson;
    private String email;
    private String phoneNumber;
}
