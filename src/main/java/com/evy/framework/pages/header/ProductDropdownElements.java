package com.evy.framework.pages.header;

import com.evy.framework.drivers.Driver;
import com.evy.framework.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class ProductDropdownElements extends BasePage {


   WebElement[] getCategoryElements(String... args) {
        WebElement[] categoryElements = new WebElement[2];
        String mainVal = String.format("//ol[@class='nav-primary']/li[contains(@class,'parent')]/a[normalize-space()='%s']", args[0]);
        WebElement mainElement = Driver.get().findElement(By.xpath(mainVal));
        categoryElements[0] = mainElement;

        if (args.length > 1 && !args[1].isEmpty()) {
            String subValue = String.format("//ol[@class='nav-primary']/li[contains(@class,'parent')]/a[normalize-space()='%s']/ancestor::li//ul//a[normalize-space()='%s']", args[0], args[1]);
            WebElement subElement = Driver.get().findElement(By.xpath(subValue));
            categoryElements[1] = subElement;
        }

        return categoryElements;
    }

}
