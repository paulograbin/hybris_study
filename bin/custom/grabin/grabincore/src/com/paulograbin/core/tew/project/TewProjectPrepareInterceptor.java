package com.paulograbin.core.tew.project;

import com.paulograbin.core.model.ProjectModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.LoadInterceptor;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TewProjectPrepareInterceptor implements PrepareInterceptor<ProjectModel> {

    private static final Logger LOG = LoggerFactory.getLogger(TewProjectPrepareInterceptor.class);

    @Override
    public void onPrepare(ProjectModel projectModel, InterceptorContext interceptorContext) throws InterceptorException {
        LOG.info("On prepare...");
    }
}
