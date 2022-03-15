package com.kps.dataexporter.impex;

import com.kps.dataexporter.enums.DataDumpExportType;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.impex.ExportResult;

import java.util.Set;

public interface DataExportImpexGeneratorService {

    ExportResult exportMultiple(Set<ItemModel> items);

    ExportResult exportDataDump(DataDumpExportType dumpExportType, CatalogVersionModel catalogVersion);

    boolean checkTypeSupported(ItemModel item);

}
