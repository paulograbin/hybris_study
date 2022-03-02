package com.paulograbin.core.solr;

import com.paulograbin.core.model.ProjectModel;
import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.ProductClassificationAttributesValueResolver;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import org.apache.log4j.Logger;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;


public class GrabinProjectNameValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider {

    final static Logger LOG = Logger.getLogger(GrabinProjectNameValueProvider.class);

    @Override
    public Collection<FieldValue> getFieldValues(IndexConfig indexConfig, IndexedProperty indexedProperty, Object model) throws FieldValueProviderException {
        LOG.info("Value provider triggered");

        Collection<FieldValue> fieldValues = new ArrayList<>();

        if (model instanceof ProjectModel) {
            ProjectModel projectModel = (ProjectModel) model;

            LOG.info("Processing project " + projectModel.getProjectId());

            String projectName = projectModel.getProjectName();
            String fieldName = indexedProperty.getName();

            LOG.info("Field name " + fieldName);
            LOG.info("Field value " + projectName);


            FieldValue fieldValue = new FieldValue(fieldName, projectName);
            fieldValues.add(fieldValue);
        }

        return fieldValues;
    }
}
