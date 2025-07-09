package com.example.tli.interviewtest1;

import com.example.tli.interviewtest1.business.EmployeeBusiness;
import com.example.tli.interviewtest1.entity.UserProfile;
import com.example.tli.interviewtest1.repository.UserProfileRepository;
import com.example.tli.interviewtest1.request.EmployeeRegistrationRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class EmployeeServiceIntegrationTest {

    @Autowired
    private EmployeeBusiness employeeBusiness;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Test
    void testCreateEmployee_persistData() {

        EmployeeRegistrationRequest request = new EmployeeRegistrationRequest();
        request.setFirstName("Emily");
        request.setLastName("Johnson");
        request.setNationalId("9876543210987");
        request.setBirthDate(LocalDate.of(1985, 8, 15));
        request.setContactInfo("emily.johnson@example.com");

        UserProfile savedProfile = employeeBusiness.createEmployee(request);

        assertNotNull(savedProfile, "UserProfile should not be null");
        assertNotNull(savedProfile.getId(), "UserProfile ID should not be null");
        assertEquals("Emily", savedProfile.getFirstName());
        assertEquals("9876543210987", savedProfile.getNationalId());

        UserProfile found = userProfileRepository.findById(savedProfile.getId()).orElse(null);
        assertNotNull(found, "UserProfile found in DB should not be null");
        assertEquals("Emily", found.getFirstName());
        assertEquals("9876543210987", found.getNationalId());
        assertEquals(LocalDate.of(1985, 8, 15), found.getBirthDate());
    }
}

