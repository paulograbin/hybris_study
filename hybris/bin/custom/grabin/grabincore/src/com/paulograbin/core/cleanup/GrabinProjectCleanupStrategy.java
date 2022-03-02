package com.paulograbin.core.cleanup;

import com.paulograbin.core.model.ProjectModel;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.jobs.maintenance.MaintenanceCleanupStrategy;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


// todo analisar hierarquia de classes

public class GrabinProjectCleanupStrategy implements MaintenanceCleanupStrategy<ProjectModel, CronJobModel> {

    private static final Logger LOG = Logger.getLogger(GrabinProjectCleanupStrategy.class);


    private ModelService modelService;
    private FlexibleSearchService flexibleSearchService;
    private Set<String> returnCode;


//    @Autowired
//    public GrabinProjectCleanupStrategy(ConfigurationService configurationService, ModelService modelService, FlexibleSearchService flexibleSearchService) {
//        this.configurationService = configurationService;
//        this.modelService = modelService;
//        this.flexibleSearchService = flexibleSearchService;
//    }

    @Override
    public FlexibleSearchQuery createFetchQuery(CronJobModel cjm) {
        final Map<String, Object> params = new HashMap<>();

        params.put(ProjectModel.TOBEDELETED, Boolean.TRUE);
//        params.put(ProjectModel.APPROVED, Boolean.FALSE);
//        params.put(ProjectModel.READY, Boolean.FALSE);

        final String querySql = " SELECT {pk} " +
                " FROM {" + ProjectModel._TYPECODE + "} " +
                " WHERE {" + ProjectModel.TOBEDELETED + "} = ?toBeDeleted ";

        final FlexibleSearchQuery query = new FlexibleSearchQuery(querySql, params);
        query.setResultClassList(Collections.singletonList(ProjectModel.class));

        SearchResult<ProjectModel> search = flexibleSearchService.<ProjectModel>search(query);
        List<ProjectModel> result = search.getResult();
        LOG.info("Result count " + result.size());

        return query;
    }

    @Override
    public void process(List<ProjectModel> elements) {
        LOG.info("Removing projects...");
        LOG.info(elements.size() + " projects to remove...");

        for (ProjectModel element : elements) {
            LOG.info("Processing project " + element.getProjectId() + " " + element.getProjectName());

            modelService.remove(element);
        }
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public void setReturnCode(Set<String> returnCode) {
        this.returnCode = returnCode;
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
