package com.retailOpsV1.RetailOps.service;

import com.retailOpsV1.RetailOps.model.BranchManager;
import com.retailOpsV1.RetailOps.repository.BranchManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchManagerService {

    @Autowired
    private BranchManagerRepository branchManagerRepository;

    // Create a new branch manager
    public BranchManager createBranchManager(BranchManager branchManager) {
        return branchManagerRepository.save(branchManager);
    }

    // Get a branch manager by ID
    public BranchManager getBranchManagerById(Long branchManagerID) {
        return branchManagerRepository.findById(branchManagerID).orElse(null);
    }

    // Update a branch manager
    public BranchManager updateBranchManager(Long branchManagerID, BranchManager branchManager) {
        BranchManager existingBranchManager = branchManagerRepository.findById(branchManagerID).orElse(null);
        if (existingBranchManager == null) {
            return null;
        }
//        existingBranchManager.setBranchID(branchManager.getName());
//        existingBranchManager.setBranchName(branchManager.getBranchName());
//        existingBranchManager.setPhoneNumber(branchManager.getPhoneNumber());
          return branchManagerRepository.save(existingBranchManager);
    }

    // Delete a branch manager
    public boolean deleteBranchManager(Long branchManagerID) {
        BranchManager branchManager = branchManagerRepository.findById(branchManagerID).orElse(null);
        if (branchManager == null) {
            return false;
        }
        branchManagerRepository.delete(branchManager);
        return true;
    }
}
