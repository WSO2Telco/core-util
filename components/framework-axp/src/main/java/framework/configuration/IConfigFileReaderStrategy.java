package framework.configuration;

import java.io.IOException;
import java.util.Map;

/**
 * The class for Configuration files read services.
 */
public interface IConfigFileReaderStrategy
{

  /**
   * Read the given configuration file from the default configuration directory.
   *
   * @param fileName
   * @return a map of properties which contains in the given file
   * @throws IOException
   */
  <K, V> Map<K, V> readFile(String fileName) throws IOException;
}
