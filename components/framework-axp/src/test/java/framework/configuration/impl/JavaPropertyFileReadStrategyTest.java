package framework.configuration.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import com.google.common.base.Joiner;
import framework.configuration.CarbonUtilsDelegator;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.fail;

public class JavaPropertyFileReadStrategyTest
{

  @Test
  public void testReadFile()
  {
    try
    {
      CarbonUtilsDelegator mock = mock(CarbonUtilsDelegator.class);

      /**
       * This is a dirty fix to resolve resource path.
       * When building individual module and the parent, resolving two different resource locations.
       */
      String currentPath = Paths.get("").toAbsolutePath().normalize().toString();
      if (currentPath.contains("components"))

      {
        String path = Joiner
            .on(File.separator)
            .join("src", "test", "resources", "com", "wso2telco", "framework", "configuration", "impl",
                "JavaPropertyFileReadStrategyTest");
        Mockito.when(mock.getCarbonConfigDirPath()).thenReturn(path);
      }
      else
      {
        String path = Joiner
            .on(File.separator)
            .join("components", "framework-axp", "src", "test", "resources", "com", "wso2telco", "framework",
                "configuration", "impl", "JavaPropertyFileReadStrategyTest");
        Mockito.when(mock.getCarbonConfigDirPath()).thenReturn(path);
      }

      JavaPropertyFileReadStrategy strategy = new JavaPropertyFileReadStrategy(mock);
      Map<String, String> propertiesMap = strategy.readFile("test.properties");

      assertThat(propertiesMap).isNotEmpty();
      assertThat(propertiesMap).hasSize(17);
    }
    catch (IOException e)
    {
      e.printStackTrace();

      fail();
    }
  }

}
