package com.paulograbin.contentmigrator.impex;

import com.paulograbin.contentmigrator.enums.DataDumpExportType;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.impex.ExportResult;

import java.util.Set;

public interface ImpexSpitterFactory {

    ExportResult export(ItemModel item);

    ExportResult exportMultiple(Set<ItemModel> items);

    ExportResult exportDataDump(DataDumpExportType dumpExportType, CatalogVersionModel catalogVersion);

    boolean checkTypeSupported(ItemModel item);

}
