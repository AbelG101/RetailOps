package com.retailOpsV1.RetailOps.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer supplyId;
    private float unitPrice;
    private LocalDate supplyDate;
    private float quantity;
    private int itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplierId")
    private Supplier supplier;

//    public Integer getSupplyId() {
//        return SupplyId;
//    }
//
//    public void setSupplyId(Long supplyId) {
//        SupplyId = supplyId;
//    }

}
