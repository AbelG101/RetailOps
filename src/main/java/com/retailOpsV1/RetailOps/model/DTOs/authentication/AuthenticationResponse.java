package com.retailOpsV1.RetailOps.model.DTOs.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String userName;
    private String roleName;
    private String supplierName;
    private String contactPerson;
    private String email;
    private String phoneNumber;
}