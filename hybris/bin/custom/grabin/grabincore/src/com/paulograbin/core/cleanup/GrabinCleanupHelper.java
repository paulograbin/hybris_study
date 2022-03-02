package com.paulograbin.core.cleanup;

import de.hybris.platform.acceleratorservices.dataimport.batch.task.CleanupHelper;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.cronjob.model.JobModel;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


public abstract class GrabinCleanupHelper extends CleanupHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrabinCleanupHelper.class);

    @Resource
    private CronJobService cronJobService;

    @Resource
    private ModelService modelService;

    @Resource
    private UserService userService;

    private List<CronJobStatus> cronJobStatuses = Arrays.asList(CronJobStatus.RUNNING, CronJobStatus.PAUSED, CronJobStatus.RUNNINGRESTART);


    protected void startCronJob(final String jobName) {
        final JobModel job = cronJobService.getJob(jobName);
        if (!job.getActive()) {
            LOGGER.info("Job " + jobName + " is inactive. Will not be executed");
            return;
        }
        LOGGER.info("Starting job " + jobName);
        final CronJobModel cronJob = getCronJobModel(jobName, job);
        modelService.save(cronJob);
        cronJobService.performCronJob(cronJob, false);
        LOGGER.info("Cronjob " + jobName + " launched");
    }

    private CronJobModel getCronJobModel(final String jobName, final JobModel job) {
        final CronJobModel cronJob = modelService.create(CronJobModel.class);
        cronJob.setJob(job);
        cronJob.setCode(jobName + "_" + System.currentTimeMillis());
        cronJob.setSessionUser(userService.getAdminUser());
        cronJob.setRemoveOnExit(Boolean.TRUE);
        return cronJob;
    }

    /**
     * Block of code for executing Cron job, After Checking if existing instance is running or not ...
     *
     * @param cronJobName
     */
    protected void checkStatusAndStartCronJob(String cronJobName) {
        CronJobModel cronjob = cronJobService.getCronJob(cronJobName);
        if (cronJobStatuses.contains(cronjob.getStatus())) {
            LOGGER.debug("Cronjob {} in status {} is in progress, will not trigger it again ", cronJobName, cronjob.getStatus().getCode());
        } else {
            cronJobService.performCronJob(cronjob, false);
        }
    }

}
