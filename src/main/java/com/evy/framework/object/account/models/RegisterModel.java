package com.evy.framework.object.account.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class RegisterModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmation;
}
