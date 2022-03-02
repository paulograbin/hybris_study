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
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * Controller for Quotes
 */
@RestController
@RequestMapping(value = "/api/project")
public class TewProjectController {

    private static final Logger LOG = Logger.getLogger(TewProjectController.class);


    @Resource(name = "projectFacade")
    private ProjectFacade projectFacade;


    @GetMapping
    public ResponseEntity<List<ProjectData>> fetchAll() {
        List<ProjectData> projectModels = projectFacade.loadAll();

        return ResponseEntity.ok(projectModels);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectData> fetchSingle(@PathVariable("projectId") final Integer projectId) {
        ProjectData projectModels = projectFacade.loadProjectById(projectId);

        return ResponseEntity.ok(projectModels);
    }

    @PostMapping("/approve/{projectId}")
    public ResponseEntity<String> approve(@PathVariable("projectId") final String projectId) {
        return ResponseEntity.status(HttpStatus.OK).body("Alou");
    }

    @PostMapping("/ready/{projectId}")
    public ResponseEntity<ProjectData> ready(@PathVariable("projectId") final Integer projectId) {
        ProjectData projectData = projectFacade.toggleReady(projectId);

        return ResponseEntity.status(HttpStatus.OK).body(projectData);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> delete(@PathVariable("projectId") final String projectId) {
        return ResponseEntity.status(HttpStatus.OK).body("Alou");
    }
}
