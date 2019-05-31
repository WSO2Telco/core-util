package framework.cache.impl;


import java.util.List;
import java.util.Map;

import framework.cache.AXPCache;
import framework.cache.AXPCacheHandler;
import framework.cache.IAXPCacheLoader;
import framework.cache.IAXPLoadingCache;

/**
 * This implementation uses {@link AXPCache} as the base cache.
 */
public class DefaultAXPLoadingCache<K, V> implements IAXPLoadingCache<K, V>
{

  private final AXPCache axpCache;

  private final IAXPCacheLoader<K, V> cacheLoader;

  public DefaultAXPLoadingCache(final IAXPCacheLoader<K, V> cacheLoader,
                                final AXPCache axpCache)
  {
    this.axpCache = axpCache;
    this.cacheLoader = cacheLoader;
  }

  /**
   * {@inheritDoc}
   *
   * @param cacheKey cache key
   * @return
   * @throws Exception
   */
  @Override
  public V getData(final K cacheKey) throws Exception
  {
    return AXPCacheHandler.getInstance().getData(axpCache, cacheLoader, cacheKey);
  }

  /**
   * {@inheritDoc}
   *
   * @param cacheKeyList cache key list
   * @return
   * @throws Exception
   */
  @Override
  public Map<K, V> getDataAsMap(final List<K> cacheKeyList) throws Exception
  {
    return AXPCacheHandler.getInstance().getDataAsMap(axpCache, cacheLoader, cacheKeyList);
  }

  /**
   * {@inheritDoc}
   *
   * @param cacheKeyList cache key list
   * @return
   * @throws Exception
   */
  @Override
  public List<V> getDataAsList(final List<K> cacheKeyList) throws Exception
  {
    return AXPCacheHandler.getInstance().getDataAsList(axpCache, cacheLoader, cacheKeyList);
  }

  /**
   * {@inheritDoc}
   *
   * @param cacheKey cache key
   * @param value    value
   */
  @Override
  public void update(final K cacheKey, final V value)
  {
    AXPCacheHandler.getInstance().update(axpCache, cacheKey, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear()
  {
    AXPCacheHandler.getInstance().clear(axpCache);
  }

  /**
   * {@inheritDoc}
   *
   * @param cacheKey
   */
  @Override
  public void remove(K cacheKey)
  {
    AXPCacheHandler.getInstance().remove(axpCache, cacheKey);
  }
}
