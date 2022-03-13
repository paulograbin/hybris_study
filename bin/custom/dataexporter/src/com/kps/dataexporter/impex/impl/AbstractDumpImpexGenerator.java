package com.kps.dataexporter.impex.impl;

import com.kps.dataexporter.impex.DumpImpexGenerator;
import com.kps.dataexporter.impex.ImpexHeaderGenerationService;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import javax.annotation.Resource;

public abstract class AbstractDumpImpexGenerator implements DumpImpexGenerator {

    private final String WHERE_CLAUSE = "\"#% impex.exportItemsFlexibleSearch( \"\"select {pk} from {%1!} \"\" );\"";
    private final String WHERE_CLAUSE_WITH_CATALOG_VERSION = "\"#% impex.exportItemsFlexibleSearch( \"\"select {pk} from {%1!} where {catalogVersion} = %2 \"\" );\"";

    @Resource
    private ImpexHeaderGenerationService impexHeaderGenerationService;

    @Override
    public abstract String generateDump(CatalogVersionModel catalogVersionModel);

    protected String generateImpex(String typecode, String whereClause) {
        StringBuilder builder = new StringBuilder();
        String generatedHeader = impexHeaderGenerationService.generateImpexHeaderForType(typecode).orElseThrow(IllegalStateException::new);
        builder.append(generatedHeader).append(whereClause);
        return builder.toString();
    }

    protected String makeWhereClause(String currentType, CatalogVersionModel catalogVersionModel) {
        return WHERE_CLAUSE_WITH_CATALOG_VERSION
                .replace("%1", currentType)
                .replace("%2", catalogVersionModel.getPk().toString())
                .concat("\n");
    }

    protected String makeWhereClause(String currentType) {
        return WHERE_CLAUSE
                .replace("%1", currentType)
                .concat("\n");
    }

}
