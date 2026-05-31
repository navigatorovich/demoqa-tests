package com.demoqa.tests;

import com.demoqa.base.TestBase;
import com.demoqa.pages.ButtonsPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Функционал кнопок")
@Feature("Кнопки и клики")
public class ButtonsTest extends TestBase {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Двойной клик по кнопке и проверка успешного сообщения")
    @Story("Двойной клик")
    public void testDoubleClick() {
        ButtonsPage buttonsPage = new ButtonsPage(driver);

        logger.info("Тест: Двойной клик. Ожидаемое сообщение: 'You have done a double click'");

        buttonsPage.open();
        buttonsPage.performDoubleClick();

        String actualMessage = buttonsPage.getDoubleClickMessage();
        assertEquals("You have done a double click", actualMessage,
                "Сообщение после двойного клика не соответствует ожидаемому");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Правый клик по кнопке и проверка успешного сообщения")
    @Story("Правый клик")
    public void testRightClick() {
        ButtonsPage buttonsPage = new ButtonsPage(driver);

        logger.info("Тест: Правый клик. Ожидаемое сообщение: 'You have done a right click'");

        buttonsPage.open();
        buttonsPage.performRightClick();

        String actualMessage = buttonsPage.getRightClickMessage();
        assertEquals("You have done a right click", actualMessage,
                "Сообщение после правого клика не соответствует ожидаемому");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Простой клик по кнопке Dynamic Click и проверка успешного сообщения")
    @Story("Динамический клик")
    public void testClick() {
        ButtonsPage buttonsPage = new ButtonsPage(driver);

        logger.info("Тест: Простой клик. Ожидаемое сообщение: 'You have done a dynamic click'");

        buttonsPage.open();
        buttonsPage.performClick();

        String actualMessage = buttonsPage.getClickMessage();
        assertEquals("You have done a dynamic click", actualMessage,
                "Сообщение после обычного клика не соответствует ожидаемому");
    }
}