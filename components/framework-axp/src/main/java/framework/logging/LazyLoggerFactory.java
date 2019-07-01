package framework.logging;

/**
 * Factory class to create a {@link LazyLogger} instance
 */
public final class LazyLoggerFactory
{

  /**
   * Get {@link LazyLogger} instance
   *
   * @param clazz
   * @return
   */
  public static LazyLogger getLogger(Class clazz)
  {
    return new LazyLoggerImpl(clazz);
  }

  /**
   * Get {@link LazyLogger} instance
   *
   * @param name
   * @return
   */
  public static LazyLogger getLogger(String name)
  {
    return new LazyLoggerImpl(name);
  }
}
