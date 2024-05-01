//package com.evy.tests;
//
//import com.evy.framework.config.ConfigReader;
//import com.evy.framework.pages.header.AccountNavigationAction;
//import com.evy.framework.pages.header.HeaderSection;
//import com.evy.framework.pages.login.LoginData;
//import com.evy.framework.pages.login.LoginElements;
//import com.evy.framework.pages.login.LoginPage;
//import com.evy.framework.pages.account.MyAccountPage;
//import com.evy.framework.pages.models.LoginModel;
//import io.qameta.allure.Description;
//import io.qameta.allure.Epic;
//import io.qameta.allure.Feature;
//import io.qameta.allure.Step;
//import io.qameta.allure.Story;
//import io.qameta.allure.testng.Tag;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//import static com.evy.framework.utils.CustomAssertions.assertThat;
//
//@Epic("Login Page")
//@Feature("Login Functionality")
//public class LoginTests extends BaseTest {
//
//    private final LoginModel loginModel = new LoginModel(ConfigReader.getEmail(), ConfigReader.getPassword());
//
//    @Test
//    @Description("Verify that all elements are displayed on the login page")
//    @Story("As a user, I want to see all elements on the login page")
//    @Tag("login")
//    public void verifyAllElementsDisplay(){
//        LoginElements loginElements= getLoginPage().getLoginElements();
//        assertThat(loginElements.getEmail().isDisplayed());
//        assertThat(loginElements.getPassword().isDisplayed());
//        assertThat(loginElements.getLoginBtn().isDisplayed());
//
//    }
//
//
//    /**
//     * Tests user login scenarios. valid & invalid login
//     */
//    @Test(dataProviderClass = LoginData.class, dataProvider = "login")
//    @Parameters({"email", "password", "expectedResult"})
//    @Story("As a user, I want to be able to log in to the website with my credentials")
//    @Description("Verify that users can log in with valid and invalid credentials")
//    @Tag("login")
//    public void testLoginScenariosWithValidAndInvalidCredentials(String email, String password, String operation, String expectedResult) {
//        LoginModel loginModel = new LoginModel(email, password);
//        String actualResponseMessage = getLoginResponseMsg(loginModel, operation);
//        assertThat(actualResponseMessage).isEqualToCondition(expectedResult, "Login Response Message");
//    }
//
//    @Test
//    @Description("Verify that the login page header is correct")
//    @Story("As a user, I want to see the correct header on the login page")
//    @Tag("login")
//    public void testLoginPageHeader() {
//        assertThat(getLoginPage().getPageTitle()).isEqualTo("Customer Login");
//    }
//
//    @Test
//    @Description("Verify that the login page URL is correct")
//    @Story("As a user, I want to access the login page with the correct URL")
//    @Tag("login")
//    public void testLoginPageUrl() {
//        assertThat(getLoginPage().getCurrentUrl()).isSubstringContained("Login Page URL", "/account/login/");
//    }
//
//    @Test
//    @Description("Verify that successful login navigation redirects to My Account page")
//    @Story("As a user, I want to be redirected to My Account page after successful login")
//    @Tag("login")
//    public void testSuccessfulLoginNavigationToMyAccountPage() {
//        assertThat(getLoginPage().setData(loginModel, true, MyAccountPage.class).getPageTitle()).isEqualTo("My Account");
//    }
//
//    @Test
//    @Description("Verify that the forget password functionality works correctly")
//    @Story("As a user, I want to be able to reset my password")
//    @Tag("login")
//    public void testUserForgetPassword(){
//        assertThat(getLoginPage().initialForgetPassword("evy@user.email")).isTrue("verify proper message display");
//    }
//
//
//    /**
//     * Gets the login response message.
//     *
//     * @return The login response message.
//     */
//    @Step("Get Login Response Message")
//    private String getLoginResponseMsg(LoginModel loginModel, String operation) {
//        return getLoginPage().setData(loginModel, false, LoginPage.class).getResponseMessage(operation);
//    }
//
//    /**
//     * Get LoginPage Object
//     * @return loginPage
//     */
//    @Step("Get Login Page")
//    private LoginPage getLoginPage() {
//        return new HeaderSection()
//                .createHeader(HeaderSection.HeaderSectionType.ACCOUNT)
//                .navigateToPage(LoginPage.class, AccountNavigationAction.LOGIN);
//    }
//}