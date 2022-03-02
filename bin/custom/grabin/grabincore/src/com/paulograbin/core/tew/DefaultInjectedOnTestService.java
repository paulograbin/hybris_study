package com.paulograbin.core.tew;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DefaultInjectedOnTestService implements InjectedOnTestService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultInjectedOnTestService.class);

    private TestService testService;

    public String teste() {
        LOG.info("Calling test service...");

        return testService.getName();
    }

    public void setTestService(TestService testService) {
        this.testService = testService;
    }
}
