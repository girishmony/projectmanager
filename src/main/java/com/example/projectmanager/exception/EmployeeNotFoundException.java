package com.example.projectmanager.exception;

public class EmployeeNotFoundException extends NotFoundException {
    public EmployeeNotFoundException(String primarySkill, String secondarySkill) {
        super("Employee not found with primary skill: "+primarySkill+" and secondary skill: "+secondarySkill);
    }

    public EmployeeNotFoundException(String employeeId) {
        super("Employee not found with id: "+employeeId);
    }
}
