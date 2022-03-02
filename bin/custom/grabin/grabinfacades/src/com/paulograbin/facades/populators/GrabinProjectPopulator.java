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
package com.paulograbin.facades.populators;

import com.paulograbin.core.model.ProjectModel;
import com.paulograbin.facades.tew.DefaultProjectFacade;
import com.paulograbin.facades.tew.ProjectData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GrabinProjectPopulator implements Populator<ProjectModel, ProjectData> {

    private static final Logger LOG = LoggerFactory.getLogger(GrabinProjectPopulator.class);


    @Override
    public void populate(ProjectModel projectModel, ProjectData projectData) throws ConversionException {
//        LOG.info("Populating project data: " + projectModel.getProjectId());

        projectData.setProjectId(String.valueOf(projectModel.getProjectId()));
        projectData.setProjectName(projectModel.getProjectName());
        projectData.setApproved(projectModel.getApproved());
        projectData.setReady(projectModel.getReady());
        projectData.setToBeDeleted(projectModel.getToBeDeleted());
        projectData.setRandomNumber(projectModel.getRandomNumber());
    }
}
