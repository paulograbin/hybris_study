package com.kps.dataexporter.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.kps.dataexporter.impex.impl.DefaultDataExportImpexGeneratorService;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.impex.ExportResult;
import de.hybris.platform.servicelayer.media.MediaService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.LinkedHashSet;


public class ExportMultipleAction implements CockpitAction<LinkedHashSet<?>, String> {
    private static final Logger LOG = Logger.getLogger(ExportMultipleAction.class);

    @Resource
    private DefaultDataExportImpexGeneratorService dataExportImpexGeneratorService;

    @Resource
    private MediaService mediaService;

    @Override
    public boolean canPerform(final ActionContext<LinkedHashSet<?>> ctx) {
        if (ctx.getData() == null || ctx.getData().isEmpty()) {
            return false;
        }

        LinkedHashSet<?> data = ctx.getData();
        Object first = data.iterator().next();
        if (!(first instanceof ItemModel)) {
            return false;
        }

        boolean isTypeSupported = dataExportImpexGeneratorService.checkTypeSupported((ItemModel) first);
        LOG.debug("Can perform? " + isTypeSupported + " (" + data.size() + ")");
        return isTypeSupported;
    }

    @Override
    public ActionResult<String> perform(final ActionContext<LinkedHashSet<?>> ctx) {
        if (ctx != null) {

            LinkedHashSet dataToExport = ctx.getData();
            LOG.debug("Data to export " + dataToExport.size());

            ExportResult result = dataExportImpexGeneratorService.exportMultiple(dataToExport);
            FileDownloadHelper.executeMediaDownload(mediaService, result.getExportedData());

            return new ActionResult(ActionResult.SUCCESS);
        }

        return new ActionResult(ActionResult.ERROR);
    }

    @Override
    public boolean needsConfirmation(ActionContext<LinkedHashSet<?>> ctx) {
        return false;
    }
}
