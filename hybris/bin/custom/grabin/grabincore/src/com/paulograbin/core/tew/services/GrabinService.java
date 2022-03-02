package com.paulograbin.core.tew.services;

import com.paulograbin.core.model.ProjectModel;

import java.util.List;

public interface GrabinService {
    List<ProjectModel> fetchAllProjects();

    ProjectModel fetchSingleProjectById(Integer projectId);

    ProjectModel toggleReady(Integer projectId);

    void triggerJob();

    void go();

    void setRandomNumberIfNotExisting(ProjectModel projectModel);
}
