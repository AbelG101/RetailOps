package com.retailOpsV1.RetailOps.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private float salary;
    private String phoneNumber;
    private Date hireDate;
    private Integer roleId;
}
