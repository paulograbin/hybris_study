package com.paulograbin.contentmigrator.impex.impl;

import com.paulograbin.contentmigrator.impex.DumpImpexGenerator;
import com.paulograbin.contentmigrator.impex.ImpexHeaderGenerationService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.KeywordModel;
import de.hybris.platform.catalog.model.ProductReferenceModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantProductModel;

import javax.annotation.Resource;

public class ProductDumpImpexGenerator implements DumpImpexGenerator {

    private final String WHERE_CLAUSE = "\"#% impex.exportItemsFlexibleSearch( \"\"select {pk} from {%1!} \"\" );\"";
    private final String WHERE_CLAUSE_WITH_CATALOG_VERSION = "\"#% impex.exportItemsFlexibleSearch( \"\"select {pk} from {%1!} where {catalogVersion} = %2 \"\" );\"";

    @Resource
    private ImpexHeaderGenerationService impexHeaderGenerationService;

    @Override
    public String generateDump(CatalogVersionModel catalogVersionModel) {
        StringBuilder builder = new StringBuilder();
        builder.append(generateImpex(KeywordModel._TYPECODE, makeWhereClause(KeywordModel._TYPECODE)));
        builder.append(generateImpex(CategoryModel._TYPECODE, makeWhereClause(CategoryModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ProductModel._TYPECODE, makeWhereClause(ProductModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(VariantProductModel._TYPECODE, makeWhereClause(VariantProductModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ProductReferenceModel._TYPECODE, makeWhereClause(ProductReferenceModel._TYPECODE)));

        return builder.toString();
    }

    private String generateImpex(String typecode, String whereClause) {
        StringBuilder builder = new StringBuilder();
        String generatedHeader = impexHeaderGenerationService.generateImpexHeaderForType(typecode).orElseThrow(IllegalStateException::new);
        builder.append(generatedHeader).append(whereClause);
        return builder.toString();
    }

    private String makeWhereClause(String currentType, CatalogVersionModel catalogVersionModel) {
        return WHERE_CLAUSE_WITH_CATALOG_VERSION
                .replace("%1", currentType)
                .replace("%2", catalogVersionModel.getPk().toString())
                .concat("\n");
    }

    private String makeWhereClause(String currentType) {
        return WHERE_CLAUSE
                .replace("%1", currentType)
                .concat("\n");
    }
}
