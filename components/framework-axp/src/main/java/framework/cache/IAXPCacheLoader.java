package framework.cache;

import java.util.List;
import java.util.Map;

/**
 * This defines the contract related to data loading logic of a client side cache
 *
 * @param <K> Type of cache key
 * @param <V> Type of cached data
 */
public interface IAXPCacheLoader<K, V>
{

  /**
   * Load all the data related to cache key list
   *
   * @param cacheKeyList cache key list
   * @return a map from each key in {@code keys} to the data associated with that key; <b>may not contain null values</b>
   * @throws Exception
   */
  Map<K, V> load(List<K> cacheKeyList) throws Exception;
}
