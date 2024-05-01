package com.evy.framework.utils;



import io.qameta.allure.Allure;
import org.openqa.selenium.NoSuchElementException;

import java.util.function.Supplier;

/**
 * A utility class for managing try-catch blocks in test automation frameworks.
 * Provides methods to execute actions and functions with error handling,
 * logging, and reporting capabilities.
 */
public class ActionUtils {

    public static void execAction(Class<?> Tclass, Runnable execution, String successMessage, String errorMessage) {
        try {
            execution.run();
            LoggerUtils.log(Tclass, LoggerUtils.LogType.INFO, successMessage);
            Allure.step(successMessage);
        } catch (NoSuchElementException e) {
            handleNoSuchElementException(Tclass, errorMessage, e);
        } catch (Exception e) {
            handleException(Tclass, errorMessage, e);
        }
    }

    public static <T> T execFunction(Class<?> Tclass, Supplier<T> execution, String successMessage, String errorMessage) {
        try {
            T result = execution.get();
            LoggerUtils.log(Tclass, LoggerUtils.LogType.INFO, successMessage);
            Allure.step(successMessage);
            return result;
        } catch (NoSuchElementException e) {
            return handleNoSuchElementException(Tclass, errorMessage, e);
        } catch (Exception e) {
            return handleException(Tclass, errorMessage, e);
        }
    }

    private static <T> T handleNoSuchElementException(Class<?> Tclass, String errorMessage, NoSuchElementException e) {
        String errorMessageWithException = errorMessage + ": " + e.getMessage();
        LoggerUtils.log(Tclass, LoggerUtils.LogType.ERROR, errorMessageWithException);
        Allure.step(errorMessageWithException);
        throw new CustomException(errorMessageWithException, e);
    }

    private static <T> T handleException(Class<?> Tclass, String errorMessage, Exception e) {
        String errorMessageWithException = errorMessage + ": " + e.getMessage();
        LoggerUtils.log(Tclass, LoggerUtils.LogType.ERROR, errorMessageWithException);
        Allure.step(errorMessageWithException);
        throw new CustomException(errorMessageWithException, e);
    }
}
