package com.paulograbin.core.solr;

import com.paulograbin.core.model.ProjectModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.provider.IdentityProvider;
import org.apache.log4j.Logger;


public class GrabinIdentityProvider implements IdentityProvider<ProjectModel> {


    final static Logger LOG = Logger.getLogger(GrabinIdentityProvider.class);


    /**
     * Provides unique string representation of indexed type identifier that is generated with respect to the model type
     * restrictions.
     *
     * @param indexConfig
     * @param model
     * @return identifier
     */
    @Override
    public String getIdentifier(IndexConfig indexConfig, ProjectModel model) {
        String generatedIdentity = model.getProjectId() + "@" + model.getProjectName();
        LOG.info("I've just generated a new identity: " + generatedIdentity);

        return generatedIdentity;
    }
}
