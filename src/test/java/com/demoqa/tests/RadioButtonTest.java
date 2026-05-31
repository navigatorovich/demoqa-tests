package com.demoqa.tests;

import com.demoqa.base.TestBase;
import com.demoqa.pages.RadioButtonPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Функционал радио-кнопок")
@Feature("Работа с радио-кнопками")
public class RadioButtonTest extends TestBase {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Выбор радио-кнопки 'Yes' и проверка, что она выбрана и текст результата совпадает")
    @Story("Выбор опции 'Yes'")
    public void testSelectYesRadio() {
        RadioButtonPage radioPage = new RadioButtonPage(driver);

        logger.info("Тест: Выбор 'Yes'. Ожидается: кнопка выбрана, результат = 'Yes'");

        radioPage.open();
        radioPage.selectYes();

        boolean isSelected = radioPage.isYesSelected();
        assertTrue(isSelected, "Radio button Yes не выбран");

        String resultText = radioPage.getResultText();
        assertEquals("Yes", resultText, "Текст результата не соответствует Yes");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Выбор радио-кнопки 'Impressive' и проверка, что она выбрана и текст результата совпадает")
    @Story("Выбор опции 'Impressive'")
    public void testSelectImpressiveRadio() {
        RadioButtonPage radioPage = new RadioButtonPage(driver);

        logger.info("Тест: Выбор 'Impressive'. Ожидается: кнопка выбрана, результат = 'Impressive'");

        radioPage.open();
        radioPage.selectImpressive();

        boolean isSelected = radioPage.isImpressiveSelected();
        assertTrue(isSelected, "Радио-кнопка Impressive не выбран");

        String resultText = radioPage.getResultText();
        assertEquals("Impressive", resultText, "Текст результата не соответствует Impressive");
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Проверка, что радио-кнопка 'No' отключена и не может быть выбрана")
    @Story("Проверка недоступности опции 'No'")
    public void testNoRadioIsDisabled() {
        RadioButtonPage radioPage = new RadioButtonPage(driver);

        logger.info("Тест: Проверка кнопки 'No'. Ожидается: кнопка неактивна");

        radioPage.open();

        boolean isEnabled = radioPage.isNoEnabled();
        assertFalse(isEnabled, "Радио-кнопка No должна быть неактивна");
    }
}