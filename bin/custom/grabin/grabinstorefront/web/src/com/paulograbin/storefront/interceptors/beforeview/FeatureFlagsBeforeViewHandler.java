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
package com.paulograbin.storefront.interceptors.beforeview;

import de.hybris.platform.acceleratorstorefrontcommons.interceptors.BeforeViewHandler;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





public class FeatureFlagsBeforeViewHandler implements BeforeViewHandler {

    private static final Logger LOG = LoggerFactory.getLogger(FeatureFlagsBeforeViewHandler.class);

    @Resource
    private BaseStoreService baseStoreService;

    @Override
    public void beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelAndView modelAndView) throws Exception {
        BaseStoreModel currentBaseStore = baseStoreService.getCurrentBaseStore();

        boolean attributeForNullTesting = currentBaseStore.isAttributeForNullTesting();
//        String getAAAA = currentBaseStore.getGetAnotherAttributeForNullTestingAAA();
//        String getAAAA = currentBaseStore.getAnotherAttributeForNullTesting();
        String getAAAA = currentBaseStore.getGetAnotherAttributeForNullTesting();

        modelAndView.addObject("mostPurchasedEnabled", currentBaseStore.isMostPurchasedProductsFeatureSwitch());
        modelAndView.addObject("maxAmountOfMostPurchasedProducts", currentBaseStore.getMaxAmountOfMostPurchasedProducts());
        modelAndView.addObject("minimumAmountOfMostPurchasedProducts", currentBaseStore.getMinimumAmountOfProductsNeededToShow());
        modelAndView.addObject("amountOfOrdersToConsider", currentBaseStore.getAmountOfOrdersToConsider());

        LOG.info("Feature flags before view handler executed...");
    }
}
