package com.paulograbin.core.tew.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class DefaultRandomService implements RandomService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultRandomService.class);

    @Override
    public Integer getRandomNumber() {
        LOG.info("Returning random number...");

        return new Random().nextInt();
    }

    @Override
    public Integer getRandomNumber(int upperLimit) {
        LOG.info("Returning random number limited at " + upperLimit);

        return new Random().nextInt(upperLimit);
    }


    @Override
    public String makeRandomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }
}
