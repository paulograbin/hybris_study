package com.paulograbin.facades.tew;

import java.util.List;


public interface ProjectFacade {

    List<ProjectData> loadAll();

    ProjectData loadProjectById(Integer projectId);

    ProjectData toggleReady(Integer projectId);
}
