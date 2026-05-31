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

public class CheckBoxPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String URL = "https://demoqa.com/checkbox";

    // Кнопка раскрытия дерева (стрелка)
    private final String expandButtonCss = "span.rc-tree-switcher.rc-tree-switcher_close";

    // Чекбокс Home
    private final String homeCheckboxCss = "span.rc-tree-checkbox[role='checkbox'][aria-label='Select Home']";

    // Результат после выбора
    @FindBy(css = "#result")
    private WebElement resultOutput;

    public CheckBoxPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ========== МЕТОДЫ СО @Step ==========

    @Step("Открыть страницу с чекбоксами")
    public void open() {
        driver.get(URL);
        wait.until(ExpectedConditions.urlContains("checkbox"));
    }

    @Step("Развернуть все узлы в дереве чекбоксов")
    public void expandAll() {
        try {
            WebElement expandBtn = driver.findElement(By.cssSelector(expandButtonCss));
            if (expandBtn.isDisplayed()) {
                expandBtn.click();
                Thread.sleep(300); // Небольшая задержка для анимации
            }
        } catch (Exception e) {
            // Уже раскрыто или нет кнопки
        }
    }

    @Step("Выбрать чекбокс Home")
    public void selectHome() {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(homeCheckboxCss)));
        checkbox.click();
    }

    @Step("Убедиться, что чекбокс Home выбран")
    public boolean isHomeSelected() {
        WebElement checkbox = driver.findElement(By.cssSelector(homeCheckboxCss));
        String ariaChecked = checkbox.getAttribute("aria-checked");
        return "true".equals(ariaChecked);
    }

    @Step("Получить текст результата после выбора")
    public String getResultText() {
        return resultOutput.getText();
    }

    @Step("Проверить, содержит ли текст результата '{text}'")
    public boolean resultContains(String text) {
        return getResultText().contains(text);
    }
}