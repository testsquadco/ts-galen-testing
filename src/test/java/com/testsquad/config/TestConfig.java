package com.testsquad.config;

import org.openqa.selenium.WebDriver;
import com.testsquad.driver.WebDriverManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {
    private static final Properties config = new Properties();
    private static final String CONFIG_FILE = "src/test/resources/config.properties";
    
    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            config.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static WebDriver getDriver() {
        return WebDriverManager.getDriver();
    }

    public static void quitDriver() {
        WebDriverManager.quitDriver();
    }

    public static String getProperty(String key) {
        return getProperty(key, null);
    }

    public static String getProperty(String key, String defaultValue) {
        return System.getProperty(key, config.getProperty(key, defaultValue));
    }

    public static String getEnvironment() {
        return getProperty("environment", "qa");
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }
} 