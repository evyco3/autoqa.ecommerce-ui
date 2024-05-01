package com.evy.framework.pages.interfaces;

public interface PageNavigationHandler {
    <U> U navigateToPage(Class<U> nextPageClass, Enum<?> navigationAction);
}