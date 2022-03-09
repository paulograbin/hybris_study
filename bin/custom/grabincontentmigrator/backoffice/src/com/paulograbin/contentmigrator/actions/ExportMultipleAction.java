package com.paulograbin.contentmigrator.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.paulograbin.contentmigrator.impex.DefaultImpexSpitterFactory;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.impex.ExportResult;
import de.hybris.platform.servicelayer.media.MediaService;
import org.apache.log4j.Logger;
import org.zkoss.zhtml.Messagebox;

import javax.annotation.Resource;
import java.util.LinkedHashSet;


public class ExportMultipleAction implements CockpitAction<LinkedHashSet<?>, String> {
    private static final Logger LOG = Logger.getLogger(ExportMultipleAction.class);

    private static final String CONFIRMATION_MESSAGE = "hmc.action.confirmpickup.confirmation.message";
    private static final String CONFIRM_PICKUP_EVENT = "grabinbackoffice.confirmpickup.event";

    @Resource(name = "impexSpitterFactory")
    private DefaultImpexSpitterFactory impexSpitterFactory;

    @Resource(name = "mediaService")
    private MediaService mediaService;


    @Override
    public boolean canPerform(final ActionContext<LinkedHashSet<?>> ctx) {
        if (ctx.getData() == null) {
            return false;
        }

        LinkedHashSet<?> data = ctx.getData();

        if (data.isEmpty()) {
            return false;
        } else {

            Object next = data.iterator().next();

            boolean b = impexSpitterFactory.checkTypeSupported(next);

            LOG.info("Can perform? " + b + " (" + data.size() + ")");

            return b;
        }
    }

    @Override
    public ActionResult<String> perform(final ActionContext<LinkedHashSet<?>> ctx) {
        if (ctx != null) {

            LinkedHashSet dataToExport = ctx.getData();
            LOG.info("Data to export " + dataToExport.size());
            if (!impexSpitterFactory.checkTypeSupported(dataToExport.iterator().next())) {
                Messagebox.show("The item type you selected to export does not have an impex generator implemented yet", "title", null, org.zkoss.zul.Messagebox.ERROR, null);

                return new ActionResult(ActionResult.ERROR);
            }

            if (dataToExport.isEmpty()) {
                Messagebox.show("To use the export feature you must select at least one in the list below", "title", null, org.zkoss.zul.Messagebox.EXCLAMATION, null);
            } else {
                ExportResult result = null;

                if (dataToExport.size() == 1) {
                    LOG.info("Exporting single model...");
                    result = impexSpitterFactory.export((ItemModel) dataToExport.iterator().next());

                } else {
                    LOG.info("Exporting several models...");
                    result = impexSpitterFactory.exportMultiple(dataToExport);
                }

                FileDownloadHelper.executeMediaDownload(mediaService, result.getExportedData());
            }

//            todo: work on the return type
            return new ActionResult(ActionResult.SUCCESS);

        } else {
            return new ActionResult(ActionResult.ERROR);
        }
    }

    @Override
    public boolean needsConfirmation(ActionContext<LinkedHashSet<?>> ctx) {
        return false;
    }

    @Override
    public String getConfirmationMessage(ActionContext<LinkedHashSet<?>> ctx) {
        LOG.info("getConfirmationMessage...");


        // todo check when this method gets called
        return ctx.getLabel(CONFIRMATION_MESSAGE);
    }
}
