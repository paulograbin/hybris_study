package com.kps.dataexporter.impex.impl;

import com.kps.dataexporter.impex.ImpexGenerator;
import com.kps.dataexporter.impex.ImpexHeaderGenerationService;
import de.hybris.platform.acceleratorcms.model.components.BreadcrumbComponentModel;
import de.hybris.platform.acceleratorcms.model.components.CategoryNavigationComponentModel;
import de.hybris.platform.acceleratorcms.model.components.FooterNavigationComponentModel;
import de.hybris.platform.acceleratorcms.model.components.JspIncludeComponentModel;
import de.hybris.platform.acceleratorcms.model.components.LogoComponentModel;
import de.hybris.platform.acceleratorcms.model.components.MiniCartComponentModel;
import de.hybris.platform.acceleratorcms.model.components.NavigationComponentModel;
import de.hybris.platform.acceleratorcms.model.components.SearchBoxComponentModel;
import de.hybris.platform.acceleratorcms.model.components.SimpleBannerComponentModel;
import de.hybris.platform.acceleratorcms.model.components.SimpleResponsiveBannerComponentModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.DocumentPageModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.cms2.model.contents.components.CMSParagraphComponentModel;
import de.hybris.platform.cms2.model.contents.components.SimpleCMSComponentModel;
import de.hybris.platform.cms2.model.contents.contentslot.ContentSlotModel;
import de.hybris.platform.cms2.model.pages.*;
import de.hybris.platform.cms2.model.relations.ContentSlotForPageModel;
import de.hybris.platform.cms2.model.relations.ContentSlotForTemplateModel;
import de.hybris.platform.cms2lib.model.components.AbstractBannerComponentModel;
import de.hybris.platform.cms2lib.model.components.ProductCarouselComponentModel;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.personalizationcms.model.CxCmsComponentContainerModel;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AbstractPageImpexGenerator extends AbstractImpexGenerator<AbstractPageModel> implements ImpexGenerator<AbstractPageModel> {

    private static final Logger LOG = Logger.getLogger(AbstractPageImpexGenerator.class);

    @Override
    public List<String> makeTypeToExportList() {
        List<String> typeList = new ArrayList<>();

        typeList.add(ContentPageModel._TYPECODE);
        typeList.add(ProductPageModel._TYPECODE);
        typeList.add(CatalogPageModel._TYPECODE);
        typeList.add(CategoryPageModel._TYPECODE);
        typeList.add(EmailPageModel._TYPECODE);
        typeList.add(DocumentPageModel._TYPECODE);

        typeList.add(AbstractBannerComponentModel._TYPECODE);
        typeList.add(AbstractCMSComponentModel._TYPECODE);
        typeList.add(BreadcrumbComponentModel._TYPECODE);
        typeList.add(CMSLinkComponentModel._TYPECODE);
        typeList.add(CMSParagraphComponentModel._TYPECODE);
        typeList.add(CategoryNavigationComponentModel._TYPECODE);
        typeList.add(ContentSlotForPageModel._TYPECODE);
        typeList.add(ContentSlotForTemplateModel._TYPECODE);
        typeList.add(ContentSlotModel._TYPECODE);
        typeList.add(CxCmsComponentContainerModel._TYPECODE);
        typeList.add(FooterNavigationComponentModel._TYPECODE);
        typeList.add(JspIncludeComponentModel._TYPECODE);
        typeList.add(LogoComponentModel._TYPECODE);
        typeList.add(MediaModel._TYPECODE);
        typeList.add(MiniCartComponentModel._TYPECODE);
        typeList.add(NavigationComponentModel._TYPECODE);
        typeList.add(PageTemplateModel._TYPECODE);
        typeList.add(ProductCarouselComponentModel._TYPECODE);
        typeList.add(SearchBoxComponentModel._TYPECODE);
        typeList.add(SimpleBannerComponentModel._TYPECODE);
        typeList.add(SimpleCMSComponentModel._TYPECODE);
        typeList.add(SimpleResponsiveBannerComponentModel._TYPECODE);

        return typeList;
    }

    @Override
    public Set<PK> makePkList(AbstractPageModel model) {
        Set<PK> pks = new HashSet<>();

        LOG.info("Page: " + model.getUid() + " - " + model.getPk());
        pks.add(model.getPk());

        LOG.info("Template: " + model.getMasterTemplate().getUid() + " - " + model.getMasterTemplate().getPk());
        pks.add(model.getMasterTemplate().getPk());

        List<ContentSlotModel> contentSlots = new ArrayList<>();

        List<ContentSlotForTemplateModel> contentSlotsForTemplate = model.getMasterTemplate().getContentSlots();
        for (ContentSlotForTemplateModel contentSlotForTemplate : contentSlotsForTemplate) {
            LOG.info("Content slot for template: " + contentSlotForTemplate.getUid() + " " + contentSlotForTemplate.getPk());

            pks.add(contentSlotForTemplate.getPk());
            contentSlots.add(contentSlotForTemplate.getContentSlot());
        }

        List<ContentSlotForPageModel> contentSlotsForPage = model.getContentSlots();
        for (ContentSlotForPageModel contentSlotForPageModel : contentSlotsForPage) {
            LOG.info("Content slot for page: " + contentSlotForPageModel.getUid() + " " + contentSlotForPageModel.getPk());

            pks.add(contentSlotForPageModel.getPk());
            contentSlots.add(contentSlotForPageModel.getContentSlot());
        }

        for (ContentSlotModel contentSlot : contentSlots) {
            LOG.info("Content slot " + contentSlot.getUid() + " " + contentSlot.getPk());
            pks.add(contentSlot.getPk());

            List<AbstractCMSComponentModel> cmsComponents = contentSlot.getCmsComponents();
            for (AbstractCMSComponentModel cmsComponent : cmsComponents) {
                pks.add(cmsComponent.getPk());
                LOG.info("Component " + cmsComponent.getUid() + " - " + cmsComponent.getItemtype() + " " + cmsComponent.getPk());

                if (cmsComponent instanceof SimpleBannerComponentModel) {
                    SimpleBannerComponentModel simpleBanner = (SimpleBannerComponentModel) cmsComponent;
                    pks.add(simpleBanner.getMedia().getPk());
                }
            }
        }

        LOG.info("Found " + pks.size() + " to export");
        return pks;
    }
}
