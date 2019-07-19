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
import framework.configuration.IConfigFileReaderStrategy;
import framework.delegator.CarbonUtilsDelegator;

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