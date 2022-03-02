package com.paulograbin.core.tew;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AsyncTestService implements TestService {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncTestService.class);

    @Override
    public String getName() {
        LOG.info("Async called...");

        return "Async";
    }
}
