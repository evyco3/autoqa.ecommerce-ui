package com.evy.framework.object;

import java.util.HashMap;
import java.util.Map;

public class PageObjectCache {
    private static final Map<Class<?>, Object> cache = new HashMap<>();

    public static <T> T getPage(Class<T> pageClass) {
        // Check if the page object exists in the cache
        if (cache.containsKey(pageClass)) {
            return pageClass.cast(cache.get(pageClass));
        } else {
            // If not, create a new instance and cache it
            try {
                T pageObject = pageClass.getDeclaredConstructor().newInstance();
                cache.put(pageClass, pageObject);
                return pageObject;
            } catch (Exception e) {
                throw new RuntimeException("Failed to create page object", e);
            }
        }
    }
}