package com.demoqa.base;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.MDC;

public class TestLoggingExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        MDC.clear();
        String testName = context.getTestMethod()
                .map(m -> m.getName())
                .orElse("unknown");
        MDC.put("testName", testName);
    }
}