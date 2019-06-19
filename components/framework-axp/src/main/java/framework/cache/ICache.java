package framework.cache;

/**
 * The interface for the cache.
 * This has the support for all the primary features of a cache
 */
public interface ICache
{

  /**
   * Insert data in to the cache with custom time-to-live value.
   *
   * @param key
   * @param value
   * @param periodInMillis time-to-live value
   * @param <K>
   * @param <V>
   */
  <K, V> void put(K key, V value, long periodInMillis);

  /**
   * Remove the data associated with the given key from the cache.
   *
   * @param key
   * @param <K>
   */
  <K> void remove(K key);

  /**
   * Get the data associated with the the given key.
   *
   * @param key
   * @param <K>
   * @return the object associated to the given cache key
   */
  <K> Object get(K key);

  /**
   * Get the current size of the cache.
   *
   * @return current size of the cache.
   */
  long size();

  /**
   * Insert data into the cache against the given key.
   *
   * @param key
   * @param value
   * @param <K>
   * @param <V>
   */
  <K, V> void put(K key, V value);

  /**
   * Clear all the data in the cache
   */
  void clear();
}
