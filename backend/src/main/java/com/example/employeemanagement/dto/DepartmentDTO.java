package com.example.employeemanagement.dto;

import com.example.employeemanagement.model.Employee;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentDTO {
    private Long departmentId;
    private String name;
    private List<Employee> employeeList;
}
