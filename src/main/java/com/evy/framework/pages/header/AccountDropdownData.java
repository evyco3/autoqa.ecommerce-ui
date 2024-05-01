package com.evy.framework.pages.header;

import org.testng.annotations.DataProvider;

public final class AccountDropdownData {
    private AccountDropdownData() {
    }

    /**
     * className,Header ,title,Url
     * @return object[][]
     */

    @DataProvider(name = "beforeLoginData")
    public static Object[][] getAccountDropdownDataBeforeLogin() {
        return new Object[][]{
                {AccountNavigationAction.LOGIN, "Login or Create an Account", "Customer Login", "/login/"},
                {AccountNavigationAction.REGISTER, "Create an Account", "Create New Customer Account", "/account/create/"}
        };
    }

    @DataProvider(name = "afterLoginData")
    public static Object[][] getAccountDropdownDataAfterLogin() {
        return new Object[][]{
                {AccountNavigationAction.MY_ACCOUNT, "My Dashboard", "My Account","/account/"},
                {AccountNavigationAction.MY_CART, "Shopping Cart", "Shopping Cart", "/cart/"},
                {AccountNavigationAction.MY_WISHLIST, "My Wishlist", "/wishlist/"},
                {AccountNavigationAction.CHECKOUT, "Checkout", "Checkout", "/checkout/"},
                {AccountNavigationAction.LOGOUT, "You are now logged out", "TealiumEcommerce Demo", "/logoutSuccess/"}
        };
    }
}