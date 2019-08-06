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

package framework.configuration.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.cache.AXPCacheBuilder;
import framework.cache.IAXPCacheLoader;
import framework.cache.IAXPLoadingCache;
import framework.configuration.ConfigFile;
import framework.configuration.ConfigFileReaderStrategy;
import framework.configuration.IConfigFileReaderService;
import framework.configuration.IConfigFileReaderStrategy;

/**
 * Implementation for the {@link IConfigFileReaderService}
 * This file reader class will help to read configuration property files with a caching support.
 */
public class ConfigFileReaderServiceImpl implements IConfigFileReaderService
{

  public static final ConfigFileReaderServiceImpl INSTANCE = new ConfigFileReaderServiceImpl();

  private static final String DOT = ".";

  private Map<String, Map<String, String>> propertyCacheMap = new HashMap<>();

  private ConfigFileReaderServiceImpl()
  {
  }

  /**
   * Get Singleton instance of {@link ConfigFileReaderServiceImpl}
   *
   * @return
   */
  public static ConfigFileReaderServiceImpl getInstance()
  {
    return INSTANCE;
  }

  /**
   * {@inheritDoc}
   *
   * @param configFile
   * @return
   * @throws Exception
   */
  @Override
  public Map<String, String> readFile(ConfigFile configFile) throws IOException
  {

    Map<String,String> propertyMap = propertyCacheMap.get(configFile.getName());
    if(propertyMap == null)
    {
      ConfigFileReaderStrategy configFileReaderStrategy = ConfigFileReaderStrategy.getInstance();

      String fileName = configFile.getName();
      IConfigFileReaderStrategy strategy = configFileReaderStrategy
              .getStrategy(fileName.substring(fileName.lastIndexOf(DOT) + 1));
      propertyMap = Collections.unmodifiableMap(strategy.readFile(fileName));
      propertyCacheMap.put(fileName, propertyMap);
    }

    return propertyMap;

  }
}
