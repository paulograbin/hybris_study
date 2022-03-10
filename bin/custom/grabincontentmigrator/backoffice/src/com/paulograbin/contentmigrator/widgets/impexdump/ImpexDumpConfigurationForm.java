package com.paulograbin.contentmigrator.widgets.impexdump;

import com.paulograbin.contentmigrator.enums.DataDumpExportType;
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
