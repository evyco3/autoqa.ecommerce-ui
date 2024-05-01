package com.evy.framework.pages;

import com.evy.framework.config.ConfigReader;
import com.evy.framework.drivers.Driver;
import com.evy.framework.utils.ActionUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

public class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    private WebElement waitForElement(ExpectedCondition<WebElement> condition) {
        try {
            return new WebDriverWait(Driver.get(), Duration.ofSeconds(ConfigReader.getExplicitTime()))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .until(condition);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while waiting for element", e);
        }
    }

    protected  WebElement waitForElementToBeVisible(WebElement element) {
        return waitForElement(ExpectedConditions.visibilityOf(element));
    }
    protected void waitForPageUrlContains(String text){
        ActionUtils.execAction(getClass(),()->
                new WebDriverWait(Driver.get(),Duration.ofSeconds(ConfigReader.getExplicitMilliSec())).until(ExpectedConditions.urlContains(text)),
                "Page Url Contains "+text,
                "Page Not Contains "+text
                );
    }

    @Step("send keys to {elementName}: {keys}")
    protected void sendKeys(WebElement element, String keys, String elementName) {
        ActionUtils.execAction(getClass(), () -> {
                    WebElement target = waitForElementToBeVisible(element);
                    target.clear();
                    target.sendKeys(keys);
                },
                "Send keys to " + elementName + ":" + keys,
                "Failed to send keys to  " + elementName
        );
    }

    @Step("Clicked on {elementName}")
    protected void click(WebElement element, String elementName) {
        ActionUtils.execAction(getClass(), () ->
                        getJavaScriptExecutor().executeScript("arguments[0].click();", waitForElementToBeVisible(element)),
                "Clicked on  " + elementName,
                "Failed to click on  " + elementName
        );
    }

    @Step("{elementName} Text Value: {text}")
    protected String getText(WebElement element, String elementName) {
        AtomicReference<String> text = new AtomicReference<>();
        ActionUtils.execFunction(getClass(), () -> {
            text.set(waitForElementToBeVisible(element).getText());
            return text.get();
        }, elementName, "Failed To Get Text From " + elementName);
        return text.get();
    }

    @Step("Select option {visibleText} from dropdown {elementName}")
    protected void selectByVisibleText(WebElement dropdown, String visibleText, String elementName) {
        ActionUtils.execAction(getClass(), () -> {
                    new Select(waitForElementToBeVisible(dropdown)).selectByVisibleText(visibleText);
                }, "Selected option " + visibleText + " from dropdown " + elementName,
                "Failed to select option " + visibleText + " from dropdown " + elementName);
    }
    @Step("Move to element {elementName}")
    protected void moveTo(WebElement element, String elementName) {
        ActionUtils.execAction(getClass(), () ->
                        new Actions(Driver.get()).moveToElement(element).perform(),
                "Move to element " + elementName,
                "Failed to move to element " + elementName
        );
    }

    @Step("Get current URL")
    public String getCurrentUrl() {
        return ActionUtils.execFunction(getClass(), Driver.get()::getCurrentUrl,
                "Current URL: " + Driver.get().getCurrentUrl(),
                "Failed to get current URL"
        );
    }
    @Step("Get Page Title")
    public String getPageTitle() {
        return ActionUtils.execFunction(getClass(), () -> Driver.get().getTitle(),
                "Page Title: " + Driver.get().getTitle(),
                "Failed to get Page Title"
        );
    }
    public boolean isElementDisplayed(WebElement element, String elementName) {
        return ActionUtils.execFunction(getClass(), element::isDisplayed,
                elementName + " is displayed",
                elementName + " is not displayed");
    }
    @Step("Get Page Header")
    public String getPageHeader() {
        return ActionUtils.execFunction(getClass(), () -> {
            WebElement headerElement = waitForElement(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".page-title > h1")));
            return headerElement.getText().trim();
        }, "Page Header", "Failed to get Page Header");
    }


    private JavascriptExecutor getJavaScriptExecutor() {
        return (JavascriptExecutor) Driver.get();
    }


}
