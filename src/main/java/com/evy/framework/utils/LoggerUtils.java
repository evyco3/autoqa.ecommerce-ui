package com.evy.framework.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for logging messages using Log4j.
 */
public final class LoggerUtils {

    /**
     * ThreadLocal instance to hold a Logger object for each thread.
     */
    private static final ThreadLocal<Logger> logger = ThreadLocal.withInitial(() -> LogManager.getLogger(LoggerUtils.class));


    private LoggerUtils() {}

    public static void log(Class<?> callingClass,LogType type, String message) {
        String className = callingClass.getSimpleName();
        try {
            switch (type) {
                case INFO:
                    logger.get().info("[{}] {}", className, message);
                    break;
                case DEBUG:
                    logger.get().debug("[{}] {}", className, message);
                    break;
                case ERROR:
                    logger.get().error("[{}] {}", className, message);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported log type: " + type);
            }
        } catch (Exception e) {
            logger.get().error("Error logging message: {}", e.getMessage(), e);
            throw new IllegalArgumentException("Error logging message: {}"+ e.getMessage());
        }
    }

    /**
     * Enumeration representing the type of log message.
     */
    public enum LogType {
        INFO, DEBUG, ERROR
    }


}
