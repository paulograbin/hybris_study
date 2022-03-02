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
package com.paulograbin.hac.setup;

import static com.paulograbin.hac.constants.GrabinhacConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.paulograbin.hac.constants.GrabinhacConstants;
import com.paulograbin.hac.service.GrabinhacService;


@SystemSetup(extension = GrabinhacConstants.EXTENSIONNAME)
public class GrabinhacSystemSetup
{
	private final GrabinhacService grabinhacService;

	public GrabinhacSystemSetup(final GrabinhacService grabinhacService)
	{
		this.grabinhacService = grabinhacService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		grabinhacService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return GrabinhacSystemSetup.class.getResourceAsStream("/grabinhac/sap-hybris-platform.png");
	}
}
