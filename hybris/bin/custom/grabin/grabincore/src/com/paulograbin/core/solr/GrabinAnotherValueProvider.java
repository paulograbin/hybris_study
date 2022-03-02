package com.paulograbin.core.solr;

import com.paulograbin.core.model.ProjectModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.spi.InputDocument;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractValueResolver;
import org.apache.log4j.Logger;


public class GrabinAnotherValueProvider extends AbstractValueResolver<ProjectModel, String, Integer> {

    final static Logger LOG = Logger.getLogger(GrabinAnotherValueProvider.class);

    @Override
    protected void addFieldValues(InputDocument inputDocument,
                                  IndexerBatchContext indexerBatchContext,
                                  IndexedProperty indexedProperty,
                                  ProjectModel projectModel,
                                  ValueResolverContext<String, Integer> valueResolverContext) throws FieldValueProviderException {
        LOG.info("GrabinAnother...");

        final String value = projectModel.getProjectName();
        LOG.info("Value " + value);
        LOG.info("Field " + indexedProperty.getName());

        inputDocument.addField(indexedProperty, value, null);
    }
}
