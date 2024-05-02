package com.evy.framework.utils;

import io.qameta.allure.Allure;
import org.assertj.core.api.AbstractAssert;

public class CustomAssertions<T> extends AbstractAssert<CustomAssertions<T>, T> {

    protected CustomAssertions(T actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public static <T> CustomAssertions<T> assertThat(T actual) {
        return new CustomAssertions<>(actual, CustomAssertions.class);
    }

    public void isConditionMet(String conditionDescription, T expectedCondition) {
        try {
            if (!actual.equals(expectedCondition)) {
                failWithMessage("Expected %s to be %s, but it was %s.", conditionDescription, expectedCondition, actual);
            } else {
                Allure.step(String.format("Condition met: %s is %s", conditionDescription, expectedCondition));
                LoggerUtils.log(CustomAssertions.class, LoggerUtils.LogType.INFO, String.format("Condition met: %s is %s", conditionDescription, expectedCondition));
            }
        } catch (AssertionError e) {
            LoggerUtils.log(CustomAssertions.class, LoggerUtils.LogType.ERROR, "Assertion error occurred: " + e.getMessage());
            throw new CustomException("Assertion error occurred", e);
        }
    }

    public CustomAssertions<T> isSubstringContained(String expectedSubstring,String conditionDescription) {
        try {
            String actualString = ((String) actual).toLowerCase();
            String expectedSubstr = expectedSubstring.toLowerCase();

            if (!actualString.contains(expectedSubstr)) {
                failWithMessage("Expected %s to contain %s, but it did not.", conditionDescription, expectedSubstring);
            } else {
                Allure.step(String.format("Condition met: %s contains %s", conditionDescription, expectedSubstring.toLowerCase()));
                LoggerUtils.log(CustomAssertions.class, LoggerUtils.LogType.INFO, String.format("Condition met: %s contains %s", conditionDescription, expectedSubstring.toLowerCase()));
            }
        } catch (AssertionError e) {
            LoggerUtils.log(CustomAssertions.class, LoggerUtils.LogType.ERROR, "Assertion error occurred: " + e.getMessage());
            throw new CustomException("Assertion error occurred", e);
        }
        return this;
    }

    public void isEqualToCondition(T expectedValue, String conditionDescription) {
        try {
            if (!actual.equals(expectedValue)) {
                failWithMessage("Expected %s to be %s, but it was %s.", conditionDescription, expectedValue, actual);
            } else {
                Allure.step(String.format("Condition met: %s is %s", conditionDescription, expectedValue));
                LoggerUtils.log(CustomAssertions.class, LoggerUtils.LogType.INFO, String.format("Condition met: %s is %s", conditionDescription, expectedValue));
            }
        } catch (AssertionError e) {
            LoggerUtils.log(CustomAssertions.class, LoggerUtils.LogType.ERROR, "Assertion error occurred: " + e.getMessage());
            throw new CustomException("Assertion error occurred", e);
        }
    }

    public void isTrue(boolean expectedValue, String conditionDescription) {
        try {
            if (!expectedValue) {
                failWithMessage("Expected %s to be true, but it was false.", conditionDescription);
            } else {
                Allure.step(String.format("Condition met: %s is true", conditionDescription));
                LoggerUtils.log(CustomAssertions.class, LoggerUtils.LogType.INFO, String.format("Condition met: %s is true", conditionDescription));
            }
        } catch (AssertionError e) {
            LoggerUtils.log(CustomAssertions.class, LoggerUtils.LogType.ERROR, "Assertion error occurred: " + e.getMessage());
            throw new CustomException("Assertion error occurred", e);
        }
    }
}
