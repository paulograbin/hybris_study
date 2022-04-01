package com.paulograbin.core.product;

import com.paulograbin.core.model.ProjectModel;
import com.paulograbin.core.tew.services.RandomService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.LoadInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Objects;


public class GrabinProductLoadInterceptor implements LoadInterceptor<ProductModel> {

    private static final Logger LOG = LoggerFactory.getLogger(GrabinProductLoadInterceptor.class);

    @Resource
    private ModelService modelService;

    @Resource
    private RandomService randomService;


    @Override
    public void onLoad(ProductModel productModel, InterceptorContext interceptorContext) throws InterceptorException {
        LOG.debug("On load...");

//            LOG.info("Ops... this product boolean attribute is null: " + productModel.getCode());
//
//            boolean b = randomService.randomBoolean();
//
//            productModel.setPentlanBoolean(b);
//            modelService.save(productModel);
    }
}
