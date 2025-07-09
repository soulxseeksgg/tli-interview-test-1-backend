package com.example.tli.interviewtest1.repository;

import com.example.tli.interviewtest1.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Long> {
    Optional<Employment> findByUserProfileId(Long userProfileId);
}
