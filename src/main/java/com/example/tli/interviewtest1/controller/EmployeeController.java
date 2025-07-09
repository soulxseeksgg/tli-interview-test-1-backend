package com.example.tli.interviewtest1.controller;

import com.example.tli.interviewtest1.business.EmployeeBusiness;
import com.example.tli.interviewtest1.entity.Employment;
import com.example.tli.interviewtest1.entity.UserProfile;
import com.example.tli.interviewtest1.request.EmployeeRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeBusiness employeeBusiness;

    @Autowired
    public EmployeeController(EmployeeBusiness employeeBusiness) {
        this.employeeBusiness = employeeBusiness;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeRegistrationRequest request) {
        UserProfile employee = employeeBusiness.createEmployee(request);
        return ResponseEntity.ok("Employee registered successfully");
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<UserProfile> getEmployeeById(@PathVariable Long id) {
        return employeeBusiness.findUserProfileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/search/{id}/personal")
    public ResponseEntity<UserProfile> updatePersonalInfo(@PathVariable Long id, @RequestBody UserProfile updatedInfo) {
        return employeeBusiness.updatePersonalInfo(id, updatedInfo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/employment")
    public ResponseEntity<Employment> updateEmployment(
            @PathVariable Long id,
            @RequestBody Employment updatedEmployment) {
        return employeeBusiness.updateEmployment(id, updatedEmployment)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}



