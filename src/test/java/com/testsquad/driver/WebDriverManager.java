package com.testsquad.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import com.testsquad.config.TestConfig;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class WebDriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            driverThreadLocal.set(createDriver());
        }
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    private static WebDriver createDriver() {
        String executionEnvironment = TestConfig.getProperty("execution.environment", "local");
        String browser = TestConfig.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(TestConfig.getProperty("headless", "false"));

        switch (executionEnvironment.toLowerCase()) {
            case "local":
                return WebDriverFactory.createDriver(browser, headless);
            case "grid":
                return createGridDriver(browser);
            case "lambdatest":
                return createLambdaTestDriver(browser);
            case "browserstack":
                return createBrowserStackDriver(browser);
            case "saucelabs":
                return createSauceLabsDriver(browser);
            default:
                throw new IllegalArgumentException("Unsupported execution environment: " + executionEnvironment);
        }
    }

    private static WebDriver createGridDriver(String browser) {
        try {
            String gridUrl = TestConfig.getProperty("grid.url");
            return new RemoteWebDriver(new URL(gridUrl), getCapabilities(browser));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Grid URL", e);
        }
    }

    private static WebDriver createLambdaTestDriver(String browser) {
        try {
            String username = TestConfig.getProperty("lambdatest.username");
            String accessKey = TestConfig.getProperty("lambdatest.accesskey");
            String hubUrl = "https://" + username + ":" + accessKey + TestConfig.getProperty("lambdatest.hub");

            DesiredCapabilities capabilities = getCapabilities(browser);
            capabilities.setCapability("platform", TestConfig.getProperty("platform", "Windows 10"));
            capabilities.setCapability("version", TestConfig.getProperty("browser.version", "latest"));
            capabilities.setCapability("build", "TestSquad Automation - " + System.currentTimeMillis());
            capabilities.setCapability("name", "UI Test Execution");
            capabilities.setCapability("visual", true);
            capabilities.setCapability("network", true);
            capabilities.setCapability("console", true);

            return new RemoteWebDriver(new URL(hubUrl), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid LambdaTest configuration", e);
        }
    }

    private static WebDriver createBrowserStackDriver(String browser) {
        try {
            String username = TestConfig.getProperty("browserstack.username");
            String accessKey = TestConfig.getProperty("browserstack.accesskey");
            String hubUrl = "https://" + username + ":" + accessKey + TestConfig.getProperty("browserstack.hub");

            DesiredCapabilities capabilities = getCapabilities(browser);
            capabilities.setCapability("os", TestConfig.getProperty("platform", "Windows"));
            capabilities.setCapability("os_version", "10");
            capabilities.setCapability("browser_version", TestConfig.getProperty("browser.version", "latest"));
            capabilities.setCapability("project", "TestSquad");
            capabilities.setCapability("build", "Build-" + System.currentTimeMillis());
            capabilities.setCapability("name", "UI Test Execution");

            return new RemoteWebDriver(new URL(hubUrl), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid BrowserStack configuration", e);
        }
    }

    private static WebDriver createSauceLabsDriver(String browser) {
        try {
            String username = TestConfig.getProperty("saucelabs.username");
            String accessKey = TestConfig.getProperty("saucelabs.accesskey");
            String hubUrl = "https://" + username + ":" + accessKey + TestConfig.getProperty("saucelabs.hub");

            DesiredCapabilities capabilities = getCapabilities(browser);
            capabilities.setCapability("platformName", TestConfig.getProperty("platform", "Windows 10"));
            capabilities.setCapability("browserVersion", TestConfig.getProperty("browser.version", "latest"));
            
            Map<String, Object> sauceOptions = new HashMap<>();
            sauceOptions.put("build", "TestSquad-" + System.currentTimeMillis());
            sauceOptions.put("name", "UI Test Execution");
            capabilities.setCapability("sauce:options", sauceOptions);

            return new RemoteWebDriver(new URL(hubUrl), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid SauceLabs configuration", e);
        }
    }

    private static DesiredCapabilities getCapabilities(String browser) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                capabilities.setCapability("ms:edgeOptions", edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        
        return capabilities;
    }
} 