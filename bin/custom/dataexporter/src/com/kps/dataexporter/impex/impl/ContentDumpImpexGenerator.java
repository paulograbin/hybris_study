package com.kps.dataexporter.impex.impl;

import com.kps.dataexporter.impex.DumpImpexGenerator;
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

public class ContentDumpImpexGenerator extends AbstractDumpImpexGenerator implements DumpImpexGenerator {

    @Override
    public String generateDump(CatalogVersionModel catalogVersionModel) {
        StringBuilder builder = new StringBuilder();
        builder.append(generateImpex(PageTemplateModel._TYPECODE, makeWhereClause(PageTemplateModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ContentSlotModel._TYPECODE, makeWhereClause(ContentSlotModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ContentSlotForTemplateModel._TYPECODE, makeWhereClause(ContentSlotForTemplateModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(DocumentPageModel._TYPECODE, makeWhereClause(DocumentPageModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CategoryPageModel._TYPECODE, makeWhereClause(CategoryPageModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ProductPageModel._TYPECODE, makeWhereClause(ProductPageModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CatalogPageModel._TYPECODE, makeWhereClause(CatalogPageModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ContentPageModel._TYPECODE, makeWhereClause(ContentPageModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ContentSlotForPageModel._TYPECODE, makeWhereClause(ContentSlotForPageModel._TYPECODE, catalogVersionModel)));

        builder.append(generateImpex(CMSParagraphComponentModel._TYPECODE, makeWhereClause(CMSParagraphComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CMSLinkComponentModel._TYPECODE, makeWhereClause(CMSLinkComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CMSNavigationNodeModel._TYPECODE, makeWhereClause(CMSNavigationNodeModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CMSNavigationEntryModel._TYPECODE, makeWhereClause(CMSNavigationEntryModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CMSFlexComponentModel._TYPECODE, makeWhereClause(CMSFlexComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(AccountNavigationComponentModel._TYPECODE, makeWhereClause(AccountNavigationComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(BreadcrumbComponentModel._TYPECODE, makeWhereClause(BreadcrumbComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CMSProductListComponentModel._TYPECODE, makeWhereClause(CMSProductListComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CMSSiteContextComponentModel._TYPECODE, makeWhereClause(CMSSiteContextComponentModel._TYPECODE, catalogVersionModel)));

        builder.append(generateImpex(NavigationComponentModel._TYPECODE, makeWhereClause(NavigationComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CategoryNavigationComponentModel._TYPECODE, makeWhereClause(CategoryNavigationComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(FooterComponentModel._TYPECODE, makeWhereClause(FooterComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(FooterNavigationComponentModel._TYPECODE, makeWhereClause(FooterNavigationComponentModel._TYPECODE, catalogVersionModel)));

        builder.append(generateImpex(ProductAddToCartComponentModel._TYPECODE, makeWhereClause(ProductAddToCartComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ProductCarouselComponentModel._TYPECODE, makeWhereClause(ProductCarouselComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ProductRefinementComponentModel._TYPECODE, makeWhereClause(ProductRefinementComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ProductVariantSelectorComponentModel._TYPECODE, makeWhereClause(ProductVariantSelectorComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(SearchBoxComponentModel._TYPECODE, makeWhereClause(SearchBoxComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(SearchResultsListComponentModel._TYPECODE, makeWhereClause(SearchResultsListComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(SimpleResponsiveBannerComponentModel._TYPECODE, makeWhereClause(SimpleResponsiveBannerComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(StoreFinderComponentModel._TYPECODE, makeWhereClause(StoreFinderComponentModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(MiniCartComponentModel._TYPECODE, makeWhereClause(MiniCartComponentModel._TYPECODE, catalogVersionModel)));

        builder.append(generateImpex(CMSActionRestrictionModel._TYPECODE, makeWhereClause(CMSActionRestrictionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CMSTimeRestrictionModel._TYPECODE, makeWhereClause(CMSTimeRestrictionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CMSBaseStoreTimeRestrictionModel._TYPECODE, makeWhereClause(CMSBaseStoreTimeRestrictionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CMSCatalogRestrictionModel._TYPECODE, makeWhereClause(CMSCatalogRestrictionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CMSCategoryRestrictionModel._TYPECODE, makeWhereClause(CMSCategoryRestrictionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CMSProductRestrictionModel._TYPECODE, makeWhereClause(CMSProductRestrictionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CMSUserGroupRestrictionModel._TYPECODE, makeWhereClause(CMSUserGroupRestrictionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(CMSInverseRestrictionModel._TYPECODE, makeWhereClause(CMSInverseRestrictionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(SimpleCMSActionModel._TYPECODE, makeWhereClause(SimpleCMSActionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(AddToCartActionModel._TYPECODE, makeWhereClause(AddToCartActionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ListAddToCartActionModel._TYPECODE, makeWhereClause(ListAddToCartActionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ListAddToEntryGroupActionModel._TYPECODE, makeWhereClause(ListAddToEntryGroupActionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ListOrderFormActionModel._TYPECODE, makeWhereClause(ListOrderFormActionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ListPickUpInStoreActionModel._TYPECODE, makeWhereClause(ListPickUpInStoreActionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(PickUpInStoreActionModel._TYPECODE, makeWhereClause(PickUpInStoreActionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ViewOrderActionModel._TYPECODE, makeWhereClause(ViewOrderActionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ViewStoreActionModel._TYPECODE, makeWhereClause(ViewStoreActionModel._TYPECODE, catalogVersionModel)));
        builder.append(generateImpex(ABTestCMSComponentContainerModel._TYPECODE, makeWhereClause(ABTestCMSComponentContainerModel._TYPECODE, catalogVersionModel)));

        return builder.toString();
    }
}
