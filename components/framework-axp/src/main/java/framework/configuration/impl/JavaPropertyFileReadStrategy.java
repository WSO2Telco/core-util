package framework.configuration.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import framework.configuration.CarbonUtilsDelegator;
import framework.configuration.IConfigFileReaderStrategy;

/**
 * Java property file reader strategy.
 * This supports for the standard java property files.
 */
public final class JavaPropertyFileReadStrategy implements IConfigFileReaderStrategy
{

  private CarbonUtilsDelegator carbonUtilsDelegator;

  public JavaPropertyFileReadStrategy(CarbonUtilsDelegator carbonUtilsDelegator)
  {
    this.carbonUtilsDelegator = carbonUtilsDelegator;
  }

  /**
   * {@inheritDoc}
   *
   * @param fileName
   * @return
   * @throws IOException
   */
  @Override
  public Map<String, String> readFile(String fileName) throws IOException
  {
    String filePath = Joiner.on(File.separator).join(carbonUtilsDelegator.getCarbonConfigDirPath(), fileName);
    Path configPath = Paths.get(filePath);
    try (InputStream stream = Files.newInputStream(configPath))
    {
      Properties properties = new Properties();
      properties.load(stream);

      return Maps.fromProperties(properties);
    }
  }
}