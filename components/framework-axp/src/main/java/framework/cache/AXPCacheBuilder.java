package framework.cache;

import framework.cache.impl.DefaultAXPLoadingCache;

/**
 * This builder is responsible for building {@link IAXPLoadingCache}
 */
public class AXPCacheBuilder
{

  private boolean noTimeLimit = true;

  /**
   * @return new {@link AXPCacheBuilder}
   */
  public static AXPCacheBuilder newBuilder()
  {
    return new AXPCacheBuilder();
  }

  /**
   * Sets {@code noTimeLimit} flag of {@link AXPCache}
   *
   * @param noTimeLimit no time limit
   * @return current instance of AXPCacheBuilder
   */
  public AXPCacheBuilder setNoTimeLimit(final boolean noTimeLimit)
  {
    this.noTimeLimit = noTimeLimit;

    return this;
  }

  /**
   * Create and returns new {@link DefaultAXPLoadingCache} by using provided {@code IAXPCacheLoader}
   *
   * @param cacheLoader cache loader
   * @param <K> Type of cache key
   * @param <V> Type of cache data
   * @return new {@link DefaultAXPLoadingCache}
   */
  public <K, V> IAXPLoadingCache<K, V> build(final IAXPCacheLoader<K, V> cacheLoader)
  {
    return new DefaultAXPLoadingCache<>(cacheLoader, new AXPCache(noTimeLimit));
  }
}
