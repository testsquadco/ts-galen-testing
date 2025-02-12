package com.testsquad.exceptions;

public class TestExecutionException extends RuntimeException {
    private final String screenshotPath;

    public TestExecutionException(String message, String screenshotPath) {
        super(message);
        this.screenshotPath = screenshotPath;
    }

    public TestExecutionException(String message, Throwable cause, String screenshotPath) {
        super(message, cause);
        this.screenshotPath = screenshotPath;
    }

    public String getScreenshotPath() {
        return screenshotPath;
    }
} 