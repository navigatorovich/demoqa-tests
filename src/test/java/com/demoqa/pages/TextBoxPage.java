package com.demoqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextBoxPage {
    private WebDriver driver;
    private final String URL = "https://demoqa.com/text-box";

    @FindBy(id = "userName")
    private WebElement userNameInput;

    @FindBy(id = "userEmail")
    private WebElement userEmailInput;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressInput;

    @FindBy(id = "permanentAddress")
    private WebElement permanentAddressInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(id = "output")
    private WebElement outputBlock;

    @FindBy(id = "name")
    private WebElement outputName;

    @FindBy(id = "email")
    private WebElement outputEmail;

    @FindBy(xpath = "//p[@id='currentAddress']")
    private WebElement outputCurrentAddress;

    @FindBy(xpath = "//p[@id='permanentAddress']")
    private WebElement outputPermanentAddress;

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Открыть страницу текстового поля")
    public void open() {
        driver.get(URL);
    }

    @Step("Ввести полное имя: {name}")
    public void setFullName(String name) {
        userNameInput.sendKeys(name);
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        userEmailInput.sendKeys(email);
    }

    @Step("Ввести текущий адрес: {address}")
    public void setCurrentAddress(String address) {
        currentAddressInput.sendKeys(address);
    }

    @Step("Ввести постоянный адрес: {address}")
    public void setPermanentAddress(String address) {
        permanentAddressInput.sendKeys(address);
    }

    @Step("Ввод данных")
    public void fillTextTextBoxForm(String name, String email, String currentAddress, String permanentAddress) {
        setFullName(name);
        setEmail(email);
        setCurrentAddress(currentAddress);
        setPermanentAddress(permanentAddress);
    }

    @Step("Нажать кнопку Submit")
    public void clickSubmit() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();
    }

    @Step("Убедиться, что отображается блок вывода")
    public boolean isOutputDisplayed() {
        try {
            return outputBlock.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Получить текст с введенным именем")
    public String getOutputName() {
        return outputName.getText().replace("Name:", "").trim();
    }

    @Step("Получить текст электронного письма с результатом поиска")
    public String getOutputEmail() {
        return outputEmail.getText().replace("Email:", "").trim();
    }

    @Step("Получить текст с текущим адресом")
    public String getOutputCurrentAddress() {
        return outputCurrentAddress.getText().replace("Current Address :", "").trim();
    }

    @Step("Получить текст с постоянным адресом")
    public String getOutputPermanentAddress() {
        return outputPermanentAddress.getText().replace("Permananet Address :", "").trim();
    }

    @Step("Проверить, что поле почты показывает ошибку валидации")
    public boolean isEmailFieldInvalid() {
        String classAttribute = userEmailInput.getAttribute("class");
        String validationMessage = (String) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].validationMessage;", userEmailInput);

        return (classAttribute != null && classAttribute.contains("field-error")) ||
                (validationMessage != null && !validationMessage.isEmpty());
    }
}