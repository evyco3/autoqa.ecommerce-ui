package com.evy.framework.pages.header;

import com.evy.framework.drivers.Driver;
import com.evy.framework.pages.BasePage;
import com.evy.framework.pages.facotry.PageObjectFactory;
import com.evy.framework.pages.interfaces.PageNavigationHandler;
import com.evy.framework.utils.CustomException;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * HeaderSection class represents the header section of a web application.
 * It contains methods and elements related to navigation within the header section.
 */
public class HeaderSection extends BasePage {

    /**
     * Creates a header section based on the specified type.
     * @param headerSectionType The type of header section to create.
     * @return A PageNavigationHandler for the created header section.
     */
    public  PageNavigationHandler createHeader(HeaderSectionType headerSectionType) {
        return switch (headerSectionType) {
            case ACCOUNT -> new AccountDropdown();
            case PRODUCT -> new ProductDropdown();
            case SEARCH -> null;
        };
    }

    /**
     * Enumeration representing the types of header sections.
     */
    public enum HeaderSectionType {
        ACCOUNT,
        PRODUCT,
        SEARCH;
    }

    /**
     * Represents the AccountDropdown header section.
     */
    private static final class AccountDropdown extends BasePage implements PageNavigationHandler {
        private final AccountDropdownElements accountDropdownElements;
        private final Map<AccountNavigationAction, Consumer<AccountDropdownElements>> navigationActions = new HashMap<>();

        public AccountDropdown() {
            this.accountDropdownElements = new AccountDropdownElements();
            navigationActions.put(AccountNavigationAction.REGISTER, elements -> {
                accountDropdownElements.clickRegisterBtn();
                waitForPageUrlContains("/account/create/");
            });
            navigationActions.put(AccountNavigationAction.LOGIN, elements -> {
                accountDropdownElements.clickLoginBtn();
                waitForPageUrlContains("/login/");
            });
            navigationActions.put(AccountNavigationAction.LOGOUT, elements -> {
                elements.clickLogoutBtn();
                waitForPageUrlContains("/logout/");
            });
            navigationActions.put(AccountNavigationAction.MY_ACCOUNT, elements -> {
                elements.clickMyAccountBtn();
                waitForPageUrlContains("/account/");
            });
            navigationActions.put(AccountNavigationAction.MY_WISHLIST, elements -> {
                elements.clickMyWishlist();
                waitForPageUrlContains("/wishlist/");
            });
            navigationActions.put(AccountNavigationAction.MY_CART, elements -> {
                elements.clickMyCartBtn();
                waitForPageUrlContains("/cart/");
            });
            navigationActions.put(AccountNavigationAction.CHECKOUT, elements -> {
                elements.clickCheckoutBtn();
                waitForPageUrlContains("/checkout/");
            });
        }


        /**
         * Navigates to the specified page based on the provided arguments.
         *
         * @param nextPageClass The class of the next page to navigate to.
         * @param  action         The arguments for navigation, e.g., "register", "login", "logout", "myAccount".
         * @return The next page after navigation.
         */
        @Override
        public <U> U navigateToPage(Class<U> nextPageClass, Enum<?>action) {
            try {
                Consumer<AccountDropdownElements> navigationAction = navigationActions.get(action);
                if (navigationAction == null) {
                    throw new CustomException("Invalid navigation action: " + action);
                }


                navigationAction.accept(accountDropdownElements);
                LoggerUtils.log(getClass(), LoggerUtils.LogType.INFO ,"Navigating to " + action + " page");
                return PageObjectFactory.createPage(nextPageClass);
            } catch (Exception e) {
                LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, "An unexpected error occurred: " + e.getMessage());
                throw new CustomException("An unexpected error occurred");
            }
        }

    }

    /**
     * Represents the Product header section.
     */
    private static final class ProductDropdown extends BasePage implements PageNavigationHandler {
        private final ProductDropdownElements productDropdownElements;

        public ProductDropdown(){
            this.productDropdownElements=new ProductDropdownElements();
        }



        @Override
        public <U> U navigateToPage(Class<U> nextPageClass, Enum<?> navigationAction) {
            return null;
        }
    }


}