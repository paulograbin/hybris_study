package com.paulograbin.core.tew.dao;

import com.paulograbin.core.model.ProjectModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class DefaultProjectDao extends DefaultGenericDao<ProjectModel> implements ProjectDao {

    FlexibleSearchService flexibleSearchService;


    @Autowired
    public DefaultProjectDao(FlexibleSearchService flexibleSearchService) {
        super(ProjectModel._TYPECODE);
        this.flexibleSearchService = flexibleSearchService;
    }

    public DefaultProjectDao() {
        super(ProjectModel._TYPECODE);
    }


    @Override
    public List<ProjectModel> findProjectsLimitedByCount(int resultCountLimit) {
        FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {pk} " +
                "FROM {" + ProjectModel._TYPECODE + "} " +
                "WHERE {" + ProjectModel.RANDOMNUMBER + " } IS NULL");
        query.setCount(resultCountLimit);

        SearchResult<ProjectModel> search = flexibleSearchService.search(query);
        return search.getResult();
    }


    @Override
    public List<ProjectModel> findAllProjectsUnapproved() {
        ProjectModel example = new ProjectModel();
        example.setApproved(false);

        return findProjectByExample(example);
    }

    @Override
    public ProjectModel findProjectById(Integer projectId) {
        ProjectModel example = new ProjectModel();
        example.setProjectId(projectId);

        return findProjectByExample(example).get(0);
    }


//
//    public List<ProjectModel> findAllProjectsUnapprovedUsingFlexibleSearch() {
//        Map<String, Boolean> params = new HashMap<>(3);
//        params.put(ProjectModel.APPROVED, Boolean.FALSE);
//
//        SearchResult<ProjectModel> search = flexibleSearchService.<ProjectModel> search("select * from {project}", params);
//        return search.getResult();
//    }
//
//


    @Override
    public List<ProjectModel> findAllProjectsNotReady() {
        ProjectModel example = new ProjectModel();
        example.setReady(false);

        return findProjectByExample(example);
    }

    @Override
    public List<ProjectModel> findAllProjectsApproved() {
        ProjectModel example = new ProjectModel();
        example.setApproved(true);

        return findProjectByExample(example);
    }

    @Override
    public List<ProjectModel> findAllProjectsReady() {
        ProjectModel example = new ProjectModel();
        example.setReady(true);

        return findProjectByExample(example);
    }

    @Override
    public List<ProjectModel> findAllProjects() {
        FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {pk} " +
                "FROM {" + ProjectModel._TYPECODE + "} ");

        SearchResult<ProjectModel> search = flexibleSearchService.search(query);
        return search.getResult();
    }

    private List<ProjectModel> findProjectByExample(final ProjectModel projectModel) {
        return flexibleSearchService.getModelsByExample(projectModel);
    }
}
