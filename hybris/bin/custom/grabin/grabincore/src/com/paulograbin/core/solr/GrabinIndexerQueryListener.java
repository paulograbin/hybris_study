package com.paulograbin.core.solr;

import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.indexer.IndexerQueryContext;
import de.hybris.platform.solrfacetsearch.indexer.IndexerQueryListener;
import de.hybris.platform.solrfacetsearch.indexer.exceptions.IndexerException;
import org.apache.log4j.Logger;


public class GrabinIndexerQueryListener implements IndexerQueryListener {

    final static Logger LOG = Logger.getLogger(GrabinIndexerQueryListener.class);

    @Override
    public void beforeQuery(IndexerQueryContext indexerQueryContext) throws IndexerException {
        IndexedType indexedType = indexerQueryContext.getIndexedType();
        LOG.info(indexedType.getIdentifier() + " - " + indexedType.getCode() + ": Before query");
    }

    @Override
    public void afterQuery(IndexerQueryContext indexerQueryContext) throws IndexerException {
        LOG.info("After query...");
    }

    @Override
    public void afterQueryError(IndexerQueryContext indexerQueryContext) throws IndexerException {
        LOG.info("After query error...");
    }
}
