package com.example.projectmanager.controller;

import com.example.projectmanager.domain.Employee;
import com.example.projectmanager.domain.Project;
import com.example.projectmanager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return ResponseEntity.created(URI.create("/")).build();
    }

    @GetMapping("/employee/secmostexp/project/{projectName}")
    public ResponseEntity<Employee> getSecMostExpEmp(@PathVariable String projectName) {
        Employee secMostExpEmp = employeeService.getSecMostExpEmp(projectName);
        return ResponseEntity.status(HttpStatus.OK).body(secMostExpEmp);
    }

    @GetMapping("/employee/skill")
    public ResponseEntity<List<Employee>> getEmployeeBySkill(String primary, String secondary) {
        List<Employee> filteredEmployees = employeeService.getEmployeeBySkill(primary,secondary);
        return ResponseEntity.status(HttpStatus.OK).body(filteredEmployees);
    }

    @PostMapping("/employee/{employeeId}/allocateproject")
    public ResponseEntity<Employee> addProjectAllocation(@PathVariable String employeeId, @RequestBody Project project) {
        employeeService.addProjectAllocation(employeeId,project);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/employee/{employeeId}/project")
    public ResponseEntity<Employee> updateProjectAllocation(@PathVariable String employeeId, String name, Float allocation) {
        employeeService.updateProjectAllocation(employeeId,name,allocation);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
