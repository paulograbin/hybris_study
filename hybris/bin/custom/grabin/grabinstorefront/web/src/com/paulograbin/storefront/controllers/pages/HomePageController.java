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

import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.contents.ContentSlotNameModel;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;

import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.model.pages.PageTemplateModel;
import de.hybris.platform.cms2.model.relations.ContentSlotForPageModel;
import de.hybris.platform.cms2.model.relations.ContentSlotForTemplateModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


/**
 * Controller for home page
 */
@Controller
@RequestMapping("/")
public class HomePageController extends AbstractPageController {

    private static final Logger LOGGER = Logger.getLogger(HomePageController.class);

    private static final String LOGOUT = "logout";
    private static final String ACCOUNT_CONFIRMATION_SIGNOUT_TITLE = "account.confirmation.signout.title";
    private static final String ACCOUNT_CONFIRMATION_CLOSE_TITLE = "account.confirmation.close.title";

    @RequestMapping(method = RequestMethod.GET)
    public String home(@RequestParam(value = WebConstants.CLOSE_ACCOUNT, defaultValue = "false") final boolean closeAcc,
                       @RequestParam(value = LOGOUT, defaultValue = "false") final boolean logout, final Model model,
                       final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
        if (logout) {
            String message = ACCOUNT_CONFIRMATION_SIGNOUT_TITLE;
            if (closeAcc) {
                message = ACCOUNT_CONFIRMATION_CLOSE_TITLE;
            }
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.INFO_MESSAGES_HOLDER, message);
            return REDIRECT_PREFIX + ROOT;
        }

        storeCmsPageInModel(model, getContentPageForLabelOrId(null));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(null));
        updatePageTitle(model, getContentPageForLabelOrId(null));

        final AbstractPageModel page = (AbstractPageModel) model.asMap().get(CMS_PAGE_MODEL);
        PageTemplateModel masterTemplate = page.getMasterTemplate();

        List<ContentSlotForTemplateModel> contentSlots = masterTemplate.getContentSlots();
        for (ContentSlotForTemplateModel contentSlot : contentSlots) {
            LOGGER.info("Content slots for template: " + contentSlot.getUid());
        }
        LOGGER.info("****");

        List<ContentSlotNameModel> availableContentSlots = masterTemplate.getAvailableContentSlots();
        for (ContentSlotNameModel availableContentSlot : availableContentSlots) {
            LOGGER.info("Available content skits " + availableContentSlot.getName());
        }
        LOGGER.info("****");


        final ContentPageModel homepageModel = (ContentPageModel) page;

        List<ContentSlotForPageModel> contentSlots1 = homepageModel.getContentSlots();
        for (ContentSlotForPageModel contentSlotForPageModel : contentSlots1) {
            LOGGER.info("Content slot for page: " + homepageModel.getUid());

            List<AbstractCMSComponentModel> cmsComponents = contentSlotForPageModel.getContentSlot().getCmsComponents();
            for (AbstractCMSComponentModel cmsComponent : cmsComponents) {
                LOGGER.info("Component: " + cmsComponent.getUid());
            }


        }
        LOGGER.info("****");

        return getViewForPage(model);
    }

    protected void updatePageTitle(final Model model, final AbstractPageModel cmsPage) {
        storeContentPageTitleInModel(model, getPageTitleResolver().resolveHomePageTitle(cmsPage.getTitle()));
    }
}