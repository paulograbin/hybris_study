package com.kps.dataexporter.impex.impl;

import com.kps.dataexporter.enums.DataDumpExportType;
import com.kps.dataexporter.factory.ExportConfigFactory;
import com.kps.dataexporter.impex.DumpImpexGenerator;
import com.kps.dataexporter.impex.ImpexGenerator;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.impex.ExportConfig;
import de.hybris.platform.servicelayer.impex.ExportResult;
import de.hybris.platform.servicelayer.impex.ExportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@UnitTest
public class DefaultDataExportImpexGeneratorServiceTest {

    private static final String HEADER_TO_EXPORT = "INSERT_UPDATE ContentPage; catalogVersion(catalog(id), version)[unique = true]; uid[unique = true]\n";

    private DefaultDataExportImpexGeneratorService defaultDataExportImpexGeneratorService;

    @Mock
    private ExportService exportService;
    @Mock
    private ProductImpexGenerator productImpexGenerator;
    @Mock
    private ContentPageImpexGenerator contentPageImpexGenerator;
    @Mock
    private ProductDumpImpexGenerator productDumpImpexGenerator;
    @Mock
    private ContentDumpImpexGenerator contentDumpImpexGenerator;
    @Mock
    private ExportConfigFactory exportConfigFactory;
    @Mock
    private ExportConfig exportConfig;
    @Mock
    private ExportResult exportResult;


    private Map<DataDumpExportType, DumpImpexGenerator> dumpExportStrategiesMap;
    private Map<String, ImpexGenerator> exportStrategiesMap;
    private Set<ItemModel> toExport;
    private ContentPageModel contentPageModel;
    private CatalogVersionModel catalogVersionModel;


    @Before
    public void setup() {
        this.defaultDataExportImpexGeneratorService = Mockito.mock(DefaultDataExportImpexGeneratorService.class, Mockito.CALLS_REAL_METHODS);
        this.exportStrategiesMap = new HashMap<>();
        this.exportStrategiesMap.put("Product", productImpexGenerator);
        this.exportStrategiesMap.put("ContentPage", contentPageImpexGenerator);

        this.dumpExportStrategiesMap = new HashMap<>();
        this.dumpExportStrategiesMap.put(DataDumpExportType.CONTENT, contentDumpImpexGenerator);
        this.dumpExportStrategiesMap.put(DataDumpExportType.PRODUCT, productDumpImpexGenerator);

        this.contentPageModel = new ContentPageModel();
        this.catalogVersionModel = new CatalogVersionModel();
        this.toExport = Set.of(contentPageModel);

        Mockito.doReturn(exportService).when(defaultDataExportImpexGeneratorService).getExportService();
        Mockito.doReturn(exportStrategiesMap).when(defaultDataExportImpexGeneratorService).getExportStrategiesMap();
        Mockito.doReturn(dumpExportStrategiesMap).when(defaultDataExportImpexGeneratorService).getDumpExportStrategiesMap();
        Mockito.doReturn(exportConfigFactory).when(defaultDataExportImpexGeneratorService).getExportConfigFactory();

        Mockito.doReturn(HEADER_TO_EXPORT).when(productDumpImpexGenerator).generateDump(catalogVersionModel);
        Mockito.doReturn(HEADER_TO_EXPORT).when(contentDumpImpexGenerator).generateDump(catalogVersionModel);
        Mockito.doReturn(HEADER_TO_EXPORT).when(contentPageImpexGenerator).generateImpex(anySet());

        Mockito.doReturn(exportConfig).when(exportConfigFactory).createExportConfig(HEADER_TO_EXPORT);
        Mockito.doReturn(exportResult).when(exportService).exportData(exportConfig);
    }

    @Test
    public void testThatSupportedTypesAreAccepted() {
        assertTrue(defaultDataExportImpexGeneratorService.checkTypeSupported(new ProductModel()));
        assertTrue(defaultDataExportImpexGeneratorService.checkTypeSupported(new ContentPageModel()));
        assertFalse(defaultDataExportImpexGeneratorService.checkTypeSupported(new LanguageModel()));
    }

    @Test(expected = IllegalStateException.class)
    public void testThatRaisesExceptionWhenCallsGenerateForUnsupportedType() {
        defaultDataExportImpexGeneratorService.exportMultiple(Set.of(new LanguageModel()));
    }

    @Test
    public void testExportForSupportedType() {
        defaultDataExportImpexGeneratorService.exportMultiple(toExport);

        verify(exportConfigFactory, times(1)).createExportConfig(HEADER_TO_EXPORT);
        verify(exportService, times(1)).exportData(exportConfig);
        verify(contentPageImpexGenerator, times(1)).generateImpex(anySet());
    }

    @Test
    public void testExportDumpForSupportedTypes() {
        defaultDataExportImpexGeneratorService.exportDataDump(DataDumpExportType.CONTENT, this.catalogVersionModel);
        defaultDataExportImpexGeneratorService.exportDataDump(DataDumpExportType.PRODUCT, this.catalogVersionModel);

        verify(exportConfigFactory, times(2)).createExportConfig(HEADER_TO_EXPORT);
        verify(exportService, times(2)).exportData(exportConfig);
        verify(contentDumpImpexGenerator, times(1)).generateDump(catalogVersionModel);
        verify(productDumpImpexGenerator, times(1)).generateDump(catalogVersionModel);
    }
}