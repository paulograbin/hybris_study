package com.paulograbin.core.workflow;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;
import de.hybris.platform.workflow.model.WorkflowItemAttachmentModel;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;


public class SurvivorshipRejectActionJob extends AbstractSurvivorshipActionJob {

    private static final Logger LOGGER = Logger.getLogger(SurvivorshipRejectActionJob.class);

    @Override
    public WorkflowDecisionModel perform(WorkflowActionModel action) {
        LOGGER.info(" &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
        LOGGER.info(" &&&& PERFORMING &&&& ");
        LOGGER.info(" &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");

        action.setPrincipalAssigned(userService.getAdminUser());


        List<WorkflowItemAttachmentModel> attachments = action.getAttachments();
        List<ItemModel> attachmentItems = action.getAttachmentItems();

        LOGGER.info("Attachments " + attachmentItems.size());
        LOGGER.info("Items " + attachmentItems.size());


        Collection<WorkflowDecisionModel> decisions = action.getDecisions();
        for (WorkflowDecisionModel decision : decisions) {
            return decision;
        }

        return null;
    }
}
