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


public class ExportAction implements CockpitAction<ItemModel, String> {
    private static final Logger LOG = Logger.getLogger(ExportAction.class);

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
    public boolean canPerform(final ActionContext<ItemModel> ctx) {
        LOG.info("Can Perform...");

        return true;
    }

    @Override
    public ActionResult<String> perform(final ActionContext<ItemModel> ctx) {
        final ItemModel data = ctx.getData();
        if (data != null) {

            ExportResult export = impexSpitterFactory.export(data);

            ActionResult<Object> objectActionResult = new ActionResult<>(ActionResult.SUCCESS, data);
            objectActionResult.setData("testeeee");
            objectActionResult.setResultCode("success");
            objectActionResult.setData(export.getExportedData().getDownloadURL());

            FileDownloadHelper.executeMediaDownload(this.mediaService, export.getExportedData());

//            todo: work on the return type
            return new ActionResult<>(ActionResult.SUCCESS, ctx.getLabel("message", new Object[]{data}));

        } else {
            return new ActionResult(ActionResult.ERROR);
        }
    }


    @Override
    public boolean needsConfirmation(ActionContext<ItemModel> ctx) {
        return false;
    }

    @Override
    public String getConfirmationMessage(ActionContext<ItemModel> ctx) {
        LOG.info("getConfirmationMessage...");

        return ctx.getLabel(CONFIRMATION_MESSAGE);
    }
}
