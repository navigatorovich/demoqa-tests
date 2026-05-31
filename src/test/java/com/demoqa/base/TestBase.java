package com.demoqa.base;

import com.demoqa.listeners.AllureScreenshotListener;
import com.demoqa.utils.AllureLogger;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@ExtendWith({AllureScreenshotListener.class, TestLoggingExtension.class, TestWatcherExtension.class})
public class TestBase {
    protected WebDriver driver;
    protected AllureLogger logger;
    private String currentTestName;

    @BeforeEach
    public void setUp(TestInfo testInfo) throws MalformedURLException {
        currentTestName = testInfo.getTestMethod().get().getName();
        AllureLogger.setCurrentTestName(currentTestName);
        this.logger = new AllureLogger(this.getClass());

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        String seleniumUrl = System.getProperty("selenium.remote", "http://localhost:4444/wd/hub");
        driver.manage().window().maximize();
        AllureScreenshotListener.setDriver(driver);

        logger.info("Test started: {}", currentTestName);
    }

    @AfterEach
    public void tearDown() {
        logger.info("Test completed: {}", currentTestName);
        attachLogFile();

        if (driver != null) driver.quit();
        AllureScreenshotListener.removeDriver();

        AllureLogger.clearCurrentTestName();
        org.slf4j.MDC.clear();
    }

    private void attachLogFile() {
        if (currentTestName == null) return;
        String path = "target/test-logs/" + currentTestName + ".log";
        try {
            if (Files.exists(Paths.get(path))) {
                String content = Files.readString(Paths.get(path));
                Allure.addAttachment("Diagnostic Log", "text/plain",
                        new ByteArrayInputStream(("\n=== LOG ===\n" + content).getBytes()), "log");
            }
        } catch (IOException e) {
            System.err.println("Attach failed: " + e.getMessage());
        }
    }
}