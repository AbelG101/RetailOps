package com.retailOpsV1.RetailOps.controller;

import com.retailOpsV1.RetailOps.model.DTOs.EmployeeDTO;
import com.retailOpsV1.RetailOps.model.DTOs.authentication.AuthenticationRequest;
import com.retailOpsV1.RetailOps.model.DTOs.authentication.AuthenticationResponse;
import com.retailOpsV1.RetailOps.model.Employee;
import com.retailOpsV1.RetailOps.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestHeader("Authorization") String authHeader) {
        List<Employee> employees = employeeService.getAllEmployees(authHeader);
        return ResponseEntity.ok(employees);
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody EmployeeDTO request) {
        return ResponseEntity.ok(employeeService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(employeeService.authenticate(request));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer employeeId, @RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(employeeService.getEmployee(employeeId, authHeader));
    }

    @DeleteMapping("/delete/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId, @RequestHeader("Authorization") String authHeader) {
        employeeService.deleteEmployee(employeeId, authHeader);
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer employeeId, @RequestBody EmployeeDTO updateRequest, @RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, updateRequest, authHeader));
    }
}
