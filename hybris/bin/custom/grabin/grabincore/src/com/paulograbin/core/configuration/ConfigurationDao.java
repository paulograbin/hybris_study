package com.paulograbin.core.configuration;

import com.paulograbin.core.model.EnvironmentConfigurationModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;


public interface ConfigurationDao extends Dao {

    EnvironmentConfigurationModel findConfiguration(String configurationCode);

    EnvironmentConfigurationModel findConfigurationForEnvironment(String configurationCode);

}
