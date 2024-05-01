package com.evy.framework.config;


import com.evy.framework.drivers.DriverFactory;
import com.evy.framework.utils.LoggerUtils;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

/**
 * Converter class to convert a string representation of a browser type to the corresponding enum value.
 */
public class StringToBrowserTypeConverter implements Converter<DriverFactory.BrowserType> {
    @Override
    public DriverFactory.BrowserType convert(Method method, String browserName) {
        if (browserName == null || browserName.isEmpty()) {
            return DriverFactory.BrowserType.CHROME;
        }
        try {
            return DriverFactory.BrowserType.valueOf(browserName.toUpperCase());
        } catch (IllegalArgumentException e) {
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, "Invalid browser name '{}'. Defaulting to CHROME. " + browserName);
            return DriverFactory.BrowserType.CHROME;
        }

    }
}
