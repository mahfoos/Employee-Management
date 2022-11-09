package com.example.employeemanagement.service.Impl;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.service.EmployeeService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final DozerBeanMapper modelMapper;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(DozerBeanMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeDTO.getEmployeeId());
        if (optionalEmployee.isPresent()){
            Employee employeeUpdate = optionalEmployee.get();
            employeeUpdate.setEmployeeId(employeeDTO.getEmployeeId());
            employeeUpdate.setFirstName(employeeDTO.getFirstName());
            employeeUpdate.setLastName(employeeDTO.getLastName());
            employeeRepository.save(employeeUpdate);
            return employeeUpdate;
        }else {
            throw new ResourceNotFoundException("Record not Found with ID " + employeeDTO.getEmployeeId());
        }
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employeeList =  employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        for (Employee employee : employeeList){
            Optional<Department> optionalDepartment = departmentRepository.findById(employee.getDepartmentId());
            EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
            employeeDTOList.add(employeeDTO);
            optionalDepartment.ifPresent(employeeDTO::setDepartment);
        }
        return employeeDTOList;
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()){
            EmployeeDTO employeeDTO = modelMapper.map(optionalEmployee.get(),EmployeeDTO.class);
            return employeeDTO;
        }else {
            throw new ResourceNotFoundException("Record not Found with ID "+ employeeId);
        }
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()){
            this.employeeRepository.delete(optionalEmployee.get());
        }else {
            throw new ResourceNotFoundException("Record not Found with ID "+ employeeId);
        }
    }
}
