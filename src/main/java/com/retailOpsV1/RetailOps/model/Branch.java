package com.retailOpsV1.RetailOps.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "branch")
public class Branch {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer branchId;
    private String branchName;
    private String location;

 @OneToOne(mappedBy = "branch",cascade = CascadeType.ALL)
//@JoinColumn(name = "branchID", referencedColumnName = "branchID")
  private BranchManager branchManager;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public void setBranchID(Integer id) {
    }


//    public void setBranchID(Integer id) {
//    }


    //getter ans setter method
}
