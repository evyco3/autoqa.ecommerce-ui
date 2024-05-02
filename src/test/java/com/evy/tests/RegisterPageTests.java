package com.evy.tests;

import com.evy.framework.object.account.AccountNavigationFactory;
import com.evy.framework.object.account.models.RegisterModel;
import com.evy.framework.object.account.pages.LoginPage;
import com.evy.framework.object.account.pages.RegisterPage;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import static com.evy.framework.utils.CustomAssertions.assertThat;

public class RegisterPageTests extends BaseTest {

    @Test
    @Description("This test verifies that all fields are displayed on the register page.")
    @Story("Verify all fields are displayed on the register page")
    @Tag("Registration")
    public void verifyAllFieldsDisplay() {
        RegisterPage registerPage = getRegisterPage();
        assertThat(registerPage.getFirstName().isDisplayed()).isTrue(true, "First name field is displayed");
        assertThat(registerPage.getLastName().isDisplayed()).isTrue(true, "Last name field is displayed");
        assertThat(registerPage.getEmail().isDisplayed()).isTrue(true, "Email field is displayed");
        assertThat(registerPage.getPassword().isDisplayed()).isTrue(true, "Password field is displayed");
        assertThat(registerPage.getConfirmation().isDisplayed()).isTrue(true, "Confirmation field is displayed");
        assertThat(registerPage.getRegisterBtn().isDisplayed()).isTrue(true, "Register button is displayed");
    }

    @Test
    @Description("This test verifies the registration process with different sets of user data.")
    @Story("Verify user registration functionality with various data sets")
    @Tag("Registration")
    public void testUserRegistration() {
        RegisterModel registerModel = new RegisterModel("aa", "vv", "ddsd@jhdj.com", "1234566", "1234566");
        assertThat(getRegistrationResponseMessage(registerModel, "valid")).isEqualToCondition("", "");
    }


    private RegisterPage getRegisterPage() {
       return AccountNavigationFactory.navigateTo(AccountNavigationFactory.Account.REGISTER, RegisterPage.class);
    }

    private String getRegistrationResponseMessage(RegisterModel registerModel, String operation) {
        return getRegisterPage().setData(registerModel, false, RegisterPage.class).getResponseMessage(operation);
    }

}
