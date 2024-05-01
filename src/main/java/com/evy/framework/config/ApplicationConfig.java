package com.evy.framework.config;

import com.evy.framework.drivers.DriverFactory;
import org.aeonbits.owner.Config;

/**
 * This interface helps us get settings for our application.
 * It provides methods to get things like which browser to use, the URL of our app
 * email and password for login, and how long to wait for elements to appear.
 */
@Config.Sources("file:${user.dir}/src/main/resources/config.properties")
public interface ApplicationConfig extends Config {

    /**
     * Gets the type of browser to use.
     *
     * @return The browser type.
     */
    @ConverterClass(StringToBrowserTypeConverter.class)
    @Key("browser")
    DriverFactory.BrowserType browser();

    /**
     * Gets the URL of our application.
     *
     * @return The URL.
     */
    @Key("url")
    String url();

    /**
     * Gets the email used for login.
     *
     * @return The email address.
     */
    @Key("email")
    String email();

    /**
     * Gets the password used for login.
     *
     * @return The password.
     */
    @Key("password")
    String password();

    /**
     * Gets the maximum time to wait for an element to appear, in seconds.
     *
     * @return The wait time in seconds.
     */
    @Key("explicitTime")
    int explicitTime();

    /**
     * Gets the maximum time to wait for an element to appear, in milliseconds.
     *
     * @return The wait time in milliseconds.
     */
    @Key("explicitMilliSec")
    int explicitMilliSec();
}
