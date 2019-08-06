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
