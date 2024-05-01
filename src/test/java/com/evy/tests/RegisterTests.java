//package com.evy.tests;
//
//import com.evy.framework.pages.header.HeaderSection;
//import com.evy.framework.pages.models.RegisterModel;
//import com.evy.framework.pages.register.RegisterData;
//import com.evy.framework.pages.register.RegisterElements;
//import com.evy.framework.pages.register.RegisterPage;
//import io.qameta.allure.Description;
//import io.qameta.allure.Epic;
//import io.qameta.allure.Feature;
//import io.qameta.allure.Story;
//
//import io.qameta.allure.testng.Tag;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//import static com.evy.framework.utils.CustomAssertions.assertThat;
//
//@Epic("Registration Page")
//@Feature("Registration Functionality")
//public class RegisterTests extends BaseTest {
//
//    @Test
//    @Description("Verify that all elements are displayed on the registration page")
//    @Story("As a user, I want to see all elements on the registration page")
//    @Tag("registration")
//    public void verifyAllElementDisplay() {
//        RegisterElements registerElements = getRegisterPage().getRegisterElements();
//        assertThat(registerElements.getFirstName().isDisplayed()).isTrue("FirstName display status");
//        assertThat(registerElements.getLastName().isDisplayed()).isTrue("lastName display status");
//        assertThat(registerElements.getEmail().isDisplayed()).isTrue("email display status");
//        assertThat(registerElements.getPassword().isDisplayed()).isTrue("password display status");
//        assertThat(registerElements.getConfirmation().isDisplayed()).isTrue("confirm password display status");
//        assertThat(registerElements.getRegisterBtn().isDisplayed()).isTrue("registration button display status");
//    }
//
//    @Test
//    @Description("Verify that the registration page title is correct")
//    @Story("As a user, I want to see the correct title on the registration page")
//    @Tag("registration")
//    public void testRegistrationPageTitle() {
//        RegisterPage registerPage = getRegisterPage();
//        assertThat(registerPage.getPageTitle()).isEqualToCondition("Create New Customer Account", "Registration Page Title");
//    }
//
//    @Test
//    @Description("Verify that the registration page URL is correct")
//    @Story("As a user, I want to access the registration page with the correct URL")
//    @Tag("registration")
//    public void testRegistrationPageURL() {
//        RegisterPage registerPage = getRegisterPage();
//        assertThat(registerPage.getCurrentUrl()).isSubstringContained("/account/create/", "Registration Page Url");
//    }
//
//    @Test
//    @Description("Verify that the registration page header is correct")
//    @Story("As a user, I want to see the correct header on the registration page")
//    @Tag("registration")
//    public void testRegistrationPageHeader() {
//        RegisterPage registerPage = getRegisterPage();
//        assertThat(registerPage.getPageTitle()).isEqualToCondition("CREATE AN ACCOUNT", "Register Page Header");
//    }
//
//
//    @Test(dataProviderClass = RegisterData.class, dataProvider = "register")
//    @Parameters({"firstName", "lastName", "email", "password", "confirmation", "operation", "exceptedResult"})
//    @Description("Verify that user registration scenarios work as expected, including valid and invalid credentials, password mismatch, username already exists, and password policy violations")
//    @Story("As a user, I want to register with valid and invalid credentials")
//    @Tag("registration")
//    public void testUserRegistrationScenarios(String firstName, String lastName, String email, String password, String confirmation, String operation, String exceptedResult) {
//        RegisterModel registerModel = new RegisterModel(firstName, lastName, email, password, confirmation);
//        var actualMessage = getRegistrationResponseMessage(registerModel, operation);
//        assertThat(actualMessage).isEqualToCondition(exceptedResult, "Registration Response Message");
//    }
//
//    @Test
//    @Description("Verify that all fields are marked as required")
//    @Story("As a user, I want to see the fields that marked as required on the registration page")
//    @Tag("registration")
//    public void testThatAllFieldsMarkAsRequired() {
//        assertThat(getRegisterPage().getRegisterElements().getFieldsRequired()).isTrue("Verify the elements in the registration page marks as required");
//
//    }
//
////    private RegisterPage getRegisterPage() {
////        return new HeaderSection().createHeader(HeaderSection.HeaderSectionType.ACCOUNT)
////                .navigateToPage(RegisterPage.class, "register");
////    }
////
////    private String getRegistrationResponseMessage(RegisterModel registerModel, String operation) {
////        return getRegisterPage().setData(registerModel, false, RegisterPage.class).getResponseMessage(operation);
////    }
//}