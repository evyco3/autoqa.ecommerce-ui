package com.evy.framework.drivers;


import com.evy.framework.utils.CustomException;
import com.evy.framework.utils.LoggerUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.EnumMap;
import java.util.function.Supplier;

/**
 * This class creates WebDriver instances for different web browsers.
 * It ensures that only one instance of the class is created (Singleton pattern).
 * This class is thread-safe, meaning it can be used safely in multi-threaded environments.
 */
public final class DriverFactory {


    // A map that stores the logic for creating WebDriver instances for each browser type
    private static final EnumMap<BrowserType, Supplier<WebDriver>> DRIVER_MAP = new EnumMap<>(BrowserType.class);

    // The single instance of DriverFactory
    private static final DriverFactory INSTANCE = new DriverFactory();

    // Private constructor to prevent creating multiple instances
    private DriverFactory() {
        initializeDriverMap();
    }

    /**
     * Returns the single instance of the DriverFactory.
     *
     * @return the single instance of DriverFactory
     */
    public static DriverFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Creates and returns a WebDriver instance for the specified browser type.
     *
     * @param browserType The type of browser to create the WebDriver instance for.
     * @return WebDriver instance corresponding to the specified browser type.
     */
    public static WebDriver createDriver(BrowserType browserType)  {
        Supplier<WebDriver> driverSupplier = DRIVER_MAP.get(browserType);
        if (driverSupplier != null) {
            try {
                return driverSupplier.get();
            } catch (Exception e) {
                throw new CustomException("Failed to create WebDriver instance for browser: " + browserType, e);

            }
        } else {
            LoggerUtils.log(DriverFactory.class, LoggerUtils.LogType.ERROR,"Unsupported browser type: " + browserType);
            throw new CustomException("Unsupported browser type: " + browserType);
        }
    }

    /**
     * Initializes the DRIVER_MAP with the logic for creating WebDriver instances for each browser type.
     */
    private void initializeDriverMap() {
        DRIVER_MAP.put(BrowserType.CHROME, () -> {
            ChromeOptions options=new ChromeOptions();
            options.addArguments("--headless");
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(options);
        });
        DRIVER_MAP.put(BrowserType.FIREFOX, () -> {
            FirefoxOptions options=new FirefoxOptions();
            options.addArguments("--headless");;
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(options);
        });
        DRIVER_MAP.put(BrowserType.EDGE, () -> {
            EdgeOptions options=new EdgeOptions();
            options.addArguments("--headless");
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver(options);
        });
        DRIVER_MAP.put(BrowserType.IE, () -> {
            InternetExplorerOptions options=new InternetExplorerOptions();
            WebDriverManager.iedriver().setup();
            return new InternetExplorerDriver(options);
        });
    }

    /**
     * Represents the different browser types supported by the factory.
     */
    public enum BrowserType {
        CHROME, EDGE, FIREFOX, IE
    }




}