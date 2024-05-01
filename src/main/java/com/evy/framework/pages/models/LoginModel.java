package com.evy.framework.pages.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NonNull
public class LoginModel {
    private String email;
    private String password;
}
