package com.paulograbin.core.tew.project;

import com.paulograbin.core.model.ProjectModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.LoadInterceptor;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TewProjectRemoveInterceptor implements RemoveInterceptor<ProjectModel> {

    private static final Logger LOG = LoggerFactory.getLogger(TewProjectRemoveInterceptor.class);

    @Override
    public void onRemove(ProjectModel projectModel, InterceptorContext interceptorContext) throws InterceptorException {
        LOG.info("On remove...");
    }
}
