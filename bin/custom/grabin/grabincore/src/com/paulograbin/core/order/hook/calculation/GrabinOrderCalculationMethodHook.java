package com.paulograbin.core.order.hook.calculation;

import de.hybris.platform.commerceservices.order.hook.CommerceCartCalculationMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.servicelayer.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;


public class GrabinOrderCalculationMethodHook implements CommerceCartCalculationMethodHook {

    private static final Logger LOG = LoggerFactory.getLogger(GrabinOrderCalculationMethodHook.class);


    @Resource
    private ModelService modelService;


    @Override
    public void afterCalculate(CommerceCartParameter parameter) {
        LOG.info("After calculation...");
    }

    @Override
    public void beforeCalculate(CommerceCartParameter parameter) {
        LOG.info("Before calculation...");
    }
}
