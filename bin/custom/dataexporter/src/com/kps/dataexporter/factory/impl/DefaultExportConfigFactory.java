package com.kps.dataexporter.factory.impl;

import com.kps.dataexporter.factory.ExportConfigFactory;
import de.hybris.platform.servicelayer.impex.ExportConfig;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;

import java.io.ByteArrayInputStream;

import static org.apache.commons.lang.CharEncoding.UTF_8;

public class DefaultExportConfigFactory implements ExportConfigFactory {

    @Override
    public ExportConfig createExportConfig(String exportHeader) {

        ExportConfig exportConfig = new ExportConfig();
        exportConfig.setSynchronous(true);
        exportConfig.setSingleFile(true);
        exportConfig.setValidationMode(ExportConfig.ValidationMode.STRICT);
        exportConfig.setScript(new StreamBasedImpExResource(new ByteArrayInputStream(exportHeader.getBytes()), UTF_8));

        return exportConfig;
    }
}
