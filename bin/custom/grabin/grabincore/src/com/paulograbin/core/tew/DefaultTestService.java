package com.paulograbin.core.tew;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DefaultTestService implements TestService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultTestService.class);

    @Override
    public String getName() {
        LOG.info("Default called...");

        return "Default";
    }
}
