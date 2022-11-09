package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.DepartmentDTO;
import com.example.employeemanagement.model.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(Department department);
    Department updateDepartment(DepartmentDTO departmentDTO);
    List<DepartmentDTO> getAllDepartment();
    DepartmentDTO getDepartmentById(Long departmentId);
    void deleteDepartment(Long departmentId);
}
