package framework.cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Joiner;
import framework.logging.LazyLogger;
import framework.logging.LazyLoggerFactory;

/**
 * Cache implementation for the AXP.
 * The cache use a {@link ConcurrentHashMap} for store data in memory. The cache has time-to-live and a purge
 * options in side the implementation. Also this has the support for init cache with no time limit.
 * This has the capability of the ttl configuration support in object level as well.
 * <p>
 * Note that in the implementation {@link SoftReference} has used to wrap the object.
 * softly-reachable objects are guaranteed to have been cleared before the virtual machine throws an OutOfMemoryError.
 * There is a separate task to clean cleared soft references from the map.
 * <p>
 * !!! HIGH IMPORTANT !!!
 * <p>
 * All the objects stored in the cache will be return without cloning or deep cloning due to improve the cache performance.
 * Because of that user should be responsible of the mutability of the stored object.
 */
public final class AXPCache implements ICache
{

  /**
   * five minutes delay between purging task
   */
  private static final long DEFAULT_PURGING_TIME = 5 * 60 * 60 * 1000;

  /**
   * one minute delay between soft reference cleaning task
   */
  private static final long EMPTY_REFERENCE_CLEANING_TASK_DELAY = 60 * 60 * 1000;

  private static LazyLogger log = LazyLoggerFactory.getLogger(AXPCache.class);

  private final ConcurrentHashMap<Object, SoftReference<CacheObject>> cache = new ConcurrentHashMap<>();

  private boolean noTimeLimit;

  private long ttl;

  private ScheduledExecutorService purgeExecutor;

  private ScheduledExecutorService cleanEmptyReferencesExecutor;

  /**
   * Initialize cache with the given time to live value.
   * Object will be purged after expiring the ttl. This will overide the configuration value defined in the
   *
   * @param ttl object time-to-live value in milli seconds
   */
  public AXPCache(long ttl)
  {
    this.ttl = ttl;
    initCleaningTasks(this.ttl);
  }

  /**
   * Does not initialize the cache purging thread if the noTimeLimit parameter is true
   * If the given boolean value is false, then the cache purge time will be applicable from the the configuration file.
   * Even if the noTimeLimit parameter true,
   *
   * @param noTimeLimit
   */
  public AXPCache(boolean noTimeLimit)
  {
    this.noTimeLimit = noTimeLimit;
    if (!noTimeLimit)
    {
      ttl = getDefaultTTLFromConfiguration();
      initCleaningTasks(ttl);
    }
    else
    {
      ttl = -1;
      /**
       * if there are any expired objects available in the cache need to clean them.
       */
      initCleaningTasks(DEFAULT_PURGING_TIME);
    }
  }

  /**
   * Initialize cache with default purging time.
   * Apply the cache object ttl value from the configuration file.
   */
  public AXPCache()
  {
    ttl = getDefaultTTLFromConfiguration();
    initCleaningTasks(ttl);
  }

  /**
   * {@inheritDoc}
   *
   * @param key
   * @param value
   * @param <K>
   * @param <V>
   */
  @Override
  public <K, V> void put(K key, V value)
  {
    if (key == null)
    {
      return;
    }
    if (value == null)
    {
      cache.remove(key);
    }
    else
    {
      long expiryTime = noTimeLimit ? -1 : System.currentTimeMillis() + ttl;
      cache.put(key, new SoftReference<>(new CacheObject(value, expiryTime)));
    }
  }

  /**
   * {@inheritDoc}
   *
   * @param key
   * @param value
   * @param periodInMillis time-to-live value
   * @param <K>
   * @param <V>
   */
  @Override
  public <K, V> void put(K key, V value, long periodInMillis)
  {
    if (key == null)
    {
      return;
    }
    if (value == null)
    {
      cache.remove(key);
    }
    else
    {
      long expiryTime = System.currentTimeMillis() + periodInMillis;
      cache.put(key, new SoftReference<>(new CacheObject(value, expiryTime)));
    }
  }

  /**
   * {@inheritDoc}
   *
   * @param key
   * @param <K>
   */
  @Override
  public <K> void remove(K key)
  {
    cache.remove(key);
  }

  /**
   * {@inheritDoc}
   *
   * @param key
   * @param <K>
   * @return
   */
  @Override
  public <K> Object get(K key)
  {
    return Optional.ofNullable(cache.get(key)).map(SoftReference::get).filter(cacheObject -> !cacheObject.isExpired())
        .map(CacheObject::getValue).orElse(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear()
  {
    cache.clear();
  }

  /**
   * {@inheritDoc}
   *
   * @return
   */
  @Override
  public long size()
  {
    return cache.entrySet().stream().filter(entry -> Optional.ofNullable(entry.getValue()).map(SoftReference::get)
        .map(cacheObject -> !cacheObject.isExpired()).orElse(false)).count();
  }

  /**
   * Note that if the framework.properties file does not available or entry is not there, -1 will be returned.
   * which means cache will not be expired.
   *
   * @return default ttl configuration in framework.properties file.
   */
  private long getDefaultTTLFromConfiguration()
  {
    long ttl;
    try
    {
      ttl = CachePropertyReader.getInstance().getDefaultTTL() * 1000;
    }
    catch (IOException e)
    {
      ttl = -1;
      log.error("Error occurred while reading property file configuration for default cache ttl.", e);
    }
    return ttl;
  }

  private void initCleaningTasks(final long ttl)
  {
    initSoftReferenceCleaningTask();
    initPurgingTask(ttl);
  }

  private void initPurgingTask(long ttl)
  {
    if (ttl <= 0)
    {
      return;
    }

    purgeExecutor = Executors.newSingleThreadScheduledExecutor();
    purgeExecutor.scheduleAtFixedRate(() -> purge(), ttl, ttl, TimeUnit.MILLISECONDS);
  }

  private void initSoftReferenceCleaningTask()
  {
    cleanEmptyReferencesExecutor = Executors.newSingleThreadScheduledExecutor();
    cleanEmptyReferencesExecutor.scheduleAtFixedRate(() -> cleanEmptyReferences(), EMPTY_REFERENCE_CLEANING_TASK_DELAY,
        EMPTY_REFERENCE_CLEANING_TASK_DELAY, TimeUnit.MILLISECONDS);
  }

  /**
   * Purge objects if the time is expired
   */
  private void purge()
  {
    log.debug(() -> Joiner.on(" ").join("Purging cache. class: ", this.getClass().getName()));
    cache.entrySet().removeIf(
        entry -> Optional.ofNullable(entry.getValue()).map(SoftReference::get)
            .map(CacheObject::isExpired).orElse(false));
  }

  /**
   * Clean garbage collected soft reference object from the map
   */
  private void cleanEmptyReferences()
  {
    cache.values().removeIf(e -> e.get() == null);
  }

  @Override
  protected void finalize() throws Throwable
  {
    super.finalize();

    if (purgeExecutor != null)
    {
      purgeExecutor.shutdown();
    }
    if (cleanEmptyReferencesExecutor != null)
    {
      cleanEmptyReferencesExecutor.shutdown();
    }
  }

  /**
   * Class to hold the cache value and expiration time
   */
  private class CacheObject
  {

    private Object value;

    private long expiryTime;

    /**
     * Set the attributes of the cache object
     *
     * @param value
     * @param expiryTime
     */
    public CacheObject(Object value, long expiryTime)
    {
      this.value = value;
      this.expiryTime = expiryTime;
    }

    public Object getValue()
    {
      return value;
    }

    boolean isExpired()
    {
      return expiryTime == -1 ? false : System.currentTimeMillis() > expiryTime;
    }
  }
}
