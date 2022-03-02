package com.paulograbin.core.cronjobs;

import com.paulograbin.core.model.ProjectModel;
import com.paulograbin.core.tew.services.GrabinService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.jalo.CronJobProgressTracker;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Random;


public class GrabinAlertJob extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = LoggerFactory.getLogger(GrabinAlertJob.class);

    public static final String TELEGRAM_BOT_KEY = "1256753556:AAHv74j5e8xTOVR5vg4b4pZ5x-wHBPWRdWk";
    public static final String TELEGRAM_GROUP_ID = "-438833696";

    @Resource
    private GrabinService defaultGrabinService;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        LOG.debug("Executing cronjob to create a new project at " + LocalDateTime.now());

//        defaultGrabinService.go();

        CronJobProgressTracker progressTracker = new CronJobProgressTracker(modelService.getSource(cronJobModel));

        for (int i = 1; i < 100; i++)
        {
            try
            {
                progressTracker.setProgress(Double.valueOf(i)); // <- set progress
                Thread.sleep(Double.valueOf(10 + (10 * Math.random())).intValue());
            }
            catch (final InterruptedException e)
            {
                return new PerformResult(CronJobResult.FAILURE, CronJobStatus.ABORTED);
            }
        }
        progressTracker.close(); // <- save last progress to the database


        Random random = new Random();
        int randomInt = random.nextInt();

        LOG.debug("Creating new project with random " + randomInt);

        ProjectModel projectModel = (ProjectModel) modelService.create(ProjectModel.class);
        projectModel.setProjectId(randomInt);
        projectModel.setProjectName("Project " + randomInt);
//        projectModel.setReady(false);
//        projectModel.setApproved(false);

        LOG.debug("Information filled");
        modelService.save(projectModel);
        LOG.debug("Saved");

        LOG.debug("Returning OK");

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }



    @Override
    public boolean isAbortable() {
        return true;
    }
}
