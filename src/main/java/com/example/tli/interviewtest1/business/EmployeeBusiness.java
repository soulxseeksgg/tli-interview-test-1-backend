package com.example.tli.interviewtest1.business;

import com.example.tli.interviewtest1.entity.Employment;
import com.example.tli.interviewtest1.entity.UserProfile;
import com.example.tli.interviewtest1.request.EmployeeRegistrationRequest;
import com.example.tli.interviewtest1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeBusiness {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeBusiness(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public UserProfile createEmployee(EmployeeRegistrationRequest request) {
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(request.getFirstName());
        userProfile.setLastName(request.getLastName());
        userProfile.setNationalId(request.getNationalId());
        userProfile.setBirthDate(request.getBirthDate());
        userProfile.setContactInfo(request.getContactInfo());

        return employeeService.saveUserProfile(userProfile);
    }

    public Optional<UserProfile> findUserProfileById(Long id) {
        return employeeService.findUserProfileById(id);
    }

    public Optional<UserProfile> updatePersonalInfo(Long id, UserProfile updatedInfo) {
        return employeeService.findUserProfileById(id).map(existing -> {
            existing.setFirstName(updatedInfo.getFirstName());
            existing.setLastName(updatedInfo.getLastName());
            existing.setBirthDate(updatedInfo.getBirthDate());
            existing.setContactInfo(updatedInfo.getContactInfo());
            return employeeService.saveUserProfile(existing);
        });
    }

    public Optional<Employment> updateEmployment(Long userProfileId, Employment updatedEmployment) {
        return employeeService.findEmploymentByUserProfileId(userProfileId).map(existing -> {
            existing.setPosition(updatedEmployment.getPosition());
            existing.setDepartment(updatedEmployment.getDepartment());
            existing.setStartDate(updatedEmployment.getStartDate());
            existing.setEndDate(updatedEmployment.getEndDate());
            return employeeService.saveEmployment(existing);
        });
    }
}

