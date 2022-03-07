package com.paulograbin.contentmigrator.service.impl;

import com.paulograbin.contentmigrator.model.ItemTypeImpexHeaderModel;
import com.paulograbin.contentmigrator.service.HardCodedHeaderService;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;


public class DefaultHardCodedHeaderService implements HardCodedHeaderService {

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Override
    public String findHardCodedHeaderForItemType(String typecode) {
        final ItemTypeImpexHeaderModel itemTypeImpexHeaderModel = new ItemTypeImpexHeaderModel();
        itemTypeImpexHeaderModel.setTypeCodeReference(typecode);

        try {
            ItemTypeImpexHeaderModel modelByExample = flexibleSearchService.getModelByExample(itemTypeImpexHeaderModel);
            if (modelByExample != null) {
                return modelByExample.getHeader();
            }
        } catch (ModelNotFoundException e) {
            return StringUtils.EMPTY;
        }

        return StringUtils.EMPTY;
    }
}