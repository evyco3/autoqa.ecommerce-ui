package com.evy.framework.object.account;

import com.evy.framework.object.BasePage;
import com.evy.framework.object.account.interfaces.AccountNavigation;
import com.evy.framework.object.account.pages.MyAccountPage;
import com.evy.framework.object.account.pages.RegisterPage;
import com.evy.framework.utils.CustomException;
import com.evy.framework.utils.LoggerUtils;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class for account navigation strategies.
 */
public final class AccountNavigationFactory {
    private static final Map<Account, AccountNavigation> navigationStrategies = new HashMap<>();

    static {
        // Initialize navigation strategies for each account type
        navigationStrategies.put(Account.REGISTER, new NavigateToRegisterPage());
        navigationStrategies.put(Account.LOGIN, new NavigateToLoginPage());
        navigationStrategies.put(Account.MY_ACCOUNT, new NavigateToMyAccountPage());
        navigationStrategies.put(Account.LOGOUT, new NavigateToLogoutPage());
    }

    /**
     * Navigates to the specified page based on the provided page ID.
     *
     * @param nextPageClass The ID of the page to navigate to.
     * @return The BasePage corresponding to the navigated page.
     * @throws CustomException if the pageID is invalid or navigation fails.
     */
    public static <T extends BasePage> T navigateTo(Account account, Class<T> nextPageClass) {
        try {
            AccountNavigation navigationStrategy = navigationStrategies.get(account);
            if (navigationStrategy == null) {
                throw new CustomException("Invalid page ID: " + account);
            }
            BasePage nextPage = navigationStrategy.navigate();
            // Check if the returned page is an instance of the specified nextPageClass
            if (!nextPageClass.isInstance(nextPage)) {
                throw new CustomException("Unexpected page type returned. Expected: " + nextPageClass.getSimpleName());
            }
            return nextPageClass.cast(nextPage);
        } catch (Exception e) {
            String errorMsg = "Error occurred while navigating to " + account + " page: " + e.getMessage();
            LoggerUtils.log(AccountNavigationFactory.class, LoggerUtils.LogType.ERROR, errorMsg);
            throw new CustomException(errorMsg);
        }
    }

    /**
     * Implements navigation to the login page.
     */
    private static final class NavigateToLoginPage extends BasePage implements AccountNavigation {
        @FindBy(css = "a[data-target-element='#header-account']")
        private WebElement accountDropdownButton;
        @FindBy(css = "a[title='Log Out']")
        private WebElement logoutButton;

        @Override
        public BasePage navigate() {
            try {
                // Click on the account dropdown button
                click(accountDropdownButton, "account dropdown");
                // Click on the logout button
                click(logoutButton, "logout button");
                // Wait for the page title to contain "Customer Login"
                waitForPageTitle("Customer Login");
                LoggerUtils.log(getClass(), LoggerUtils.LogType.INFO, "Navigate to logout success page");
                return new BasePage();
            } catch (NoSuchElementException | TimeoutException e) {
                String errorMsg = constructErrorMsg("Navigate to login page - Element not found or Timeout", e);
                LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMsg);
                throw new CustomException(errorMsg, e);
            }
        }
    }

    /**
     * Implements navigation to the register page.
     */
    private static final class NavigateToRegisterPage extends BasePage implements AccountNavigation {
        @FindBy(css = "a[data-target-element='#header-account']")
        private WebElement accountDropdownButton;
        @FindBy(css = "a[title='Register']")
        private WebElement registerButton;

        @Override
        public BasePage navigate() {
            try {
                // Click on the account dropdown button
                click(accountDropdownButton, "account dropdown button");
                // Click on the register button
                click(registerButton, "register button");
                // Wait for the page title to contain "Create New Customer Account"
                waitForPageTitle("Create New Customer Account");
                LoggerUtils.log(getClass(), LoggerUtils.LogType.INFO, "Navigate to register page");
                return new RegisterPage();
            } catch (NoSuchElementException | TimeoutException e) {
                String errorMsg = constructErrorMsg("Navigate to register page - Element not found or Timeout", e);
                LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMsg);
                throw new CustomException(errorMsg, e);
            }
        }
    }

    /**
     * Implements navigation to the My Account page.
     */
    private static final class NavigateToMyAccountPage extends BasePage implements AccountNavigation {
        @FindBy(css = "a[data-target-element='#header-account']")
        private WebElement accountDropdownButton;
        @FindBy(css = "#header-account a[title='My Account']")
        private WebElement myAccountButton;
        @FindBy(css = "p.welcome-msg")
        private WebElement helloMessage;

        @Override
        public BasePage navigate() {
            try {
                // Click on the account dropdown button
                click(accountDropdownButton, "account dropdown button");
                // Click on the My Account button
                click(myAccountButton, "my account button");
                // Wait for the hello message to be visible
                waitForElementToBeVisible(helloMessage);
                LoggerUtils.log(getClass(), LoggerUtils.LogType.INFO, "Navigate to my account page");
                return new MyAccountPage();
            } catch (NoSuchElementException | TimeoutException e) {
                String errorMsg = constructErrorMsg("Navigate to my account page - Element not found or Timeout", e);
                LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMsg);
                throw new CustomException(errorMsg, e);
            }
        }
    }

    /**
     * Implements navigation to the logout page.
     */
    private static final class NavigateToLogoutPage extends BasePage implements AccountNavigation {
        @FindBy(css = "a[data-target-element='#header-account']")
        private WebElement accountDropdownButton;
        @FindBy(css = "a[title='Log Out']")
        private WebElement logoutButton;

        @Override
        public BasePage navigate() {
            try {
                // Click on the account dropdown button
                click(accountDropdownButton, "account dropdown");
                // Click on the logout button
                click(logoutButton, "logout button");
                // Wait for the page URL to contain "Tealium Ecommerce Demo"
                waitForPageUrlContains("Tealium Ecommerce Demo");
                LoggerUtils.log(getClass(), LoggerUtils.LogType.INFO, "Navigate to logout success page");
                return new BasePage();
            }catch (NoSuchElementException | TimeoutException e) {
                String errorMsg = constructErrorMsg("Navigate to logout page - Element not found or Timeout", e);
                LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMsg);
                throw new CustomException(errorMsg, e);
            }
        }
    }

    private static String constructErrorMsg(String operation, Exception e) {
        return "Error occurred while " + operation + ": " + e.getMessage();
    }

    public enum Account {
        LOGIN, REGISTER, LOGOUT, MY_ACCOUNT
    }
}