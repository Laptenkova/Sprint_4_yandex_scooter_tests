package ru.practicum.pageobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.practicum.factory.DriverFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static ru.practicum.constants.FaqAnswers.*;

/**
 * Тесты для страницы MainPage.
 * Проверяет корректность раскрытия вопросов и отображения ответов в разделе "Вопросы о важном"
 */
@RunWith(Parameterized.class)
public class MainPageTest {

    private static final String ACCORDION_HEADING = "accordion__heading-%s";

    private WebDriver driver;
    private MainPage mainPage;

    /**
     * Параметр: идентификатор вопроса
     */
    @Parameterized.Parameter
    public String questionId;

    /**
     * Параметр: ожидаемый текст ответа, соответствующий вопросу
     */
    @Parameterized.Parameter(1)
    public String expectedAnswer;

    /**
     * Инициализирует WebDriver и создаёт объект MainPage перед выполнением каждого теста
     */
    @Before
    public void setUp() {
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.initializeDriver();
        mainPage = new MainPage(driver);
    }

    /**
     * Закрывает WebDriver после каждого теста
     */
    @After
    public void tearDown() {
        driver.quit();
    }

    /**
     * Набор параметров с данными тестов:
     * пары - "идентификатор вопроса" и "ожидаемый ответ" на вопрос
     *
     * @return коллекция данных для параметризованных тестов
     */
    @Parameterized.Parameters(name = "Ожидаемый ответ - {1}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"0", COST_ANSWER},
                {"1", MULTIPLE_SCOOTERS_ANSWER},
                {"2", RENTAL_TIME_ANSWER},
                {"3", TODAY_ORDER_ANSWER},
                {"4", EXTENSION_ANSWER},
                {"5", CHARGING_ANSWER},
                {"6", CANCELLATION_ANSWER},
                {"7", DELIVERY_BOUNDS_ANSWER}
        });
    }

    /**
     * Проверяет, что при раскрытии вопросов из параметров
     * отображается корректное разъяснение
     */
    @Test
    public void shouldDisplayCorrectAnswerTest() {
        mainPage.clickCookiesAccept();
        By questionLocator = By.id(String.format(ACCORDION_HEADING, questionId));
        mainPage.clickQuestion(questionLocator);
        String actualAnswer = mainPage.getAnswerText(questionId);
        assertEquals(expectedAnswer, actualAnswer);
    }
}

