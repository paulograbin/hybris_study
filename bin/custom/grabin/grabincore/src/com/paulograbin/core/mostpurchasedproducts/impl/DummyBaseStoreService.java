package com.paulograbin.core.mostpurchasedproducts.impl;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.List;


public class DummyBaseStoreService implements BaseStoreService {

    @Override
    public List<BaseStoreModel> getAllBaseStores() {
        return null;
    }

    @Override
    public BaseStoreModel getBaseStoreForUid(String s) throws AmbiguousIdentifierException, UnknownIdentifierException {
        return null;
    }

    @Override
    public BaseStoreModel getCurrentBaseStore() {
        return null;
    }
}
