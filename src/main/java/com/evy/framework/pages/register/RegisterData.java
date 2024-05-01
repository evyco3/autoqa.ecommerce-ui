package com.evy.framework.pages.register;

import com.evy.framework.data.ExcelDataProvider;
import org.testng.annotations.DataProvider;

public final class RegisterData {

    private RegisterData(){
    }

    /**
     * Retrieves registration data from the Excel sheet.
     *
     * @return 2D array of registration data.
     */
    @DataProvider(name = "register")
    public static Object[][] getRegistrationData() {
        return ExcelDataProvider.getData("register");
    }

}
