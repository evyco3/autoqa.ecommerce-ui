package com.evy.framework.pages.product;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.CustomException;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class ProductListingPage extends BasePage {

    private final ProductListingElements productListingElements;

    public ProductListingPage() {
        this.productListingElements = new ProductListingElements();
    }

    public boolean getImagesSrcAttribute() {
        try {
            for (WebElement element : this.productListingElements.getImgCollection()) {
                String srcAttribute = element.getAttribute("src");
                if (srcAttribute == null || srcAttribute.isEmpty()) {
                    return false;
                }
            }
            return true; // Return true only if all elements have non-empty src attributes
        } catch (NoSuchElementException e) {
            String errorMsg = "Element not found while getting image attributes: " + e.getMessage();
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMsg);
            throw new CustomException(errorMsg, e);
        } catch (WebDriverException e) {
            String errorMsg = "WebDriver exception occurred while getting image attributes: " + e.getMessage();
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMsg);
            throw new CustomException(errorMsg, e);
        } catch (Exception e) {
            String errorMsg = "An unexpected error occurred while getting image attributes: " + e.getMessage();
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMsg);
            throw new CustomException(errorMsg, e);
        }
    }

    public boolean getImageAltAttribute(){
        try {
            for (WebElement element : this.productListingElements.getImgCollection()) {
                String altAttribute = element.getAttribute("alt");
                if (altAttribute == null || altAttribute.isEmpty()) {
                    return false;
                }
            }
            return true; // Return true only if all elements have non-empty alt attributes
        } catch (NoSuchElementException e) {
            String errorMsg = "Element not found while getting image alt attributes: " + e.getMessage();
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMsg);
            throw new CustomException(errorMsg, e);
        } catch (WebDriverException e) {
            String errorMsg = "WebDriver exception occurred while getting image alt attributes: " + e.getMessage();
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMsg);
            throw new CustomException(errorMsg, e);
        } catch (Exception e) {
            String errorMsg = "An unexpected error occurred while getting image alt attributes: " + e.getMessage();
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMsg);
            throw new CustomException(errorMsg, e);
        }
    }
}
