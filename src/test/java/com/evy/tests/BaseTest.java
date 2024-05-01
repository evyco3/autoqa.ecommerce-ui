package com.evy.tests;

import com.evy.framework.drivers.Driver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseTest {

    @BeforeMethod
    public void setup(){
        Driver.init();
    }
    @AfterMethod
    public void tearDown(){
        Driver.quit();
    }

}
