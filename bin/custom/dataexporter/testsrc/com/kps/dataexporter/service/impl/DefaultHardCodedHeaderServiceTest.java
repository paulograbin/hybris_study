package com.kps.dataexporter.service.impl;

import com.kps.dataexporter.model.HardCodedImpexHeaderModel;
import de.hybris.bootstrap.annotations.UnitTest;

import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@UnitTest
public class DefaultHardCodedHeaderServiceTest {

    private static final String TEST_HEADER = "TEST_HEADER";
    private static final String TYPE_REFERENCE = "Language";

    private DefaultHardCodedHeaderService defaultHardCodedHeaderService;
    private HardCodedImpexHeaderModel example;
    private HardCodedImpexHeaderModel result;

    @Mock
    private FlexibleSearchService flexibleSearchService;


    @Before
    public void setup() {
        this.defaultHardCodedHeaderService = Mockito.mock(DefaultHardCodedHeaderService.class, Mockito.CALLS_REAL_METHODS);
        this.example = new HardCodedImpexHeaderModel();
        this.example.setTypeCodeReference(TYPE_REFERENCE);

        this.result = new HardCodedImpexHeaderModel();
        this.result.setHeader(TEST_HEADER);
        this.result.setTypeCodeReference(TYPE_REFERENCE);

        Mockito.doReturn(flexibleSearchService).when(defaultHardCodedHeaderService).getFlexibleSearchService();
        Mockito.doReturn(result).when(flexibleSearchService).getModelByExample(example);
        Mockito.doReturn(example).when(defaultHardCodedHeaderService).createExampleModel(TYPE_REFERENCE);
    }

    @Test
    public void testCanFindExistingHardCodedHeader() {
        String hardCodedHeaderForItemType = defaultHardCodedHeaderService.findHardCodedHeaderForItemType(TYPE_REFERENCE);
        assertEquals(hardCodedHeaderForItemType, TEST_HEADER);
    }

    @Test
    public void testReturnsEmptyWhenHardCodedHeaderNotPresent() {
        Mockito.doThrow(new ModelNotFoundException("")).when(flexibleSearchService).getModelByExample(example);

        String hardCodedHeaderForItemType = defaultHardCodedHeaderService.findHardCodedHeaderForItemType(TYPE_REFERENCE);
        assertEquals(hardCodedHeaderForItemType, "");
    }

}