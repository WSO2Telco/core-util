package framework.configuration;

import framework.configuration.impl.JavaPropertyFileReadStrategy;
import framework.configuration.impl.XMLPropertyFileReadStrategy;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigFileReaderStrategyTest
{

  @Test
  public void testGetStrategy_extensionProperties()
  {
    assertThat(ConfigFileReaderStrategy.getInstance().getStrategy("properties"))
        .isInstanceOf(JavaPropertyFileReadStrategy.class);
  }

  @Test
  public void testGetStrategy_extensionXml()
  {
    assertThat(ConfigFileReaderStrategy.getInstance().getStrategy("xml"))
        .isInstanceOf(XMLPropertyFileReadStrategy.class);
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testGetStrategy_extensionOther()
  {
    ConfigFileReaderStrategy.getInstance().getStrategy("abc");
  }
}
