package com.testsquad.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.testsquad.config.TestConfig;

public class HomePage extends BasePage {
    @FindBy(css = "#header")
    private WebElement header;

    @FindBy(css = "#test_box-container")
    private WebElement mainContent;

    @FindBy(css = "#footer")
    private WebElement footer;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        try {
            waitForVisibility(mainContent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToHome() {
        String url = TestConfig.getBaseUrl();
        System.out.println("Navigating to: " + url);
        driver.get(url);
        
        waitForPageLoad();
        isPageLoaded();
    }

    private void waitForPageLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
} 