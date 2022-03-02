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
package com.impexloader.paulograbin.setup;

import static com.impexloader.paulograbin.constants.GrabinimpexloaderConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.impexloader.paulograbin.constants.GrabinimpexloaderConstants;
import com.impexloader.paulograbin.service.GrabinimpexloaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SystemSetup(extension = GrabinimpexloaderConstants.EXTENSIONNAME)
public class GrabinimpexloaderSystemSetup
{

	private static final Logger LOG = LoggerFactory.getLogger(GrabinimpexloaderSystemSetup.class);

	private final GrabinimpexloaderService grabinimpexloaderService;

	public GrabinimpexloaderSystemSetup(final GrabinimpexloaderService grabinimpexloaderService)
	{
		this.grabinimpexloaderService = grabinimpexloaderService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		grabinimpexloaderService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return GrabinimpexloaderSystemSetup.class.getResourceAsStream("/grabinimpexloader/sap-hybris-platform.png");
	}
}
