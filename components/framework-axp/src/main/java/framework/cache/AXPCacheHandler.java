package framework.cache;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This handler is responsible for managing {@link AXPCache} by using {@link IAXPCacheLoader}
 */
@SuppressWarnings("unchecked")
public final class AXPCacheHandler implements IAXPCacheHandlerService
{

  private static final IAXPCacheHandlerService INSTANCE = new AXPCacheHandler();

  private AXPCacheHandler()
  {
  }

  public static IAXPCacheHandlerService getInstance()
  {
    return INSTANCE;
  }

  /**
   * {@inheritDoc}
   *
   * @param axpCache    client cache
   * @param cacheLoader cache loader
   * @param cacheKey    cache key
   * @param <K>
   * @param <V>
   * @return
   * @throws Exception
   */
  @Override
  public <K, V> V getData(final AXPCache axpCache, final IAXPCacheLoader<K, V> cacheLoader, final K cacheKey) throws
      Exception
  {
    final V cachedData = (V) axpCache.get(cacheKey);
    if (cachedData != null)
    {
      return cachedData;
    }
    else
    {
      Map<K, V> missingData = cacheLoader.load(Collections.singletonList(cacheKey));
      for (final Map.Entry<K, V> loadedDataEntry : missingData.entrySet())
      {
        axpCache.put(loadedDataEntry.getKey(), loadedDataEntry.getValue());
      }
    }

    return (V) axpCache.get(cacheKey);
  }

  /**
   * {@inheritDoc}
   *
   * @param axpCache     client cache
   * @param cacheLoader  cache loader
   * @param cacheKeyList cache key list
   * @param <K>
   * @param <V>
   * @return
   * @throws Exception
   */
  @Override
  public <K, V> Map<K, V> getDataAsMap(final AXPCache axpCache, final IAXPCacheLoader<K, V> cacheLoader,
                                       final List<K> cacheKeyList) throws Exception
  {
    final Map<K, V> dataMap = new HashMap<>();
    final List<K> missingCacheKeyList = new ArrayList<>();

    for (final K cacheKey : cacheKeyList)
    {
      final V cachedData = (V) axpCache.get(cacheKey);

      if (cachedData != null)
      {
        dataMap.put(cacheKey, cachedData);
      }
      else
      {
        missingCacheKeyList.add(cacheKey);
      }
    }

    if (!missingCacheKeyList.isEmpty())
    {
      final Map<K, V> missingDataMap = cacheLoader.load(missingCacheKeyList);

      for (final Map.Entry<K, V> loadedDataEntry : missingDataMap.entrySet())
      {
        axpCache.put(loadedDataEntry.getKey(), loadedDataEntry.getValue());
      }

      dataMap.putAll(missingDataMap);
    }

    return dataMap;
  }

  /**
   * {@inheritDoc}
   *
   * @param axpCache     client cache
   * @param cacheLoader  cache loader
   * @param cacheKeyList cache key list
   * @param <K>
   * @param <V>
   * @return
   * @throws Exception
   */
  @Override
  public <K, V> List<V> getDataAsList(final AXPCache axpCache, final IAXPCacheLoader<K, V> cacheLoader,
                                      final List<K> cacheKeyList) throws Exception
  {
    final Map<K, V> dataMap = getDataAsMap(axpCache, cacheLoader, cacheKeyList);

    final List<V> resultDataList = new ArrayList<>();

    for (final K cacheKey : cacheKeyList)
    {
      resultDataList.add(dataMap.get(cacheKey));
    }

    return resultDataList;
  }

  /**
   * {@inheritDoc}
   *
   * @param axpCache client cache
   * @param cacheKey cache key
   * @param value    value
   * @param <K>
   * @param <V>
   */
  @Override
  public <K, V> void update(final AXPCache axpCache, final K cacheKey, final V value)
  {
    axpCache.put(cacheKey, value);
  }

  /**
   * {@inheritDoc}
   *
   * @param axpCache
   */
  @Override
  public void clear(final AXPCache axpCache)
  {
    axpCache.clear();
  }

  /**
   * {@inheritDoc}
   *
   * @param axpCache
   * @param cacheKey
   * @param <K>
   */
  @Override
  public <K> void remove(final AXPCache axpCache, K cacheKey)
  {
    axpCache.remove(cacheKey);
  }
}
