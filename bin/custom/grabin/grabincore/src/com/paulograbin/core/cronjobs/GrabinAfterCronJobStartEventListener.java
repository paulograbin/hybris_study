package com.paulograbin.core.cronjobs;

import de.hybris.platform.core.PK;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.event.events.AfterCronJobFinishedEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GrabinAfterCronJobStartEventListener extends AbstractEventListener<AfterCronJobFinishedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(GrabinAfterCronJobStartEventListener.class);


    private ModelService modelService;

    @Override
    protected void onEvent(AfterCronJobFinishedEvent afterCronJobFinishedEvent) {
        LOG.info("Starting.............................");

        String cronJob = afterCronJobFinishedEvent.getCronJob();
        LOG.info("Cronjob: " + cronJob);
        PK cronJobPK = afterCronJobFinishedEvent.getCronJobPK();
        LOG.info("Cronjob PK: " + cronJobPK);
        String cronJobType = afterCronJobFinishedEvent.getCronJobType();
        LOG.info("Cronjob Type: " + cronJobType);

        String job = afterCronJobFinishedEvent.getJob();
        LOG.info("Job: " + job);
        String jobType = afterCronJobFinishedEvent.getJobType();
        LOG.info("Job type: " + jobType);

        boolean scheduled = afterCronJobFinishedEvent.isScheduled();
        LOG.info("scheduled: " + scheduled);
        PK scheduledByTriggerPk = afterCronJobFinishedEvent.getScheduledByTriggerPk();
        LOG.info("scheduledByTriggerPk: " + scheduledByTriggerPk);
        boolean synchronous = afterCronJobFinishedEvent.isSynchronous();
        LOG.info("synchronous: " + synchronous);
        CronJobResult result = afterCronJobFinishedEvent.getResult();
        LOG.info("result: " + result);
        CronJobStatus status = afterCronJobFinishedEvent.getStatus();
        LOG.info("status: " + status);

        LOG.info("Finished.............................");
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
