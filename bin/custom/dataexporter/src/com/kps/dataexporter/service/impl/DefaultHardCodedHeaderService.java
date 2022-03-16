package com.kps.dataexporter.service.impl;

import com.kps.dataexporter.model.HardCodedImpexHeaderModel;
import com.kps.dataexporter.service.HardCodedHeaderService;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;


public class DefaultHardCodedHeaderService implements HardCodedHeaderService {

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Override
    public String findHardCodedHeaderForItemType(String typecode) {
        final HardCodedImpexHeaderModel hardCodedImpexHeader = new HardCodedImpexHeaderModel();
        hardCodedImpexHeader.setTypeCodeReference(typecode);

        try {
            HardCodedImpexHeaderModel modelByExample = flexibleSearchService.getModelByExample(hardCodedImpexHeader);
            if (modelByExample != null) {
                return modelByExample.getHeader();
            }
        } catch (ModelNotFoundException e) {
            return StringUtils.EMPTY;
        }

        return StringUtils.EMPTY;
    }
}
