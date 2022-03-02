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

import de.hybris.platform.acceleratorservices.config.SiteConfigService;
import de.hybris.platform.acceleratorstorefrontcommons.interceptors.BeforeViewHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class to gather value of storefront.wro4j.enabled from properties file, which can enable wro4j.
 */
public class ConfigWro4jBeforeViewHandler implements BeforeViewHandler
{
	private static final Logger LOG = Logger.getLogger(ConfigWro4jBeforeViewHandler.class);


	private SiteConfigService siteConfigService;

	protected SiteConfigService getSiteConfigService()
	{
		return siteConfigService;
	}

	@Required
	public void setSiteConfigService(final SiteConfigService siteConfigService)
	{
		this.siteConfigService = siteConfigService;
	}

	@Override
	public void beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelAndView modelAndView)
			throws Exception
	{
        Boolean attributeValue = Boolean.valueOf(getSiteConfigService().getBoolean("storefront.wro4j.enabled", false));

        LOG.info("WRO4J Enabled? " + attributeValue);

        modelAndView.addObject("wro4jEnabled", attributeValue);
	}
}
