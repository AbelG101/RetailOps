package com.retailOpsV1.RetailOps.controller;

import com.retailOpsV1.RetailOps.model.Branch;
import com.retailOpsV1.RetailOps.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branch")
public class BranchController {
@Autowired
private BranchService branchService;

  @PostMapping
 public Branch createBranch(@RequestBody Branch branch) {
 return branchService.createBranch(branch);
 }

 @GetMapping("/branchId")
public Branch getBranchById(@PathVariable Integer branchId) {
 return branchService.getBranchById(branchId);
}

 @PutMapping("/branchId")
public Branch updateBranch(@PathVariable Integer branchId, @RequestBody Branch branch) {
 return branchService.updateBranch(branchId, branch);
 }

@DeleteMapping("/branchId")
public void deleteBranch(@PathVariable Integer branchId) {
 branchService.deleteBranch(branchId);
 }}



