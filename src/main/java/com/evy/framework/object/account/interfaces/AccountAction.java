package com.evy.framework.object.account.interfaces;

import com.evy.framework.object.BasePage;
import com.evy.framework.object.account.models.RegisterModel;
import lombok.NonNull;

public interface AccountAction<T> {


    <T extends BasePage> Object setData(RegisterModel model, boolean criteria, Class<T> nextPageClass);

    String getResponseMessage(@NonNull String operation);
}