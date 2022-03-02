package com.paulograbin.core.process;

import de.hybris.platform.orderprocessing.model.GrabinProcessModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.task.RetryLaterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrabinAlertOwnerAction extends AbstractProceduralAction<GrabinProcessModel> {

    private static final Logger LOG = LoggerFactory.getLogger(GrabinAlertOwnerAction.class);

    @Override
    public void executeAction(GrabinProcessModel grabinProcessModel) throws RetryLaterException, Exception {
        LOG.info("Processo comecou ............................................");
        LOG.info("Processo comecou ............................................");
        LOG.info("Processo comecou ............................................");
        LOG.info("Processo comecou ............................................");
        LOG.info("Processo comecou ............................................");
    }
}
