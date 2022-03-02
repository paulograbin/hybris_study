package com.paulograbin.backoffice.actions;

import com.hybris.backoffice.widgets.notificationarea.NotificationService;
import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.paulograbin.core.impex.impl.DefaultImpexSpitterFactory;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.impex.model.ImpExMediaModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.impex.ExportResult;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;
import org.zkoss.zul.Filedownload;

import javax.annotation.Resource;
import java.io.InputStream;


public class ExportAction implements CockpitAction<AbstractPageModel, String> {

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
    public boolean canPerform(final ActionContext<AbstractPageModel> ctx) {
        LOG.info("Can Perform...");

        return true;
    }

    @Override
    public ActionResult<String> perform(final ActionContext<AbstractPageModel> ctx) {
        final Object data = ctx.getData();

        if ((data != null) && (data instanceof AbstractPageModel)) {

            AbstractPageModel abstractPageModel = (AbstractPageModel) data;

            ExportResult export = impexSpitterFactory.export(abstractPageModel);

            ActionResult<Object> objectActionResult = new ActionResult<>(ActionResult.SUCCESS, abstractPageModel);
            objectActionResult.setData("testeeee");
            objectActionResult.setResultCode("success");
            objectActionResult.setData(export.getExportedData().getDownloadURL());

//            StringBuilder sb = new StringBuilder();
//            sb.append("Successfull: ").append(export.isSuccessful()).append("\n");
//            sb.append("Exported data: ").append(export.getExportedData().getDownloadURL()).append("\n");
//            sb.append("Exported media: ").append(export.getExportedMedia()).append("\n");
//
//            Messagebox.show(sb.toString());
            executeMediaDownload(export.getExportedData());
//            executeMediaDownload(export.getExportedMedia());

//            todo: work on the return type
            return new ActionResult<String>(ActionResult.SUCCESS, ctx.getLabel("message", new Object[]{data}));

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
    public boolean needsConfirmation(ActionContext<AbstractPageModel> ctx) {
        return false;
    }

    @Override
    public String getConfirmationMessage(ActionContext<AbstractPageModel> ctx) {
        LOG.info("getConfirmationMessage...");

        return ctx.getLabel(CONFIRMATION_MESSAGE);
    }
}
