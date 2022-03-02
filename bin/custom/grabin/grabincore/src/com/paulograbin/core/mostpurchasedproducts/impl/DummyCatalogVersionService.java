package com.paulograbin.core.mostpurchasedproducts.impl;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.DuplicatedItemIdentifier;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class DummyCatalogVersionService implements CatalogVersionService {


    @Override
    public void setSessionCatalogVersion(String catalogId, String catalogVersionName) {

    }

    @Override
    public void setSessionCatalogVersions(Collection<CatalogVersionModel> catalogVersions) {

    }

    @Override
    public void addSessionCatalogVersion(CatalogVersionModel catalogVersion) {

    }

    @Override
    public Collection<CatalogVersionModel> getSessionCatalogVersions() {
        return null;
    }

    @Override
    public CatalogVersionModel getCatalogVersion(String catalogId, String catalogVersionName) {
        return null;
    }

    @Override
    public CatalogVersionModel getSessionCatalogVersionForCatalog(String catalogId) {
        return null;
    }

    @Override
    public Collection<CatalogVersionModel> getSessionCatalogVersionsForCatalog(String catalogId) {
        return null;
    }

    @Override
    public boolean canRead(CatalogVersionModel catalogVersion, UserModel user) {
        return false;
    }

    @Override
    public boolean canWrite(CatalogVersionModel catalogVersion, UserModel user) {
        return false;
    }

    @Override
    public Collection<CatalogVersionModel> getAllWritableCatalogVersions(PrincipalModel principal) {
        return null;
    }

    @Override
    public Collection<CatalogVersionModel> getAllReadableCatalogVersions(PrincipalModel principal) {
        return null;
    }

    @Override
    public Collection<CatalogVersionModel> getAllCatalogVersions() {
        return null;
    }

    @Override
    public <T extends CatalogVersionModel> Collection<T> getAllCatalogVersionsOfType(Class<T> versionType) {
        return null;
    }

    @Override
    public Collection<DuplicatedItemIdentifier> findDuplicatedIds(CatalogVersionModel catalogVersionModel) {
        return null;
    }
}
