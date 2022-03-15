package com.kps.dataexporter.impex.impl;

import com.kps.dataexporter.factory.HeaderLibraryGeneratorFactory;
import com.kps.dataexporter.service.HardCodedHeaderService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.impex.jalo.exp.generator.HeaderLibraryGenerator;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
@UnitTest
public class DefaultImpexHeaderGenerationServiceTest {

    private static final String MOCK_RETURN_FOR_GENERATED_HEADER =
                    "#  -------------------------------------------------------\n" +
                    "#  Generated header library\n" +
                    "#  -------------------------------------------------------\n\n" +
                    "Language;&Item;active[allownull=true];creationtime[forceWrite=true,dateformat=dd.MM.yyyy hh:mm:ss];fallbackLanguages(isocode);isocode[unique=true,allownull=true];modifiedtime[dateformat=dd.MM.yyyy hh:mm:ss];name[lang=de];name[lang=en];owner(&Item)[forceWrite=true]\n";
    private static final String TYPE_CODE = "Language";

    private DefaultImpexHeaderGenerationService defaultImpexHeaderGenerationService;

    @Mock
    private HardCodedHeaderService hardCodedHeaderService;
    @Mock
    private HeaderLibraryGeneratorFactory headerLibraryGeneratorFactory;
    @Mock
    private HeaderLibraryGenerator headerLibraryGenerator;

    @Before
    public void setup() {

        this.defaultImpexHeaderGenerationService = Mockito.mock(DefaultImpexHeaderGenerationService.class, Mockito.CALLS_REAL_METHODS);

        Mockito.doReturn(StringUtils.EMPTY).when(hardCodedHeaderService).findHardCodedHeaderForItemType(anyString());
        Mockito.doReturn(headerLibraryGenerator).when(headerLibraryGeneratorFactory).createHeaderLibraryGenerator(TYPE_CODE);
        Mockito.doReturn(MOCK_RETURN_FOR_GENERATED_HEADER).when(headerLibraryGenerator).generateScript();

        Mockito.doReturn(hardCodedHeaderService).when(defaultImpexHeaderGenerationService).getHardCodedHeaderService();
        Mockito.doReturn(headerLibraryGeneratorFactory).when(defaultImpexHeaderGenerationService).getHeaderLibraryGeneratorFactory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCallingGenerateImpexWithEmptyTypeCodeRaisesException() {
        this.defaultImpexHeaderGenerationService.generateImpexHeaderForType("");
    }

    @Test
    public void testHeaderCreationWhenNoCustomHeaderIsPresent() {
        String expectedHeader = "INSERT_UPDATE Language;active;fallbackLanguages(isocode);isocode[unique=true];name[lang=de];name[lang=en];\n";
        Optional<String> generatedHeaderOptional = defaultImpexHeaderGenerationService.generateImpexHeaderForType(TYPE_CODE);

        assertTrue(generatedHeaderOptional.isPresent());
        assertEquals(expectedHeader, generatedHeaderOptional.get());
    }

    @Test
    public void testCustomHeaderIsUsedWhenPresent() {
        String customHeader = "INSERT_UPDATE Language;active;fallbackLanguages(isocode);isocode[unique=true];name[lang=de];name[lang=en];";
        Mockito.doReturn(customHeader).when(hardCodedHeaderService).findHardCodedHeaderForItemType(TYPE_CODE);

        Optional<String> generatedHeaderOptional = defaultImpexHeaderGenerationService.generateImpexHeaderForType(TYPE_CODE);

        assertTrue(generatedHeaderOptional.isPresent());
        assertEquals(customHeader + "\n", generatedHeaderOptional.get());
    }


}