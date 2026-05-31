package com.demoqa.base;

import com.demoqa.utils.AllureLogger;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LoggingExceptionExtension implements AfterEachCallback {
    @Override
    public void afterEach(ExtensionContext context) {
        context.getExecutionException().ifPresent(ex -> {
            String testName = context.getTestMethod()
                    .map(m -> m.getName())
                    .orElse("unknown");

            AllureLogger logger = new AllureLogger(context.getRequiredTestClass());
            logger.error("Test failed: " + ex.getMessage());
            logger.error("STACK TRACE", ex);
        });
    }
}