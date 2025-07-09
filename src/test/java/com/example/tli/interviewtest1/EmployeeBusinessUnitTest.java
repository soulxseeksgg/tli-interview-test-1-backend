package com.example.tli.interviewtest1;

import com.example.tli.interviewtest1.business.EmployeeBusiness;
import com.example.tli.interviewtest1.entity.UserProfile;
import com.example.tli.interviewtest1.request.EmployeeRegistrationRequest;
import com.example.tli.interviewtest1.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeBusinessUnitTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeBusiness employeeBusiness;

    @Test
    void testCreateEmployee_mockSave() {
        EmployeeRegistrationRequest request = new EmployeeRegistrationRequest();
        request.setFirstName("Emily");
        request.setLastName("Johnson");
        request.setNationalId("9876543210987");
        request.setBirthDate(LocalDate.of(1985, 8, 15));
        request.setContactInfo("emily.johnson@example.com");

        UserProfile mockSaved = new UserProfile();
        mockSaved.setId(2L);
        mockSaved.setFirstName(request.getFirstName());
        mockSaved.setLastName(request.getLastName());
        mockSaved.setNationalId(request.getNationalId());
        mockSaved.setBirthDate(request.getBirthDate());
        mockSaved.setContactInfo(request.getContactInfo());

        when(employeeService.saveUserProfile(any())).thenReturn(mockSaved);

        UserProfile result = employeeBusiness.createEmployee(request);

        assertNotNull(result, "Result should not be null");
        assertEquals(2L, result.getId());
        assertEquals("Emily", result.getFirstName());
        assertEquals("9876543210987", result.getNationalId());

        verify(employeeService, times(1)).saveUserProfile(any());
    }
}

