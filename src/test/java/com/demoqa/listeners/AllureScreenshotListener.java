package com.demoqa.listeners;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

public class AllureScreenshotListener implements AfterTestExecutionCallback {

    private static final Logger log = LoggerFactory.getLogger(AllureScreenshotListener.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static void removeDriver() {
        driverThreadLocal.remove();
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        // Проверяем, упал ли тест
        if (context.getExecutionException().isPresent()) {
            WebDriver driver = driverThreadLocal.get();
            if (driver != null) {
                try {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    Allure.addAttachment("Screenshot on failure", "image/png",
                            new ByteArrayInputStream(screenshot), "png");
                    log.info("✅ Скриншот успешно прикреплён к отчёту");
                } catch (Exception e) {
                    log.error("❌ Не удалось сделать скриншот: {}", e.getMessage(), e);
                }
            } else {
                log.error("❌ Driver is null, скриншот не сделан");
            }
        }
        // Логи больше не трогаем — этим занимается TestBase
    }
}