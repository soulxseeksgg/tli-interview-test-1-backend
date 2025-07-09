package com.example.tli.interviewtest1.service;

import com.example.tli.interviewtest1.entity.Employment;
import com.example.tli.interviewtest1.entity.UserProfile;
import com.example.tli.interviewtest1.repository.EmploymentRepository;
import com.example.tli.interviewtest1.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final UserProfileRepository userProfileRepository;
    private final EmploymentRepository employmentRepository;

    @Autowired
    public EmployeeService(UserProfileRepository userProfileRepository,
                           EmploymentRepository employmentRepository) {
        this.userProfileRepository = userProfileRepository;
        this.employmentRepository = employmentRepository;
    }

    public UserProfile saveUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public Optional<UserProfile> findUserProfileById(Long id) {
        return userProfileRepository.findById(id);
    }

    public Optional<UserProfile> findUserProfileByNationalId(String nationalId) {
        return userProfileRepository.findByNationalId(nationalId);
    }

    public Optional<Employment> findEmploymentByUserProfileId(Long userProfileId) {
        return employmentRepository.findByUserProfileId(userProfileId);
    }

    public Employment saveEmployment(Employment employment) {
        return employmentRepository.save(employment);
    }
}

