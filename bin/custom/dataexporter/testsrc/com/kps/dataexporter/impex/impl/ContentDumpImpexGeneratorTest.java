package com.kps.dataexporter.impex.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorcms.model.actions.*;
import de.hybris.platform.acceleratorcms.model.components.*;
import de.hybris.platform.acceleratorcms.model.restrictions.CMSActionRestrictionModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.DocumentPageModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.contents.components.CMSFlexComponentModel;
import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.cms2.model.contents.components.CMSParagraphComponentModel;
import de.hybris.platform.cms2.model.contents.components.CMSSiteContextComponentModel;
import de.hybris.platform.cms2.model.contents.containers.ABTestCMSComponentContainerModel;
import de.hybris.platform.cms2.model.contents.contentslot.ContentSlotModel;
import de.hybris.platform.cms2.model.navigation.CMSNavigationEntryModel;
import de.hybris.platform.cms2.model.navigation.CMSNavigationNodeModel;
import de.hybris.platform.cms2.model.pages.*;
import de.hybris.platform.cms2.model.relations.ContentSlotForPageModel;
import de.hybris.platform.cms2.model.relations.ContentSlotForTemplateModel;
import de.hybris.platform.cms2.model.restrictions.*;
import de.hybris.platform.cms2lib.model.components.ProductCarouselComponentModel;
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
public class ContentDumpImpexGeneratorTest {

    public static final String WHERE_CLAUSE_WITH_CATALOG = "WHERE_CLAUSE_WITH_CATALOG";
    public static final String WHERE_CLAUSE = "WHERE CLAUSE";

    @Spy
    private ContentDumpImpexGenerator contentDumpImpexGenerator;

    @Mock
    private CatalogVersionModel catalogVersionModel;

    @Before
    public void setup() {
        Mockito.doReturn("INSERT_UPDATE TYPE").when(contentDumpImpexGenerator).generateImpex(anyString(), anyString());
        Mockito.doReturn(WHERE_CLAUSE).when(contentDumpImpexGenerator).makeWhereClause(anyString());
        Mockito.doReturn(WHERE_CLAUSE_WITH_CATALOG).when(contentDumpImpexGenerator).makeWhereClause(anyString(), anyObject());
    }

    @Test
    public void testGenerateDumpForRequiredTypes() {
        contentDumpImpexGenerator.generateDump(catalogVersionModel);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(PageTemplateModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ContentSlotModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ContentSlotForTemplateModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(DocumentPageModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CategoryPageModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ProductPageModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CatalogPageModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ContentPageModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ContentSlotForPageModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);

        verify(contentDumpImpexGenerator, times(1)).generateImpex(CMSParagraphComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CMSLinkComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CMSNavigationNodeModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CMSNavigationEntryModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CMSFlexComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(AccountNavigationComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(BreadcrumbComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CMSProductListComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CMSSiteContextComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(NavigationComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CategoryNavigationComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(FooterComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(FooterNavigationComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ProductAddToCartComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ProductCarouselComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ProductRefinementComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ProductVariantSelectorComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(SearchBoxComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(SearchResultsListComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(SimpleResponsiveBannerComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(StoreFinderComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(MiniCartComponentModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);

        verify(contentDumpImpexGenerator, times(1)).generateImpex(CMSActionRestrictionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CMSTimeRestrictionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CMSBaseStoreTimeRestrictionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CMSCatalogRestrictionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CMSCategoryRestrictionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CMSProductRestrictionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CMSUserGroupRestrictionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(CMSInverseRestrictionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(SimpleCMSActionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(AddToCartActionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ListAddToCartActionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ListAddToEntryGroupActionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ListOrderFormActionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ListPickUpInStoreActionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(PickUpInStoreActionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ViewOrderActionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ViewStoreActionModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);
        verify(contentDumpImpexGenerator, times(1)).generateImpex(ABTestCMSComponentContainerModel._TYPECODE, WHERE_CLAUSE_WITH_CATALOG);

    }

}