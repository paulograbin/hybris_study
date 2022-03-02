package com.paulograbin.core.impex;

import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrabinTranslator extends AbstractValueTranslator {

    private static final Logger LOG = LoggerFactory.getLogger(GrabinTranslator.class);


    @Override
    public Object importValue(String s, Item item) throws JaloInvalidParameterException {
        return s;
    }

    @Override
    public String exportValue(Object o) throws JaloInvalidParameterException {
        return null;
    }
}
