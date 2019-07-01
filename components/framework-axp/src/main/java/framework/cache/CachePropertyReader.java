package framework.cache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import com.google.common.base.Joiner;
import framework.configuration.ConfigFile;
import framework.delegator.CarbonUtilsDelegator;

/**
 * This class is a dedicated class to read the cache related configurations
 */
class CachePropertyReader
{

  private static CachePropertyReader instance;

  private String DEFAULT_CACHE_TIMEOUT = "framework.axpcache.timeout";

  private Properties properties = new Properties();

  static synchronized CachePropertyReader getInstance()
  {
    if (instance == null)
    {
      instance = new CachePropertyReader();
    }

    return instance;
  }

  /**
   * get default ttl value configured in framework.properties file in conf folder.
   *
   * @return configured value if available, otherwise -1.
   * @throws IOException
   */
  long getDefaultTTL() throws IOException
  {
    if (properties.isEmpty())
    {
      String filePath = Joiner.on(File.separator)
          .join(CarbonUtilsDelegator.getInstance().getCarbonConfigDirPath(), ConfigFile.FRAMEWORK_CONF.getName());
      Path configPath = Paths.get(filePath);
      try (InputStream stream = Files.newInputStream(configPath))
      {
        properties.load(stream);
      }
    }
    String property = properties.getProperty(DEFAULT_CACHE_TIMEOUT);

    return property != null && !property.isEmpty() ? Long.parseLong(property) : -1;
  }
}
