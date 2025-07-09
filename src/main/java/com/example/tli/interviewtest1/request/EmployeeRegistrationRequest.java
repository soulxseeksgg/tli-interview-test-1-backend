package com.example.tli.interviewtest1.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRegistrationRequest {
    private String firstName;
    private String lastName;
    private String nationalId;
    private LocalDate birthDate;
    private String contactInfo;
}
