package com.demoqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ButtonsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private final By doubleClickBtn = By.id("doubleClickBtn");
    private final By rightClickBtn = By.id("rightClickBtn");
    private final By clickBtn = By.xpath("//button[text()='Click Me']");

    private final By doubleClickMessage = By.id("doubleClickMessage");
    private final By rightClickMessage = By.id("rightClickMessage");
    private final By clickMessage = By.id("dynamicClickMessage");

    public ButtonsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    @Step("Открыть страницу кнопок")
    public void open() {
        driver.get("https://demoqa.com/buttons");
        wait.until(ExpectedConditions.visibilityOfElementLocated(doubleClickBtn));
    }

    @Step("Двойным щелчком мыши нажать на кнопку")
    public void performDoubleClick() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(doubleClickBtn));
        actions.doubleClick(button).perform();
    }

    @Step("Нажать правой кнопкой мыши на кнопке")
    public void performRightClick() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(rightClickBtn));
        actions.contextClick(button).perform();
    }

    @Step("Выполнить простой щелчок по динамической кнопке")
    public void performClick() {
        wait.until(ExpectedConditions.elementToBeClickable(clickBtn)).click();
    }

    @Step("Получить сообщение после двойного клика")
    public String getDoubleClickMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(doubleClickMessage)).getText();
    }

    @Step("Получить сообщение после правого клика")
    public String getRightClickMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(rightClickMessage)).getText();
    }

    @Step("Получить сообщение после обычного клика")
    public String getClickMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(clickMessage)).getText();
    }
}