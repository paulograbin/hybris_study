package com.paulograbin.core.impex;

import de.hybris.platform.util.CSVCellDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;


public class GrabinAnotherDecorator implements CSVCellDecorator {

    private static final Logger LOG = LoggerFactory.getLogger(GrabinAnotherDecorator.class);

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final DateTimeFormatter dateTimeFormatter;


    public GrabinAnotherDecorator() {
        dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
    }

    @Override
    public String decorate(int i, Map<Integer, String> map) {
        LOG.info("Decorating: " + i);

        return dateTimeFormatter.format(LocalDateTime.now());
    }
}
