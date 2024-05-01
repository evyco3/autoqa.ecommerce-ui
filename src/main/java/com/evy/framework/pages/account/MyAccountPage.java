package com.evy.framework.pages.account;

import com.evy.framework.drivers.Driver;
import com.evy.framework.pages.BasePage;
import com.evy.framework.pages.facotry.PageObjectFactory;
import com.evy.framework.pages.interfaces.PageNavigationHandler;
import com.evy.framework.utils.CustomException;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class MyAccountPage extends BasePage implements PageNavigationHandler {

    public <U> U navigateToPage(Class<U> nextPageClass, String... args) {
        if (args.length != 1) {
            throw new CustomException("Expected args length to be equal to 1");
        }
        try {
            String value = String.format("//div[@class='block block-account']//ul//a[text()='%s']", args[0]);
            WebElement element = Driver.get().findElement(By.xpath(value));
            waitForElementToBeVisible(element);
            LoggerUtils.log(getClass(), LoggerUtils.LogType.INFO,"Moving to Page: "+args[0]);
            click(element, args[0]);
            return PageObjectFactory.createPage(nextPageClass);
        } catch (NoSuchElementException e) {
            String errorMsg="Element Not Found"+e;
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR,errorMsg);
            throw new CustomException(errorMsg);
        } catch (WebDriverException e) {
            String errorMsg="WebDriver Exception Occurred";
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR,errorMsg);
            throw new CustomException(errorMsg);
        } catch (Exception e) {
          String errorMsg="An unexpected error occurred"+e;
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR,errorMsg);
            throw new CustomException(errorMsg);
        }
    }

    @Override
    public <U> U navigateToPage(Class<U> nextPageClass, Enum<?> navigationAction) {
        return null;
    }
}
