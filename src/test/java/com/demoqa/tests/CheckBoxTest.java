package com.demoqa.tests;

import com.demoqa.base.TestBase;
import com.demoqa.pages.CheckBoxPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Функционал чекбоксов")
@Feature("Работа с чекбоксами")
public class CheckBoxTest extends TestBase {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Выбор корневого чекбокса и проверка, что он выбран и результат содержит 'home'")
    @Story("Выбор корневого чекбокса")
    public void testSelectHomeCheckbox() {
        CheckBoxPage checkBoxPage = new CheckBoxPage(driver);

        logger.info("Тест: Выбор чекбокса Home. Ожидается: чекбокс выбран, результат содержит 'home'");

        checkBoxPage.open();
        checkBoxPage.selectHome();

        boolean isSelected = checkBoxPage.isHomeSelected();
        assertTrue(isSelected, "Чекбокс Home не выбран");

        boolean resultContains = checkBoxPage.resultContains("home");
        assertTrue(resultContains, "Результат не содержит 'home'");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Выбор чекбокса Home и проверка, что результат содержит 'desktop' и 'documents'")
    @Story("Наследование выбора дочерних элементов")
    public void testResultContainsDesktopAndDocuments() {
        CheckBoxPage checkBoxPage = new CheckBoxPage(driver);

        logger.info("Тест: Выбор Home автоматически выбирает дочерние элементы. Ожидается: результат содержит 'desktop' и 'documents'");

        checkBoxPage.open();
        checkBoxPage.selectHome();

        boolean hasDesktop = checkBoxPage.resultContains("desktop");
        assertTrue(hasDesktop, "Результат не содержит 'desktop'");

        boolean hasDocuments = checkBoxPage.resultContains("documents");
        assertTrue(hasDocuments, "Результат не содержит 'documents'");
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Раскрыть всё дерево, выбрать чекбокс Home и проверить выбор и наличие результата")
    @Story("Раскрытие дерева элементов")
    public void testCheckboxStatesAfterExpand() {
        CheckBoxPage checkBoxPage = new CheckBoxPage(driver);

        logger.info("Тест: Раскрытие дерева и выбор Home. Ожидается: чекбокс выбран, результат не пустой");

        checkBoxPage.open();
        checkBoxPage.expandAll();

        checkBoxPage.selectHome();

        boolean isSelected = checkBoxPage.isHomeSelected();
        assertTrue(isSelected, "После раскрытия чекбокс не выбран");

        String resultText = checkBoxPage.getResultText();
        assertFalse(resultText.isEmpty(), "Результат пустой");
    }
}