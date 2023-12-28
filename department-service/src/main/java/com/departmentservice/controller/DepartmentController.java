package com.departmentservice.controller;

import com.departmentservice.client.EmployeeClient;
import com.departmentservice.model.Department;
import com.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository repository;
    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping("/add")
    public Department newDepartment(@RequestBody Department department) {
        LOGGER.info("Department add: {}",department);
        return repository.addDepartment(department);
    }

    @GetMapping("/all")
    public List<Department> findAll() {
        LOGGER.info("Department find all");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        LOGGER.info("Department find by id: {id}");
        return repository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> getDepartmentWithEmployees() {
        List<Department> departments = repository.findAll();
        departments.forEach(department -> {
            department.setEmployees(employeeClient.employeesByDepartmentId(department.getId()));
        });
    }

}
