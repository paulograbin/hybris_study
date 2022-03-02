package com.paulograbin.core.tew.dao;

import com.paulograbin.core.model.ProjectModel;

import java.util.List;

public interface ProjectDao {
    List<ProjectModel> findProjectsLimitedByCount(int resultCountLimit);

    List<ProjectModel> findAllProjectsUnapproved();

    ProjectModel findProjectById(Integer projectId);

    List<ProjectModel> findAllProjectsNotReady();

    List<ProjectModel> findAllProjectsApproved();

    List<ProjectModel> findAllProjectsReady();

    List<ProjectModel> findAllProjects();
}
