package framework.cache;

import framework.cache.impl.DefaultAXPLoadingCache;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.fail;

public class AXPCacheBuilderTest
{

  @Test
  public void testBuild()
  {
    try
    {
      final IAXPLoadingCache<String, String> clientLoadingCache = AXPCacheBuilder.newBuilder().setNoTimeLimit(true)
          .build(cacheKeyList -> null);

      assertThat(clientLoadingCache).isInstanceOf(DefaultAXPLoadingCache.class);
    }
    catch (Exception e)
    {
      e.printStackTrace();

      fail();
    }
  }
}
