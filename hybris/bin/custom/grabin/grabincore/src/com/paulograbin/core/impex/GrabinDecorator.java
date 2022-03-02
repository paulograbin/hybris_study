package com.paulograbin.core.impex;

import com.paulograbin.core.tew.services.RandomService;
import de.hybris.platform.core.Registry;
import de.hybris.platform.util.CSVCellDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;


public class GrabinDecorator implements CSVCellDecorator {

    private static final Logger LOG = LoggerFactory.getLogger(GrabinDecorator.class);

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final DateTimeFormatter dateTimeFormatter;
    private final RandomService randomService;



    public GrabinDecorator() {
        dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
        randomService = (RandomService) Registry.getApplicationContext().getBean("randomService");
    }

    @Override
    public String decorate(int i, Map<Integer, String> map) {
        LOG.info("Decorating: " + i);
        Integer randomNumber = randomService.getRandomNumber();

        return dateTimeFormatter.format(LocalDateTime.now()) + " " + randomNumber;
    }
}
