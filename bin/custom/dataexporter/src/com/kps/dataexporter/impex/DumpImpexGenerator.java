package com.kps.dataexporter.impex;

import de.hybris.platform.catalog.model.CatalogVersionModel;


public interface DumpImpexGenerator {
    String generateDump(CatalogVersionModel catalogVersionModel);
}
