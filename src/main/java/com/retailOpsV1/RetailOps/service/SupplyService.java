package com.retailOpsV1.RetailOps.service;

import com.retailOpsV1.RetailOps.model.Supply;
import com.retailOpsV1.RetailOps.repository.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplyService {
    @Autowired
    private SupplyRepository supplyRepository;

     public Supply createSupply(Supply supply){
         return supplyRepository.save(supply);
     }

     public Optional<Supply> getSupplyById(Integer id){
         return supplyRepository.findBySupplyId(id);
     }

     public Supply updateSupply(Integer id, Supply updatedSupply){
         updatedSupply.setSupplyId(id);
         return supplyRepository.save(updatedSupply);
     }

     public void deleteSupply(Integer id){
         supplyRepository.deleteById(id);
     }
}
