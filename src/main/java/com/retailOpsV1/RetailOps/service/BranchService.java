package com.retailOpsV1.RetailOps.service;

import com.retailOpsV1.RetailOps.model.Branch;
import com.retailOpsV1.RetailOps.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {
    @Autowired
private BranchRepository branchRepository;

 public Branch createBranch(Branch branch) {
 return branchRepository.save(branch);
 }

 public Branch getBranchById(Integer branchId) {
 return branchRepository.findById(branchId).orElse(null);
}

 public Branch updateBranch(Integer branchId, Branch updatedBranch) {
 Branch existingBranch = branchRepository.findById(branchId).orElse(null);
 if (existingBranch != null) {
 existingBranch.setBranchName(updatedBranch.getBranchName());
 existingBranch.setLocation(updatedBranch.getLocation());
 // Update other fields as needed
 return branchRepository.save(existingBranch);
 }
 return null;
 }

 public void deleteBranch(Integer branchId) {
 branchRepository.deleteById(branchId);
}}
