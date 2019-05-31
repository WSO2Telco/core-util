package framework.cache.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.common.collect.Lists;
import framework.cache.AXPCache;
import framework.cache.IAXPCacheLoader;
import framework.cache.IAXPLoadingCache;
import org.fest.reflect.core.Reflection;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.*;
import static org.testng.Assert.fail;


public class DefaultAXPLoadingCacheTest
{
  private TestCacheLoader mockCacheLoader;

  private DefaultAXPLoadingCache<TestCacheKey, String> loadingCache;

  private AXPCache axpCache;

  @BeforeMethod
  public void setUp()
  {
    mockCacheLoader = mock(TestCacheLoader.class);

    loadingCache = new DefaultAXPLoadingCache<>(mockCacheLoader, new AXPCache(true));

    axpCache = Reflection.field("axpCache").ofType(AXPCache.class).in(this.loadingCache).get();
    axpCache.put(getTestCacheKey(1), "1*");
    axpCache.put(getTestCacheKey(2), "2");
    axpCache.put(getTestCacheKey(3), "3*");
  }

  @Test
  public void testGetDataAsMap()
  {
    try
    {
      final List<TestCacheKey> keyList = Lists.newArrayList();
      keyList.add(getTestCacheKey(1));
      keyList.add(getTestCacheKey(4));
      keyList.add(getTestCacheKey(3));
      keyList.add(getTestCacheKey(5));

      Mockito.when(mockCacheLoader.load(anyList())).thenCallRealMethod();

      Map<TestCacheKey, String> dataMap = loadingCache.getDataAsMap(keyList);

      assertThat(dataMap).isNotNull();
      assertThat(dataMap.size()).isEqualTo(4);
      assertThat(dataMap.get(getTestCacheKey(1))).isEqualTo( "1*");
      assertThat(dataMap.get(getTestCacheKey(4))).isEqualTo( "4");
      assertThat(dataMap.get(getTestCacheKey(3))).isEqualTo( "3*");
      assertThat(dataMap.get(getTestCacheKey(5))).isEqualTo( "5");

      assertThat(axpCache.size()).isEqualTo( 5);
      assertThat(axpCache.get(getTestCacheKey(1))).isEqualTo( "1*");
      assertThat(axpCache.get(getTestCacheKey(2))).isEqualTo( "2");
      assertThat(axpCache.get(getTestCacheKey(3))).isEqualTo( "3*");
      assertThat(axpCache.get(getTestCacheKey(4))).isEqualTo( "4");
      assertThat(axpCache.get(getTestCacheKey(5))).isEqualTo( "5");

      verify(mockCacheLoader, only()).load(anyList());

      dataMap = loadingCache.getDataAsMap(keyList);

      assertThat(dataMap).isNotNull();
      assertThat(dataMap.size()).isEqualTo( 4);
      assertThat(dataMap.get(getTestCacheKey(1))).isEqualTo( "1*");
      assertThat(dataMap.get(getTestCacheKey(4))).isEqualTo( "4");
      assertThat(dataMap.get(getTestCacheKey(3))).isEqualTo( "3*");
      assertThat(dataMap.get(getTestCacheKey(5))).isEqualTo( "5");

      assertThat(axpCache.size()).isEqualTo( 5);
      assertThat(axpCache.get(getTestCacheKey(1))).isEqualTo("1*");
      assertThat(axpCache.get(getTestCacheKey(2))).isEqualTo("2");
      assertThat(axpCache.get(getTestCacheKey(3))).isEqualTo("3*");
      assertThat(axpCache.get(getTestCacheKey(4))).isEqualTo("4");
      assertThat(axpCache.get(getTestCacheKey(5))).isEqualTo("5");

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
      final List<TestCacheKey> keyList = Lists.newLinkedList();
      keyList.add(getTestCacheKey(1));
      keyList.add(getTestCacheKey(4));
      keyList.add(getTestCacheKey(3));
      keyList.add(getTestCacheKey(5));

      when(mockCacheLoader.load(anyList())).thenCallRealMethod();

      List<String> dataList = loadingCache.getDataAsList(keyList);

      assertThat(dataList).isNotNull();
      assertThat(dataList.size()).isEqualTo( 4);
      assertThat(dataList.get(0)).isEqualTo( "1*");
      assertThat(dataList.get(1)).isEqualTo( "4");
      assertThat(dataList.get(2)).isEqualTo( "3*");
      assertThat(dataList.get(3)).isEqualTo( "5");

      assertThat(axpCache.size()).isEqualTo( 5);
      assertThat(axpCache.get(getTestCacheKey(1))).isEqualTo( "1*");
      assertThat(axpCache.get(getTestCacheKey(2))).isEqualTo( "2");
      assertThat(axpCache.get(getTestCacheKey(3))).isEqualTo( "3*");
      assertThat(axpCache.get(getTestCacheKey(4))).isEqualTo( "4");
      assertThat(axpCache.get(getTestCacheKey(5))).isEqualTo( "5");

      verify(mockCacheLoader, only()).load(anyList());

      dataList = loadingCache.getDataAsList(keyList);

      assertThat(dataList).isNotNull();
      assertThat(dataList.size()).isEqualTo( 4);
      assertThat(dataList.get(0)).isEqualTo( "1*");
      assertThat(dataList.get(1)).isEqualTo( "4");
      assertThat(dataList.get(2)).isEqualTo( "3*");
      assertThat(dataList.get(3)).isEqualTo( "5");

      assertThat(axpCache.size()).isEqualTo( 5);
      assertThat(axpCache.get(getTestCacheKey(1))).isEqualTo( "1*");
      assertThat(axpCache.get(getTestCacheKey(2))).isEqualTo( "2");
      assertThat(axpCache.get(getTestCacheKey(3))).isEqualTo( "3*");
      assertThat(axpCache.get(getTestCacheKey(4))).isEqualTo( "4");
      assertThat(axpCache.get(getTestCacheKey(5))).isEqualTo( "5");

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
    TestCacheKey key = getTestCacheKey(1);

    String result = loadingCache.getData(key);

    assertThat(result).isEqualTo( "1*");
  }

  @Test
  public void testGetData_nonCached() throws Exception
  {
    TestCacheKey key = getTestCacheKey(6);
    List<TestCacheKey> keys = Lists.newArrayList(key);
    when(mockCacheLoader.load(keys)).thenCallRealMethod();

    String result = loadingCache.getData(key);

    assertThat(result).isEqualTo("6");
    verify(mockCacheLoader, only()).load(keys);
  }

  @Test
  public void testUpdate() throws Exception
  {
    TestCacheKey key = getTestCacheKey(7);
    List<TestCacheKey> keys = Lists.newArrayList(key);

    loadingCache.update(key, "7");

    assertThat(loadingCache.getData(key)).isEqualTo( "7");
    verify(mockCacheLoader, never()).load(keys);
  }

  @Test
  public void testClear() throws Exception
  {
    IAXPLoadingCache<TestCacheKey, String> cache =
        new DefaultAXPLoadingCache<>(mockCacheLoader, new AXPCache(true));
    TestCacheKey key = getTestCacheKey(8);

    loadingCache.clear();

    assertThat(cache.getData(key)).isNull();
  }

  private TestCacheKey getTestCacheKey(int key)
  {
    return new TestCacheKey(key, String.valueOf(key));
  }

  private static class TestCacheKey
  {

    private final int key;

    private final String anotherAttribute;

    TestCacheKey(int key, String anotherAttribute)
    {
      this.key = key;
      this.anotherAttribute = anotherAttribute;
    }

    @Override
    public boolean equals(Object o)
    {
      if (this == o)
      {
        return true;
      }

      if (!(o instanceof TestCacheKey))
      {
        return false;
      }

      TestCacheKey that = (TestCacheKey) o;

      return key == that.key && Objects.equals(anotherAttribute, that.anotherAttribute);
    }

    @Override
    public int hashCode()
    {
      return Objects.hash(key, anotherAttribute);
    }
  }

  private class TestCacheLoader implements IAXPCacheLoader<TestCacheKey, String>
  {

    @Override
    public Map<TestCacheKey, String> load(List<TestCacheKey> cacheKeyList)
    {
      final Map<TestCacheKey, String> dataMap = new HashMap<>();

      for (TestCacheKey testCacheKey : cacheKeyList)
      {
        dataMap.put(testCacheKey, testCacheKey.anotherAttribute);
      }

      return dataMap;
    }
  }
}
