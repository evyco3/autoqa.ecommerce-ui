package com.evy.framework.pages.interfaces;

import com.evy.framework.pages.BasePage;
import com.evy.framework.pages.models.LoginModel;
import com.evy.framework.pages.models.RegisterModel;
import lombok.NonNull;


public interface UserActionHandler<T> {



    <U> U setData(T model, boolean criteria, Class<U> nextPagClass);


    String getResponseMessage(@NonNull String operation);
}