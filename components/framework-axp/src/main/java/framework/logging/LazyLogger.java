package framework.logging;

import java.util.function.Supplier;

/**
 * Facade to support application logging.
 * following log level operation support is available through this class.
 * <p>
 * * TRACE
 * * INFO
 * * DEBUG
 * * WARN
 * * ERROR
 * * FATAL
 * <p>
 * Create an instance using {@link LazyLoggerFactory#getLogger(Class)}
 */
public interface LazyLogger
{

  /**
   * Log a message object with the TRACE level.
   *
   * @param supplier function to get the message object
   */
  void trace(Supplier<?> supplier);

  /**
   * Log a message object with the TRACE level including the stack trace of the Throwable t passed as parameter
   *
   * @param supplier function to get the message object
   * @param t
   */
  void trace(Supplier<?> supplier, Throwable t);

  /**
   * Log a message object with the INFO level.
   *
   * @param supplier function to get the message object
   */
  void info(Supplier<?> supplier);

  /**
   * Log a message object with the INFO level including the stack trace of the Throwable t passed as parameter
   *
   * @param supplier function to get the message object
   * @param t
   */
  void info(Supplier<?> supplier, Throwable t);

  /**
   * Log a message object with the DEBUG level.
   *
   * @param supplier function to get the message object
   */
  void debug(Supplier<?> supplier);

  /**
   * Log a message object with the DEBUG level including the stack trace of the Throwable t passed as parameter
   *
   * @param supplier function to get the message object
   * @param t
   */
  void debug(Supplier<?> supplier, Throwable t);

  /**
   * Log a message object with the WARN level.
   *
   * @param message
   */
  void warn(Object message);

  /**
   * Log a message object with the WARN level including the stack trace of the Throwable t passed as parameter
   *
   * @param message
   * @param t
   */
  void warn(Object message, Throwable t);

  /**
   * Log a message object with the ERROR level.
   *
   * @param message
   */
  void error(Object message);

  /**
   * Log a message object with the ERROR level including the stack trace of the Throwable t passed as parameter
   *
   * @param message
   * @param t
   */
  void error(Object message, Throwable t);

  /**
   * Log a message object with the FATAL level.
   *
   * @param message
   */
  void fatal(Object message);

  /**
   * Log a message object with the FATAL level including the stack trace of the Throwable t passed as parameter
   *
   * @param message
   * @param t
   */
  void fatal(Object message, Throwable t);
}
