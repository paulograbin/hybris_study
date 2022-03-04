package com.paulograbin.contentmigrator.actions;


import com.hybris.backoffice.widgets.notificationarea.NotificationService;
import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.paulograbin.contentmigrator.impex.DefaultImpexSpitterFactory;
import de.hybris.platform.impex.model.ImpExMediaModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;
import org.zkoss.zhtml.Filedownload;

import javax.annotation.Resource;
import java.io.InputStream;
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
        ActionContext<LinkedHashSet> ctx1 = ctx;
//        final ItemModel data = ctx1.getData();
        if (ctx1 != null) {

//            ExportResult export = impexSpitterFactory.export(data);

//            executeMediaDownload(export.getExportedData());

//            todo: work on the return type
//            return new ActionResult<>(ActionResult.SUCCESS, ctx.getLabel("message", new Object[]{data}));
            return new ActionResult<>(ActionResult.SUCCESS, ctx.getLabel("message", new Object[]{}));

        } else {
            return new ActionResult(ActionResult.ERROR);
        }
    }

    protected void executeMediaDownload(ImpExMediaModel media) {
        InputStream mediaStream = this.mediaService.getStreamFromMedia(media);
        String mime = media.getMime();
        String fileName = media.getCode();
        this.executeBrowserMediaDownload(mediaStream, mime, fileName);
    }

    protected void executeBrowserMediaDownload(InputStream mediaStream, String mime, String fileName) {
        Filedownload.save(mediaStream, mime, fileName);
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
