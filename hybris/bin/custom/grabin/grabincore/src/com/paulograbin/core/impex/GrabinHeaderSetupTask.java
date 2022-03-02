package com.paulograbin.core.impex;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.HeaderSetupTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Optional;

public class GrabinHeaderSetupTask extends HeaderSetupTask {

    private static final Logger LOG = LoggerFactory.getLogger(GrabinHeaderSetupTask.class);


    /**
     * Initially creates the header.
     *
     * @param file
     * @return the header
     */
    @Override
    public BatchHeader execute(File file) {
        LOG.info("Setup header task");

        return super.execute(file);
    }
}
