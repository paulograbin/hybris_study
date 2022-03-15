package com.paulograbin.core.tew.project;

import com.paulograbin.core.model.ProjectModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TewProjectValidateInterceptor implements ValidateInterceptor<ProjectModel> {

    private static final Logger LOG = LoggerFactory.getLogger(TewProjectValidateInterceptor.class);

    @Override
    public void onValidate(ProjectModel projectModel, InterceptorContext interceptorContext) throws InterceptorException {
        LOG.info("On validate...");
    }
}
