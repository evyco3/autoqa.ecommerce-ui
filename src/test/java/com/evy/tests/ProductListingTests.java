//package com.evy.tests;
//
//import com.evy.framework.pages.header.HeaderSection;
//import com.evy.framework.pages.product.ProductListingPage;
//import io.qameta.allure.Description;
//import io.qameta.allure.Story;
//import io.qameta.allure.testng.Tag;
//import org.testng.annotations.Test;
//
//import static com.evy.framework.utils.CustomAssertions.assertThat;
//
//public class ProductListingTests extends BaseTest{
//
//    @Test
//    @Description("Verify that the product listing page title is correct")
//    @Story("As a user, I want to see the correct title on the product listing page")
//    @Tag("productListing")
//    public void testProductListingPageTitle() {
//        assertThat(getProductListingPage().getPageTitle()).isEqualToCondition("Men", "Product Listing Page Title");
//    }
//
//    @Test
//    @Description("Verify that the product listing page URL is correct")
//    @Story("As a user, I want to access the product listing page with the correct URL")
//    @Tag("productListing")
//    public void testProductListPageUrl(){
//        assertThat(getProductListingPage().getCurrentUrl()).isEqualToCondition("https://ecommerce.tealiumdemo.com/men.html", "Product Listing Page URL");
//    }
//
//    @Test
//    @Description("Verify that all images on the product listing page have a valid src attribute")
//    @Story("As a user, I want to see all images on the product listing page")
//    @Tag("productListing")
//    public void testImagesAttribute(){
//        assertThat(getProductListingPage().getImagesSrcAttribute()).isTrue("Not all images have a valid src attribute");
//        assertThat(getProductListingPage().getImageAltAttribute()).isTrue("Not all images have a valid alt attribute");
//    }
//
//
//
//    private ProductListingPage getProductListingPage(){
//        return new HeaderSection().createHeader(HeaderSection.HeaderSectionType.ACCOUNT)
//                .navigateToPage(ProductListingPage.class,"Men","View All Men");
//    }
//}