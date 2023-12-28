package com.example.employeeservice.controller;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping
    public Employee addEmployee(Employee employee) {
        LOGGER.info("Adding new employee: {}", employee);
        return employeeRepository.addEmployee(employee);
    }

    @GetMapping
    public List<Employee> allEmployees() {
        LOGGER.info("returning all employees");
        return employeeRepository.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee employeeById(@PathVariable Long id) {
        LOGGER.info("Getting employee with id: {}", id);
        return employeeRepository.getEmployeeById(id);

    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> employeesByDepartmentId(@PathVariable Long departmentId) {
        LOGGER.info("Getting employees based on department id: {}", departmentId);
        return employeeRepository.getEmployeeInDepartment(departmentId);
    }

}
