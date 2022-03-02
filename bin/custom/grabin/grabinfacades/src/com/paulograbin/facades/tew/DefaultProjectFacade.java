package com.paulograbin.facades.tew;

import com.paulograbin.core.model.ProjectModel;
import com.paulograbin.core.tew.services.GrabinService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;


public class DefaultProjectFacade implements ProjectFacade {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultProjectFacade.class);

    private GrabinService grabinService;
    private Converter<ProjectModel, ProjectData> projectConverter;


    @Override
    public List<ProjectData> loadAll() {
        List<ProjectModel> projectModels = grabinService.fetchAllProjects();

        List<ProjectData> projectData = projectConverter.convertAll(projectModels);

        return projectData;
    }

    @Override
    public ProjectData loadProjectById(Integer projectId) {
        ProjectModel projectModel = grabinService.fetchSingleProjectById(projectId);

        return projectConverter.convert(projectModel);
    }

    @Override
    public ProjectData toggleReady(Integer projectId) {
        ProjectModel projectModel = grabinService.toggleReady(projectId);

        return projectConverter.convert(projectModel);
    }

    @Required
    public void setGrabinService(GrabinService grabinService) {
        this.grabinService = grabinService;
    }

    @Required
    public void setProjectConverter(Converter<ProjectModel, ProjectData> projectConverter) {
        this.projectConverter = projectConverter;
    }
}
