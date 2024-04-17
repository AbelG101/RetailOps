package com.retailOpsV1.RetailOps.service;

import com.retailOpsV1.RetailOps.model.DTOs.EmployeeDTO;
import com.retailOpsV1.RetailOps.model.DTOs.authentication.AuthenticationRequest;
import com.retailOpsV1.RetailOps.model.DTOs.authentication.AuthenticationResponse;
import com.retailOpsV1.RetailOps.model.Employee;
import com.retailOpsV1.RetailOps.repository.EmployeeRepository;
import com.retailOpsV1.RetailOps.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(EmployeeDTO request) {
        if (employeeRepository.findByUserName(request.getUserName()).isPresent()) {
            throw new IllegalStateException("Username is already taken");
        }
        // TODO: Add validation for checking whether phone number exists in the db and role Id is valid
        var employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .hireDate(request.getHireDate())
                .phoneNumber(request.getPhoneNumber())
                .salary(request.getSalary())
                .role(roleRepository.findByRoleId(request.getRoleId()))
                .build();
        employeeRepository.save(employee);
        var jwtToken = jwtService.generateToken(employee);
        var refreshToken = jwtService.generateRefreshToken(employee);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .userName(employee.getUsername())
                .roleName(employee.getRole().getName())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUserName(),
                    request.getPassword()
            )
        );
        var employee = employeeRepository.findByUserName(request.getUserName())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(employee);
        var refreshToken = jwtService.generateRefreshToken(employee);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .userName(employee.getUsername())
                .roleName(employee.getRole().getName())
                .build();
    }

    public List<Employee> getAllEmployees(String authHeader) {
        String token = authHeader.substring(7);
        final String loggedInUserName = jwtService.extractUserName(token);
        String loggedInUserRole = employeeRepository.findByUserName(loggedInUserName).get().getRole().getName();
        if (isNotAdminOrStoreManager(loggedInUserRole)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Error: You don't have permission to fetch all employee data.");
        }
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Integer employeeId, String authHeader) {
        Employee employee = getValidatedEmployee(employeeId, authHeader);
        return employee;
    }

    public void deleteEmployee(Integer employeeId, String authHeader) {
        Employee employee = getValidatedEmployee(employeeId, authHeader);
        employeeRepository.deleteById(employee.getEmployeeId());
    }

    public Employee updateEmployee(Integer employeeId, EmployeeDTO updateRequest, String authHeader) {
        if (employeeId == null || updateRequest == null || authHeader == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input parameters");
        }
        Employee originalEmployeeAccount = getValidatedEmployee(employeeId, authHeader);

        // TODO: add branchId update when the branch module is finished
        // Update only if fields are provided in the update request
        if (updateRequest.getPassword() != null)
            originalEmployeeAccount.setPassword(passwordEncoder.encode(updateRequest.getPassword()));
        if (updateRequest.getLastName() != null)
            originalEmployeeAccount.setLastName(updateRequest.getLastName());
        if (updateRequest.getFirstName() != null)
            originalEmployeeAccount.setFirstName(updateRequest.getFirstName());
        if (updateRequest.getRoleId() != null)
            originalEmployeeAccount.setRole(roleRepository.findByRoleId(updateRequest.getRoleId()));
        if (updateRequest.getHireDate() != null)
            originalEmployeeAccount.setHireDate(updateRequest.getHireDate());
        if (updateRequest.getUserName() != null)
            originalEmployeeAccount.setUserName(updateRequest.getUserName());
        if (updateRequest.getSalary() != 0.0f)
            originalEmployeeAccount.setSalary(updateRequest.getSalary());
        if (updateRequest.getPhoneNumber() != null)
            originalEmployeeAccount.setPhoneNumber(updateRequest.getPhoneNumber());
        
        return employeeRepository.save(originalEmployeeAccount);
    }

    private Employee getValidatedEmployee(Integer employeeId, String authHeader) throws IllegalStateException {
        Employee userToBeAccessed = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Employee with id " + employeeId + " not found"));

        final String tokenPrefix = "Bearer ";

        String token = authHeader.substring(tokenPrefix.length());
        final String loggedInUserName = jwtService.extractUserName(token);
        Employee loggedInUser = employeeRepository.findByUserName(loggedInUserName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Logged-in user not found"));

        String loggedInUserRole = loggedInUser.getRole().getName();

        if (!loggedInUserName.equalsIgnoreCase(userToBeAccessed.getUsername()) &&
                isNotAdminOrStoreManager(loggedInUserRole)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN ,"Error: You don't have permission to access that account");
        }

        return userToBeAccessed;
    }

    private boolean isNotAdminOrStoreManager(String role) {
        return !role.equalsIgnoreCase("admin") && !role.equalsIgnoreCase("store manager");
    }
}
