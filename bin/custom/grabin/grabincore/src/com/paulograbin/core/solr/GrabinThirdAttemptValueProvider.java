package com.paulograbin.core.solr;

import com.paulograbin.core.model.ProjectModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.spi.InputDocument;
import de.hybris.platform.solrfacetsearch.provider.ValueResolver;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * <p>
 * Implementations of this interface are responsible for resolving the values to be indexed. This interface works at the
 * indexed property level.
 * </p>
 *
 * @see TypeValueResolver
 */
public class GrabinThirdAttemptValueProvider implements ValueResolver<ProjectModel> {

    final static Logger LOG = Logger.getLogger(GrabinThirdAttemptValueProvider.class);

    /**
     * Resolves the values to be indexed. The indexed properties that use the same value resolver are grouped and this
     * method is called once for each one of these groups.
     *
     * @param document          - document that will be indexed, all the resolved values should be added as fields to this document
     * @param batchContext      - the current indexer batch context
     * @param indexedProperties - the indexed properties that use the same value resolver
     * @param model             - the values should be resolved for this model instance
     * @throws FieldValueProviderException if an error occurs
     */
    @Override
    public void resolve(InputDocument document, IndexerBatchContext batchContext, Collection<IndexedProperty> indexedProperties, ProjectModel model) throws FieldValueProviderException {
        for (IndexedProperty indexedProperty : indexedProperties) {
            LOG.info("Indexed prop" + indexedProperty.getDisplayName());

//            Works but not used

            document.addField(indexedProperty, model.getToBeDeleted(), "");
        }
    }
}
