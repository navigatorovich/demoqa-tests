package com.demoqa.base;

import com.demoqa.utils.AllureLogger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestWatcherExtension implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getTestMethod()
                .map(m -> m.getName())
                .orElse("unknown");

        AllureLogger.setCurrentTestName(testName);
        AllureLogger logger = new AllureLogger(context.getRequiredTestClass());
        logger.error("Test failed: " + cause.getMessage());
        logger.error("STACK TRACE", cause);
    }
}