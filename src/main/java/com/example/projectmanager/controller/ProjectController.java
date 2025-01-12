package com.example.projectmanager.controller;

import com.example.projectmanager.domain.Project;
import com.example.projectmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/project")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        projectService.addProject(project);
        return ResponseEntity.created(URI.create("/")).build();
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getProjects() {
        List<Project> projects = projectService.getProjects();
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    @PutMapping("/project")
    public ResponseEntity<Project> updateProject(@RequestBody Project project) {
        projectService.addProject(project);
        return ResponseEntity.created(URI.create("/")).build();
    }
}
