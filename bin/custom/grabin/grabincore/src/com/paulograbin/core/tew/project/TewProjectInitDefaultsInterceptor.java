package com.paulograbin.core.tew.project;

import com.paulograbin.core.impex.GrabinImpexTransformerTask;
import com.paulograbin.core.model.ProjectModel;
import de.hybris.platform.servicelayer.interceptor.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TewProjectInitDefaultsInterceptor implements InitDefaultsInterceptor<ProjectModel> {


    private static final Logger LOG = LoggerFactory.getLogger(TewProjectInitDefaultsInterceptor.class);


    @Override
    public void onInitDefaults(ProjectModel projectModel, InterceptorContext interceptorContext) throws InterceptorException {

    }
}
