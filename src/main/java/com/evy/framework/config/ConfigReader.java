package com.evy.framework.config;

import com.evy.framework.drivers.DriverFactory;
import com.evy.framework.utils.CustomException;
import com.evy.framework.utils.LoggerUtils;
import org.aeonbits.owner.ConfigFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton class responsible for reading the application configuration.
 * This class ensures that the configuration is loaded only once to ensure thread safety.
 *  addresses the potential deadlock issue. between driver driverFactory and config reader classes
 */
public final class ConfigReader {

    // Singleton instance of the application configuration
    private static volatile ApplicationConfig config;

    // Lock to ensure thread safety during initialization
    private static final Lock lock = new ReentrantLock();

    // Private constructor to prevent instantiation
    private ConfigReader() {}

    /**
     * Gets the application configuration.
     *
     * @return The application configuration.
     * @throws CustomException if there is an error loading the configuration.
     */
    private static ApplicationConfig getConfig() {
        synchronized (ConfigReader.class) {
            if (config == null) {
                try {
                    config = ConfigFactory.create(ApplicationConfig.class);
                } catch (Exception e) {
                    LoggerUtils.log(ConfigReader.class, LoggerUtils.LogType.ERROR, "Error loading application configuration: " + e.getMessage());
                    throw new CustomException("Error loading application configuration", e);
                }
            }
        }
        return config;
    }

    public static DriverFactory.BrowserType getBrowser() {
        return getConfig().browser();
    }

    public static int getExplicitTime() {
        return getConfig().explicitTime();
    }

    public static String getUrl() {
        return getConfig().url();
    }

    public static String getEmail() {
        return getConfig().email();
    }

    public static String getPassword() {
        return getConfig().password();
    }

    public static int getExplicitMilliSec() {
        return getConfig().explicitMilliSec();
    }
}