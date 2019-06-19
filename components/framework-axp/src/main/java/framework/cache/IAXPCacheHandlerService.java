package framework.cache;

import java.util.List;
import java.util.Map;

/**
 * AXP cache handler service.
 * This class is responsible of handling axp cache related operations for the loading cache.
 */
public interface IAXPCacheHandlerService
{

  /**
   * Returns the data associated with {@code cacheKey} from provided {@code axpCache}. If the data related to given
   * {@code cacheKey} is not in the cache, then the missing data will be loaded according to the logic implemented in
   * {@link IAXPCacheLoader#load(List)} and update the provided {@code AXPCache}.
   *
   * @param axpCache    client cache
   * @param cacheLoader cache loader
   * @param cacheKey    cache key
   * @param <K>         Type of cache key
   * @param <V>         Type of cached data
   * @return data associated with {@code cacheKey}
   * @throws Exception
   */
  <K, V> V getData(AXPCache axpCache, IAXPCacheLoader<K, V> cacheLoader, K cacheKey)
      throws Exception;

  /**
   * Returns a map of data associated with {@code cacheKeyList} from provided {@code axpCache}. If the data related
   * to given {@code cacheKeyList} are not in the cache, then the missing data will be loaded according to the logic
   * implemented in {@link IAXPCacheLoader#load(List)} and update the provided {@code axpCache}.
   *
   * @param axpCache     client cache
   * @param cacheLoader  cache loader
   * @param cacheKeyList cache key list
   * @param <K>          Type of cache key
   * @param <V>          Type of cached data
   * @return a map of data associated with {@code cacheKeyList}
   * @throws Exception
   */
  <K, V> Map<K, V> getDataAsMap(AXPCache axpCache, IAXPCacheLoader<K, V> cacheLoader,
                                List<K> cacheKeyList)
      throws Exception;

  /**
   * Returns a list of data associated with {@code cacheKeyList} from provided {@code axpCache}. If the data related
   * to given {@code cacheKeyList} are not in the cache, then the missing data will be loaded according to the logic
   * implemented in {@link IAXPCacheLoader#load(List)} and update the provided {@code axpCache}.
   * <p>
   * This always preserve the order of the data list, according to the provided {@code cacheKeyList}
   *
   * @param axpCache     client cache
   * @param cacheLoader  cache loader
   * @param cacheKeyList cache key list
   * @param <K>          Type of cache key
   * @param <V>          Type of cached data
   * @return a list of data associated with {@code cacheKeyList}, the order will be preserved
   * @throws Exception
   */
  <K, V> List<V> getDataAsList(AXPCache axpCache, IAXPCacheLoader<K, V> cacheLoader,
                               List<K> cacheKeyList)
      throws Exception;

  /**
   * Add or update cache entries with provided values in provided client cache
   *
   * @param axpCache client cache
   * @param cacheKey cache key
   * @param value    value
   */
  <K, V> void update(AXPCache axpCache, K cacheKey, V value);

  /**
   * Clear all cached data in provide cache
   */
  void clear(AXPCache axpCache);

  /**
   * Remove data from the cache associated with the given cache key
   *
   * @param cacheKey
   * @param <K>
   */
  <K> void remove(final AXPCache axpCache, K cacheKey);
}
