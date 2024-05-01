package com.evy.framework.pages.register;

import com.evy.framework.pages.BasePage;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class RegisterElements extends BasePage {

    @FindBy(css = "p.required")
    private WebElement fieldsRequired;
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
    @FindBy(css = "button[title*='Reg']")
    private WebElement registerBtn;
    @FindBy(css = ".success-msg span")
    private WebElement validRegistrationMsg;
    @FindBy(css = ".error-msg span")
    private WebElement invalidUsedEmailErrorMsg;
    @FindBy(css = "#advice-validate-cpassword-confirmation")
    private WebElement invalidUnmatchedPasswordErrorMsg;
    @FindBy(css = "#advice-validate-password-password")
    private WebElement invalidPasswordPatternMsg;


    RegisterElements setFirstName(@NonNull String firstName) {
        sendKeys(this.firstName, firstName, "firstName");
        return this;
    }

    RegisterElements setLastName(@NonNull String lastName) {
        sendKeys(this.lastName, lastName, "lastName");
        return this;
    }

    RegisterElements setEmail(@NonNull String email) {
        sendKeys(this.email, email, "email");
        return this;
    }

    RegisterElements setPassword(@NonNull String password) {
        sendKeys(this.password, password, "password");
        return this;
    }

    RegisterElements setConfirmation(@NonNull String confirmation) {
        sendKeys(this.confirmation, confirmation, "confirmation");
        return this;
    }

    RegisterElements clickRegisterBtn() {
        click(this.registerBtn, "register button");
        return this;
    }

   String getValidRegistrationMsg() {
        return getText(this.validRegistrationMsg, "valid registration message");
    }

    String getInvalidUsedEmailError() {
        return getText(this.invalidUsedEmailErrorMsg, "invalid used email error");
    }

     String getInvalidUnmatchedPasswordError() {
        return getText(this.invalidUnmatchedPasswordErrorMsg, "invalid unmatched passwords error");
    }

     String getInvalidPasswordPattern() {
        return getText(this.invalidPasswordPatternMsg, "invalid password pattern");
    }
    String getPageHeade1r(){
        return null;
    }

}
