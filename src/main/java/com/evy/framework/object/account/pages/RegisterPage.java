package com.evy.framework.object.account.pages;

import com.evy.framework.object.BasePage;
import com.evy.framework.object.HomePage;
import com.evy.framework.object.account.interfaces.AccountAction;
import com.evy.framework.object.account.models.RegisterModel;
import com.evy.framework.utils.CustomException;
import com.evy.framework.utils.LoggerUtils;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class RegisterPage extends BasePage implements AccountAction<RegisterModel> {
    @FindBy(css = "#firstname")
    private WebElement firstName;
    @FindBy(css = "#lastname")
    private WebElement lastName;
    @FindBy(css = "#email_address")
    private WebElement email;
    @FindBy(css = "#password")
    private WebElement password;
    @FindBy(css = "#confirmation")
    private WebElement confirmation;
    @FindBy(css = "button[title='Register']")
    private WebElement registerBtn;

    @Override
    public <T extends BasePage> T setData(RegisterModel model, boolean criteria, Class<T> nextPageClass) {
        try{
            setFirstName(model.getFirstName())
                    .setLastName(model.getLastName())
                    .setEmail(model.getEmail())
                    .setPassword(model.getPassword())
                    .setConfirmation(model.getConfirmation())
                    .clickRegisterBtn();
            if(criteria){
               waitForPageUrlContains("Tealium Ecommerce Demo");
               return nextPageClass.cast(HomePage.class);
            }else{
                return nextPageClass.cast(this);
            }

        }catch (Exception e){
            String errorMsg="Failed to complete Registration";
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR,errorMsg);
            throw new CustomException(errorMsg,e);
        }
    }

    @Override
    public String getResponseMessage(@NonNull String operation) {
        if(operation.equals("valid")){
            return "yes!";
        }
        return null;
    }

    private RegisterPage setFirstName(@NonNull String firstName) {
        sendKeys(this.firstName,firstName,"firstName");
        return this;
    }

    private RegisterPage setLastName(@NonNull String lastName) {
        sendKeys(this.lastName,lastName,"lastName");
        return this;
    }

    private RegisterPage setEmail(@NonNull  String email) {
        sendKeys(this.email,email,"email");
        return this;
    }

    private RegisterPage setPassword(@NonNull String password) {
       sendKeys(this.password,password,"password");
       return this;
    }

    private RegisterPage setConfirmation(@NonNull String confirmation) {
        sendKeys(this.confirmation,confirmation,"confirmation");
        return this;
    }

    private RegisterPage clickRegisterBtn() {
        click(this.registerBtn,"register button");
        return this;
    }
}
