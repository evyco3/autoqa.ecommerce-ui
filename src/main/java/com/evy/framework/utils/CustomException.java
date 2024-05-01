package com.evy.framework.utils;



/**
 * Custom exception class for handling custom exceptions in the framework.
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
