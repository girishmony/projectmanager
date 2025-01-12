package com.example.projectmanager.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDate;

@Document(collection = "project")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project {

    @Id
    private String id;

    private String accountName;

    private String projectName;

    private Float allocation;

    private LocalDate ProjectStartDate;
    private LocalDate ProjectEndDate;

    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Float getAllocation() {
        return allocation;
    }

    public void setAllocation(Float allocation) {
        this.allocation = allocation;
    }

    public LocalDate getProjectStartDate() {
        return ProjectStartDate;
    }

    public void setProjectStartDate(LocalDate projectStartDate) {
        ProjectStartDate = projectStartDate;
    }

    public LocalDate getProjectEndDate() {
        return ProjectEndDate;
    }

    public void setProjectEndDate(LocalDate projectEndDate) {
        ProjectEndDate = projectEndDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
