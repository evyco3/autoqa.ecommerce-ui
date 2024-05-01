package com.evy.framework.pages.product;

import com.evy.framework.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class ProductListingElements extends BasePage {
    @FindBy(css = "img[id*='product-collection-image']")
    private List<WebElement> imgCollection;
    @FindBy(css = ".category-products>ul")
    private WebElement gridViewElement;
    @FindBy(css = ".category-products>ol")
    private WebElement listViewElement;
}
