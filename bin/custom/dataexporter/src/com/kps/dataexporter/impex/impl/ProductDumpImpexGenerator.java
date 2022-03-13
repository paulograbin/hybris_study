package com.kps.dataexporter.impex.impl;

import com.kps.dataexporter.impex.DumpImpexGenerator;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.KeywordModel;
import de.hybris.platform.catalog.model.ProductReferenceModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantProductModel;


public class ProductDumpImpexGenerator extends AbstractDumpImpexGenerator implements DumpImpexGenerator {

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
}
