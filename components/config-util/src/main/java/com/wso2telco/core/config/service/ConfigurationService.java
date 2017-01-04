package com.wso2telco.core.config.service;

import com.wso2telco.core.config.DataHolder;

/**
 * Created by madusha on 1/2/17.
 */
public interface ConfigurationService {
    /**
     * Gets the DataHolder object.
     *
     * @return DataHolder
     */
    DataHolder getDataHolder();

    /**
     * Unset the configurations from the service
     */
    void unregisterDataHolder();
}
