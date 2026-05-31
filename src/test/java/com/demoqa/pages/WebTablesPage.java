package com.demoqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebTablesPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть страницу веб-таблиц")
    public void open() {
        driver.get("https://demoqa.com/webtables");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("addNewRecordButton")));
    }

    @Step("Добавить новую запись: {firstName} {lastName}, email: {email}")
    public void addNewRecord(String firstName, String lastName, String email, String age, String salary, String department) {
        driver.findElement(By.id("addNewRecordButton")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("age")).sendKeys(age);
        driver.findElement(By.id("salary")).sendKeys(salary);
        driver.findElement(By.id("department")).sendKeys(department);

        driver.findElement(By.id("submit")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("submit")));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("tbody"), firstName));
    }

    @Step("Проверить, что запись с именем '{firstName}' присутствует в таблице")
    public boolean isRecordPresent(String firstName) {
        return driver.findElement(By.tagName("tbody")).getText().contains(firstName);
    }

    @Step("Удалить запись с именем '{firstName}'")
    public void deleteRecordByFirstName(String firstName) {
        String deleteXpath = String.format(
                "//tbody/tr[td[text()='%s']]/td[last()]//span[contains(@id, 'delete')]",
                firstName);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deleteXpath))).click();

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(By.tagName("tbody"), firstName)));
    }

    @Step("Редактировать запись: изменить имя с '{oldFirstName}' на '{newFirstName}'")
    public void editRecordByFirstName(String oldFirstName, String newFirstName) {
        String editXpath = String.format(
                "//tbody/tr[td[text()='%s']]/td[last()]//span[contains(@id, 'edit')]",
                oldFirstName);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(editXpath))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
        driver.findElement(By.id("firstName")).clear();
        driver.findElement(By.id("firstName")).sendKeys(newFirstName);

        driver.findElement(By.id("submit")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("submit")));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("tbody"), newFirstName));
    }
}