package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public void createEmployee(@RequestBody Employee employee) throws Exception {
        this.employeeService.createEmployee(employee);
    }

    @PutMapping
    public void updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        this.employeeService.updateEmployee(employeeDTO);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployee(){
        return this.employeeService.getAllEmployee();
    }

    @GetMapping("{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return this.employeeService.getEmployeeById(employeeId);
    }

    @DeleteMapping("{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId){
        this.employeeService.deleteEmployee(employeeId);
    }





}
