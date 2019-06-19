package framework.configuration;

import framework.configuration.impl.JavaPropertyFileReadStrategy;
import framework.configuration.impl.XMLPropertyFileReadStrategy;

/**
 * The class to resolve file reader strategy by extension
 */
public final class ConfigFileReaderStrategy
{

  private static final ConfigFileReaderStrategy INSTANCE = new ConfigFileReaderStrategy();

  private static final String EXTENSION_PROPERTIES = "properties";

  private ConfigFileReaderStrategy()
  {
  }

  private static final String EXTENSION_XML = "xml";

  /**
   * get singleton instance of {@link ConfigFileReaderStrategy}
   *
   * @return
   */
  public static ConfigFileReaderStrategy getInstance()
  {
    return INSTANCE;
  }

  /**
   * Get the file read strategy by file extension
   *
   * @param extension
   * @return
   */
  public IConfigFileReaderStrategy getStrategy(String extension)
  {
    switch (extension)
    {
      case EXTENSION_PROPERTIES:
        return new JavaPropertyFileReadStrategy(CarbonUtilsDelegator.getInstance());
      case EXTENSION_XML:
        return new XMLPropertyFileReadStrategy();
      default:
        //TODO introduce new runtime exception
        throw new RuntimeException("Unsupported file type.");
    }
  }
}
