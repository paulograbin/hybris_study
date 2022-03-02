package com.paulograbin.core.mostpurchasedproducts.impl;

import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.product.UnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class DummyUnitService implements UnitService {


    @Override
    public UnitModel getUnitForCode(String code) {
        return null;
    }

    @Override
    public Set<UnitModel> getAllUnits() {
        return null;
    }

    @Override
    public Set<String> getAllUnitTypes() {
        return null;
    }

    @Override
    public Set<UnitModel> getUnitsForUnitType(String unitType) {
        return null;
    }
}
