package com.kps.dataexporter.impex.impl;

import com.kps.dataexporter.impex.ImpexHeaderGenerationService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.PK;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.Optional;

import static com.kps.dataexporter.constants.DataexporterConstants.IMPEX_EXPORT_FILTER_CLAUSE_USING_TYPE;
import static com.kps.dataexporter.constants.DataexporterConstants.IMPEX_EXPORT_FILTER_CLAUSE_USING_TYPE_AND_CATALOG_VERSION;
import static org.mockito.Matchers.anyString;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@UnitTest
public class AbstractDumpImpexGeneratorTest {

    private static final String NEW_LINE = "\n";
    private static final String TEST_TYPE = "TEST_TYPE";
    private static final String TEST_WHERE_CLAUSE = "TEST_WHERE_CLAUSE";
    private static final String TEST_TYPE_IMPEX_HEADER = "INSERT_UPDATE TEST_TYPE \n";

    private AbstractDumpImpexGenerator abstractDumpImpexGenerator;

    @Mock
    private CatalogVersionModel catalogVersionModel;
    @Mock
    private ImpexHeaderGenerationService impexHeaderGenerationService;

    private PK catalogVersionPk;

    @Before
    public void setup() {
        this.catalogVersionPk = PK.parse("1111");
        this.abstractDumpImpexGenerator = Mockito.mock(AbstractDumpImpexGenerator.class, Mockito.CALLS_REAL_METHODS);

        Mockito.when(this.abstractDumpImpexGenerator.getImpexHeaderGenerationService()).thenReturn(impexHeaderGenerationService);
        Mockito.when(this.impexHeaderGenerationService.generateImpexHeaderForType(anyString())).thenReturn(Optional.of(TEST_TYPE_IMPEX_HEADER));
        Mockito.when(this.catalogVersionModel.getPk()).thenReturn(catalogVersionPk);
    }


    @Test
    public void testGenerateImpex() {
        String expectedResult = TEST_TYPE_IMPEX_HEADER.concat(TEST_WHERE_CLAUSE);
        assertEquals(expectedResult, this.abstractDumpImpexGenerator.generateImpex(TEST_TYPE, TEST_WHERE_CLAUSE));
    }

    @Test(expected = IllegalStateException.class)
    public void testGenerateImpexRaisesExceptionWhenTypeInvalid() {
        Mockito.when(this.impexHeaderGenerationService.generateImpexHeaderForType("INVALID_TYPE")).thenReturn(Optional.empty());
        this.abstractDumpImpexGenerator.generateImpex("INVALID_TYPE", TEST_WHERE_CLAUSE);
    }

    @Test
    public void testCreationOfFilterClauseWithoutCatalogVersion() {
        String expectedFilterClause =
                IMPEX_EXPORT_FILTER_CLAUSE_USING_TYPE.replace("%1", TEST_TYPE).concat(NEW_LINE);

        assertEquals(expectedFilterClause, this.abstractDumpImpexGenerator.makeWhereClause(TEST_TYPE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCallingMakeWhereClauseWithEmptyStringRaisesException() {
        this.abstractDumpImpexGenerator.makeWhereClause("");
    }

    @Test
    public void testCreationOfFilterClauseWithCatalogVersion() {
        String expectedFilterClause =
                IMPEX_EXPORT_FILTER_CLAUSE_USING_TYPE_AND_CATALOG_VERSION.replace("%1", TEST_TYPE)
                        .replace("%2", catalogVersionModel.getPk().toString())
                        .concat(NEW_LINE);

        assertEquals(expectedFilterClause, this.abstractDumpImpexGenerator.makeWhereClause(TEST_TYPE, catalogVersionModel));
    }

    @Test(expected = NullPointerException.class)
    public void testCallingMakeWhereClauseWithNullCatalogRaisesException() {
        this.abstractDumpImpexGenerator.makeWhereClause(TEST_TYPE, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCallingMakeWhereClauseWithNullTypeRaisesException() {
        this.abstractDumpImpexGenerator.makeWhereClause(null, catalogVersionModel);
    }

}