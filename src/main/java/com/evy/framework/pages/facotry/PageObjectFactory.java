package com.evy.framework.pages.facotry;

import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public class PageObjectFactory<T> {

    private final T pageObject;

    private PageObjectFactory(T pageObject) {
        this.pageObject = pageObject;
    }

    @SuppressWarnings("unchecked")
    public static <R> R castToType(Object obj) {
        return (R) obj;
    }

    @SneakyThrows
    public static <T> T createPage(Class<T> pageClass) {
        T pageObject = pageClass.getDeclaredConstructor().newInstance();
        return new PageObjectFactory<>(pageObject).getPageObject();
    }
}
