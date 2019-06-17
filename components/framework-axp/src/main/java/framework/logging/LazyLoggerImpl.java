package framework.logging;

import java.util.function.Supplier;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Implementation for the {@link LazyLogger}
 * This implementation is based on the {@link Logger}
 */
public final class LazyLoggerImpl implements LazyLogger
{

  private final Logger logger;

  protected LazyLoggerImpl(Class clazz)
  {
    logger = LogManager.getLogger(clazz);
  }

  protected LazyLoggerImpl(String name)
  {
    logger = LogManager.getLogger(name);
  }

  /**
   * {@inheritDoc}
   *
   * @param supplier function to get the message object
   */
  @Override
  public void trace(Supplier<?> supplier)
  {
    if (logger.isTraceEnabled())
    {
      logger.trace(supplier.get());
    }
  }

  /**
   * {@inheritDoc}
   *
   * @param supplier function to get the message object
   * @param t
   */
  @Override
  public void trace(Supplier<?> supplier, Throwable t)
  {
    if (logger.isTraceEnabled())
    {
      logger.trace(supplier.get(), t);
    }
  }

  /**
   * {@inheritDoc}
   *
   * @param supplier function to get the message object
   */
  @Override
  public void info(Supplier<?> supplier)
  {
    if (logger.isInfoEnabled())
    {
      logger.info(supplier.get());
    }
  }

  /**
   * {@inheritDoc}
   *
   * @param supplier function to get the message object
   * @param t
   */
  @Override
  public void info(Supplier<?> supplier, Throwable t)
  {
    if (logger.isInfoEnabled())
    {
      logger.info(supplier.get(), t);
    }
  }

  /**
   * {@inheritDoc}
   *
   * @param supplier function to get the message object
   */
  @Override
  public void debug(Supplier<?> supplier)
  {
    if (logger.isDebugEnabled())
    {
      logger.debug(supplier.get());
    }
  }

  /**
   * {@inheritDoc}
   *
   * @param supplier function to get the message object
   * @param t
   */
  @Override
  public void debug(Supplier<?> supplier, Throwable t)
  {
    if (logger.isDebugEnabled())
    {
      logger.debug(supplier.get(), t);
    }
  }

  /**
   * {@inheritDoc}
   *
   * @param message
   */
  @Override
  public void warn(Object message)
  {
    logger.warn(message);
  }

  /**
   * {@inheritDoc}
   *
   * @param message
   * @param t
   */
  @Override
  public void warn(Object message, Throwable t)
  {
    logger.warn(message, t);
  }

  /**
   * {@inheritDoc}
   *
   * @param message
   */
  @Override
  public void error(Object message)
  {
    logger.error(message);
  }

  /**
   * {@inheritDoc}
   *
   * @param message
   * @param t
   */
  @Override
  public void error(Object message, Throwable t)
  {
    logger.error(message);
  }

  /**
   * {@inheritDoc}
   *
   * @param message
   */
  @Override
  public void fatal(Object message)
  {
    logger.fatal(message);
  }

  /**
   * {@inheritDoc}
   *
   * @param message
   * @param t
   */
  @Override
  public void fatal(Object message, Throwable t)
  {
    logger.fatal(message, t);
  }
}
