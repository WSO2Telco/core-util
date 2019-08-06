/*
 * Copyright (c) 2019, APIGATE PVT (Ltd). (https://www.apigate.com/) All Rights Reserved.
 * APIGATE Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package framework.configuration;

import framework.configuration.impl.JavaPropertyFileReadStrategy;
import framework.configuration.impl.XMLPropertyFileReadStrategy;
import framework.delegator.CarbonUtilsDelegator;

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
