package com.demoqa.utils;

import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AllureLogger {
    private final Logger log;
    private static final ThreadLocal<String> currentTestName = new ThreadLocal<>();

    public AllureLogger(Class<?> clazz) {
        this.log = LoggerFactory.getLogger(clazz);
    }

    public static void setCurrentTestName(String testName) {
        currentTestName.set(testName);
    }

    public static void clearCurrentTestName() {
        currentTestName.remove();
    }

    private String getTestName() {
        String name = org.slf4j.MDC.get("testName");
        return name != null ? name : currentTestName.get();
    }

    public void step(String message) {
        log.info(message);
        Allure.step(message);
    }

    public void step(String message, Object... args) {
        String f = format(message, args);
        log.info(f);
        Allure.step(f);
    }

    public void info(String message, Object... args) {
        writeToFile("[INFO] " + format(message, args));
    }

    public void error(String message, Object... args) {
        writeToFile("[ERROR] " + format(message, args));
    }

    public void error(String message, Throwable throwable) {
        writeToFile("[ERROR] " + message);
        writeToFile("[STACK_TRACE] " + getStackTrace(throwable));
    }

    private String format(String message, Object... args) {
        if (args == null || args.length == 0) return message;
        String result = message;
        for (Object arg : args)
            result = result.replaceFirst("\\{\\}", arg == null ? "null" : arg.toString());
        return result;
    }

    private void writeToFile(String message) {
        String testName = getTestName();
        if (testName == null) return;
        try {
            Files.createDirectories(Paths.get("target/test-logs"));
            String line = java.time.LocalDateTime.now() + " - " + message + System.lineSeparator();
            Files.writeString(Paths.get("target/test-logs/" + testName + ".log"), line,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Log write failed: " + e.getMessage());
        }
    }

    private String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}