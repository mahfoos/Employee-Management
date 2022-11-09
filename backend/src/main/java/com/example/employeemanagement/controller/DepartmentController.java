package com.example.employeemanagement.controller;


import com.example.employeemanagement.dto.DepartmentDTO;
import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public void createDepartment(@RequestBody Department department) {
        this.departmentService.createDepartment(department);
    }

    @PutMapping
    public void updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
        this.departmentService.updateDepartment(departmentDTO);
    }

    @GetMapping
    public List<DepartmentDTO> getAllEmployee(){
        return this.departmentService.getAllDepartment();
    }

    @GetMapping("{departmentId}")
    public DepartmentDTO getEmployeeById(@PathVariable Long departmentId){
        return this.departmentService.getDepartmentById(departmentId);
    }

    @DeleteMapping("{departmentId}")
    public void deleteEmployee(@PathVariable Long departmentId){
        this.departmentService.deleteDepartment(departmentId);
    }

}
