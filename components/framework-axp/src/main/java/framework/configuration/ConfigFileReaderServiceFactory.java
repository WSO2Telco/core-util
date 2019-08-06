package framework.configuration;

import framework.configuration.impl.ConfigFileReaderServiceImpl;

/**
 * Factory for create Config file reader service
 */
public class ConfigFileReaderServiceFactory {

    /**
     * Get configuration file reader service
     *
     * @return Singleton instance of {@link ConfigFileReaderServiceImpl}
     */
    public static IConfigFileReaderService getConfigFileReaderSerice() {
        return ConfigFileReaderServiceImpl.getInstance();
    }
}
