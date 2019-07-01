package framework.configuration;

/**
 * enum to hold the configuration file names which contains in the default config directory.
 */
public enum ConfigFile
{

  /**
   * oneapi-validation-conf.properties
   */
  ONEAPI_VALIDATION_CONF("oneapi-validation-conf.properties"),

  /**
   * mediator-conf.properties
   */
  MEDIATOR_CONF("mediator-conf.properties"),

  /**
   * framework.properties
   */
  FRAMEWORK_CONF("framework.properties"),

  /**
   * MobileCountryConfig.xml
   */
  MOBILE_COUNTRY_CONF("MobileCountryConfig.xml");


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
