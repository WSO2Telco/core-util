package framework.configuration;

/**
 * enum to hold the configuration file names which contains in the default config directory.
 */
public enum ConfigFile
{
  /**
   * framework.properties
   */
  FRAMEWORK_CONF("framework.properties");

  private final String name;

  ConfigFile(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }
}
