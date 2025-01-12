package com.example.projectmanager.service;

import com.example.projectmanager.domain.Employee;
import com.example.projectmanager.domain.Project;
import com.example.projectmanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public void addEmployee(Employee employee) {
        employeeRepository.addEmployee(employee);
    }


    public Employee getSecMostExpEmp(String projectName) {
        return employeeRepository.getSecMostExpEmp(projectName);
    }

    public List<Employee> getEmployeeBySkill(String primary, String secondary) {
        return employeeRepository.getEmployeeBySkill(primary,secondary);
    }

    public void addProjectAllocation(String employeeId, Project project) {
        employeeRepository.addProjectAllocation(employeeId,project);
    }

    public void updateProjectAllocation(String employeeId, String projectName, Float allocation) {
        employeeRepository.updateProjectAllocation(employeeId,projectName,allocation);
    }
}
