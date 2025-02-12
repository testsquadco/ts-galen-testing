package com.testsquad.tests;

import com.testsquad.base.BaseTest;
import com.testsquad.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import com.testsquad.annotations.ScreenSizes;
import com.testsquad.utils.EnvironmentConfig;
import com.testsquad.config.TestConfig;

public class HomePageTest extends BaseTest {
    private HomePage homePage;

    @BeforeEach
    void setupTest() {
        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Verify homepage layout across different screen sizes")
    @ScreenSizes({"mobile", "tablet", "desktop"})
    void testHomePageLayout() {
        executeWithScreenshot("homePageLayout", () -> {
            homePage.navigateToHome();

            for (var screenSize : SCREEN_SIZES) {
                resizeWindow(screenSize);
                String deviceType = getDeviceType(screenSize.getWidth());
                
                checkLayout("specs/homepage.gspec", 
                           Arrays.asList(deviceType));
            }
        });
    }

    @Test
    @DisplayName("Verify navigation menu functionality")
    void testNavigationMenu() {
        executeWithScreenshot("navigationMenu", () -> {
            homePage.navigateToHome();
            // Add navigation menu tests here
        });
    }

    private String getDeviceType(int width) {
        if (width <= 375) return "mobile";
        if (width <= 768) return "tablet";
        return "desktop";
    }
} 