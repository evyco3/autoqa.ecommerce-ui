package com.evy.framework.pages.login;

import com.evy.framework.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Getter
public class LoginElements extends BasePage {
    @FindBy(css = "#email")
    private WebElement email;
    @FindBy(css = "#pass")
    private WebElement password;
    @FindBy(css = "button[title*='Log']")
    private WebElement loginBtn;
    @FindBy(css = ".welcome-msg>p>strong")
    private WebElement validLoginMsg;
    @FindBy(css = ".error-msg span")
    private WebElement invalidLoginMsg;
    @FindBy(xpath = "//a[text()='Forgot Your Password?']")
    private WebElement forgetPasswordBtn;
    @FindBy(css = "#email_address")
    private WebElement forgetPasswordEmail;
    @FindBy(css = "#form-validate button[type='submit']")
    private WebElement forgetSubmitPasswordBtn;
    @FindBy(css = ".success-msg span")
    private WebElement validForgetPasswordMsg;




    protected LoginElements setEmail(String email){
        sendKeys(this.email, email,"email");
        return this;
    }
    protected LoginElements setPassword(String password){
        sendKeys(this.password,password,"password");
        return this;
    }
    protected LoginElements clickLoginBtn(){
        click(this.loginBtn, "login button");
        return this;
    }
    protected String getValidLoginMsg(){
        return getText(this.validLoginMsg, "valid login message");
    }
    protected String getInvalidLoginMsg(){
        return getText(this.invalidLoginMsg, "invalid login message");
    }
    protected LoginElements clickForgetPasswordBtn(){
        click(this.forgetPasswordBtn,"forget login password");
        return this;
    }
    protected LoginElements setForgetPasswordEmail(String forgetPasswordEmail){
        sendKeys(this.forgetPasswordEmail,forgetPasswordEmail,"forget password email");
        return this;
    }
    protected LoginElements clickForgetSubmitPasswordBtn(){
        click(this.forgetSubmitPasswordBtn,"forget submit password button");
        return this;
    }
    protected boolean getValidForgetPasswordMessage(){
       return isElementDisplayed(this.validForgetPasswordMsg,"forget password message");
    }
}
