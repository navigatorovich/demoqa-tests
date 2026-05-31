package com.demoqa.tests;

import com.demoqa.base.TestBase;
import com.demoqa.pages.TextBoxPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Функционал Text Box")
@Feature("Работа с текстовыми полями")
public class TextBoxTest extends TestBase {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Заполнение всех полей формы Text Box и проверка отправленных данных")
    @Story("Заполнение формы валидными данными")
    public void testFillTextBoxForm() {
        TextBoxPage textBoxPage = new TextBoxPage(driver);

        logger.info("Входные данные: name='Анатолий', email='anatoly@test.com', currentAddress='Адрес 1', permanentAddress='Адрес 2'");

        textBoxPage.open();

        textBoxPage.fillTextTextBoxForm("Анатолий", "anatoly@test.com", "Адрес 1", "Адрес 2");

        textBoxPage.clickSubmit();

        boolean isDisplayed = textBoxPage.isOutputDisplayed();
        assertTrue(isDisplayed, "Output блок не отображается");

        String actualName = textBoxPage.getOutputName();
        assertEquals("Анатолий", actualName, "Имя не совпадает с ожидаемым");

        String actualEmail = textBoxPage.getOutputEmail();
        assertEquals("anatoly@test.com", actualEmail, "Email не совпадает с ожидаемым");

        String actualCurrentAddress = textBoxPage.getOutputCurrentAddress();
        assertEquals("Адрес ", actualCurrentAddress, "Текущий адрес не совпадает с ожидаемым");

        String actualPermanentAddress = textBoxPage.getOutputPermanentAddress();
        assertEquals("Адрес 2", actualPermanentAddress, "Постоянный адрес не совпадает с ожидаемым");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Отправка формы с невалидным email должна показать ошибку валидации")
    @Story("Валидация некорректного email")
    public void testSubmitWithInvalidEmail() {
        TextBoxPage textBoxPage = new TextBoxPage(driver);

        logger.info("Входные данные: name='Петр', email='invalid-email', currentAddress='Адрес 3', permanentAddress='Адрес 4'");

        textBoxPage.open();

        textBoxPage.fillTextTextBoxForm("Петр", "invalid-email", "Адрес 3", "Адрес 4");

        textBoxPage.clickSubmit();

        boolean isInvalid = textBoxPage.isEmailFieldInvalid();
        assertTrue(isInvalid, "Email поле должно показывать ошибку валидации");
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Отправка пустой формы должна проходить без ошибок")
    @Story("Отправка пустой формы")
    public void testSubmitWithEmptyFields() {
        TextBoxPage textBoxPage = new TextBoxPage(driver);

        logger.info("Входные данные: все поля пустые");

        textBoxPage.open();

        textBoxPage.fillTextTextBoxForm("", "", "", "");

        textBoxPage.clickSubmit();

        logger.info("Форма с пустыми полями отправлена успешно");
    }
}