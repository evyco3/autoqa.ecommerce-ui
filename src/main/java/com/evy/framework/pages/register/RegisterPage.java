package com.evy.framework.pages.register;

import com.evy.framework.pages.BasePage;
import com.evy.framework.pages.facotry.PageObjectFactory;
import com.evy.framework.pages.interfaces.UserActionHandler;
import com.evy.framework.pages.models.RegisterModel;
import com.evy.framework.utils.CustomException;
import com.evy.framework.utils.LoggerUtils;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;


public final class RegisterPage extends BasePage implements UserActionHandler<RegisterModel> {

    private final RegisterElements registerElements;

    public RegisterPage(){
        this.registerElements=new RegisterElements();
    }
    public RegisterElements getRegisterElements(){
        return this.registerElements;
    }


    @Override
    public <U>U setData(RegisterModel model, boolean criteria, Class<U> nextPageClass) {
        try {
            registerElements.setFirstName(model.getFirstName())
                    .setLastName(model.getLastName())
                    .setEmail(model.getEmail())
                    .setPassword(model.getPassword())
                    .setConfirmation(model.getConfirmPassword())
                    .clickRegisterBtn();
            if(criteria){
               // waitForElementToBeVisible(registerElements.isElementDisplayed(this.registerElements.getValidRegistrationMsg()));
                LoggerUtils.log(getClass(), LoggerUtils.LogType.INFO,"Moving to Header Section");
                return PageObjectFactory.castToType(this);
            }
            else
              return   PageObjectFactory.createPage(nextPageClass);

        } catch (TimeoutException e) {
            String errorMessage = "Timed out waiting for registration to complete: " + e.getMessage();
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMessage);
            throw new CustomException(errorMessage, e);
        } catch (WebDriverException e) {
            String errorMessage = "Failed to complete registration: " + e.getMessage();
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMessage);
            throw new CustomException(errorMessage, e);
        }

    }



    @Override
    public String getResponseMessage(@NonNull String operation) {
        return switch (operation) {
            case "valid registration" -> registerElements.getValidRegistrationMsg();
            case "invalid used email" -> registerElements.getInvalidUsedEmailError();
            case "invalid unmatched password" ->registerElements.getInvalidUnmatchedPasswordError();
            case "invalid password pattern" ->registerElements.getInvalidPasswordPattern();
            default -> {
                LoggerUtils.log(getClass(), LoggerUtils.LogType.INFO,"Unexpected value: "+operation);
                throw new CustomException("Unexpected value: " + operation);
            }
        };
    }






}
