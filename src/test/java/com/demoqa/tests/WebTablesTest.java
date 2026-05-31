package com.demoqa.tests;

import com.demoqa.base.TestBase;
import com.demoqa.pages.WebTablesPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Функционал веб-таблиц")
@Feature("Управление записями в таблице")
public class WebTablesTest extends TestBase {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Добавление новой записи в веб-таблицу и проверка её появления")
    @Story("Добавление новой записи")
    public void testAddNewRecord() {
        WebTablesPage webTablesPage = new WebTablesPage(driver);

        logger.info("Тест: Добавление записи. Данные: firstName='Иван', lastName='Петров', email='ivan@test.com', age=30, salary=50000, department='IT'");

        webTablesPage.open();
        webTablesPage.addNewRecord("Иван", "Петров", "ivan@test.com", "30", "50000", "IT");

        boolean isPresent = webTablesPage.isRecordPresent("Иван");
        assertTrue(isPresent, "Запись с именем Иван не найдена");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Удаление записи из веб-таблицы и проверка её исчезновения")
    @Story("Удаление записи")
    public void testDeleteRecord() {
        WebTablesPage webTablesPage = new WebTablesPage(driver);

        logger.info("Тест: Удаление записи. Создается запись 'ДляУдаления', затем удаляется");

        webTablesPage.open();
        webTablesPage.addNewRecord("ДляУдаления", "Тест", "delete@test.com", "25", "40000", "QA");

        boolean isCreated = webTablesPage.isRecordPresent("ДляУдаления");
        assertTrue(isCreated, "Запись не была создана перед удалением");

        webTablesPage.deleteRecordByFirstName("ДляУдаления");

        boolean isStillPresent = webTablesPage.isRecordPresent("ДляУдаления");
        assertFalse(isStillPresent, "Запись не была удалена");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Редактирование существующей записи в веб-таблице и проверка изменений")
    @Story("Редактирование записи")
    public void testEditRecord() {
        WebTablesPage webTablesPage = new WebTablesPage(driver);

        logger.info("Тест: Редактирование записи. Старое имя='Редактируемый', новое имя='Измененный'");

        webTablesPage.open();
        webTablesPage.addNewRecord("Редактируемый", "Тестов", "edit@test.com", "28", "45000", "Dev");

        boolean isCreated = webTablesPage.isRecordPresent("Редактируемый");
        assertTrue(isCreated, "Запись не была создана перед редактированием");

        webTablesPage.editRecordByFirstName("Редактируемый", "Измененный");

        boolean isNewPresent = webTablesPage.isRecordPresent("Измененный");
        assertTrue(isNewPresent, "Отредактированная запись не найдена");

        boolean isOldPresent = webTablesPage.isRecordPresent("Редактируемый");
        assertFalse(isOldPresent, "Старое имя осталось на странице");
    }
}