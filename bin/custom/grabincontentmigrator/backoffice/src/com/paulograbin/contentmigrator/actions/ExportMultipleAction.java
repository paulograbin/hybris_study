package com.paulograbin.contentmigrator.actions;

import com.hybris.backoffice.widgets.notificationarea.NotificationService;
import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.paulograbin.contentmigrator.impex.DefaultImpexSpitterFactory;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.impex.ExportResult;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.LinkedHashSet;


public class ExportMultipleAction implements CockpitAction<LinkedHashSet, String> {
    private static final Logger LOG = Logger.getLogger(ExportMultipleAction.class);

    private static final String CONFIRMATION_MESSAGE = "hmc.action.confirmpickup.confirmation.message";
    private static final String CONFIRM_PICKUP_EVENT = "grabinbackoffice.confirmpickup.event";

//    todo remove unnused attributes

    @Resource(name = "modelService")
    private ModelService modelService;

    @Resource(name = "businessProcessService")
    private BusinessProcessService businessProcessService;

    @Resource(name = "notificationService")
    private NotificationService notificationService;

    @Resource(name = "impexSpitterFactory")
    private DefaultImpexSpitterFactory impexSpitterFactory;

    @Resource(name = "mediaService")
    private MediaService mediaService;


    @Override
    public boolean canPerform(final ActionContext<LinkedHashSet> ctx) {
        LOG.info("Can Perform...");

        return true;
    }

    @Override
    public ActionResult<String> perform(final ActionContext<LinkedHashSet> ctx) {
        if (ctx != null) {

            LinkedHashSet dataToExport = ctx.getData();
            LOG.info("Data to export " + dataToExport.size());

            if (dataToExport.isEmpty()) {
//                Export every model instance

            } else {
                if (dataToExport.size() == 1) {
                    LOG.info("Exporting single model...");
                    ExportResult export = impexSpitterFactory.export((ItemModel) dataToExport.iterator().next());

                    FileDownloadHelper.executeMediaDownload(mediaService, export.getExportedData());
                } else {

                }
            }

//            ExportResult export = impexSpitterFactory.export(dataToExport);

//            executeMediaDownload(export.getExportedData());

//            todo: work on the return type
//            return new ActionResult<>(ActionResult.SUCCESS, ctx.getLabel("message", new Object[]{dataToExport}));
            return new ActionResult<>(ActionResult.SUCCESS, ctx.getLabel("message", new Object[]{}));

        } else {
            return new ActionResult(ActionResult.ERROR);
        }
    }

    @Override
    public boolean needsConfirmation(ActionContext<LinkedHashSet> ctx) {
        return false;
    }

    @Override
    public String getConfirmationMessage(ActionContext<LinkedHashSet> ctx) {
        LOG.info("getConfirmationMessage...");

        return ctx.getLabel(CONFIRMATION_MESSAGE);
    }
}
