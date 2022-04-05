package com.kps.dataexporter.impex.impl;

import com.kps.dataexporter.enums.DataDumpExportType;
import com.kps.dataexporter.factory.ExportConfigFactory;
import com.kps.dataexporter.impex.DumpImpexGenerator;
import com.kps.dataexporter.impex.ImpexGenerator;
import com.kps.dataexporter.impex.DataExportImpexGeneratorService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.impex.model.ImpExMediaModel;
import de.hybris.platform.servicelayer.impex.ExportConfig;
import de.hybris.platform.servicelayer.impex.ExportResult;
import de.hybris.platform.servicelayer.impex.ExportService;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Set;


public class DefaultDataExportImpexGeneratorService implements DataExportImpexGeneratorService {

    private static final Logger LOG = Logger.getLogger(DefaultDataExportImpexGeneratorService.class);

    private ExportService exportService;
    private Map<DataDumpExportType, DumpImpexGenerator> dumpExportStrategiesMap;
    private Map<String, ImpexGenerator> exportStrategiesMap;
    private ExportConfigFactory exportConfigFactory;

    @Override
    public boolean checkTypeSupported(ItemModel item) {
        return getExportStrategiesMap().containsKey(item.getItemtype());
    }

    public ExportResult exportMultiple(Set<ItemModel> items) {
        ItemModel firstElement = items.iterator().next();

        ImpexGenerator impexGenerator = getImpexGeneratorForTypeCode(firstElement);
        String exportImpexHeader = impexGenerator.generateImpex(items);

        ExportResult exportResult = runExport(exportImpexHeader);
        printExportResult(exportResult);

        return exportResult;
    }

    @Override
    public ExportResult exportDataDump(DataDumpExportType dumpExportType, CatalogVersionModel catalogVersionModel) {
        DumpImpexGenerator dumpImpexGenerator = getDumpExportStrategiesMap().get(dumpExportType);
        String impexDump = dumpImpexGenerator.generateDump(catalogVersionModel);
        LOG.info("Impex that will be used to export data:");
        LOG.info(impexDump);

        ExportResult exportResult = runExport(impexDump);
        printExportResult(exportResult);

        return exportResult;
    }

    private ImpexGenerator getImpexGeneratorForTypeCode(ItemModel firstElement) {
        ImpexGenerator impexGenerator = getExportStrategiesMap().get(firstElement.getItemtype());
        if (impexGenerator == null) {
            throw new IllegalStateException("No instance of ImpexGenerator configured for item type " + firstElement.getItemtype());
        }
        return impexGenerator;
    }

    private ExportResult runExport(String exportHeader) {
        final ExportConfig exportConfig = getExportConfigFactory().createExportConfig(exportHeader);
        return getExportService().exportData(exportConfig);
    }

    private void printExportResult(ExportResult exportResult) {
        ImpExMediaModel exportedData = exportResult.getExportedData();
        ImpExMediaModel exportedMedia = exportResult.getExportedMedia();

        if (exportResult.isSuccessful()) {
            LOG.debug("Exported data: " + exportedData.getLocation());
            LOG.debug("Internal URL: " + exportedData.getInternalURL());
            LOG.debug("Size: " + exportedData.getSize());
            LOG.debug("Encoding: " + exportedData.getEncoding());
            LOG.debug("Extraction id: " + exportedData.getExtractionId());
            LOG.debug("Zip entry: " + exportedData.getZipentry());
            LOG.debug("Real file name: " + exportedData.getRealFileName());

            LOG.debug("Exported media: " + exportedMedia.getLocation());
            LOG.debug("Exported media: " + exportedMedia.getSize());
        }
    }

    public ExportService getExportService() {
        return exportService;
    }

    public void setExportService(ExportService exportService) {
        this.exportService = exportService;
    }

    public Map<DataDumpExportType, DumpImpexGenerator> getDumpExportStrategiesMap() {
        return dumpExportStrategiesMap;
    }

    public void setDumpExportStrategiesMap(Map<DataDumpExportType, DumpImpexGenerator> dumpExportStrategiesMap) {
        this.dumpExportStrategiesMap = dumpExportStrategiesMap;
    }

    public Map<String, ImpexGenerator> getExportStrategiesMap() {
        return exportStrategiesMap;
    }

    public void setExportStrategiesMap(Map<String, ImpexGenerator> exportStrategiesMap) {
        this.exportStrategiesMap = exportStrategiesMap;
    }

    public ExportConfigFactory getExportConfigFactory() {
        return exportConfigFactory;
    }

    public void setExportConfigFactory(ExportConfigFactory exportConfigFactory) {
        this.exportConfigFactory = exportConfigFactory;
    }
}
