package com.kps.dataexporter.factory;

import de.hybris.platform.servicelayer.impex.ExportConfig;

public interface ExportConfigFactory {
    ExportConfig createExportConfig(String exportHeader);
}
