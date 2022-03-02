package com.paulograbin.core.tew.jobs;

import com.paulograbin.core.model.ProjectModel;
import com.paulograbin.core.tew.dao.ProjectDao;
import com.paulograbin.core.tew.services.GrabinService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.jalo.CronJobProgressTracker;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


public class GrabinRandomNumberSetterJob extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = LoggerFactory.getLogger(GrabinRandomNumberSetterJob.class);

    private static final int RESULT_COUNT = 10;


    @Resource
    private GrabinService defaultGrabinService;

    @Resource
    private ProjectDao projectDao;


    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        LOG.info("Executing cronjob at " + LocalDateTime.now());

        List<ProjectModel> projectsLimitedByCount = fetchProjectsToProcess();

        if (projectsLimitedByCount.size() > RESULT_COUNT) {
            LOG.error("Oloko deu erro aqui...");
            return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
        }

        if (projectsLimitedByCount.isEmpty()) {
            LOG.info("Not project found without random number, so we do nothing...");
            LOG.info("Not project found without random number, so we do nothing...");
            LOG.info("Not project found without random number, so we do nothing...");
            LOG.info("Not project found without random number, so we do nothing...");

            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        }

        return processJobs(cronJobModel, projectsLimitedByCount);
    }

    private PerformResult processJobs(CronJobModel cronJobModel, List<ProjectModel> projectsLimitedByCount) {
        for (ProjectModel projectModel : projectsLimitedByCount) {
//            if (cronJobModel.getRequestAbort()) {
//                LOG.info("This cronjob was aborted, returning...");
//                return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.ABORTED);
//            }

            defaultGrabinService.setRandomNumberIfNotExisting(projectModel);
        }
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    private List<ProjectModel> fetchProjectsToProcess() {
        return projectDao.findProjectsLimitedByCount(RESULT_COUNT);
    }

    @Override
    public boolean isAbortable() {
        return true;
    }
}
