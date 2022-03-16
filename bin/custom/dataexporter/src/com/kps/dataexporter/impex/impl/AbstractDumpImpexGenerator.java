package com.kps.dataexporter.impex.impl;

import com.kps.dataexporter.impex.DumpImpexGenerator;
import com.kps.dataexporter.impex.ImpexHeaderGenerationService;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import javax.annotation.Resource;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.kps.dataexporter.constants.DataexporterConstants.*;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

public abstract class AbstractDumpImpexGenerator implements DumpImpexGenerator {

    @Resource
    private ImpexHeaderGenerationService impexHeaderGenerationService;

    @Override
    public abstract String generateDump(CatalogVersionModel catalogVersionModel);

    protected String generateImpex(String typecode, String whereClause) {
        checkArgument(isNotEmpty(typecode), "typecode cannot not be empty");
        checkArgument(isNotEmpty(whereClause), "whereClause cannot not be empty");

        StringBuilder builder = new StringBuilder();
        String generatedHeader = getImpexHeaderGenerationService().generateImpexHeaderForType(typecode).orElseThrow(IllegalStateException::new);
        builder.append(generatedHeader).append(whereClause);
        return builder.toString();
    }

    protected String makeWhereClause(String type, CatalogVersionModel catalogVersionModel) {
        checkArgument(isNotEmpty(type), "Type cannot not be empty");
        checkNotNull(type, "CatalogVersionModel cannot be null");

        return IMPEX_EXPORT_FILTER_CLAUSE_USING_TYPE_AND_CATALOG_VERSION
                .replace("%1", type)
                .replace("%2", catalogVersionModel.getPk().toString())
                .concat("\n");
    }

    protected String makeWhereClause(String type) {
        checkArgument(isNotEmpty(type), "Type cannot not be empty");

        return IMPEX_EXPORT_FILTER_CLAUSE_USING_TYPE
                .replace("%1", type)
                .concat("\n");
    }

    public ImpexHeaderGenerationService getImpexHeaderGenerationService() {
        return impexHeaderGenerationService;
    }

    public void setImpexHeaderGenerationService(ImpexHeaderGenerationService impexHeaderGenerationService) {
        this.impexHeaderGenerationService = impexHeaderGenerationService;
    }
}
