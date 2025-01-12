package com.example.projectmanager.repository;

import com.example.projectmanager.domain.Employee;
import com.example.projectmanager.domain.Project;
import com.example.projectmanager.exception.EmployeeNotFoundException;
import com.example.projectmanager.exception.NotFoundException;
import com.example.projectmanager.exception.ProjectAllocationException;
import com.example.projectmanager.exception.ProjectNotFoundException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public void addEmployee(Employee employee) {
        mongoTemplate.save(employee);
    }

    public Employee getSecMostExpEmp(String projectName) {
        MatchOperation projectMatch = Aggregation.match(Criteria.where("projects.projectName").is(projectName));
        SortOperation expDescSort = Aggregation.sort(Sort.by("overallExperience").descending());
        SkipOperation skip = Aggregation.skip(1);
        LimitOperation limit = Aggregation.limit(1);
        Aggregation aggregation = Aggregation.newAggregation(projectMatch,expDescSort,skip,limit);
        AggregationResults<Employee> filteredEmpAggResult = mongoTemplate.aggregate(aggregation, "employee", Employee.class);

        Employee filterdEmployee = filteredEmpAggResult.getUniqueMappedResult();
        if(filterdEmployee == null) {
            throw new ProjectNotFoundException(projectName);
        }
        return filterdEmployee;
    }

    public List<Employee> getEmployeeBySkill(String primary, String secondary) {
        Criteria priSkillCriteria = Criteria.where("primarySkill").is(primary);
        Criteria secSkillCriteria = Criteria.where("secondarySkill").is(secondary);
        Query query= new Query();
        query.addCriteria(priSkillCriteria).addCriteria(secSkillCriteria);
        List<Employee> employees = mongoTemplate.find(query, Employee.class);
        if(employees.isEmpty()) {
            throw new EmployeeNotFoundException(primary,secondary);
        }
        return employees;
    }

    public void addProjectAllocation(String employeeId, Project project) {
        Update update = new Update();
        update.push("projects",project);
        Query query = new Query();
        Criteria filterById = Criteria.where("employeeId").is(employeeId);
        query.addCriteria(filterById);
        try {
            UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Employee.class);
            long modifiedCount = updateResult.getModifiedCount();
            if(modifiedCount == 0) {
                throw new EmployeeNotFoundException(employeeId);
            }
        } catch (DataIntegrityViolationException e) {
            throw new ProjectAllocationException("Number of projects cannot exceed 3");
        }
    }

    public void updateProjectAllocation(String employeeId, String projectName, Float allocation) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(employeeId));
        Update update = new Update();
        update.set("projects.$[allocation]",allocation)
                .filterArray(Criteria.where("projectName").is(projectName));
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Employee.class);
        long modifiedCount = updateResult.getModifiedCount();
        if(modifiedCount == 0) {
            throw new EmployeeNotFoundException(employeeId);
        }
    }
}
