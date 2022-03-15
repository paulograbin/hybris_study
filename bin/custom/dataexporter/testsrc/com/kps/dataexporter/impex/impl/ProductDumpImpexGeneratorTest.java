package com.kps.dataexporter.impex.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.KeywordModel;
import de.hybris.platform.catalog.model.ProductReferenceModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantProductModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@UnitTest
public class ProductDumpImpexGeneratorTest {

    public static final String WHERE_CLAUSE_WITH_CATALOG = "WHERE_CLAUSE_WITH_CATALOG";
    public static final String WHERE_CLAUSE = "WHERE CLAUSE";

    @Spy
    private ProductDumpImpexGenerator productDumpImpexGenerator;

    @Mock
    private CatalogVersionModel catalogVersionModel;

    @Before
    public void setup() {
        Mockito.doReturn("INSERT_UPDATE TYPE").when(productDumpImpexGenerator).generateImpex(anyString(), anyString());
        Mockito.doReturn(WHERE_CLAUSE).when(productDumpImpexGenerator).makeWhereClause(anyString());
        Mockito.doReturn(WHERE_CLAUSE_WITH_CATALOG).when(productDumpImpexGenerator).makeWhereClause(anyString(), anyObject());
    }

    @Test
    public void testGenerateDumpForRequiredTypes() {
        productDumpImpexGenerator.generateDump(catalogVersionModel);

        verify(productDumpImpexGenerator, times(1)).generateImpex(KeywordModel._TYPECODE, WHERE_CLAUSE);
        verify(productDumpImpexGenerator, times(1)).generateImpex(CategoryModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(productDumpImpexGenerator, times(1)).generateImpex(ProductModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(productDumpImpexGenerator, times(1)).generateImpex(VariantProductModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(productDumpImpexGenerator, times(1)).generateImpex(ProductReferenceModel._TYPECODE, WHERE_CLAUSE);
    }

}