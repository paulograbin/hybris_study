package com.kps.dataexporter.impex.impl;

import com.kps.dataexporter.enums.DataDumpExportType;
import com.kps.dataexporter.impex.DumpImpexGenerator;
import com.kps.dataexporter.impex.ImpexGenerator;
import com.kps.dataexporter.impex.ImpexSpitterFactory;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.hac.data.form.ImpexContentFormData;
import de.hybris.platform.impex.enums.ImpExValidationModeEnum;
import de.hybris.platform.impex.model.ImpExMediaModel;
import de.hybris.platform.servicelayer.impex.ExportConfig;
import de.hybris.platform.servicelayer.impex.ExportResult;
import de.hybris.platform.servicelayer.impex.ExportService;
import de.hybris.platform.servicelayer.impex.ImpExValidationResult;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.lang.CharEncoding.UTF_8;


public class DefaultImpexSpitterFactory implements ImpexSpitterFactory {

    private static final Logger LOG = Logger.getLogger(DefaultImpexSpitterFactory.class);

    private ExportService exportService;
    private Map<DataDumpExportType, DumpImpexGenerator> dumpExportStrategiesMap;
    private Map<String, ImpexGenerator> exportStrategiesMap;

    @Override
    public boolean checkTypeSupported(ItemModel item) {
        return exportStrategiesMap.containsKey(item.getItemtype());
    }

    @Override
    public ExportResult export(ItemModel item) {
        ImpexGenerator impexGenerator = getImpexGeneratorForTypeCode(item);
        String exportImpexHeader = impexGenerator.generateImpex(item);

        ExportResult exportResult = runExport(exportImpexHeader);
        printExportResult(exportResult);

        return exportResult;
    }

    public ExportResult exportMultiple(Set<ItemModel> items) {
//        Check if models from multiple types were selected because in such case next line may not be enough

        ItemModel firstElement = items.iterator().next();

        ImpexGenerator impexGenerator = getImpexGeneratorForTypeCode(firstElement);
        String exportImpexHeader = impexGenerator.generateImpex(items);

        ExportResult exportResult = runExport(exportImpexHeader);
        printExportResult(exportResult);

        return exportResult;
    }

    @Override
    public ExportResult exportDataDump(DataDumpExportType dumpExportType, CatalogVersionModel catalogVersionModel) {
        DumpImpexGenerator dumpImpexGenerator = this.getDumpExportStrategiesMap().get(dumpExportType);
        String impexDump = dumpImpexGenerator.generateDump(catalogVersionModel);

        ExportResult exportResult = runExport(impexDump);
        printExportResult(exportResult);

        return exportResult;
    }

    private ImpexGenerator getImpexGeneratorForTypeCode(ItemModel firstElement) {
        ImpexGenerator impexGenerator = exportStrategiesMap.get(firstElement.getItemtype());
        if (impexGenerator == null) {
            throw new IllegalStateException("No instance of ImpexGenerator configured for item type " + firstElement.getItemtype());
        }
        return impexGenerator;
    }

    private void printExportResult(ExportResult exportResult) {
        ImpExMediaModel exportedData = exportResult.getExportedData();
        ImpExMediaModel exportedMedia = exportResult.getExportedMedia();

        if (exportResult.isSuccessful()) {
            LOG.info("Exported data: " + exportedData.getLocation());
            LOG.info("Exported data: " + "https://electronics.local:9002" + exportedData.getDownloadURL());
            LOG.info("Internal URL: " + exportedData.getInternalURL());
            LOG.info("Size: " + exportedData.getSize());
            LOG.info("Encoding: " + exportedData.getEncoding());
            LOG.info("Extraction id: " + exportedData.getExtractionId());
            LOG.info("Zip entry: " + exportedData.getZipentry());
            LOG.info("Real file name: " + exportedData.getRealFileName());

            LOG.info("Exported media: " + exportedMedia.getLocation());
            LOG.info("Exported media: " + exportedMedia.getSize());
            LOG.info("Exported media: " + "https://electronics.local:9002" + exportedMedia.getDownloadURL());
        }
    }

    private ExportResult runExport(String s) {
        ImpexContentFormData contentFormData = new ImpexContentFormData();
        contentFormData.setEncoding(UTF_8);
        contentFormData.setValidationEnum(ImpExValidationModeEnum.EXPORT_ONLY);

        contentFormData.setScriptContent(s);

        ExportConfig exportConfig = new ExportConfig();
        exportConfig.setSynchronous(true);
        exportConfig.setSingleFile(true);
        exportConfig.setValidationMode(ExportConfig.ValidationMode.STRICT);
        exportConfig.setScript(new StreamBasedImpExResource(new ByteArrayInputStream(contentFormData.getScriptContent().getBytes()), contentFormData.getEncoding()));


        ImpExValidationResult impExValidationResult = this.exportService.validateExportScript(s, null);
        LOG.info("Validation result: " + impExValidationResult.isSuccessful());

        return this.exportService.exportData(exportConfig);
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
}
