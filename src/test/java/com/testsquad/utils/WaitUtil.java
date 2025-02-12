package com.testsquad.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.function.Function;

public class WaitUtil {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
    private static final Duration DEFAULT_POLL_INTERVAL = Duration.ofMillis(500);

    public WaitUtil(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT, DEFAULT_POLL_INTERVAL);
    }

    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public <T> T waitFor(Function<WebDriver, T> condition) {
        return wait.until(condition);
    }

    public void waitForPageLoad() {
        wait.until(driver -> {
            String state = String.valueOf(((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return document.readyState"));
            return state.equals("complete");
        });
    }
} 