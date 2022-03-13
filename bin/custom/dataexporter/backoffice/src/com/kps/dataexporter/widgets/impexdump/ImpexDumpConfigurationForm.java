package com.kps.dataexporter.widgets.impexdump;

import com.kps.dataexporter.enums.DataDumpExportType;
import de.hybris.platform.catalog.model.CatalogVersionModel;

public class ImpexDumpConfigurationForm {

    private CatalogVersionModel catalogVersionModel;
    private DataDumpExportType dataDumpExportType;

    public CatalogVersionModel getCatalogVersionModel() {
        return catalogVersionModel;
    }

    public void setCatalogVersionModel(CatalogVersionModel catalogVersionModel) {
        this.catalogVersionModel = catalogVersionModel;
    }

    public DataDumpExportType getDataDumpExportType() {
        return dataDumpExportType;
    }

    public void setDataDumpExportType(DataDumpExportType dataDumpExportType) {
        this.dataDumpExportType = dataDumpExportType;
    }
}
