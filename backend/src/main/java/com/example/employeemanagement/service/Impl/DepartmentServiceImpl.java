package com.example.employeemanagement.service.Impl;

import com.example.employeemanagement.dto.DepartmentDTO;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.service.DepartmentService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DozerBeanMapper modelMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public DepartmentServiceImpl(DozerBeanMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(DepartmentDTO departmentDTO) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentDTO.getDepartmentId());
        if (optionalDepartment.isPresent()){
            Department departmentUpdate = optionalDepartment.get();
            departmentUpdate.setDepartmentId(departmentDTO.getDepartmentId());
            departmentUpdate.setName(departmentDTO.getName());
            departmentRepository.save(departmentUpdate);
            return departmentUpdate;
        }else {
            throw new ResourceNotFoundException("Record not Found with ID " + departmentDTO.getDepartmentId());
        }
    }

    @Override
    public List<DepartmentDTO> getAllDepartment() {
        List<Department> departmentList =  departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();

        for (Department department : departmentList){
            List<Employee> employees = employeeRepository.findAllByDepartmentId(department.getDepartmentId());
            DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);
            if(employees.size()>0){
                departmentDTO.setEmployeeList(employees);
            }
            departmentDTOList.add(departmentDTO);
        }
        return departmentDTOList;
    }

    @Override
    public DepartmentDTO getDepartmentById(Long departmentId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isPresent()){
            DepartmentDTO departmentDTO = modelMapper.map(optionalDepartment.get(),DepartmentDTO.class);
            return departmentDTO;
        }else {
            throw new ResourceNotFoundException("Record not Found with ID "+ departmentId);
        }
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isPresent()){
            this.departmentRepository.delete(optionalDepartment.get());
        }else {
            throw new ResourceNotFoundException("Record not Found with ID "+ departmentId);
        }
    }
}
