package com.paulograbin.core.tew.services;

import com.paulograbin.core.model.ProjectModel;
import com.paulograbin.core.tew.dao.DefaultProjectDao;
import de.hybris.platform.servicelayer.cronjob.CronJobHistoryService;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DefaultGrabinService implements GrabinService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultGrabinService.class);


    @Resource
    DefaultProjectDao projectDao;

    @Resource
    DefaultGenericDao<ProjectModel> anotherProjectDao;

    CronJobService cronJobService;
    CronJobHistoryService cronJobHistoryService;
    ModelService modelService;


    static {
        LOG.info("Static");
    }

    @Autowired
    public DefaultGrabinService(CronJobService cronJobService, CronJobHistoryService cronJobHistoryService) {
        LOG.info("Constructor");

        this.cronJobService = cronJobService;
        this.cronJobHistoryService = cronJobHistoryService;
    }


    @Override
    public List<ProjectModel> fetchAllProjects() {
        return projectDao.findAllProjects();
    }


    @Override
    public ProjectModel fetchSingleProjectById(Integer projectId) {
        return projectDao.findProjectById(projectId);
    }

    @Override
    public ProjectModel toggleReady(Integer projectId) {
        ProjectModel projectById1 = this.fetchSingleProjectById(projectId);

        projectById1.setReady(!projectById1.getReady());
        modelService.save(projectById1);

        return projectById1;
    }













    @Override
    public void triggerJob() {
        cronJobService.getCronJob("aaa");
    }


    @Override
    public void go() {
        LOG.info("Gone going...");

        List<ProjectModel> allProjectsUnapproved = projectDao.findAllProjectsUnapproved();
        List<ProjectModel> allProjectsNotReady = projectDao.findAllProjectsNotReady();

        List<ProjectModel> allProjectsApproved = projectDao.findAllProjectsApproved();
        List<ProjectModel> allProjectsReady = projectDao.findAllProjectsReady();

        Map<String, Boolean> params = new HashMap<>(3);
        params.put(ProjectModel.APPROVED, Boolean.FALSE);
        List<ProjectModel> anotherApprovedListOfProjects = anotherProjectDao.find(params);
    }

    public void setModelService(ModelService modelService) {
        LOG.info("Setter setModelService");

        this.modelService = modelService;
    }

    @Override
    public void setRandomNumberIfNotExisting(ProjectModel projectModel) {
        LOG.info("Evaluating random number setting for project " + projectModel.getProjectName());

        int i = RandomUtils.nextInt(1000);

        if (projectModel.getRandomNumber() == null) {
            LOG.info("Found one without random number, so set it as " + i);

            projectModel.setRandomNumber(i);
            modelService.save(projectModel);
        }
    }
}
