package com.evy.tests;

import com.evy.framework.config.ConfigReader;
import com.evy.framework.pages.BasePage;
import com.evy.framework.pages.header.AccountDropdownData;
import com.evy.framework.pages.header.AccountNavigationAction;
import com.evy.framework.pages.header.HeaderSection;
import com.evy.framework.pages.login.LoginPage;
import com.evy.framework.pages.models.LoginModel;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.evy.framework.utils.CustomAssertions.assertThat;

@Epic("Account Management")
@Feature("Account Dropdown")
public class AccountDropdownTests extends BaseTest {

    @Test(dataProviderClass = AccountDropdownData.class, dataProvider = "beforeLoginData")
    @Description("Verify that clicking on the '{}' link in the account dropdown menu navigates to the correct page before login")
    @Story("As a user, I want to access my account information and settings through the account dropdown menu")
    @Tag("accountDropdownBeforeLogin")
    @Parameters({"action","expectedPageHeader", "expectedPageTitle", "expectedPageUrl"})
    public void verifyAccountDropdownLinksBeforeLogin(AccountNavigationAction action, String expectedPageHeader, String expectedPageTitle, String expectedPageUrl) {
        BasePage accountDropdownPage = getAccountDropdownURLBeforeLogin(action);
        String actualPageUrl = accountDropdownPage.getCurrentUrl();
        String actualPageTitle=accountDropdownPage.getPageTitle();
        String actualPageHeader=accountDropdownPage.getPageHeader();
        assertThat(actualPageUrl).isSubstringContained(expectedPageUrl, "URL of the page after clicking on the '" + action + "' link");
        assertThat(actualPageTitle).isEqualToCondition(expectedPageTitle, "Page title after clicking on the '" + action + "' link");
        assertThat(actualPageHeader).isSubstringContained(expectedPageHeader, "Page header after clicking on the '" + action + "' link");
    }

    @Test(dataProviderClass = AccountDropdownData.class, dataProvider = "afterLoginData")
    @Description("Verify that clicking on the '{}' link in the account dropdown menu navigates to the correct page after login")
    @Story("As a user, I want to access my account information and settings through the account dropdown menu")
    @Tag("accountDropdownAfterLogin")
    @Parameters({"action","expectedPageHeader", "expectedPageTitle", "expectedPageUrl"})
    public void verifyAccountDropdownLinksAfterLogin(AccountNavigationAction action,String expectedPageHeader,String expectedPageTitle,String expectedPageUrl) {
        BasePage accountDropdownPage=getAccountDropdownURLAfterLogin(action);
        String actualPageUrl = accountDropdownPage.getCurrentUrl();
        String actualPageTitle=accountDropdownPage.getPageTitle();
        String actualPageHeader=accountDropdownPage.getPageHeader();
        assertThat(actualPageUrl).isSubstringContained(expectedPageUrl, "URL of the page after clicking on the '" + action + "' link");
        assertThat(actualPageTitle).isEqualToCondition(expectedPageTitle, "Page title after clicking on the '" + action + "' link");
        assertThat(actualPageHeader).isSubstringContained(expectedPageHeader, "Page header after clicking on the '" + action + "' link");
    }

    /**
     * This method navigates to an account page through the account dropdown menu and returns the current URL before login.
     */
    @Step("Navigate to account dropdown URL before login")
    private BasePage getAccountDropdownURLBeforeLogin(AccountNavigationAction action) {
        return new HeaderSection().createHeader(HeaderSection.HeaderSectionType.ACCOUNT)
                .navigateToPage(BasePage.class, action);
    }

    /**
     * This method navigates to an account page through the account dropdown menu and returns the current URL after login.
     */
    @Step("Navigate to account dropdown URL after login")
    private BasePage getAccountDropdownURLAfterLogin(AccountNavigationAction action) {
        return login().createHeader(HeaderSection.HeaderSectionType.ACCOUNT)
                .navigateToPage(BasePage.class, action);
    }

    /**
     * This method performs a login.
     */
    @Step("Perform login")
    private HeaderSection login() {
        LoginModel loginModel = new LoginModel(ConfigReader.getEmail(), ConfigReader.getPassword());
        return new HeaderSection().createHeader(HeaderSection.HeaderSectionType.ACCOUNT).navigateToPage(LoginPage.class, AccountNavigationAction.LOGIN)
                .setData(loginModel, true, HeaderSection.class);
    }
}