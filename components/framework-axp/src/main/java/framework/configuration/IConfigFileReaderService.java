package framework.configuration;

import java.util.Map;

/**
 * The class for read configuration files in config directory.
 */
public interface IConfigFileReaderService
{

  /**
   * Read the given file in the default config directory.
   *
   * @param configFile
   * @return
   * @throws Exception
   */
  <K, V> Map<K, V> readFile(ConfigFile configFile) throws Exception;
}
