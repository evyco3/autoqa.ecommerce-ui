package com.evy.framework.pages.login;

import com.evy.framework.data.ExcelDataProvider;
import org.testng.annotations.DataProvider;

public final class LoginData {

    public LoginData(){}

    @DataProvider(name="login")
    public static Object[][]getLoginData(){
        return ExcelDataProvider.getData("login");
    }
}
