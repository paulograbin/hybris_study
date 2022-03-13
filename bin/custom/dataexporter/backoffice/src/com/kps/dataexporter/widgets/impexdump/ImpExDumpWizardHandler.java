package com.kps.dataexporter.widgets.impexdump;

import com.hybris.cockpitng.config.jaxb.wizard.CustomType;
import com.hybris.cockpitng.widgets.configurableflow.FlowActionHandler;
import com.hybris.cockpitng.widgets.configurableflow.FlowActionHandlerAdapter;
import com.kps.dataexporter.actions.FileDownloadHelper;
import com.kps.dataexporter.enums.DataDumpExportType;
import com.kps.dataexporter.impex.impl.DefaultImpexSpitterFactory;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.impex.ExportResult;
import de.hybris.platform.servicelayer.media.MediaService;

import javax.annotation.Resource;
import java.util.Map;

public class ImpExDumpWizardHandler implements FlowActionHandler {

    @Resource(name = "impexSpitterFactory")
    private DefaultImpexSpitterFactory impexSpitterFactory;

    @Resource(name = "mediaService")
    private MediaService mediaService;

    @Override
    public void perform(CustomType customType, FlowActionHandlerAdapter adapter, Map<String, String> map) {
        CatalogVersionModel catalogVersion = adapter.getWidgetInstanceManager().getModel().getValue("impexDumpConfigurationForm.catalogVersionModel", CatalogVersionModel.class);
        DataDumpExportType dumpType = adapter.getWidgetInstanceManager().getModel().getValue("impexDumpConfigurationForm.dataDumpExportType", DataDumpExportType.class);

        ExportResult result = impexSpitterFactory.exportDataDump(dumpType, catalogVersion);
        FileDownloadHelper.executeMediaDownload(mediaService, result.getExportedData());
    }
}
