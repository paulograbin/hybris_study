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
package com.paulograbin.storefront.controllers.cms;

import com.paulograbin.core.tew.services.DefaultGrabinService;
import de.hybris.platform.acceleratorcms.model.components.SimpleResponsiveBannerComponentModel;
import de.hybris.platform.acceleratorfacades.device.ResponsiveMediaFacade;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import com.paulograbin.storefront.controllers.ControllerConstants;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller for CMS SimpleResponsiveBannerComponent
 */
@Controller("SimpleResponsiveBannerComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.SimpleResponsiveBannerComponent)
public class SimpleResponsiveBannerComponentController extends AbstractAcceleratorCMSComponentController<SimpleResponsiveBannerComponentModel> {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleResponsiveBannerComponentController.class);


    @Resource(name = "responsiveMediaFacade")
    private ResponsiveMediaFacade responsiveMediaFacade;

    @Resource(name = "commerceCommonI18NService")
    private CommerceCommonI18NService commerceCommonI18NService;

    @Override
    protected void fillModel(final HttpServletRequest request, final Model model, final SimpleResponsiveBannerComponentModel component) {
		LOG.info("Controller for " + component.getUid());

        final List<ImageData> mediaDataList = responsiveMediaFacade.getImagesFromMediaContainer(component.getMedia(commerceCommonI18NService.getCurrentLocale()));
        model.addAttribute("medias", mediaDataList);
        model.addAttribute("urlLink", component.getUrlLink());
    }
}
