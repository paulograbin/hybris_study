package com.paulograbin.core.solr;

import com.paulograbin.core.model.ProjectModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.spi.InputDocument;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractValueResolver;

public class GrabinTesteResolver extends AbstractValueResolver<ProjectModel, Object, Object> {


    @Override
    protected void addFieldValues(InputDocument inputDocument, IndexerBatchContext indexerBatchContext, IndexedProperty indexedProperty, ProjectModel projectModel, ValueResolverContext<Object, Object> valueResolverContext) throws FieldValueProviderException {

    }
}
