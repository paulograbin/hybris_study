/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.paulograbin.storefront.controllers.pages;

import com.paulograbin.facades.tew.ProjectData;
import com.paulograbin.facades.tew.ProjectFacade;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * Controller for product details page
 */
@Controller
@RequestMapping(value = "/projects")
public class ProjectPageController extends AbstractPageController {

    private static final Logger LOG = Logger.getLogger(ProjectPageController.class);


    @Resource(name = "projectFacade")
    private ProjectFacade projectFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String productDetail(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException, UnsupportedEncodingException {
        final List<ProjectData> projectModels = projectFacade.loadAll();

        LOG.info(projectModels.size() + " projects found");

        ContentPageModel cmsPage = getCmsPageService().getPageForLabelOrId("projectPageGrabin");
        model.addAttribute(CMS_PAGE_MODEL, cmsPage);
        model.addAttribute("projects", projectModels.subList(0, 10));


//        AbstractPageModel abstractPageModel = (AbstractPageModel) model.asMap().get(CMS_PAGE_MODEL);
//        PageTemplateModel masterTemplate = cmsPage.getMasterTemplate();
//
//        LOG.info("Master template is: " + masterTemplate.getUid());
//        List<ContentSlotForTemplateModel> contentSlots = masterTemplate.getContentSlots();
//
//        LOG.info("Template has the following slots:");
//        for (ContentSlotForTemplateModel contentSlot : contentSlots) {
//            LOG.info("Content slot: " + contentSlot.getUid() + " - " + contentSlot.getPosition());
//
//            List<AbstractCMSComponentModel> cmsComponents = contentSlot.getContentSlot().getCmsComponents();
//            LOG.info("Components: ");
//            for (AbstractCMSComponentModel cmsComponent : cmsComponents) {
//                LOG.info("Component: " + cmsComponent.getUid() + " - " + cmsComponent.getType());
//
//                if (cmsComponent instanceof SimpleBannerComponentModel) {
//                    SimpleBannerComponentModel cmsComponent1 = (SimpleBannerComponentModel) cmsComponent;
//                    LOG.info("Media code: " + cmsComponent1.getMedia().getCode());
//                }
//            }
//        }

        return getViewForPage(model);
//        return "pages/projects/projectPage";
    }
}