package framework.cache;

import java.util.List;
import java.util.Map;

/**
 * This defines the contract related to loading cache. Use {@link AXPCacheBuilder} to define
 * and create {@link IAXPLoadingCache}
 *
 * @param <K> Type of cache key
 * @param <V> Type of cached data
 */
public interface IAXPLoadingCache<K, V>
{

  /**
   * Returns data associated with {@code cacheKey} from the cache. If the data related to given {@code cacheKey} is not
   * in the cache, then the missing data will be loaded according to the logic implemented in
   * {@link IAXPCacheLoader#load(List)} and update the cache.
   *
   * @param cacheKey cache key
   * @return Returns data associated with {@code cacheKey} from the cache
   * @throws Exception
   */
  V getData(K cacheKey) throws Exception;

  /**
   * Returns a map of data associated with {@code cacheKeyList} from the cache. If the data related to given
   * {@code cacheKeyList} are not in the cache, then the missing data will be loaded according to the logic implemented
   * in {@link IAXPCacheLoader#load(List)} and update the cache.
   *
   * @param cacheKeyList cache key list
   * @return Returns a map of data associated with {@code cacheKeyList} from the cache
   * @throws Exception
   */
  Map<K, V> getDataAsMap(List<K> cacheKeyList) throws Exception;

  /**
   * Returns a list of data associated with {@code cacheKeyList} from the cache. If the data related to given
   * {@code cacheKeyList} are not in the cache, then the missing data will be loaded according to the logic implemented
   * in {@link IAXPCacheLoader#load(List)} and update the cache.
   * <p>
   * This always preserve the order of the data list, according to the provided {@code cacheKeyList}
   *
   * @param cacheKeyList cache key list
   * @return Returns a list of data associated with {@code cacheKeyList} from the cache, the order will be preserved
   * @throws Exception
   */
  List<V> getDataAsList(List<K> cacheKeyList) throws Exception;

  /**
   * Add or update cache entries with provided values
   *
   * @param cacheKey cache key
   * @param value    value
   */
  void update(K cacheKey, V value);

  /**
   * remove data from the cache associated with the given cache key
   *
   * @param cacheKey
   */
  void remove(K cacheKey);

  /**
   * Clear all cached data
   */
  void clear();

}
