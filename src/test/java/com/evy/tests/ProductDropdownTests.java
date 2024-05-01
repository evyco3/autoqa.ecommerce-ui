//package com.evy.tests;
//
//import com.evy.framework.pages.header.HeaderSection;
//import com.evy.framework.pages.product.ProductDropdownData;
//import com.evy.framework.pages.product.ProductListingPage;
//import com.evy.framework.utils.CustomAssertions;
//import io.qameta.allure.Description;
//import io.qameta.allure.Story;
//import io.qameta.allure.testng.Tag;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//public class ProductDropdownTests extends BaseTest {
//
//    @Test(dataProviderClass = ProductDropdownData.class, dataProvider = "productDropdown")
//    @Description("Verify that navigating to a product category through the dropdown menu redirects to the correct URL")
//    @Story("As a user, I want to navigate to different product categories through the product dropdown menu so that I can easily find and purchase the products I need")
//    @Tag("productDropdown")
//    @Parameters({"mainProduct", "subProduct", "expectedUrl"})
//    public void verifyProductNavigationURL(String mainProduct, String subProduct, String expectedUrl) {
//        // Navigate to the product category page and get the actual URL.
//        var actualURL = getProductNavigationURL(mainProduct, subProduct);
//        // Assert that the actual URL matches the expected URL.
//        CustomAssertions.assertThat(actualURL).isEqualToCondition(expectedUrl, "Product Category Navigation Url");
//    }
//
//
//    private String getProductNavigationURL(String productValue1, String productValue2) {
//        return new HeaderSection().createHeader(HeaderSection.HeaderSectionType.PRODUCT)
//                .navigateToPage(ProductListingPage.class, productValue1, productValue2).getCurrentUrl();
//    }
//}
