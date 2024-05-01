package com.evy.framework.pages.header;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountDropdownElements extends BasePage {

    // Web elements in the Account header section
    // These elements allow navigation to various account-related pages
    @FindBy(css = "a[data-target-element='#header-account']")
    private WebElement accountDropdownBtn;
    @FindBy(css = "#header-account a[title='My Account']")
    private WebElement myAccountBtn;
    @FindBy(css = "a[title*='My Wishlist']")
    private WebElement myWishListBtn;
    @FindBy(css = "a[title*='My Cart']")
    private WebElement myCartBtn;
    @FindBy(css = "a[class='top-link-checkout']")
    private WebElement checkoutBtn;
    @FindBy(css = "#header-account a[title='Register']")
    private WebElement registerBtn;
    @FindBy(css = "#header-account a[title='Log In']")
    private WebElement loginBtn;
    @FindBy(css = "#header-account a[title='Log Out']")
    private WebElement logoutBtn;

    private AccountDropdownElements clickAccountDropdownBtn() {
        click(this.accountDropdownBtn, "account dropdown button");
        return this;
    }

    void clickLoginBtn() {
        clickAccountDropdownBtn().click(this.loginBtn, "login button");
    }

    void clickRegisterBtn() {
        clickAccountDropdownBtn().click(this.registerBtn, "register button");
    }

    void clickMyAccountBtn() {
        clickAccountDropdownBtn().click(this.myAccountBtn, "my account button");
    }

    void clickLogoutBtn() {
        clickAccountDropdownBtn().click(this.logoutBtn, "logout button");
    }

    void clickMyWishlist() {
        clickAccountDropdownBtn().click(this.myWishListBtn,"myWishlist button");
    }

    void clickMyCartBtn() {
        clickAccountDropdownBtn().click(this.myCartBtn, "cart button");
    }
    void clickCheckoutBtn(){
        clickAccountDropdownBtn().click(this.checkoutBtn,"checkout button");
    }


}
