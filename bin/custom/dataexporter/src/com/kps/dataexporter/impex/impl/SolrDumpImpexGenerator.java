package com.kps.dataexporter.impex.impl;

import com.kps.dataexporter.impex.DumpImpexGenerator;
import de.hybris.platform.acceleratorservices.model.redirect.SolrPageRedirectModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.solrfacetsearch.model.SolrIndexModel;
import de.hybris.platform.solrfacetsearch.model.SolrIndexerBatchModel;
import de.hybris.platform.solrfacetsearch.model.SolrIndexerDistributedProcessModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrEndpointUrlModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedPropertyModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedTypeModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexerQueryModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexerQueryParameterModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrServerConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrStopWordModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrSynonymConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrValueRangeModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrValueRangeSetModel;
import de.hybris.platform.solrfacetsearch.model.cron.SolrQueryStatisticsCollectorCronJobModel;
import de.hybris.platform.solrfacetsearch.model.cron.SolrUpdateStopWordsCronJobModel;
import de.hybris.platform.solrfacetsearch.model.cron.SolrUpdateSynonymsCronJobModel;
import de.hybris.platform.solrfacetsearch.model.indexer.SolrIndexedCoresRecordModel;
import de.hybris.platform.solrfacetsearch.model.indexer.cron.SolrExtIndexerCronJobModel;
import de.hybris.platform.solrfacetsearch.model.indexer.cron.SolrIndexOptimizationCronJobModel;
import de.hybris.platform.solrfacetsearch.model.indexer.cron.SolrIndexerCronJobModel;
import de.hybris.platform.solrfacetsearch.model.indexer.cron.SolrIndexerHotUpdateCronJobModel;
import de.hybris.platform.solrfacetsearch.model.redirect.SolrCategoryRedirectModel;
import de.hybris.platform.solrfacetsearch.model.redirect.SolrFacetSearchKeywordRedirectModel;
import de.hybris.platform.solrfacetsearch.model.redirect.SolrURIRedirectModel;


public class SolrDumpImpexGenerator extends AbstractDumpImpexGenerator implements DumpImpexGenerator {

    @Override
    public String generateDump(CatalogVersionModel catalogVersionModel) {
        StringBuilder builder = new StringBuilder();

        builder.append(generateImpex(SolrCategoryRedirectModel._TYPECODE));
        builder.append(generateImpex(SolrEndpointUrlModel._TYPECODE));
        builder.append(generateImpex(SolrExtIndexerCronJobModel._TYPECODE));
        builder.append(generateImpex(SolrFacetSearchConfigModel._TYPECODE));
        builder.append(generateImpex(SolrFacetSearchKeywordRedirectModel._TYPECODE));
        builder.append(generateImpex(SolrIndexConfigModel._TYPECODE));
        builder.append(generateImpex(SolrIndexModel._TYPECODE));
//        builder.append(generateImpex(SolrIndexOperationModel._TYPECODE));
        builder.append(generateImpex(SolrIndexOptimizationCronJobModel._TYPECODE));
        builder.append(generateImpex(SolrIndexedCoresRecordModel._TYPECODE));
        builder.append(generateImpex(SolrIndexedPropertyModel._TYPECODE));
        builder.append(generateImpex(SolrIndexedTypeModel._TYPECODE));
        builder.append(generateImpex(SolrIndexerBatchModel._TYPECODE));
        builder.append(generateImpex(SolrIndexerCronJobModel._TYPECODE));
        builder.append(generateImpex(SolrIndexerDistributedProcessModel._TYPECODE));
        builder.append(generateImpex(SolrIndexerHotUpdateCronJobModel._TYPECODE));
        builder.append(generateImpex(SolrIndexerQueryModel._TYPECODE));
        builder.append(generateImpex(SolrIndexerQueryParameterModel._TYPECODE));
        builder.append(generateImpex(SolrPageRedirectModel._TYPECODE));
        builder.append(generateImpex(SolrQueryStatisticsCollectorCronJobModel._TYPECODE));
        builder.append(generateImpex(SolrSearchConfigModel._TYPECODE));
        builder.append(generateImpex(SolrServerConfigModel._TYPECODE));
        builder.append(generateImpex(SolrStopWordModel._TYPECODE));
        builder.append(generateImpex(SolrSynonymConfigModel._TYPECODE));
        builder.append(generateImpex(SolrURIRedirectModel._TYPECODE));
        builder.append(generateImpex(SolrUpdateStopWordsCronJobModel._TYPECODE));
        builder.append(generateImpex(SolrUpdateSynonymsCronJobModel._TYPECODE));
        builder.append(generateImpex(SolrValueRangeModel._TYPECODE));
        builder.append(generateImpex(SolrValueRangeSetModel._TYPECODE));
//        builder.append(SolrSearchQueryPropertyModel._TYPECODE);
//        builder.append(SolrSearchQuerySortModel._TYPECODE);
//        builder.append(SolrSearchQueryTemplateModel._TYPECODE);
//        builder.append(SolrSortFieldModel._TYPECODE);
//        builder.append(SolrSortModel._TYPECODE);

        return builder.toString();
    }
}
