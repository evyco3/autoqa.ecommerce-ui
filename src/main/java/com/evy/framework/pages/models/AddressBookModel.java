package com.evy.framework.pages.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddressBookModel {
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String city;
    private String zip;
    private String country;
}
