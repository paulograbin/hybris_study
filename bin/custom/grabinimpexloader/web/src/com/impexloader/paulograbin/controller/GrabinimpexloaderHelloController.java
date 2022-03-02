/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.impexloader.paulograbin.controller;

import static com.impexloader.paulograbin.constants.GrabinimpexloaderConstants.PLATFORM_LOGO_CODE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.impexloader.paulograbin.service.GrabinimpexloaderService;


@Controller
public class GrabinimpexloaderHelloController
{
	@Autowired
	private GrabinimpexloaderService grabinimpexloaderService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(final ModelMap model)
	{
		model.addAttribute("logoUrl", grabinimpexloaderService.getHybrisLogoUrl(PLATFORM_LOGO_CODE));
		return "welcome";
	}
}
