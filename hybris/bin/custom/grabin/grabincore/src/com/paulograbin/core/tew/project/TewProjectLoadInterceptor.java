package com.paulograbin.core.tew.project;

import com.paulograbin.core.model.ProjectModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.LoadInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Random;


public class TewProjectLoadInterceptor implements LoadInterceptor<ProjectModel> {

    private static final Logger LOG = LoggerFactory.getLogger(TewProjectLoadInterceptor.class);


    @Resource
    private ModelService modelService;



    @Override
    public void onLoad(ProjectModel projectModel, InterceptorContext interceptorContext) throws InterceptorException {
        LOG.debug("On load...");

//        if (Objects.isNull(projectModel.getRandomNumber())) {
//            LOG.info("Ops... this guy has no Random Number: " + projectModel.getProjectId());
//
//            int newRandomNumber = new Random().nextInt(100000);
//
//            projectModel.setRandomNumber(newRandomNumber);
//            modelService.save(projectModel);
//        }
    }
}
