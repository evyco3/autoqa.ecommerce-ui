package com.evy.framework.pages.product;

import com.evy.framework.data.ExcelDataProvider;
import org.testng.annotations.DataProvider;

public final class ProductDropdownData {

    private ProductDropdownData(){}

    @DataProvider(name = "productDropdown")
    public static Object[][] getProductDropdownData(){
        return ExcelDataProvider.getData("productDropdown");
    }
}
