package com.evy.framework.pages.login;

import com.evy.framework.drivers.Driver;
import com.evy.framework.pages.BasePage;
import com.evy.framework.pages.facotry.PageObjectFactory;
import com.evy.framework.pages.interfaces.UserActionHandler;
import com.evy.framework.pages.models.LoginModel;
import com.evy.framework.utils.CustomException;
import com.evy.framework.utils.LoggerUtils;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;


public class LoginPage extends BasePage implements UserActionHandler<LoginModel> {

    private  final LoginElements loginElements;

    public LoginPage(){
        this.loginElements=new LoginElements();
    }

    public LoginElements getLoginElements(){
        return this.loginElements;
    }


    @Override
    public<U> U setData(LoginModel model, boolean criteria, Class<U> nextPagClass) {
        try {
            loginElements.setEmail(model.getEmail())
                    .setPassword(model.getPassword())
                    .clickLoginBtn();
            if (criteria) {
                waitForElementToBeVisible(Driver.get().findElement(By.xpath("//h1[text()='My Dashboard']")));
                LoggerUtils.log(getClass(), LoggerUtils.LogType.INFO, "Navigate to Header Section");
                return PageObjectFactory.createPage(nextPagClass);
            } else {
                return PageObjectFactory.castToType(this);
            }
        } catch (NoSuchElementException e) {
            String errorMessage = "Timed out waiting for login to complete: " + e.getMessage();
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMessage);
            throw new CustomException(errorMessage, e);
        } catch (WebDriverException e) {
            String errorMessage = "Failed to complete login: " + e.getMessage();
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMessage);
            throw new CustomException(errorMessage, e);
        }
    }


    @Override
    public String getResponseMessage(@NonNull String operation) {
        return switch (operation){
          case "valid login"->{
              LoggerUtils.log(getClass(), LoggerUtils.LogType.INFO,"valid login message: "+loginElements.getValidLoginMsg());
             yield  loginElements.getValidLoginMsg();
          }
          case "invalid login"->{
              LoggerUtils.log(getClass(), LoggerUtils.LogType.INFO,"invalid login message: "+loginElements.getInvalidLoginMsg());
              yield loginElements.getInvalidLoginMsg();
          }
            default -> {
                String errorMsg="UnExpected value : "+operation;
                LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR,errorMsg);
                throw new CustomException(errorMsg);
            }
        } ;

    }

    public boolean initialForgetPassword(String email) {
        try {
         return    loginElements.clickForgetPasswordBtn()
            .setForgetPasswordEmail(email).
            clickForgetSubmitPasswordBtn().getValidForgetPasswordMessage();
        } catch (NoSuchElementException e) {
            String errorMessage = "Element not found while initiating forget password: " + e.getMessage();
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMessage);
            throw new CustomException(errorMessage, e);
        } catch (TimeoutException e) {
            String errorMessage = "Timed out while initiating forget password: " + e.getMessage();
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMessage);
            throw new CustomException(errorMessage, e);
        }
    }


}
