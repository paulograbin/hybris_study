package com.paulograbin.core.workflow;

import de.hybris.platform.catalog.synchronization.CatalogSynchronizationService;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.workflow.jobs.AutomatedWorkflowTemplateJob;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.Map;


public abstract class AbstractSurvivorshipActionJob implements AutomatedWorkflowTemplateJob {

    private static final Logger LOGGER = Logger.getLogger(AbstractSurvivorshipActionJob.class);


    @Resource
    protected UserService userService;

    private CatalogSynchronizationService catalogSynchronizationService;
    private CronJobService cronJobService;
    private Map<String, String> sourceToCatalogMap;








    public CatalogSynchronizationService getCatalogSynchronizationService() {
        return catalogSynchronizationService;
    }

    public void setCatalogSynchronizationService(CatalogSynchronizationService catalogSynchronizationService) {
        this.catalogSynchronizationService = catalogSynchronizationService;
    }

    public CronJobService getCronJobService() {
        return cronJobService;
    }

    public void setCronJobService(CronJobService cronJobService) {
        this.cronJobService = cronJobService;
    }

    public Map<String, String> getSourceToCatalogMap() {
        return sourceToCatalogMap;
    }

    public void setSourceToCatalogMap(Map<String, String> sourceToCatalogMap) {
        this.sourceToCatalogMap = sourceToCatalogMap;
    }
}
