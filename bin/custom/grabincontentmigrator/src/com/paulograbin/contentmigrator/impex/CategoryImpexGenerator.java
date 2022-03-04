package com.paulograbin.contentmigrator.impex;

import de.hybris.platform.catalog.model.KeywordModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.PK;
import org.apache.log4j.Logger;

import java.util.*;

public class CategoryImpexGenerator extends AbstractImpexGenerator<CategoryModel> implements ImpexGenerator<CategoryModel> {

    private static final Logger LOG = Logger.getLogger(CategoryImpexGenerator.class);

    public CategoryImpexGenerator(ImpexHeaderGenerationService impexHeaderGenerationService1) {
        super(impexHeaderGenerationService1);
    }

    @Override
    public Set<PK> makePkList(CategoryModel model) {
        Set<PK> pks = new HashSet<>();

        List<KeywordModel> keywords = model.getKeywords();
        for(KeywordModel keyword: keywords) {
            LOG.info("Keyword: " + keyword.getKeyword() + " - " + keyword.getPk());
            pks.add(keyword.getPk());
        }

        LOG.info("Category: " + model.getCode() + " - " + model.getPk());
        pks.add(model.getPk());

        for(CategoryModel superCategory: model.getSupercategories()) {
            LOG.info("Super-category: " + superCategory.getCode() + " - " + superCategory.getPk());
            pks.add(superCategory.getPk());
        }

        for(CategoryModel subCategory: model.getSupercategories()) {
            LOG.info("Sub-category: " + subCategory.getCode() + " - " + subCategory.getPk());
            pks.add(subCategory.getPk());
        }

        return pks;
    }

    @Override
    public Map<String, Set<PK>> makePkMap(CategoryModel model) {
        Map<String, Set<PK>> itemTypeToPksMap = new HashMap<>();

        List<KeywordModel> keywords = model.getKeywords();
        Set<PK> keywordPks = new HashSet<>();
        for(KeywordModel keyword: keywords) {
            LOG.info("Keyword: " + keyword.getKeyword() + " - " + keyword.getPk());
            keywordPks.add(keyword.getPk());
        }
        itemTypeToPksMap.put(KeywordModel._TYPECODE, keywordPks);

        Set<PK> categoryPks = new HashSet<>();
        LOG.info("Category: " + model.getCode() + " - " + model.getPk());
        categoryPks.add(model.getPk());

        itemTypeToPksMap.put(CategoryModel._TYPECODE, categoryPks);
        return itemTypeToPksMap;
    }

    @Override
    public List<String> makeTypeToExportList() {
        List<String> typeList = new ArrayList<>();

        typeList.add(KeywordModel._TYPECODE);
        typeList.add(CategoryModel._TYPECODE);

        return typeList;
    }

}
