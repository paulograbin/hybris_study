package com.paulograbin.core.configuration;

import com.paulograbin.core.model.EnvironmentConfigurationModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;


public class DefaultConfigurationDao extends DefaultGenericDao<EnvironmentConfigurationModel> implements ConfigurationDao {

    public DefaultConfigurationDao() {
        super(EnvironmentConfigurationModel._TYPECODE);
    }

    @Override
    public EnvironmentConfigurationModel findConfiguration(String configurationCode) {
        return null;
    }

    @Override
    public EnvironmentConfigurationModel findConfigurationForEnvironment(String configurationCode) {
        return null;
    }
}
