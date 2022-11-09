package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.model.Employee;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO getEmployeeById(Long employeeId);
    void deleteEmployee(Long employeeId);

}
