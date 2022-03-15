package com.paulograbin.core.tew.project;

import com.paulograbin.core.model.ProjectModel;
import com.paulograbin.core.tew.services.RandomService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.LoadInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Objects;


public class TewProjectLoadInterceptor implements LoadInterceptor<ProjectModel> {

    private static final Logger LOG = LoggerFactory.getLogger(TewProjectLoadInterceptor.class);

    @Resource
    private ModelService modelService;

    @Resource
    private RandomService randomService;


    @Override
    public void onLoad(ProjectModel projectModel, InterceptorContext interceptorContext) throws InterceptorException {
        LOG.debug("On load...");

        if (Objects.isNull(projectModel.getSetByInterceptors())) {
            LOG.info("Ops... this guys attribute set by interceptors is null: " + projectModel.getProjectId());

            String randomString = randomService.makeRandomString();

            projectModel.setSetByInterceptors(randomString);
            modelService.save(projectModel);
        }
    }
}
