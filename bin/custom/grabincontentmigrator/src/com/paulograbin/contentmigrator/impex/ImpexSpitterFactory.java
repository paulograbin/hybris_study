package com.paulograbin.contentmigrator.impex;

import com.paulograbin.contentmigrator.enums.DataDumpExportType;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.impex.ExportResult;

import java.util.Set;

public interface ImpexSpitterFactory {

    ExportResult export(ItemModel itemModel);

    ExportResult exportMultiple(Set<ItemModel> itemModel);

    ExportResult exportMultiple(DataDumpExportType dumpExportType, CatalogVersionModel catalogVersionModel);

    void test();

    boolean checkTypeSupported(Object next);

}
