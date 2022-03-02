package com.paulograbin.core.cleanup;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;


public class GrabinProjectCleanupHelper extends GrabinCleanupHelper {

    private static final String GRABIN_CREATION_CRON_JOB = "techAttr2ClassAssignCreationJob";

    @Override
    public void cleanup(BatchHeader header, boolean error) {
        super.checkStatusAndStartCronJob(GRABIN_CREATION_CRON_JOB);
        super.cleanup(header, error);
    }
}
