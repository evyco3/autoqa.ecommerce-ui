package com.evy.framework.pages.account;

import com.evy.framework.pages.BasePage;
import com.evy.framework.pages.facotry.PageObjectFactory;
import com.evy.framework.pages.interfaces.UserActionHandler;
import com.evy.framework.pages.models.AddressBookModel;
import com.evy.framework.utils.CustomException;
import com.evy.framework.utils.LoggerUtils;
import lombok.NonNull;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressBookPage extends BasePage implements UserActionHandler<AddressBookModel> {
    @FindBy(css = "button[title='Add New Address']")
    private WebElement addNewAddressBtn;
    @FindBy(css = "#firstname")
    private WebElement firstName;
    @FindBy(css = "#lastname")
    private WebElement lastName;
    @FindBy(css = "#telephone")
    private WebElement telephone;
    @FindBy(css = "#street_1")
    private WebElement address;
    @FindBy(css = "#city")
    private WebElement city;
    @FindBy(css = "#country")
    private WebElement country;
    @FindBy(css = "button[title='Save Address']")
    private WebElement saveAddressBtn;
    @FindBy(css = ".success-msg span")
    private WebElement validAddressBookMsg;


    @Override
    public <U> U setData(AddressBookModel model, boolean criteria, Class<U> nextPagClass) {
        try {
            clickAddNewAddressBtn()
                    .setFirstName(model.getFirstName())
                    .setLastName(model.getLastName())
                    .setPhone(model.getPhone())
                    .setAddress(model.getAddress())
                    .setCity(model.getCity())
                    .setCountry(model.getCountry())
                    .clickSaveAddressBtn();
            waitForElementToBeVisible(this.validAddressBookMsg);
            return PageObjectFactory.createPage(nextPagClass);
        } catch (NoSuchElementException e) {
            String errorMsg = "Element not found: " + e.getMessage();
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMsg);
            throw new CustomException(errorMsg, e);
        } catch (WebDriverException e) {
            String errorMsg = "WebDriver exception occurred: " + e.getMessage();
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMsg);
            throw new CustomException(errorMsg, e);
        } catch (Exception e) {
            String errorMsg = "An unexpected error occurred: " + e.getMessage();
            LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR, errorMsg);
            throw new CustomException(errorMsg, e);
        }
    }


    @Override
    public String getResponseMessage(@NonNull String operation) {
       if(operation.equals("valid")) {
           return getValidAddressBookMsg();
       }
       else {
           var message = "Not Valid  operation " + operation;
           LoggerUtils.log(getClass(), LoggerUtils.LogType.ERROR,message );
           throw new CustomException(message);
       }

    }
    private AddressBookPage clickAddNewAddressBtn(){
        click(this.addNewAddressBtn,"add new address button");
        return this;
    }
    private AddressBookPage setFirstName(@NonNull String firstName){
        sendKeys(this.firstName,firstName,"firstName");
        return this;
    }
    private AddressBookPage setLastName(@NonNull String lastName){
        sendKeys(this.lastName,lastName,"lastName");
        return this;
    }
    private AddressBookPage setPhone(@NonNull String telephone){
        sendKeys(this.telephone,telephone,"telephone");
        return this;
    }
    private AddressBookPage setAddress(@NonNull String address){
        sendKeys(this.address,address,"address");
        return this;
    }
    private AddressBookPage setCity(@NonNull String city){
        sendKeys(this.city,city,"city");
        return this;
    }
    private AddressBookPage setCountry(String country){
        selectByVisibleText(this.country,country,"country");
        return this;
    }
    private AddressBookPage clickSaveAddressBtn(){
        click(this.saveAddressBtn,"save address button");
        return this;
    }
    private String getValidAddressBookMsg(){
        return getText(this.validAddressBookMsg,"valid add new address book message");
    }
}
