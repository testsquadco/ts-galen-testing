package com.testsquad.base;

import com.galenframework.testng.GalenTestNgTestBase;
import com.galenframework.api.Galen;
import com.testsquad.config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.junit.jupiter.api.extension.ExtendWith;
import com.testsquad.reporting.GalenReportListener;
import com.testsquad.exceptions.TestExecutionException;
import com.testsquad.utils.ScreenshotUtil;
import com.testsquad.utils.EnvironmentConfig;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriverException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

@ExtendWith(GalenReportListener.class)
public class BaseTest {
    protected WebDriver driver;
    protected static final List<Dimension> SCREEN_SIZES = Arrays.asList(
        new Dimension(375, 667),  // Mobile
        new Dimension(768, 1024), // Tablet
        new Dimension(1920, 1080) // Desktop
    );

    @BeforeEach
    public void setUp() {
        driver = TestConfig.getDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                // Ignore exceptions during cleanup
            }
        }
    }

    @BeforeAll
    static void setupEnvironment() {
        String env = TestConfig.getEnvironment();
        EnvironmentConfig.loadEnvironment(env);
    }

    protected void resizeWindow(Dimension size) {
        try {
            driver.manage().window().setSize(size);
            // Add a small delay to allow the window to resize
            Thread.sleep(500);
        } catch (WebDriverException | InterruptedException e) {
            // If window resize fails, recreate the driver
            tearDown();
            setUp();
            driver.manage().window().setSize(size);
        }
    }

    protected void checkLayout(String specPath, List<String> tags) {
        try {
            Galen.checkLayout(driver, specPath, tags);
        } catch (Exception e) {
            throw new RuntimeException("Failed to check layout: " + e.getMessage(), e);
        }
    }

    protected void retryOnFailure(Runnable test) {
        int maxRetries = Integer.parseInt(TestConfig.getProperty("max.retries", "3"));
        int retryInterval = Integer.parseInt(TestConfig.getProperty("retry.interval.seconds", "2"));
        
        Exception lastException = null;
        for (int i = 0; i < maxRetries; i++) {
            try {
                test.run();
                return;
            } catch (Exception e) {
                lastException = e;
                if (i < maxRetries - 1) {
                    try {
                        Thread.sleep(retryInterval * 1000L);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(ie);
                    }
                }
            }
        }
        throw new RuntimeException("Test failed after " + maxRetries + " retries", lastException);
    }

    protected void executeWithScreenshot(String testName, Runnable test) {
        try {
            test.run();
        } catch (Exception e) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, testName);
            throw new TestExecutionException(
                "Test failed: " + testName, 
                e, 
                screenshotPath
            );
        }
    }
} 