package com.example.projectmanager.repository;

import com.example.projectmanager.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {

    @Autowired
    ProjectMongoRepository projectMongoRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public void addProject(Project project) {
        mongoTemplate.save(project);
    }

    public List<Project> getProjects() {
        return mongoTemplate.findAll(Project.class);
    }

    public void updateProject(Project project) {
        Query query= new Query();
        query.addCriteria(Criteria.where("_id").is(project.getId()));

        Update update = new Update();
        update.set("accountName", project.getAccountName());
        update.set("projectName", project.getProjectName());
        update.set("allocation", project.getAllocation());
        update.set("remarks", project.getRemarks());
        update.set("projectStartDate", project.getProjectStartDate());
        update.set("projectEndDate", project.getProjectEndDate());

        mongoTemplate.findAndModify(query, update, Project.class);
        System.out.println("Data Modified");
    }
}
