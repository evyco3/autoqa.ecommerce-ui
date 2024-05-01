package com.evy.framework.drivers;

import com.evy.framework.config.ConfigReader;

import com.evy.framework.utils.CustomException;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.time.Duration;

/**
 * A thread-safe utility class for managing the lifecycle of WebDriver instances.
 * This class follows the Singleton design pattern and uses ThreadLocal to ensure
 * that each thread has its own instance of the WebDriver.
 */

//final class to prevent extends from different classes
public final class Driver {

    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = ThreadLocal.withInitial(() -> null);
    private static final Driver INSTANCE = new Driver();

    private Driver() {
        // Private constructor to prevent instantiation
    }


    /**
     * Returns the singleton instance of the Driver class.
     *
     * @return the singleton instance of Driver
     */
    public static  Driver getInstance() {
        return INSTANCE;
    }

    /**
     * Initializes the WebDriver instance for the current thread.
     * If a WebDriver instance already exists for the current thread, it will be reused.
     *
     * @throws CustomException if an error occurs during WebDriver initialization
     */
    public static void init() {
        WebDriver driver = get();
        if (driver != null) {
            return; // If driver already exists, no need to initialize again
        }
        try {
            synchronized (Driver.class) {
                driver = get(); // Check again inside synchronized block
                if (driver == null) {
                    driver = DriverFactory.createDriver(ConfigReader.getBrowser());
                    set(driver);
                    configureDriverTimeouts(driver);
                    navigateToUrl(driver, ConfigReader.getUrl());
                }
            }
        } catch (Exception e) {
            String errorMessage = "Failed to initialize WebDriver: " + e.getMessage();
            LoggerUtils.log(Driver.class, LoggerUtils.LogType.ERROR, errorMessage);
            throw new CustomException(errorMessage, e);
        }
    }

    public static void quit() {
        WebDriver driver = get();
        if (driver == null) {
            return; // If no driver exists, no need to quit
        }
        try {
            synchronized (Driver.class) {
                driver = get(); // Check again inside synchronized block
                if (driver != null) {
                    driver.quit();
                    remove();
                }
            }
        } catch (WebDriverException e) {
            String errorMessage = "Failed to quit WebDriver: " + e.getMessage();
            LoggerUtils.log(Driver.class, LoggerUtils.LogType.ERROR, errorMessage);
            throw new CustomException(errorMessage, e);
        }
    }

    /**
     * Returns the WebDriver instance for the current thread.
     *
     * @return the WebDriver instance for the current thread
     */
    public static WebDriver get() {
        return DRIVER_THREAD_LOCAL.get();
    }

    /**
     * Configures the timeout settings for the WebDriver instance.
     *
     * @param driver the WebDriver instance to configure
     */
    private static void configureDriverTimeouts(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
    }

    /**
     * Navigates the WebDriver instance to the specified URL.
     *
     * @param driver the WebDriver instance to navigate
     * @param url    the URL to navigate to
     */
    private static void navigateToUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    private static void set(WebDriver driver) {
        DRIVER_THREAD_LOCAL.set(driver);
    }

    private static void remove() {
        DRIVER_THREAD_LOCAL.remove();
    }
}