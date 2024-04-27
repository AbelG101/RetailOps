package com.retailOpsV1.RetailOps.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "branch_manager")
public class BranchManager {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private long branch_managerID;

//    public Branch getBranch() {
//        return branch;}
//
//    public void setBranch(Branch branch) {
//        this.branch = branch;
//    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branchId")
    private Branch branch;

//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }

    @OneToOne(cascade = CascadeType.ALL)
 @JoinColumn(name = "employeeId")
   private Employee employee;


//    public void setBranchName(Branch branch) {
//    }

//    public Branch getBranchName() {
//        return null;
//    }
}
