package com.example.projectmanager.service;

import com.example.projectmanager.domain.Project;
import com.example.projectmanager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public void addProject(Project project) {
        projectRepository.addProject(project);
    }

    public void updateProject(Project project) {
        projectRepository.updateProject(project);
    }

    public List<Project> getProjects() {
        return projectRepository.getProjects();
    }
}
