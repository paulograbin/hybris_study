package com.paulograbin.core.cronjobs;

import de.hybris.platform.core.PK;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.event.events.BeforeCronJobStartEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;


public class GrabinBeforeCronJobStartEventListener extends AbstractEventListener<BeforeCronJobStartEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(GrabinBeforeCronJobStartEventListener.class);

    @Resource
    private ModelService modelService;

    @Override
    protected void onEvent(BeforeCronJobStartEvent beforeCronJobStartEvent) {
        LOG.info("Starting.............................");

        String cronJob = beforeCronJobStartEvent.getCronJob();
        LOG.info("Cronjob: " + cronJob);
        PK cronJobPK = beforeCronJobStartEvent.getCronJobPK();
        LOG.info("Cronjob PK: " + cronJobPK);
        String cronJobType = beforeCronJobStartEvent.getCronJobType();
        LOG.info("Cronjob Type: " + cronJobType);

        String job = beforeCronJobStartEvent.getJob();
        LOG.info("Job: " + job);
        String jobType = beforeCronJobStartEvent.getJobType();
        LOG.info("Job type: " + jobType);

        boolean scheduled = beforeCronJobStartEvent.isScheduled();
        LOG.info("scheduled: " + scheduled);
        PK scheduledByTriggerPk = beforeCronJobStartEvent.getScheduledByTriggerPk();
        LOG.info("scheduledByTriggerPk: " + scheduledByTriggerPk);
        boolean synchronous = beforeCronJobStartEvent.isSynchronous();
        LOG.info("synchronous: " + synchronous);

        LOG.info("Finished.............................");
    }
}
