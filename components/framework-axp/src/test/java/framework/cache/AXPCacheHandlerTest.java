package framework.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.beust.jcommander.internal.Lists;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.*;
import static org.testng.Assert.fail;

public class AXPCacheHandlerTest
{


  private AXPCache axpCache;

  private TestCacheLoader mockCacheLoader;

  @BeforeMethod
  public void setUp()
  {
    axpCache = new AXPCache(true);
    axpCache.put(1, "1*");
    axpCache.put(2, "2");
    axpCache.put(3, "3*");

    mockCacheLoader = mock(TestCacheLoader.class);
  }

  @Test
  public void testGetDataAsMap()
  {
    try
    {
      final List<Integer> keyList = Lists.newArrayList();
      keyList.add(1);
      keyList.add(4);
      keyList.add(3);
      keyList.add(5);

      when(mockCacheLoader.load(anyList())).thenCallRealMethod();

      Map<Integer, String> dataMap =
          AXPCacheHandler.getInstance().getDataAsMap(axpCache, mockCacheLoader, keyList);

      assertThat(dataMap).isNotNull();
      assertThat(dataMap.size()).isEqualTo(4);
      assertThat(dataMap.get(1)).isEqualTo("1*");
      assertThat(dataMap.get(4)).isEqualTo("4");
      assertThat(dataMap.get(3)).isEqualTo("3*");
      assertThat(dataMap.get(5)).isEqualTo("5");

      assertThat(axpCache.size()).isEqualTo(5);
      assertThat(axpCache.get(1)).isEqualTo("1*");
      assertThat(axpCache.get(2)).isEqualTo("2");
      assertThat(axpCache.get(3)).isEqualTo("3*");
      assertThat(axpCache.get(4)).isEqualTo("4");
      assertThat(axpCache.get(5)).isEqualTo("5");

      verify(mockCacheLoader, only()).load(anyList());

      dataMap = AXPCacheHandler.getInstance().getDataAsMap(axpCache, mockCacheLoader, keyList);

      Assertions.assertThat(dataMap).isNotNull();
      assertThat(dataMap.size()).isEqualTo(4);
      assertThat(dataMap.get(1)).isEqualTo("1*");
      assertThat(dataMap.get(4)).isEqualTo("4");
      assertThat(dataMap.get(3)).isEqualTo("3*");
      assertThat(dataMap.get(5)).isEqualTo("5");

      assertThat(axpCache.size()).isEqualTo(5);
      assertThat(axpCache.get(1)).isEqualTo("1*");
      assertThat(axpCache.get(2)).isEqualTo("2");
      assertThat(axpCache.get(3)).isEqualTo("3*");
      assertThat(axpCache.get(4)).isEqualTo("4");
      assertThat(axpCache.get(5)).isEqualTo("5");

      verify(mockCacheLoader, only()).load(anyList());
    }
    catch (Exception e)
    {
      e.printStackTrace();

      fail();
    }
  }

  @Test
  public void testGetDataAsList()
  {
    try
    {
      final List<Integer> keyList = Lists.newArrayList();
      keyList.add(1);
      keyList.add(4);
      keyList.add(3);
      keyList.add(5);

      when(mockCacheLoader.load(anyList())).thenCallRealMethod();

      List<String> dataList =
          AXPCacheHandler.getInstance().getDataAsList(axpCache, mockCacheLoader, keyList);

      assertThat(dataList).isNotNull();
      assertThat(dataList.size()).isEqualTo(4);
      assertThat(dataList.get(0)).isEqualTo("1*");
      assertThat(dataList.get(1)).isEqualTo("4");
      assertThat(dataList.get(2)).isEqualTo("3*");
      assertThat(dataList.get(3)).isEqualTo("5");

      assertThat(axpCache.size()).isEqualTo(5);
      assertThat(axpCache.get(1)).isEqualTo("1*");
      assertThat(axpCache.get(2)).isEqualTo("2");
      assertThat(axpCache.get(3)).isEqualTo("3*");
      assertThat(axpCache.get(4)).isEqualTo("4");
      assertThat(axpCache.get(5)).isEqualTo("5");

      verify(mockCacheLoader, only()).load(anyList());

      dataList = AXPCacheHandler.getInstance().getDataAsList(axpCache, mockCacheLoader, keyList);

      assertThat(dataList).isNotNull();
      assertThat(dataList.size()).isEqualTo(4);
      assertThat(dataList.get(0)).isEqualTo("1*");
      assertThat(dataList.get(1)).isEqualTo("4");
      assertThat(dataList.get(2)).isEqualTo("3*");
      assertThat(dataList.get(3)).isEqualTo("5");

      assertThat(axpCache.size()).isEqualTo(5);
      assertThat(axpCache.get(1)).isEqualTo("1*");
      assertThat(axpCache.get(2)).isEqualTo("2");
      assertThat(axpCache.get(3)).isEqualTo("3*");
      assertThat(axpCache.get(4)).isEqualTo("4");
      assertThat(axpCache.get(5)).isEqualTo("5");

      verify(mockCacheLoader, only()).load(anyList());
    }
    catch (Exception e)
    {
      e.printStackTrace();

      fail();
    }
  }

  @Test
  public void testGetData_cached() throws Exception
  {
    String result = AXPCacheHandler.getInstance().getData(axpCache, mockCacheLoader, 1);
    assertThat(result).isEqualTo("1*");
  }

  @Test
  public void testGetData_nonCached() throws Exception
  {
    List<Integer> keys = Lists.newArrayList();
    keys.add(7);
    when(mockCacheLoader.load(keys)).thenCallRealMethod();
    String result = AXPCacheHandler.getInstance().getData(axpCache, mockCacheLoader, 7);
    assertThat(result).isEqualTo("7");
  }

  @Test
  public void testUpdate()
  {
    int key = 2;
    AXPCacheHandler.getInstance().update(axpCache, key, "updated value");
    assertThat(axpCache.get(key)).isEqualTo("updated value");
  }

  @Test
  public void testClearCache()
  {
    AXPCache cache = new AXPCache(true);
    cache.put(1, 1L);
    cache.put(2, 2L);
    cache.put(3, 3L);

    AXPCacheHandler.getInstance().clear(cache);
    assertThat(cache.get(1l)).isNull();
    assertThat(cache.get(2l)).isNull();
    assertThat(cache.get(3l)).isNull();

    assertThat(cache.get(1)).isNull();
    assertThat(cache.get(2)).isNull();
    assertThat(cache.get(3)).isNull();
  }

  private static class TestCacheLoader implements IAXPCacheLoader<Integer, String>
  {

    @Override
    public Map<Integer, String> load(List<Integer> cacheKeyList) throws Exception
    {
      final Map<Integer, String> dataMap = new HashMap<>();

      for (Integer testCacheKey : cacheKeyList)
      {
        dataMap.put(testCacheKey, testCacheKey + "");
      }

      return dataMap;
    }
  }
}
