package com.paulograbin.core.solr;

import com.paulograbin.core.model.ProjectModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.util.ArrayList;
import java.util.Collection;

import static org.apache.commons.lang3.BooleanUtils.isFalse;

public class GrabinTextoLocalizadoValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider {

    @Override
    public Collection<FieldValue> getFieldValues(IndexConfig indexConfig, IndexedProperty indexedProperty, Object model) throws FieldValueProviderException {
        if (isFalse(model instanceof ProjectModel)) {
            throw new FieldValueProviderException("Must be instance of ProjectModel");
        }

        Collection<FieldValue> fieldValues = new ArrayList();
        ProjectModel product = (ProjectModel) model;

        return fieldValues;
    }
}
