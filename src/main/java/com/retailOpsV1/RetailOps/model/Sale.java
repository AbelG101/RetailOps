package com.retailOpsV1.RetailOps.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Sale {
    @Id
    @GeneratedValue
    private Integer saleId;
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
