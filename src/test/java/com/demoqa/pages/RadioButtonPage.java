package com.demoqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RadioButtonPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By yesRadioBtn = By.cssSelector("label[for='yesRadio']");
    private final By impressiveRadioBtn = By.cssSelector("label[for='impressiveRadio']");
    private final By noRadioBtn = By.cssSelector("label[for='noRadio']");
    private final By resultText = By.cssSelector(".text-success");

    public RadioButtonPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Step("Открыть страницу с радио кнопками")
    public void open() {
        driver.get("https://demoqa.com/radio-button");
    }

    @Step("Выбрать радио кнопку Yes")
    public void selectYes() {
        WebElement radioBtn = wait.until(ExpectedConditions.elementToBeClickable(yesRadioBtn));
        radioBtn.click();
    }

    @Step("Выбрать радио кнопку Impressive")
    public void selectImpressive() {
        WebElement radioBtn = wait.until(ExpectedConditions.elementToBeClickable(impressiveRadioBtn));
        radioBtn.click();
    }

    @Step("Попытаться выбрать радио кнопку No")
    public void selectNo() {
        WebElement radioBtn = driver.findElement(noRadioBtn);
        if (radioBtn.isEnabled()) {
            radioBtn.click();
        }
    }

    @Step("Проверить, выбрана ли радио кнопка Yes")
    public boolean isYesSelected() {
        WebElement radioBtn = driver.findElement(By.id("yesRadio"));
        return radioBtn.isSelected();
    }

    @Step("Проверить, выбрана ли радио кнопка Impressive")
    public boolean isImpressiveSelected() {
        WebElement radioBtn = driver.findElement(By.id("impressiveRadio"));
        return radioBtn.isSelected();
    }

    @Step("Проверить, доступна ли радио кнопка No")
    public boolean isNoEnabled() {
        WebElement radioBtn = driver.findElement(By.id("noRadio"));
        return radioBtn.isEnabled();
    }

    @Step("Получить текст результата")
    public String getResultText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(resultText)).getText();
    }

    @Step("Проверить, содержит ли результат текст '{text}'")
    public boolean resultContains(String text) {
        return getResultText().contains(text);
    }
}