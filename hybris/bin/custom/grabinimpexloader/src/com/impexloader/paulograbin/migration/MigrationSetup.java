package com.impexloader.paulograbin.migration;

import com.impexloader.paulograbin.service.GrabinimpexloaderService;
import de.hybris.platform.servicelayer.event.events.AfterInitializationEndEvent;
import de.hybris.platform.validation.events.AfterInitializationEndEventListener;
import de.hybris.platform.validation.services.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MigrationSetup extends AfterInitializationEndEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(MigrationSetup.class);

    private GrabinimpexloaderService grabinimpexloaderService;

    public MigrationSetup(GrabinimpexloaderService grabinimpexloaderService) {
        this.grabinimpexloaderService = grabinimpexloaderService;
    }

    @Override
    protected void onEvent(AfterInitializationEndEvent event) {
        LOG.info("************************** MIGRATION STARTED **************************");
        LOG.info("************************** MIGRATION STARTED **************************");
        LOG.info("************************** MIGRATION STARTED **************************");
        LOG.info("************************** MIGRATION STARTED **************************");
        LOG.info("************************** MIGRATION STARTED **************************");
        LOG.info("************************** MIGRATION STARTED **************************");
        LOG.info("************************** MIGRATION STARTED **************************");
        LOG.info("************************** MIGRATION STARTED **************************");
        LOG.info("************************** MIGRATION STARTED **************************");
        LOG.info("************************** MIGRATION STARTED **************************");
        LOG.info("************************** MIGRATION STARTED **************************");
        LOG.info("************************** MIGRATION STARTED **************************");
        LOG.info("************************** MIGRATION STARTED **************************");

//        update running system: initmethod = update

        super.onEvent(event);
    }

    @Override
    public ValidationService getValidationService() {
        return super.getValidationService();
    }
}
