package com.testsquad.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.testsquad.driver.WebDriverFactory;

public class TestConfig {
    private static final Properties config = new Properties();
    private static final String CONFIG_FILE = "src/test/resources/config.properties";

    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE);
            config.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + CONFIG_FILE, e);
        }
    }

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", config.getProperty("browser", "chrome"));
        boolean headless = Boolean.parseBoolean(
            System.getProperty("headless", config.getProperty("headless", "false"))
        );
        
        return WebDriverFactory.createDriver(browser, headless);
    }

    public static String getBaseUrl() {
        return config.getProperty("base.url", "http://localhost:8080");
    }

    public static String getEnvironment() {
        return System.getProperty("env", config.getProperty("environment", "qa"));
    }

    public static String getProperty(String key, String defaultValue) {
        return System.getProperty(key, config.getProperty(key, defaultValue));
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }
} 