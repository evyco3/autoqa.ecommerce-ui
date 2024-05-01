package com.evy.framework.pages.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;


@Getter
@NonNull
@AllArgsConstructor
public class RegisterModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
}
