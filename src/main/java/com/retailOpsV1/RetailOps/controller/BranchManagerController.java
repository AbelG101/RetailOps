package com.retailOpsV1.RetailOps.controller;

import com.retailOpsV1.RetailOps.model.BranchManager;
import com.retailOpsV1.RetailOps.service.BranchManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branch-managers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BranchManagerController {
    @Autowired
    private BranchManagerService branchManagerService;

        // Create a new Branch Manager
        @PostMapping("/create")
        public ResponseEntity<BranchManager> createBranchManager(@RequestBody BranchManager branchManager) {
            BranchManager newBranchManager = branchManagerService.createBranchManager(branchManager);
            return ResponseEntity.ok(newBranchManager);
        }

        // Read a Branch Manager by ID
        @GetMapping("/{branchManagerID}")
        public ResponseEntity<BranchManager> getBranchManagerById(@PathVariable Long branchManagerID) {
            BranchManager branchManager = branchManagerService.getBranchManagerById(branchManagerID);
            if (branchManager == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(branchManager);
        }

        // Update a Branch Manager
        @PutMapping("/Update/{branchManagerID}")
        public ResponseEntity<BranchManager> updateBranchManager(@PathVariable Long branchManagerID, @RequestBody BranchManager branchManager) {
            BranchManager updatedBranchManager = branchManagerService.updateBranchManager(branchManagerID, branchManager);
            if (updatedBranchManager == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedBranchManager);
        }

        // Delete a Branch Manager
        @DeleteMapping("/Delete/{branchManagerID}")
        public ResponseEntity<Void> deleteBranchManager(@PathVariable Long branchManagerID) {
            boolean deleted = branchManagerService.deleteBranchManager(branchManagerID);
            if (!deleted) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        }
    }









