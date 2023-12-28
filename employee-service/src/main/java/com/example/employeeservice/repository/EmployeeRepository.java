package com.example.employeeservice.repository;

import com.example.employeeservice.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    private List<Employee> employeeList = new ArrayList<>();

    //add employee
    public Employee addEmployee(Employee newEmployee) {
        employeeList.add(newEmployee);
        return newEmployee;
    }

    //get all
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    //get by id
    public Employee getEmployeeById(Long id) {
        return employeeList.stream()
                .filter(employee -> employee.id().equals(id))
                .findFirst()
                .orElseThrow();

    }

    //get employees based on department
    public List<Employee> getEmployeeInDepartment(Long id){
        return employeeList.stream()
                .filter(a -> a.departmentId().equals(id))
                .toList();
    }
}