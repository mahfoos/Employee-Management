package com.example.employeemanagement.dto;

import com.example.employeemanagement.model.Department;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Department department;
}
