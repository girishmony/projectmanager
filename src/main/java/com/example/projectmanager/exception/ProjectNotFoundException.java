package com.example.projectmanager.exception;

public class ProjectNotFoundException extends NotFoundException {
    public ProjectNotFoundException(String projectName) {
        super("No employee allocated to project "+projectName+" or project does not exist");
    }
}
