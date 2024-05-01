package com.evy.framework.utils;

import com.evy.framework.drivers.Driver;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.UUID;

public class AllureTrendsListener implements ITestListener {

    private long startTime;

    @Override
    public void onTestStart(ITestResult result) {
        startTime = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();
        Allure.getLifecycle().startTestCase(uuid);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long executionTime = System.currentTimeMillis() - startTime;
        Allure.getLifecycle().updateTestCase(result.getInstance().getClass().getName(), testResult -> {
            testResult.setStatus(Status.PASSED);
            testResult.setStart(System.currentTimeMillis() - executionTime);
            testResult.setStop(System.currentTimeMillis());
        });
    }

    @Override
    public void onTestFailure(ITestResult result) {
        long executionTime = System.currentTimeMillis() - startTime;
        Allure.getLifecycle().updateTestCase(result.getInstance().getClass().getName(), testResult -> {
            testResult.setStatus(Status.FAILED);
            testResult.setStart(System.currentTimeMillis() - executionTime);
            testResult.setStop(System.currentTimeMillis());
        });
        captureScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        long executionTime = System.currentTimeMillis() - startTime;
        Allure.getLifecycle().updateTestCase(result.getInstance().getClass().getName(), testResult -> {
            testResult.setStatus(Status.SKIPPED);
            testResult.setStart(System.currentTimeMillis() - executionTime);
            testResult.setStop(System.currentTimeMillis());
        });
    }

    @Override
    public void onFinish(ITestContext context) {
        // Finish the test container
        Allure.getLifecycle().stopTestContainer(context.getSuite().getName());
    }

    private void captureScreenshot() {
        if (Driver.get() instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) Driver.get();
            byte[] screenshotData = screenshotDriver.getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshotData));
        } else {
            // Log an error if WebDriver instance does not support taking screenshots
            System.err.println("WebDriver instance does not support taking screenshots.");
        }
    }
}
